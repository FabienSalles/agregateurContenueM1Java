package app.view;

import app.core.URLFlow;
import app.core.exception.UnknownTypeException;

public class Main 
{
	public static void main(String args[])
	{
		URLFlow flow = new URLFlow("http://rss.lemonde.fr/c/205/f/3050/index.rss");
		flow.displayArticles();
	}
}
