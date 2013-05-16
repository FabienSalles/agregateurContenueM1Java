package app.view;

import java.util.LinkedHashSet;
import java.util.Set;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Scanner;

import app.core.exception.UnknownTypeException;
import app.flow.Flow;
import app.flow.FlowTable;
import app.flow.FolderFlow;
import app.flow.HTMLFlow;

public class Main 
{
	public static void main(String args[])
	{
		FolderFlow flow = new FolderFlow("/home/fsalles/src/MIAGE/PROJET1_M1/data/dossier");
		flow.saveArticles();
//		Set<Flow> flows = FlowTable.getInstance().getFlow();
//		
//		for(Flow flow : flows)
//		{
//			flow.displayArticles();
//		}
//		Flow flow = null;
//		try {
//			flow = new HTMLFlow(new URL("http://www.eric-pidoux.com/actu/jsoup-parser-simplement-du-html-en-java"));
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		flow.displayArticles();
//		URLFlow flow = new URLFlow("http://rss.lemonde.fr/c/205/f/3050/index.rss");
//		flow.save();
//		flow.displayArticles();
//		System.out.println(flow.get(0).getContent());
//		View v = new View();
//		
//		Scanner s = new Scanner(System.in);
//		
//		v.welcomeView();
//		
//		while (v.getCurrentState() != 5) {
//			v.printView();
//			int i = s.nextInt();
//			v.setCurrentState(i);
//		}
		
		System.out.println("Now Exiting...");
		
		//URLFlow flow = new URLFlow("http://www.eric-pidoux.com/actu/jsoup-parser-simplement-du-html-en-java", "html");
		//URLFlow flow = new URLFlow("http://rss.lemonde.fr/c/205/f/3050/index.rss");
		//flow.displayArticles();
		//System.out.println(flow.get(0).getContent());
	}
}
