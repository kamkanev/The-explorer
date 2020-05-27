package ui.menus;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import ui.customComponents.BorderPanel;

public class MenuTester {
	
	public static final String TITLE = "Adroandra";//Adroandra
	public static final String SUBTITLE = "The child of prophecy";//The child of prophecy
	public static HashMap<String, JFrame> windows = new HashMap<String, JFrame>();

	public static void main(String[] args) {
		
		StartWindow stWin = new StartWindow(TITLE, SUBTITLE, windows);
		
		NewGameWindow newGame = new NewGameWindow(TITLE, stWin);
		
		LoadGameWindow loadGame = new LoadGameWindow(stWin);
//		
//		StartMenu start;
//		try {
//			start = new StartMenu(newGame);
//			start.setVisible(true);
//		} catch (FontFormatException e) {
//			System.err.println(e.getMessage());
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println(e.getMessage());
//		}
		
		windows.put("new game", newGame);
		windows.put("load game", loadGame);
		
		newGame.add(BorderPanel.addAllBorderButtons(newGame, stWin));
		
		stWin.setVisible(true);
		
//		windows.clear();

	}

}
