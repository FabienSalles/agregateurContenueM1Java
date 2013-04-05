package app.core;

import app.model.Article;

public interface ArticleParser
{
	public void parse(String article);
	public String parseTitle();
	public String parseContent();
	public String parseDate();
	public String parseAuthor();
	public Article search(String keyworkds);
}
