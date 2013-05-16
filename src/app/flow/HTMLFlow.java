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

public class HTMLFlow extends Flow implements ArticleRecover
{	
	/**
	 *  Chemin du flux pour le web
	 */
	protected URL url;
	/**
	 *  Chemin du flux en local
	 */
	protected File file;
	
	public HTMLFlow(URL url)
	{
		super(url.getPath());
		this.setUrl(url);
		this.recover();
	}
	
	public HTMLFlow(File file)
	{
		super(file.getAbsolutePath());
		this.setFile(file);
		this.recover();
	}
	
	public void setUrl(URL url)
	{
		this.url = url;
		this.path = url.getPath();
	}
	
	public void setFile(File file)
	{
		this.file = file;
		this.path = file.getAbsolutePath();
	}
	
	/**
	 * Recover Html articles
	 */
	@Override
	public void recover()
	{
		org.jsoup.nodes.Document doc = null;
		
		try {
			if (this.url != null)
			{
				
				doc = Jsoup.parse(this.url, 3000);
			}
			else
			{
				doc = Jsoup.parse(this.file, "UTF-8");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (doc != null)
		{
			Elements elems = doc.select("article");
			for (int i = 0; i < elems.size(); i++)
	        {
	        	this.articles.add(new HTMLArticle(doc, elems.get(i), this.url != null ? this.url.getHost() : this.file.getName()));
	        }
			if (this.articles.size() == 0)
			{
				this.articles.add(new HTMLArticle(doc, doc.select("body").get(0), this.url != null ? this.url.getHost() : this.file.getName()));
			}
		}
	}
    
	@Override
	public Set<Article> search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}

}
