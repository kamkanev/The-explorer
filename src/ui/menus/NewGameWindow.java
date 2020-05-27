package ui.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import engineTester.MainGameLoop;
import toolbox.Fonts;
import toolbox.Images;
import toolbox.JavaToMySQL;
import toolbox.World;
import ui.customComponents.BorderPanel;

public class NewGameWindow extends JFrame {
	
	private JLabel title = new JLabel("Start a new journey", JLabel.CENTER);
	
	private JTextField worldName = new JTextField();
	private JTextField chName = new JTextField();
	
	private JRadioButton jenaGen = new JRadioButton("Female");
	private JRadioButton mujGen = new JRadioButton("Male");
	private ButtonGroup genterSelect = new ButtonGroup();
	
	private JRadioButton storyMode = new JRadioButton("Story Mode");
	private JRadioButton sandMode = new JRadioButton("Sondbox");
	private ButtonGroup gameTypeSelect = new ButtonGroup();
	
	private JTextField seedName = new JTextField();
	
	private JButton submit = new JButton("Start");
	
	private Point mouse = null;
	private JFrame mainWindow;
	
	public NewGameWindow(String title, JFrame main) {
		
		super("New Game - "+title);
		
		mainWindow = main;
		
		setSize(600, 500);
		
//		WORD_SIZE = Fonts.getFontSizeByScreen(this, 25);
//		System.err.println(WORD_SIZE);
		
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setUndecorated(true);
		
		makeMoveable();
//		updateOnResize();
		
		setResizable(false);
		
		loadCustomBorder();
		loadTitle();
//		loadButtons();
		loadForm();
		
		Images.setBackground(this, "back8.jpg", true);
	}
	
	private void loadCustomBorder() {
		JPanel border = new JPanel();
		border.setBounds(0, 0, 600, 500);
		border.setLayout(null);
		border.setOpaque(false);
		border.add(BorderPanel.addAllBorderButtons(this, mainWindow));
		this.add(border);
		
	}
	
	private void loadTitle() {
		
		title.setBounds(this.getWidth()/2 - 175, 10, 350, 100);
		title.setForeground(Color.WHITE);
		try {
			title.setFont(Fonts.loadFont("Algerian", Font.PLAIN, 30f));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.add(title);
		
	}
	
	private void loadForm() {
		
		
		FocusListener focusLis = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				String name = e.getComponent().getName();
				if(name == worldName.getName()) {
					worldName.setBorder(null);
				}else if(name == chName.getName()) {
					chName.setBorder(null);
				}else if(name == "sName") {
					seedName.setBorder(null);
					if(!seedName.isEnabled()) {
						seedName.setBackground(Color.LIGHT_GRAY);
					}
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				String name = e.getComponent().getName();
				if(name == worldName.getName()) {
					worldName.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3, true));
				}else if(name == chName.getName()) {
					chName.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3, true));
				}else if(name == "sName") {
					seedName.setBackground(Color.WHITE);
					seedName.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3, true));
				}
				
				
			}
		};
		
		JPanel wName = new JPanel();
		
//		world
		
		wName.setBounds(0, 100, getWidth(), 40);
		wName.setLayout(null);
		
		worldName.setName("worldName");
//		worldName.setColumns(12);
		worldName.setBounds(150, 5, 300, 30);
		worldName.setHorizontalAlignment(JTextField.CENTER);
		worldName.setBorder(null);//BorderFactory.createLineBorder(Color.CYAN, 1, true)
		
		JLabel worldLabel = new JLabel("World name:");
		worldLabel.setBounds(25, 5, 110, 30);
		
		worldLabel.setForeground(Color.WHITE);
		
//		worldName.addMouseListener(mouseA1);
		worldName.addFocusListener(focusLis);
		
//		character
		
		JPanel cName = new JPanel();
		
		cName.setBounds(0, wName.getY() + wName.getHeight() + 10, getWidth(), 40);
		cName.setLayout(null);
		
		chName.setName("chName");
