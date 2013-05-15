package app.view;

import java.util.LinkedHashSet;
import java.util.Set;
import java.sql.SQLException;
import java.util.Scanner;

import app.core.exception.UnknownTypeException;
import app.flow.Flow;
import app.flow.FlowTable;
import app.flow.FlowType;
import app.flow.HTMLFlow;
import app.flow.MarkdownFlow;
import app.flow.RSSFlow;

public class Main 
{
	public static void main(String args[])
	{		
		View v = new View();
		Scanner s = new Scanner(System.in);

		v.welcomeView();
//		
		while (v.getCurrentState() != 5) {
			v.printView();
			int i = s.nextInt();
			v.setCurrentState(i);
		}
//		
//		RSSFlow flow = new RSSFlow("http://rss.lemonde.fr/c/205/f/3050/index.rss");
//		flow.displayArticles();
//		Set<Flow> flows = FlowTable.getInstance().getFlow();
//		
//		for(Flow flow : flows)
//		{
//			flow.displayArticles();
//		}
		//URLFlow flow = new URLFlow("http://www.eric-pidoux.com/actu/jsoup-parser-simplement-du-html-en-java", "html");
//		URLFlow flow = new URLFlow("http://rss.lemonde.fr/c/205/f/3050/index.rss");
//		flow.save();
//		flow.displayArticles();
//		System.out.println(flow.get(0).getContent());
//		View v = new View();
//		
//		
//		System.out.println("Now Exiting...");
		
		//URLFlow flow = new URLFlow("http://www.eric-pidoux.com/actu/jsoup-parser-simplement-du-html-en-java", "html");
		//URLFlow flow = new URLFlow("http://rss.lemonde.fr/c/205/f/3050/index.rss");
		//flow.displayArticles();
		//System.out.println(flow.get(0).getContent());
	}
}
