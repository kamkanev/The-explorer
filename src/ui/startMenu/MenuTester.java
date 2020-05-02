package ui.startMenu;

import java.awt.FontFormatException;
import java.io.IOException;

public class MenuTester {

	public static void main(String[] args) {
		
		StartMenu start;
		try {
			start = new StartMenu();
			start.setVisible(true);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		

	}

}
