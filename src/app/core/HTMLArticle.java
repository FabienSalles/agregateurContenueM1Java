package app.core;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import app.model.Article;

public class HTMLArticle extends Article{

	public HTMLArticle(Document doc, Element item, String domain)
	{
		this.parseTitle(doc);
		this.author = this.parseAuthor(doc);
		if (this.author == null)
		{
			this.author = domain;
		}
		this.date = this.parseDate(doc);
		this.content = item.html();
	}

	public String parseTitle(Document doc)
	{	
		return this.title = doc.select("title").first().text();	
	}

	public String getContent()
	{
		return HTMLArticle.parse(this.content);
	}

	public String parseDate(Document doc) {
		Elements metas = doc.select("meta");
		for(int i = 0; i < metas.size(); i++)
		{
			Element item = metas.get(i);
			if (item.attr("http-equiv").equals("last-modified"))
			{
				this.date = item.attr("content");
			}
		}
		
		return this.date;
	}

	public String parseAuthor(Document doc)
	{
		Elements metas = doc.select("meta");
		for(int i = 0; i < metas.size(); i++)
		{
			Element item = metas.get(i);
			if (item.attr("name").equals("last-modified"))
			{
				this.title = item.attr("content");
			}
		}
	
		return this.title;
	}

	public Article search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String parse(String content)
	{
		return content.replaceAll("<.[^>]*>", "");
	}

}
