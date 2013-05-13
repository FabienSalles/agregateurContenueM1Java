package app.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import app.core.exception.UnknownTypeException;
import app.model.Article;
import app.model.Flow;

public class URLFlow extends Flow implements ArticleRecover
{
	/**
	 *  Chemin du flux
	 */
	protected URL url;
	
	public URLFlow(String url)
	{
		super();
		initFlow(url);
	}
	
	public URLFlow(String url, String type)
	{
		super(type);
		initFlow(url);
	}
	
	private void initFlow(String url)
	{
		try 
		{
			this.url = new URL(url);
			this.recoverType();
			this.recover();
	    }
		catch (MalformedURLException e)
		{
	    	throw new RuntimeException(e);
		}
		catch (UnknownTypeException e)
		{
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void recover()
	{
		if (this.type.equals("rss"))
		{
			this.recoverRss();
		}
		if (this.type.equals("html") || this.type.equals("htm") || this.type.equals("php"))
		{
			this.recoverHtml();
		}
	}
	
	public void recoverRss()
	{
		org.w3c.dom.Document doc = null;
		
		try {
        	DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = builder.parse(this.url.openStream());
		} catch (SAXException ex) {
			Logger.getLogger(URLFlow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(URLFlow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(URLFlow.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		if (doc != null)
		{
			NodeList nodes = doc.getElementsByTagName("item");
	        for (int i = 0; i < nodes.getLength(); i++)
	        {
	        	this.articles.add(new XMLArticle(nodes.item(i)));
	        }
		}
	}
	
	/**
	 * Recover Html articles
	 */
	public void recoverHtml()
	{
		org.jsoup.nodes.Document doc = null;
		try {
			doc = Jsoup.connect(this.url.toString()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (doc != null)
		{
			Elements elems = doc.select("article");
			for (int i = 0; i < elems.size(); i++)
	        {
	        	this.articles.add(new HTMLArticle(doc, elems.get(i), this.url.getHost()));
	        }
			if (this.articles == null)
			{
				this.articles.add(new HTMLArticle(doc, doc, this.url.getHost()));
			}
		}
	}

    /**
     * Afficher une Date GML au format francais
     * @param gmtDate
     * @return
     */
    public String GMTDateToFrench(String gmtDate) {
        try {
            SimpleDateFormat dfGMT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            dfGMT.parse(gmtDate);
            SimpleDateFormat dfFrench = new SimpleDateFormat("EEEE, d MMMM yyyy HH:mm:ss", Locale.FRANCE);
            return dfFrench.format(dfGMT.getCalendar().getTime());
        } catch (ParseException ex) {
            Logger.getLogger(URLFlow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
	
	@Override
	public Set<Article> search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String recoverType() throws UnknownTypeException {
		
		if (this.type == null)
		{
			String str[] = url.getFile().split("\\.");
			
			if (str.length==1)
			{
				throw new UnknownTypeException("type missing");
			}
			this.type = str[str.length-1];
		}
		return this.type;
	}
}
