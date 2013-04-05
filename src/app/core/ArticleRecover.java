package app.core;

import java.util.Set;

import app.core.exception.UnknownTypeException;
import app.model.Article;

public interface ArticleRecover {
	public void recover();
	public Set<Article> search(String keyworkds);
	public String recoverType() throws UnknownTypeException;
}
