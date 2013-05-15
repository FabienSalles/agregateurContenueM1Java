package app.flow;

import java.net.URL;
import java.util.Set;

import app.article.Article;
import app.core.exception.UnknownTypeException;

public class FolderFlow extends Flow implements ArticleRecover
{

	public FolderFlow(String url)
	{
		super(url);
		this.recover();
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
	
    public String recoverType(URL url) throws UnknownTypeException {
		
		String str[] = url.getFile().split("\\.");
		
		if (str.length==1)
		{
			throw new UnknownTypeException("type missing");
		}

		return str[str.length-1];
	}
}
