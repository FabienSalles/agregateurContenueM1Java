package app.view;

import java.util.LinkedHashSet;
import java.util.Set;

import app.core.URLFlow;
import app.core.exception.UnknownTypeException;
import app.model.Flow;
import app.model.FlowTable;

public class Main 
{
	public static void main(String args[])
	{
		Set<Flow> flows = FlowTable.getInstance().getFlow();
		
		for(Flow flow : flows)
		{
			flow.displayArticles();
		}
		//URLFlow flow = new URLFlow("http://www.eric-pidoux.com/actu/jsoup-parser-simplement-du-html-en-java", "html");
//		URLFlow flow = new URLFlow("http://rss.lemonde.fr/c/205/f/3050/index.rss");
//		flow.save();
//		flow.displayArticles();
//		System.out.println(flow.get(0).getContent());
	}
}
