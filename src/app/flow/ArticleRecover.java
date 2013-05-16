package app.flow;

import java.util.Set;

import app.article.Article;

/**
 * Interface for recover Article
 * @author fsalles
 *
 */
public interface ArticleRecover
{
	/**
	 * Recover article in source
	 */
	public void recover();
	
	/**
	 * Search article in source
	 * @param keyworkds
	 * @return
	 */
	public Set<Article> search(String keyworkds);
}
