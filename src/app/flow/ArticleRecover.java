package app.flow;

import java.util.Set;

import app.article.Article;

public interface ArticleRecover
{
	public void recover();
	public Set<Article> search(String keyworkds);
}
