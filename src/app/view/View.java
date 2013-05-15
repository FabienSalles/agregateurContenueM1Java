package app.view;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import test.model.FlowTableTest;
import app.model.Article;
import app.model.ArticleTable;
import app.model.Flow;
import app.model.FlowTable;

public class View
{
	private int currentState;
	private Scanner scanner;
	private final int DEFAULT = 0;
	private final int ADD = 1;
	private final int MODIFY = 2;
	private final int DELETE = 3;
	private final int SEARCH = 4;
	
	private Set<Flow> flows;
	
	public View() {
		this.currentState = DEFAULT;
		this.scanner = new Scanner(System.in);
		this.flows = FlowTable.getInstance().getFlow();
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
		System.out.println("Type a flow to add ->");

		String str = "";
		while (scanner.hasNextLine() && str == "")
			str = scanner.nextLine();
		System.out.println(str);
		
		Flow f = new Flow();
	}
	
	private void modifyView() {
		System.out.println("(Modify View) Modify Flow :");
		
		
		if (this.flows.size() > 0) {
			int i = 0;
			for (Flow flow: this.flows) {
				System.out.println(i + ") " + flow.getPath());
				i++;
			}
			System.out.println("Enter the number of the flow to edit ->");
		
			i = scanner.nextInt();
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
		
			i = scanner.nextInt();
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
		
		try {
			
			Set<Article> articles = ArticleTable.searchArticlesByKeywords(str);
			
			if (articles != null) {
				articleView(articles);
			} else {
				System.out.println("No article(s) found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void articleView(Set<Article> articles) {
		for (Article a : articles) {
			a.toString();
		}
	}
	
	public int getCurrentState() {
		return this.currentState;
	}
	
	public void setCurrentState(int i) {
		this.currentState = i;
	}
}
