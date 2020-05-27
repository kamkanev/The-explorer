package ui.menus;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import toolbox.Fonts;
import toolbox.JavaToMySQL;
import ui.customComponents.BorderPanel;

public class LoadGameWindow extends JFrame {
	
	private Point mouse = new Point();
	
	private JFrame back = new JFrame();
	
	private List<JPanel> worlds = new ArrayList<JPanel>();
	private JPanel main = new JPanel();
	
	public LoadGameWindow(JFrame back) {
		
		super("Load Game");

		this.back = back;
		
		setSize(600, 500);

		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setUndecorated(true);
		
		makeMoveable();
//		updateOnResize();
		
		setResizable(false);
		
		this.add(BorderPanel.addAllBorderButtons(this, back));
		
		loadMainCon();
		
	}
	
	private void loadMainCon() {
		
//	       JScrollPane scrollPane = new JScrollPane(main, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	       
	       
//	        scrollPane.setOpaque(false);
		
		main.setVisible(true);
		main.setBounds(20, 50, 560, 400);
		main.setBackground(Color.YELLOW);
		loadWorlds();
		main.setLayout(null);
//		this.add(scrollPane);
		this.add(main);
		
	}
	
	private void loadWorlds() {
		
		
		
		worlds = JavaToMySQL.getWorlds(this);
		
		for(int i = 0; i < worlds.size(); i++) {
			
			JPanel w = worlds.get(i);
			
			int deleted = getComponentByName(w, "number").getHeight();
			
	    	JButton delete = new JButton("Delete");
	    	
	    	delete.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    	delete.setFocusable(false);
	    	delete.setBounds(470, 50, 70, 40);
	    	
	    	delete.setBackground(new Color(193, 2, 2));
	    	delete.setForeground(Color.WHITE);
	    	
	    	try {
				delete.setFont(Fonts.loadFont("Englisht", 18f));
			} catch (FontFormatException | IOException e1) {
				e1.printStackTrace();
			}
	    	
	    	delete.addMouseListener(new MouseAdapter() {
	    		
	    		@Override
	    		public void mouseExited(MouseEvent e) {
	    			super.mouseExited(e);
	    			delete.setBackground(new Color(193, 2, 2));
	    	    	delete.setForeground(Color.WHITE);
	    		}
	    		
	    		@Override
	    		public void mouseEntered(MouseEvent e) {
	    			super.mouseEntered(e);
	    			delete.setBackground(new Color(255, 49, 0));
	    	    	delete.setForeground(Color.BLACK);
	    		}
	    		
			});
	    	
	    	delete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					

						
//						win.remove(w);
//						win.invalidate();
//						win.repaint();
//						w.setVisible(false);
//						main.remove(w);
						
						deleteWorld(deleted);
					
						//delete from database

					
				}

				
			});
	    	
	    	w.add(delete);
	    	
	    	main.add(w);
		}
		
	}
	
	private Component getComponentByName(JPanel jp, String name) {
		
		for(Component c : jp.getComponents()) {
			
			if(c.getName() == name) {
				
				return c;
			}
			
		}
		
		return null;
		
	}
	
	private void deleteWorld(int deleted) {
		
		if(deleted >= 0) {
			
			//delete player
			
			//delete entities
			JavaToMySQL.deleteEntities(deleted);
			//delete world
			JavaToMySQL.deleteWorld(deleted);
			//generate new list
			JavaToMySQL.deletePlayer(deleted);
			
			this.invalidate();
			this.repaint();
			
			for(JPanel jp : worlds) {
				
				main.remove(jp);
				
			}
			
			loadWorlds();
			
			this.invalidate();
			this.repaint();
			
		}
		
		
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
