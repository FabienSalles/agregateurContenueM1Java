package app.article;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import app.database.Query;

public class ArticleTable {
	
	public static Set<Article> getArticles() throws SQLException 
	{
		Set<Article> articles = new LinkedHashSet<Article>();
		
		Connection conn = Query.getInstance();
		Statement stat = conn.createStatement();
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
	
	public static Set<Article> searchArticlesByKeywords(String keywords) throws SQLException
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
		
		Connection conn = Query.getInstance();
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
}
