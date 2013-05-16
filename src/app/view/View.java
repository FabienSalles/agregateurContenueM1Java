package app.view;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import test.model.FlowTableTest;
import app.article.Article;
import app.article.ArticleTable;
import app.flow.Flow;
import app.flow.FlowTable;
import app.flow.FlowType;
import app.flow.FolderFlow;
import app.flow.HTMLFlow;
import app.flow.RSSFlow;

public class View
{
	private int currentState;
	private Scanner scanner;
	private final int DEFAULT = 0;
	private final int ADD = 1;
	private final int MODIFY = 2;
	private final int DELETE = 3;
	private final int SEARCH = 4;
	private final int QUIT = 5;
	
	private Set<Flow> flows;
	private ArticleTable articleTable;
	
	public View() {
		this.currentState = DEFAULT;
		this.scanner = new Scanner(System.in);
		this.flows = FlowTable.getInstance().getFlow();
		this.articleTable = ArticleTable.getInstance();
	}
	
	public void printView() {
		switch (this.currentState) {
			case ADD:
				addView();
				break;
			case MODIFY:
				modifyView();
				break;
			case DELETE:
				deleteView();
				break;
			case SEARCH:
				searchView();
				break;
			case QUIT:
				quitView();
				break;
		}
		defaultView();
	}
	
	public void welcomeView() {
		System.out.println("#########################################################");
		System.out.println("################# Agregateur de contenu #################");
		System.out.println("########                                         ########");
		System.out.println("#########################################################");
	}
	
	private void defaultView() {
		System.out.println("(Default View) Menu : ");
		System.out.println("\t1. Add Flow.");
		System.out.println("\t2. Modify Flow.");
		System.out.println("\t3. Delete Flow.");
		System.out.println("\t4. Search Flow.");
		System.out.println("\t5. Quit.");
	}
	
	private void addView() {
		System.out.println("(Add View) Add Flow :");
		
		String type = "";
		do {
			System.out.println("Select a type of flow (case sensitive, EXIT to leave) ->");
			flowTypeView();
			type = scanner.next();
			if (type.equalsIgnoreCase("EXIT"))
				return;
		} while (!checkFlowType(type));
		
		FlowType ft = FlowType.valueOf(type);
		
		System.out.println("Type a flow to add (No space allowed) ->");
		
		String flowPath = scanner.next();
		
		String typeofPath = getTypeOfPath(flowPath);
		Flow flow = null;
		switch(ft)
		{
			case HTML:
				if (typeofPath.equals("url"))
				{
					try {
						flow = new HTMLFlow(new URL(flowPath));
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
				else
				{
					flow = new HTMLFlow(new File(flowPath));
				}
				break;
			case RSS:
				if (typeofPath.equals("url"))
				{
					try {
						flow = new RSSFlow(new URL(flowPath));
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
				else
				{
					flow = new RSSFlow(new File(flowPath));
				}
				break;
			case FOLDER:
				flow = new FolderFlow(flowPath);
				break;
			default:
				break;
		}
		flow.save();
		flows.add(flow);
		System.out.println("The flow has been added.");
	}
	
	private void flowTypeView() {
		for (FlowType ft : FlowType.values())
			System.out.print(ft.name() + " ");
		System.out.print("\n");
	}
	
	private void modifyView() {
		System.out.println("(Modify View) Modify Flow :");
		
		if (this.flows.size() > 0) {
			int i = 0;
			for (Flow f: this.flows) {
				System.out.println(i + ") " + f.getPath());
				i++;
			}
			
			System.out.print("Enter the number of the flow to edit -> ");
			int value = Integer.parseInt(scanner.next());
			
			i = 0;
			for (Flow f: this.flows) {
				if (i == value) {
					System.out.println(f.getPath());
					break;
				}
				i++;
			}
			
			String newPath = scanner.next();
			System.out.println(newPath);
			
			i = 0;
			for (Flow f: this.flows) {
				if (i == value) {
					f.setPath(newPath);
					f.recover();
					f.update();
				}
				i++;
			}
			
			i = 0;
			for (Flow f: this.flows) {
				System.out.println(i + ") " + f.getPath());
				i++;
			}
			
		} else {
			System.out.println("No flow to modify.");
		}
	}
	
	private void deleteView() {
		System.out.println("(Delete View) Delete Flow :");
		
		FlowTable ft = FlowTable.getInstance();
		this.flows = ft.getFlow();
		
		if (this.flows.size() > 0) {
			int i = 0;
			for (Flow flow: this.flows) {
				System.out.println(i + ") " + flow.getPath());
				i++;
			}
			System.out.println("Enter the number of the flow to delete ->");
		
			int value = Integer.parseInt(scanner.next());
			
			i = 0;
			for (Flow flow: this.flows) {
				if (i == value)
					if (flow.getRowid() != null)
						flow.delete();
					this.flows.remove(flow);
				i++;
			}
			
			i = 0;
			for (Flow flow: this.flows) {
				System.out.println(i + ") " + flow.getPath());
				i++;
			}
			
		} else {
			System.out.println("No flow to delete.");
		}
	}
	
	private void searchView() {
		FlowTable ft = FlowTable.getInstance();
		this.flows = ft.getFlow();
		
		System.out.println("(Search View) Search Flow :");
		System.out.println("Enter key words (seperate words with spaces) ->");

		String str = "";
		while (scanner.hasNextLine() && str == "")
			str = scanner.nextLine();
		System.out.println(str);
		
		Set<Article> articles = new LinkedHashSet<Article>();
		
		for (Flow f : this.flows) {
			articles.addAll(f.search(str));
		}

		for (Article a : articles) {
			System.out.println(a);
		}
	}
	
	private void quitView() {
		System.out.println("Now exiting...");
		System.exit(127);
	}
	
	private boolean checkFlowType(String str) {
		for (FlowType ft : FlowType.values()) {
			if (ft.name().compareTo(str) == 0)
				return true;
		}
		return false;
	}
	
	public int getCurrentState() {
		return this.currentState;
	}
	
	public void setCurrentState(int i) {
		this.currentState = i;
	}
	
	private void generateReport() throws Exception {
		File f = new File("report.html");
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write("<html>")
	}
	
	private String getTypeOfPath(String path)
	{
		if (path.startsWith("http"))
		{
			return "url";
		}
		return "file";
	}
}
