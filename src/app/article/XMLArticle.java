package app.article;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLArticle extends Article implements ArticleSearch
{
	public XMLArticle(Node item)
	{
		NodeList listChild = item.getChildNodes();
		
		for(int i=0; i<listChild.getLength(); i++)
		{
			Node child = listChild.item(i);
			
			if(child.getNodeName().equals("title"))
			{
				this.title = child.getTextContent();
			}
			if(child.getNodeName().equals("author"))
			{
				this.author = child.getTextContent();
			}
			if(child.getNodeName().equals("description"))
			{
				this.content = child.getTextContent();
			}
			if(child.getNodeName().equals("pubDate"))
			{
				this.date = child.getTextContent();
			}	
		}
	}

	@Override
	public Article search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}

}
