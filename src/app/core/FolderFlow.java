package app.core;

import java.util.Set;

import app.model.Article;
import app.model.Flow;

public class FolderFlow extends Flow implements ArticleRecover
{
	/**
	 *  Chemin du flux
	 */
	protected String path;

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
	
	
	@Override
	public void recover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Article> search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String recoverType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
