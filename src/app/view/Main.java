package app.view;

import java.util.Scanner;

public class Main 
{
	public static void main(String args[])
	{		
		View v = new View();
		Scanner s = new Scanner(System.in);
		
		v.welcomeView();

		while (v.getCurrentState() != 5) {
			v.printView();
			int i = s.nextInt();
			v.setCurrentState(i);
		}
	}
}
