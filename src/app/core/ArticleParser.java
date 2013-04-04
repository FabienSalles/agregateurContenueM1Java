package app.core;

public interface ArticleParser
{
	public void Parse(String article);
	public String parseTitle();
	public String parseContent();
	public String parseDate();
	public String parseAuthor();
}