//		worldName.setColumns(12);
		chName.setBounds(150, 5, 300, 30);
		chName.setHorizontalAlignment(JTextField.CENTER);
		chName.setBorder(null);//BorderFactory.createLineBorder(Color.CYAN, 1, true)
		
		JLabel charLabel = new JLabel("Character name:");
		charLabel.setBounds(15, 5, 130, 30);
		
		charLabel.setForeground(Color.WHITE);
		
//		chName.addMouseListener(mouseA1);
		chName.addFocusListener(focusLis);
		
//		gender
		
		JPanel gName = new JPanel();
		
		gName.setBounds(0, cName.getY() + cName.getHeight() + 10, getWidth(), 40);
		gName.setLayout(null);
		
		jenaGen.setName("jena");
//		worldName.setColumns(12);
		jenaGen.setBounds(150, 5, 100, 30);
		jenaGen.setHorizontalAlignment(JRadioButton.CENTER);
		jenaGen.setBorder(null);//BorderFactory.createLineBorder(Color.CYAN, 1, true)
		jenaGen.setOpaque(false);
		
		mujGen.setName("muj");
//		worldName.setColumns(12);
		mujGen.setBounds(250, 5, 100, 30);
		mujGen.setHorizontalAlignment(JRadioButton.CENTER);
		mujGen.setBorder(null);//BorderFactory.createLineBorder(Color.CYAN, 1, true)
		mujGen.setOpaque(false);
		mujGen.setSelected(true);
		
		JLabel genLabel = new JLabel("Select gender:");
		genLabel.setBounds(15, 5, 130, 30);
		
		genLabel.setForeground(Color.WHITE);
		jenaGen.setForeground(Color.WHITE);
		mujGen.setForeground(Color.YELLOW);
		
		mujGen.setFocusable(false);
		jenaGen.setFocusable(false);
		
		mujGen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mujGen.setForeground(Color.YELLOW);
				jenaGen.setForeground(Color.WHITE);
				mujGen.setFocusable(false);
				
			}
		});
		
		jenaGen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jenaGen.setForeground(Color.YELLOW);
				mujGen.setForeground(Color.WHITE);
				jenaGen.setFocusable(false);
				
			}
		});
		
//		game mode
		
		JPanel mName = new JPanel();
		
		mName.setBounds(0, gName.getY() + gName.getHeight() + 10, getWidth(), 40);
		mName.setLayout(null);
		
		storyMode.setName("story");
//		worldName.setColumns(12);
		storyMode.setBounds(150, 5, 100, 30);
		storyMode.setHorizontalAlignment(JRadioButton.CENTER);
		storyMode.setBorder(null);//BorderFactory.createLineBorder(Color.CYAN, 1, true)
		storyMode.setOpaque(false);
		
		sandMode.setName("sandbox");
//		worldName.setColumns(12);
		sandMode.setBounds(250, 5, 100, 30);
		sandMode.setHorizontalAlignment(JRadioButton.CENTER);
		sandMode.setBorder(null);//BorderFactory.createLineBorder(Color.CYAN, 1, true)
		sandMode.setOpaque(false);
		
		storyMode.setSelected(true);
		
		JLabel modLabel = new JLabel("Select mode:");
		modLabel.setBounds(15, 5, 130, 30);
		
		modLabel.setForeground(Color.WHITE);
		sandMode.setForeground(Color.WHITE);
		storyMode.setForeground(Color.YELLOW);
		
		sandMode.setFocusable(false);
		storyMode.setFocusable(false);
		
		sandMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sandMode.setForeground(Color.YELLOW);
				storyMode.setForeground(Color.WHITE);
				sandMode.setFocusable(false);
				seedName.setEnabled(true);

					seedName.setBackground(Color.WHITE);
				
			}
		});
		
		storyMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				storyMode.setForeground(Color.YELLOW);
				sandMode.setForeground(Color.WHITE);
				storyMode.setFocusable(false);
				seedName.setEnabled(false);
				
				seedName.setBackground(Color.LIGHT_GRAY);
			}
		});
		
//		seed
		
		JPanel sName = new JPanel();
		
		sName.setBounds(0, mName.getY() + mName.getHeight() + 10, getWidth(), 40);
		sName.setLayout(null);
		
		seedName.setName("sName");
