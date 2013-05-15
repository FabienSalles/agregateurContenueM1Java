package app.flow;

import java.sql.SQLException;
import java.util.Set;

import app.article.Article;
import app.article.ArticleTable;

public class SQLiteFlow extends Flow implements ArticleRecover
{
	@Override
	public void recover()
	{
		try {
			this.articles = ArticleTable.getInstance().getArticles();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Set<Article> search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}
}
