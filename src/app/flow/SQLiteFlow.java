package app.flow;

import java.sql.SQLException;
import java.util.Set;

import app.article.Article;
import app.article.ArticleTable;

public class SQLiteFlow extends Flow
{
	public SQLiteFlow()
	{
		this.type = FlowType.DATABASE;
	}
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
	public Set<Article> search(String keywords) {
		return super.search(keywords);
	}
}
