package app.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import app.model.Article;

public class HTMLArticle extends Article{

	public HTMLArticle(Document doc, Node item, String domain)
	{
		this.parseTitle(doc);
		this.author = this.parseAuthor(doc);
		if (this.author == null)
		{
			this.author = domain;
		}
		this.date = this.parseDate(doc);
		this.content = item.getTextContent();
	}

	public void parse(String article) {
		// TODO Auto-generated method stub
		
	}

	public String parseTitle(Document doc) {
		NodeList nodes = doc.getElementsByTagName("title");
		
		return this.title = nodes.item(0).getTextContent(); 
		
	}

	public String getContent() {
		return this.content.replaceAll("/(<.*>)/", "");
	}

	public String parseDate(Document doc) {
		NodeList nodes = doc.getElementsByTagName("meta");
		for(int i=0; i<nodes.getLength(); i++)
		{
			Element item = (Element) nodes.item(i);
			if (item.getAttribute("http-equiv").equals("last-modified"))
			{
				this.date = item.getAttribute("content");
			}
		}
		
		return this.date;
	}

	public String parseAuthor(Document doc)
	{
		NodeList nodes = doc.getElementsByTagName("meta");
		for(int i=0; i<nodes.getLength(); i++)
		{
			Element item = (Element) nodes.item(i);
			if (item.getAttribute("name").equals("author"))
			{
				this.title = item.getAttribute("content");
			}
		}
		
		return this.title;
	}

	public Article search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}

}
