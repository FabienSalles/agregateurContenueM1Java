package app.core;

import java.util.Set;

import app.model.Article;
import app.model.Flow;

public class SQLiteFlow extends Flow implements ArticleRecover
{
	@Override
	public void recover()
	{
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
