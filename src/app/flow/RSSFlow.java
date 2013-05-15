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

public class RSSFlow extends Flow implements ArticleRecover
{	
	public RSSFlow(String url)
	{
		super(url);
		this.recover();
	}
	
	
	@Override
	public void recover()
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

}
