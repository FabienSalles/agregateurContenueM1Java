package app.flow;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import app.article.Article;
import app.article.HTMLArticle;
import app.article.XMLArticle;
import app.core.exception.UnknownTypeException;

public class HTMLFlow extends Flow
{
	public HTMLFlow(URL url)
	{
		super(url.getPath(), FlowType.HTML);
		this.setUrl(url);
		this.recover();
	}
	
	public HTMLFlow(File file)
	{
		super(file.getAbsolutePath(), FlowType.HTML);
		this.setFile(file);
		this.recover();
	}
	
	public HTMLFlow(Integer rowid, File file)
	{
		this(file);
		this.rowid = rowid;		
	}
	
	public HTMLFlow(Integer rowid, URL url)
	{
		this(url);
		this.rowid = rowid;		
	}
	
	/**
	 * Recover Html articles
	 */
	@Override
	public void recover()
	{
		org.jsoup.nodes.Document doc = null;
		
		try {
			// if the flow is an url
			if (this.url != null)
			{
				
				doc = Jsoup.parse(this.url, 3000);
			}
			else
			{ // else is a local path
				doc = Jsoup.parse(this.file, "UTF-8");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (doc != null)
		{
			Elements elems = doc.select("article");
			// for multiple articles in flow
			for (int i = 0; i < elems.size(); i++)
	        {
	        	this.articles.add(new HTMLArticle(doc, elems.get(i), this.url != null ? this.url.getHost() : this.file.getName()));
	        }
			if (this.articles.size() == 0)
			{
				this.articles.add(new HTMLArticle(doc, doc.select("body").get(0), this.url != null ? this.url.getHost() : this.file.getName()));
			}
		}
		
		this.toString();
	}
    
	@Override
	public Set<Article> search(String keywords) {
		return super.search(keywords);
	}

}
