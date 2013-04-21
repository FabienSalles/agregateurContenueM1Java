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

import org.w3c.dom.Document;
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
	}
	
	public void recoverRss()
	{
		try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(this.url.openStream());
            NodeList nodes = null;
            Element element = null;
            
            nodes = doc.getElementsByTagName("item");
            for (int i = 0; i < nodes.getLength(); i++)
            {
            	this.articles.add(new XMLArticle(nodes.item(i)));
            }
        } catch (SAXException ex) {
            Logger.getLogger(URLFlow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(URLFlow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(URLFlow.class.getName()).log(Level.SEVERE, null, ex);
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
		
		String str[] = url.getFile().split("\\.");
		
		if (str.length==0)
		{
			throw new UnknownTypeException("type missing");
		}
		this.type = str[str.length-1];
		return this.type;
	}
}
