package app.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Flow implements Model
{
	
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
		this.articles = new LinkedHashSet();
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

	public void displayArticles()
	{
		for(Article article: this.articles)
		{
			System.out.println(article);
		}
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
