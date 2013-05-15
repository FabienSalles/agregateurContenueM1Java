package app.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import app.database.Query;
import app.flow.Flow;
import app.flow.FlowTable;

public class ArticleTable
{
	
	private Set<Flow> flows;
	private Connection conn;
	private static ArticleTable instance;
	
	private ArticleTable()
	{
		this.conn  = Query.getInstance();
	}
	
	public Set<Article> getArticles() throws SQLException 
	{
		Set<Article> articles = new LinkedHashSet<Article>();
		
		Statement stat = this.conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT * FROM Articles;");
        
		while(rs.next())
		{
    		Article a = new Article();
	
			a.setTitle(rs.getString("title"));
			a.setContent(rs.getString("content"));
    		a.setAuthor(rs.getString("author"));
    		a.setDate(rs.getString("date"));
    
    		articles.add(a);
	    }
        
		return articles;
	}
	
	public Set<Article> searchArticlesByKeywords(String keywords) throws SQLException
	{
		if (keywords.length() < 1)
			return null;
		
		String[] splittedKeywords =  keywords.split(" ");
		
		if (splittedKeywords.length < 1)
			return null;
		
		String query = "SELECT * FROM Articles ";
		
		for (int i = 0; i < splittedKeywords.length; i++) {
			if (i == 0) {
				query += "WHERE title LIKE \"%" + splittedKeywords[i] + 
						"%\" OR content LIKE \"%" + splittedKeywords[i] + "%\" ";
			} else {
				query += "OR title LIKE \"%" +  splittedKeywords[i] + 
						"%\" OR content LIKE \"%" + splittedKeywords[i] + "%\" ";
			}
		}

		Set<Article> articles = new LinkedHashSet<Article>();
		
		Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(query);
        
        while(rs.next())
        {
            Article a = new Article();
        	
        	a.setTitle(rs.getString("title"));
        	a.setContent(rs.getString("content"));
            a.setAuthor(rs.getString("author"));
            a.setDate(rs.getString("date"));
            
            articles.add(a);
        }
        
		return articles;
	}
	
	/**
    * Méthode qui va nous retourner notre instance
    * et la créer si elle n'existe pas...
    * @return ArticleTable articletable
	 * @throws  
    */
    public static ArticleTable getInstance()
    {
        if (instance == null) {
        	instance = new ArticleTable();
        }
        return instance;    
    }
}
