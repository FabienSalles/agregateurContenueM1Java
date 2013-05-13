package app.view;

import app.core.URLFlow;
import app.core.exception.UnknownTypeException;

public class Main 
{
	public static void main(String args[])
	{
		URLFlow flow = new URLFlow("http://www.eric-pidoux.com/actu/jsoup-parser-simplement-du-html-en-java", "html");
		//URLFlow flow = new URLFlow("http://rss.lemonde.fr/c/205/f/3050/index.rss");
		//flow.displayArticles();
		System.out.println(flow.get(0).getContent());
	}
}