//		worldName.setColumns(12);
		seedName.setBounds(150, 5, 300, 30);
		seedName.setHorizontalAlignment(JTextField.CENTER);
		seedName.setBorder(null);//BorderFactory.createLineBorder(Color.CYAN, 1, true)
		
		JLabel seedLabel = new JLabel("Seed:");
		seedLabel.setBounds(15, 5, 130, 30);
		
		seedLabel.setForeground(Color.WHITE);
		
//		chName.addMouseListener(mouseA1);
		seedName.addFocusListener(focusLis);
		seedName.setEnabled(false);
		seedName.setBackground(Color.LIGHT_GRAY);
		
//		submit button
		
		submit.setBounds(this.getWidth()-100, this.getHeight()-50, 100, 50);
		submit.setBorder(null);
		submit.setBackground(Color.GREEN);
		submit.setVisible(true);
		
		submit.setFocusable(false);
		
		submit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				submit.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				submit.setBorder(null);
				submit.setForeground(Color.WHITE);
			}
			
		});
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				World.createNewWorld(getInfo());
				JavaToMySQL.putNewWorldWithPlayer(getInfo());
				
				dispose();
				MainGameLoop.heroName = chName.getText();
				MainGameLoop.heroId = JavaToMySQL.getLastPlayerId();
				MainGameLoop.worldId = JavaToMySQL.getLastWorldId();
				MainGameLoop.runGame();

			}
		});
		
		
		try {
			worldLabel.setFont(Fonts.loadFont("Englisht", Font.PLAIN, 22f));
			worldName.setFont(Fonts.loadFont("Englisht", 20f));
			
			charLabel.setFont(Fonts.loadFont("Englisht", Font.PLAIN, 20f));
			chName.setFont(Fonts.loadFont("Englisht", 20f));
			
			genLabel.setFont(Fonts.loadFont("Englisht", Font.PLAIN, 20f));
			jenaGen.setFont(Fonts.loadFont("ENDOR", Font.PLAIN, 15f));
			mujGen.setFont(Fonts.loadFont("ENDOR", Font.PLAIN, 15f));
			
			modLabel.setFont(Fonts.loadFont("Englisht", Font.PLAIN, 20f));
			storyMode.setFont(Fonts.loadFont("ENDOR", Font.PLAIN, 15f));
			sandMode.setFont(Fonts.loadFont("ENDOR", Font.PLAIN, 15f));
			
			seedLabel.setFont(Fonts.loadFont("Englisht", Font.PLAIN, 20f));
			seedName.setFont(Fonts.loadFont("Englisht", 20f));
			
			submit.setForeground(Color.WHITE);
			submit.setFont(Fonts.loadFont("Englisht", Font.PLAIN, 30f));
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		wName.add(worldLabel);
		wName.add(worldName);
		
		cName.add(charLabel);
		cName.add(chName);
		
		gName.add(genLabel);
		
		genterSelect.add(jenaGen);
		genterSelect.add(mujGen);
		
		gName.add(jenaGen);
		gName.add(mujGen);
		
		mName.add(modLabel);
		
		gameTypeSelect.add(storyMode);
		gameTypeSelect.add(sandMode);
		
		mName.add(storyMode);
		mName.add(sandMode);
		
		sName.add(seedLabel);
		sName.add(seedName);
		
		wName.setOpaque(false);
		cName.setOpaque(false);
		gName.setOpaque(false);
		mName.setOpaque(false);
		sName.setOpaque(false);
		
		
		this.add(wName);
		this.add(cName);
		this.add(gName);
		this.add(mName);
		this.add(sName);
		this.add(submit);
		
	}
	
	private HashMap<String, String> getInfo() {
		
		HashMap<String, String> info = new HashMap<String, String>();
		
		info.put(worldName.getName(), worldName.getText());
		info.put(chName.getName(), chName.getText());
		
		if(mujGen.isSelected()) {
			info.put("gender", mujGen.getName());
		}else {
			info.put("gender", jenaGen.getName());
		}
		
		if(storyMode.isSelected()) {
			info.put("mode", storyMode.getName());
		}else {
			info.put("mode", sandMode.getName());
			info.put(seedName.getName(), seedName.getText());
		}
		
		info.put("coords", "100/0/100");
		
		return info;
		
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
