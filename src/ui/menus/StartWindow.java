package ui.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import toolbox.Fonts;
import toolbox.Images;
import ui.customComponents.BorderPanel;

public class StartWindow extends JFrame {
	
	private JLabel newGame = new JLabel("New Game");
	private JLabel loadGame = new JLabel("Load Game");
	private JLabel options = new JLabel("Options");
	private JLabel credits = new JLabel("Credits");
	private JLabel exit = new JLabel("Exit");
	
	private float WORD_SIZE ;//= 22.0f;
	private final float TITLE_SIZE = 50.0f;
	private final float SUBTITLE_SIZE = 20.0f;
	
	private Point mouse = null;
	
	private HashMap<String, JFrame> windows = new HashMap<String, JFrame>();

	public StartWindow(String title, String subTitle, HashMap<String, JFrame> windows){
		
		super(title + ": " + subTitle);
		
		this.windows = windows;
		
		setSize(600, 500);
		
		WORD_SIZE = Fonts.getFontSizeByScreen(this, 25);
//		System.err.println(WORD_SIZE);
		
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setUndecorated(true);
		
		makeMoveable();
//		updateOnResize();
		
		setResizable(false);
		
		loadCustomBorder();
		loadTitles(title, subTitle);
		loadButtons();
		
		Images.setBackground(this, "back3.jpg", true);
		
	}
	
	private void loadCustomBorder() {
		JPanel border = new JPanel();
		border.setBounds(0, 0, 600, 500);
		border.setLayout(null);
		border.setOpaque(false);
		border.add(BorderPanel.addAllDefaultBorderButtons(this));
		this.add(border);
		
	}
	
	private void loadTitles(String tit, String subTit) {
		
		JLabel title = new JLabel(tit, JLabel.CENTER);
		JLabel subTitle = new JLabel(subTit, JLabel.CENTER);
		
		Font titleFont = null;
		try {
			titleFont = Fonts.loadFont("Algerian", Font.PLAIN, TITLE_SIZE);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		Font subTitleFont = null;
		try {
			subTitleFont = Fonts.loadFont("Algerian", Font.BOLD + Font.ITALIC, SUBTITLE_SIZE);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		title.setFont(titleFont);
		title.setSize(400, 60);
		title.setLocation(this.getWidth()/2 - title.getWidth()/2, 40);
		title.setVisible(true);
		
		subTitle.setFont(subTitleFont);
		subTitle.setSize(400, 60);
		subTitle.setLocation(this.getWidth()/2 - subTitle.getWidth()/2, title.getHeight() + title.getY() - 20);
		subTitle.setVisible(true);
		
		this.add(title);
		this.add(subTitle);
		
	}

	private void loadButtons() {
		
		Font buttonFont = null;
		
		try {
			buttonFont = Fonts.loadFont("Englisht", Font.PLAIN, WORD_SIZE);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		MouseAdapter mouseWork = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				String name = e.getComponent().getName();
				if(name == exit.getName()) {
					
					dispose();
					System.exit(0);
					
				}else if(name == newGame.getName()) {
					
					System.out.println("Create new Game");
					windows.get(name).setVisible(true);
					dispose();
					windows.get(name).setFocusable(true);
//					new File("worlds").mkdir();
					
				}else if(name == loadGame.getName()) {
					
					System.out.println("Load game");
					windows.get(name).setVisible(true);
					dispose();
					windows.get(name).setFocusable(true);
					
				}else if(name == options.getName()) {
					
					System.out.println("Open options");
					windows.get(name).setVisible(true);
					dispose();
					windows.get(name).setFocusable(true);
					
				}else if(name == credits.getName()) {
					
					System.out.println("Credits to be shown");
					windows.get(name).setVisible(true);
					dispose();
					windows.get(name).setFocusable(true);
					
				}
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				e.getComponent().setFont(e.getComponent().getFont().deriveFont(WORD_SIZE+3));
				e.getComponent().setForeground(Color.GRAY);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				e.getComponent().setFont(e.getComponent().getFont().deriveFont(WORD_SIZE));
				e.getComponent().setForeground(Color.BLACK);
			}
			
		};
		
		
		
		loadFontToAllButtonsInMainMenu(buttonFont);
		loadColorToAllButtonsInMainMenu(Color.BLACK);
		loadMouseListenerToAllButtonsInMainMenu(mouseWork);
		
		newGame.setBounds(30, 130, 150, 60);
		newGame.setName("new game");

		
		loadGame.setBounds(newGame.getX(), newGame.getY() + newGame.getHeight()-10, 150, 60);
		loadGame.setName("load game");

		
		options.setBounds(newGame.getX(), loadGame.getY() + loadGame.getHeight()-10, 150, 60);
		options.setName("options");

		
		credits.setBounds(newGame.getX(),  options.getY() + options.getHeight()-10, 150, 60);
		credits.setName("credits");

		
		exit.setBounds(newGame.getX(),  credits.getY() + credits.getHeight()-10, 150, 60);
		exit.setName("exit");
		
		add(newGame);
		add(loadGame);
		add(options);
		add(credits);
		add(exit);
		
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
	
	private void loadMouseListenerToAllButtonsInMainMenu(MouseListener listener) {
		newGame.addMouseListener(listener);
		loadGame.addMouseListener(listener);
		options.addMouseListener(listener);
		credits.addMouseListener(listener);
		exit.addMouseListener(listener);
	}
	
	private void updateOnResize() {
		
		final JFrame frame = this;
		this.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {
//				super.componentResized(e);
				WORD_SIZE = Fonts.getFontSizeByScreen(frame, 25);
				loadButtons();
				Images.setBackground(frame, "back3.jpg", true);
				frame.invalidate();
				frame.validate();
				frame.repaint();
			}
			
		});
	}
	
	private void makeMoveable() {
		
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
	
	private void framedragMouseDragged(java.awt.event.MouseEvent evt) {

		int x = evt.getXOnScreen();


		int y = evt.getYOnScreen();

		 

		this.setLocation((int)(x - mouse.getX()), (int)(y - mouse.getY()));

		}
	
	private void framedragMousePressed(java.awt.event.MouseEvent evt) {

		mouse  = evt.getPoint();

		}

}
