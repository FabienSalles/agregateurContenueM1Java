package app.article;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HTMLArticle extends Article implements ArticleSearch{

	protected Element content;
	
	public HTMLArticle(Document doc, Element item, String domain)
	{
		this.parseTitle(doc);
		this.author = this.parseAuthor(doc);
		if (this.author == null)
		{
			this.author = domain;
		}
		this.date = this.parseDate(doc);
		this.content = item;
	}

	public String parseTitle(Document doc)
	{	
		return this.title = doc.select("title").first().text();	
	}

	public String getContent()
	{
		return this.content.text(); //HTMLArticle.parse(this.content.html()));
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
