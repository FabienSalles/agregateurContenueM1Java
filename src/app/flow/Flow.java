package app.flow;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import app.article.Article;
import app.database.Model;
import app.database.Query;

public class Flow implements Model, ArticleRecover
{
	/**
	 * Id of the flow in the database
	 */
	protected Integer rowid;
	
	/**
	 *  Chemin du flux pour le web
	 */
	protected URL url;
	/**
	 *  Chemin du flux en local
	 */
	protected File file;
	
	/**
	 *  liste d'articles correspondant au flux
	 */
	protected Set<Article> articles;
	
	/**
	 * Prepare statement for request in database
	 */
	protected PreparedStatement prepare;
	
	/**
	 * Type du flux pour la BDD
	 */
	protected FlowType type;
	
	/**
	 * Path of the flow
	 */
	protected String path;
	
	/**
	 * @constructor
	 */
	public Flow()
	{
		this.articles = new LinkedHashSet();
	}
	
	public Flow(String path)
	{
		this();
		this.path =path;
	}
	
	public Flow(String path, FlowType type)
	{
		this(path);
		this.type = type;
	}
	
	public Flow(Integer rowid, String path, FlowType type)
	{
		this(path, type);
		this.rowid = rowid;
	}
	
	/**
	 * getArticles
	 * @return set<Article>
	 */
	public Set<Article> getArticles()
	{
		return articles;
	}
		
	/**
	 * setArticles
	 * @param articles
	 */
	public void setArticles(Set<Article> articles)
	{
		this.articles = articles;
	}
	
	
	public FlowType getType()
	{
		return type;
	}

	public void setType(FlowType type)
	{
		this.type = type;
	}

	
	public Integer getRowid() 
	{
		return rowid;
	}

	public void setRowid(Integer rowid)
	{
		this.rowid = rowid;
	}

	public String getPath()
	{
		return path;
	}
	
	public void setUrl(URL url)
	{
		this.url = url;
		this.path = url.getProtocol() + "://" + url.getHost() + url.getPath();
	}
	
	public void setFile(File file)
	{
		this.file = file;
		this.path = file.getAbsolutePath();
	}

	public void setPath(String path)
	{
		this.path = path;
		if (this.getTypeOfPath(path).equals("url"))
		{
			try {
				this.setUrl(new URL(path));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			this.setFile(new File(path));
		}
	}

	/**
	 * Render articles
	 */
	public void displayArticles()
	{
		for(Article article: this.articles)
		{
			System.out.println(article);
		}
	}
	
	public String getTypeOfPath(String path)
	{
		if (path.startsWith("http"))
		{
			return "url";
		}
		return "file";
	}
	
	/**
	 * Get article by index
	 * @param index
	 * @return
	 */
	public Article get(int index)
	{
		int i = 0;
		Article find = null;
		for(Article article: this.articles)
		{
			if (i == index)
			{
				find = article;
				break;
			}
			i++;
		}
		return find;
	}
	
	/**
	 * Remove article in the list
	 * @param index
	 * @return
	 */
	public boolean remove(int index)
	{
		int i = 0;
		boolean delete = false;
		for(Article article: this.articles)
		{
			if (i == index)
			{
				article.delete();
				delete = this.articles.remove(article);
				break;
			}
			i++;
		}
		return delete;
	}
	/**
	 * Save article in the database
	 */
	@Override
	public void save()
	{
		try {
			this.prepare = Query.getInstance().prepareStatement("INSERT INTO flow (path, type) values (?, ?)");
			this.prepare.setString(1, this.path);
			this.prepare.setString(2,  this.type.getType());
			this.prepare.executeUpdate();
			this.rowid = this.prepare.getGeneratedKeys().getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Save all articles in the database instead save flow
	 */
	public void saveArticles()
	{
		for(Article article: this.articles)
		{
			article.save();
		}
	}
	/**
	 * Update article in the database
	 */
	@Override
	public void update()
	{
		if (this.rowid != null)
		{
			try {
				this.prepare = Query.getInstance().prepareStatement("update flow SET path=?, type=? where rowid=?");
				this.prepare.setString(1, this.path);
				this.prepare.setString(2,  this.type.getType());
				this.prepare.setInt(3, this.rowid);
				this.prepare.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Delete article in the database
	 */
	@Override
	public void delete()
	{
		if (this.rowid != null)
		{
			try {
				this.prepare = Query.getInstance().prepareStatement("DELETE FROM flow SET where rowid=?");
				this.prepare.setInt(1, this.rowid);
				this.prepare.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString()
	{
		return "Flow [rowid=" + rowid + ", type=" + type + ", path=" + path
				+ "]";
	}

	@Override
	public void recover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Article> search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
