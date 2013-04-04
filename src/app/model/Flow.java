package app.model;

import java.util.Set;

public class Flow implements Model
{
	/**
	 *  Chemin du flux
	 */
	protected String path;
	
	/**
	 *  liste d'articles correspondant au flux
	 */
	protected Set<Article> articles;
	
	/**
	 * Type du flux pour la BDD
	 */
	protected String type;
	/**
	 * @constructor
	 */
	public Flow()
	{
		
	}
	
	/**
	 * getPath
	 * @return String
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * setPath
	 * @param path
	 */
	public void setPath(String path)
	{
		this.path = path;
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
	
	
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}
