package app.flow;

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
	public HTMLFlow(String url)
	{
		super(url);
		this.recover();
	}
	
	/**
	 * Recover Html articles
	 */
	@Override
	public void recover()
	{
		org.jsoup.nodes.Document doc = null;
		try {
			doc = Jsoup.connect(this.path).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (doc != null)
		{
			Elements elems = doc.select("article");
			for (int i = 0; i < elems.size(); i++)
	        {
				System.out.println(elems);
	        	this.articles.add(new HTMLArticle(doc, elems.get(i), this.url.getHost()));
	        }
			if (this.articles.size() == 0)
			{
				this.articles.add(new HTMLArticle(doc, doc.select("body").get(0), this.url.getHost()));
			}
		}
	}
    
	@Override
	public Set<Article> search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}

}
