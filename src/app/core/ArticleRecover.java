package app.core;

import java.util.Set;

import app.model.Article;

public interface ArticleRecover {
	public void recover();
	public Set<Article> search(String keyworkds);
}
