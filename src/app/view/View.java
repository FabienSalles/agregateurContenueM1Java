package app.view;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import test.model.FlowTableTest;
import app.article.Article;
import app.article.ArticleTable;
import app.flow.Flow;
import app.flow.FlowTable;
import app.flow.FlowType;

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
	private ArticleTable articles;
	
	public View() {
		this.currentState = DEFAULT;
		this.scanner = new Scanner(System.in);
		this.flows = FlowTable.getInstance().getFlow();
		this.articles = ArticleTable.getInstance();
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
		System.out.println("Type a type of flow to add ->");

		String str = "";
		while (scanner.hasNextLine() && str == "")
			str = scanner.nextLine();
		System.out.println(str);
		
		System.out.println("Type a flow to add ->");
		while (scanner.hasNextLine() && str == "")
			str = scanner.nextLine();
		System.out.println(str);
		
		Flow f = new Flow();
		
		this.flows.add(f);
		
		System.out.println("The flow has been added.");
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
		
		if (this.flows.size() > 0) {
			int i = 0;
			for (Flow flow: this.flows) {
				System.out.println(flow.getRowid() + ") " + flow.getPath());
				i++;
			}
			System.out.println("Enter the number of the flow to delete ->");
		
			i = scanner.nextInt();
			int flag = 0;
			for (Flow flow: this.flows) {
				if (flow.getRowid() == i) {
					flow.delete();
					flag = 1;
					break;
				}
			}
			
			if (flag == 0) {
				System.out.println(i + " was not found.");
			} else {
				System.out.println(i + " has been deleted.");
			}
			
		} else {
			System.out.println("No flow to delete.");
		}
	}
	
	private void searchView() {
		System.out.println("(Search View) Search Flow :");
		System.out.println("Enter key words (seperate words with spaces) ->");

		String str = "";
		while (scanner.hasNextLine() && str == "")
			str = scanner.nextLine();
		System.out.println(str);
		
		Set<Article> articles;
		try {
			articles = this.articles.searchArticlesByKeywords(str);
			if (this.articles != null) {
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
