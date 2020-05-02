package ui.startMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sun.font.FontFamily;
import ui.customComponents.BackButton;
import ui.customComponents.BorderPanel;
import ui.customComponents.CloseButton;
import ui.customComponents.MaximizeButton;
import ui.customComponents.MinimizeButton;

public class StartMenu extends JFrame {
	
	JFrame win = this;
	
	JLabel title = new JLabel("Adroandra", JLabel.CENTER);
	JLabel subTitle = new JLabel("The child of prophecy", JLabel.CENTER);
	//pod zaglavie - The child of prophecy
	JLabel newGame = new JLabel("New Game");
	JLabel loadGame = new JLabel("Load Game");
	JLabel options = new JLabel("Options");
	JLabel credits = new JLabel("Credits");
	JLabel exit = new JLabel("Exit");
	JLabel background;
	Point mouse = null;
	
	JLabel close = new JLabel("X", JLabel.CENTER);
	JLabel minimize = new JLabel("-", JLabel.CENTER);
	
	JPanel upBorder = new JPanel();
	JPanel mainMenu = new JPanel();
	JPanel crGameMenu = new JPanel();
	
	MouseAdapter forButtons = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			String name = e.getComponent().getName();
			if(name == exit.getName()) {
				System.exit(0);
			}else if(name == newGame.getName()) {
				System.out.println("Create new Game");
				mainMenu.setVisible(false);
				mainMenu.remove(upBorder);
				crGameMenu.setVisible(true);
				crGameMenu.add(upBorder);
				repaint();
			}else if(name == loadGame.getName()) {
				System.out.println("Load game");
			}else if(name == options.getName()) {
				System.out.println("Open options");
			}else if(name == credits.getName()) {
				System.out.println("Credits to be shown");
			}
			
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			e.getComponent().setFont(e.getComponent().getFont().deriveFont(25.0f));
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			e.getComponent().setFont(e.getComponent().getFont().deriveFont(22.0f));
		}
		
	};
	
	
	public StartMenu() throws FontFormatException, IOException {
//		super();
		setTitle("Adroandra");
		setSize(600, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		loadUpmenu();
		loadTitle();

		loadMainmenu();
		setBackground("back3.jpg");
//		setBackground(crGameMenu, "back4.jpg");
		loadNewGameMenu();
		makeMOvable();
		
	}
	
	private void framedragMouseDragged(java.awt.event.MouseEvent evt) {

		int x = evt.getXOnScreen();


		int y = evt.getYOnScreen();

		 

		this.setLocation((int)(x - mouse.getX()), (int)(y - mouse.getY()));

		}
	
	private void framedragMousePressed(java.awt.event.MouseEvent evt) {

		mouse  = evt.getPoint();

		}
	
	private void makeMOvable() {
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				super.mousePressed(e);
				framedragMousePressed(e);
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				framedragMouseDragged(e);
				
			}
		});
		
	}
	
	private void setBackground(JComponent jc,String url) {
		ImageIcon img = new ImageIcon("res/backgrounds/"+url);
		
		background = new JLabel("", img, JLabel.CENTER);
		background.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		
		jc.add(background);
		
	}
	
	private void setBackground(String url) {
		ImageIcon img = new ImageIcon("res/backgrounds/"+url);
		
		background = new JLabel("", img, JLabel.CENTER);
		background.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		
		this.add(background);
		
	}
	
	private Font loadFont(String name, int style, float size) throws FontFormatException, IOException {
		String fName = "res/fonts/"+name+".ttf";
	    Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fName)).deriveFont(style, size);
	    GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fName)));
	    
	    return font;
	}
	
	private void loadTitle() throws FontFormatException, IOException {
		//new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 30);
		Font titleFont = loadFont("Algerian", Font.PLAIN, 50f);
		Font subTitleFont = loadFont("Algerian", Font.BOLD + Font.ITALIC, 20);
//		k.deriveFont(Font.BOLD + Font.ITALIC);
		title.setFont(titleFont);
		title.setSize(400, 60);
		title.setLocation(this.getWidth()/2 - title.getWidth()/2, 40);
		title.setVisible(true);
		
		subTitle.setFont(subTitleFont);
		subTitle.setSize(400, 60);
		subTitle.setLocation(this.getWidth()/2 - subTitle.getWidth()/2, title.getHeight() + title.getY() - 20);
		subTitle.setVisible(true);
		
		mainMenu.add(subTitle);
		mainMenu.add(title);
	}
	
	private void loadColorToAllButtonsInMainMenu(Color c) {
		newGame.setForeground(c);
		loadGame.setForeground(c);
		options.setForeground(c);
		credits.setForeground(c);
		exit.setForeground(c);
	}
	
	private void loadFontToAllButtonsInMainMenu(Font f) {
		newGame.setFont(f);
		loadGame.setFont(f);
		options.setFont(f);
		credits.setFont(f);
		exit.setFont(f);
	}
	
	private void loadMainmenu() throws FontFormatException, IOException {
		
		mainMenu.setBounds(0, 0, 600, 500);
		mainMenu.setLayout(null);
		mainMenu.setVisible(true);
		
		Font butsFont = loadFont("Englisht", Font.PLAIN, 22);
		
		loadColorToAllButtonsInMainMenu(Color.BLACK);
		
		loadFontToAllButtonsInMainMenu(butsFont);
		
		newGame.setBounds(30, 130, 120, 60);
		newGame.setName("new game");
		newGame.addMouseListener(forButtons);
//		newGame.setForeground(Color.WHITE);
		mainMenu.add(newGame);
		
		loadGame.setBounds(newGame.getX(), newGame.getY() + newGame.getHeight()-10, 120, 60);
		loadGame.setName("load game");
		loadGame.addMouseListener(forButtons);
//		loadGame.setForeground(Color.WHITE);
		mainMenu.add(loadGame);
		
		options.setBounds(newGame.getX(), loadGame.getY() + loadGame.getHeight()-10, 120, 60);
		options.setName("options");
		options.addMouseListener(forButtons);
//		options.setForeground(Color.WHITE);
		mainMenu.add(options);
		
		credits.setBounds(newGame.getX(),  options.getY() + options.getHeight()-10, 120, 60);
		credits.setName("credits");
		credits.addMouseListener(forButtons);
//		credits.setForeground(Color.WHITE);
		mainMenu.add(credits);
		
		exit.setBounds(newGame.getX(),  credits.getY() + credits.getHeight()-10, 120, 60);
		exit.setName("exit");
		exit.addMouseListener(forButtons);
//		exit.setForeground(Color.WHITE);
		mainMenu.add(exit);
		
//		mainMenu.add(close);
//		mainMenu.add(minimize);
//		JPanel upBorder2 = ne;
//		mainMenu.add(upBorder);
		mainMenu.setOpaque(false);
		
//		mainMenu.add(new MaximizeButton(new Rectangle(0, 0, 30, 30), this));
		
		mainMenu.add(BorderPanel.addAllDefaultBorderButtons(this));
//		mainMenu.add(BorderPanel.addAllBorderButtons(this, null));
//		mainMenu.add(new BackButton(new Rectangle(0, 0, 60, 30)));
		
		this.add(mainMenu);
		
	}
	
	private void loadNewGameMenu() {
		
		crGameMenu.setBounds(0, 0, 600, 500);
		crGameMenu.setLayout(null);
		crGameMenu.setVisible(false);
		crGameMenu.setOpaque(false);
		
//		JPanel upBorder2 = upBorder;
//		crGameMenu.add(upBorder2);
//		crGameMenu.add(BorderPanel.addAllBorderButtons(this));
		
//		crGameMenu.add(new MaximizeButton(new Rectangle(0, 0, 30, 30), this));
		
//		crGameMenu.add(BorderPanel.addCloseButton(this));
		
		this.add(crGameMenu);
		
	}
	
	private void loadUpmenu() {
		Font clFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
		
		upBorder.setBounds(0, 0, 600, 30);
		upBorder.setLayout(null);
		upBorder.setVisible(true);
		upBorder.setOpaque(false);
		
		close.setBounds(this.getWidth()-30, 0, 30, 30);
		close.setFont(clFont);
		close.setBorder(null);
		close.setOpaque(true);
		close.setBackground(Color.RED);
		close.setFocusable(false);
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				close.setBackground(Color.RED);
				win.setState(JFrame.EXIT_ON_CLOSE);
				System.exit(0);
			}
		});
		
		minimize.setBounds(this.getWidth()-60, 0, 30, 30);
		minimize.setFont(clFont);
		minimize.setBorder(null);
		minimize.setOpaque(true);
		minimize.setBackground(Color.GRAY);
		minimize.setFocusable(false);
		minimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				minimize.setBackground(Color.GRAY);
				win.setState(JFrame.ICONIFIED);
				
			}
		});
		
		upBorder.add(close);
		upBorder.add(minimize);
		
//		this.add(upBorder);
		
	}

}
