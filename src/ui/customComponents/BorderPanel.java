package ui.customComponents;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BorderPanel {
	
	public static JPanel addAllBorderButtons(JFrame win, JFrame back) {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0, 0, win.getWidth(), 30);
		jp.setOpaque(false);
		
		jp.add(new BackButton(new Rectangle(2, 0, 40, 30), win, back));
		
		jp.add(new CloseButton(new Rectangle(win.getWidth()-30, 0, 30, 30), win));
		if(win.isResizable()) {
			jp.add(new MaximizeButton(new Rectangle(win.getWidth()-60, 0, 30, 30), win));
			jp.add(new MinimizeButton(new Rectangle(win.getWidth()-90, 0, 30, 30), win));
		}else {
			jp.add(new MinimizeButton(new Rectangle(win.getWidth()-60, 0, 30, 30), win));
		}
		
		return jp;
		
	}
	
	public static JPanel addAllDefaultBorderButtons(JFrame win) {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0, 0, win.getWidth(), 30);
		jp.setOpaque(false);
		
		jp.add(new CloseButton(new Rectangle(win.getWidth()-30, 0, 30, 30), win));
		if(win.isResizable()) {
			jp.add(new MaximizeButton(new Rectangle(win.getWidth()-60, 0, 30, 30), win));
			jp.add(new MinimizeButton(new Rectangle(win.getWidth()-90, 0, 30, 30), win));
		}else {
			jp.add(new MinimizeButton(new Rectangle(win.getWidth()-60, 0, 30, 30), win));
		}
		
		return jp;
		
	}
	
	public static JLabel addCloseButton(JFrame win) {
		return (new CloseButton(new Rectangle(win.getWidth()-30, 0, 30, 30), win));
	}
	
	public static JLabel addMaximizeButton(JFrame win) {
		return (new MaximizeButton(new Rectangle(win.getWidth()-30, 0, 30, 30), win));
	}
	
	public static JLabel addMinimizeButton(JFrame win) {
		return (new MinimizeButton(new Rectangle(win.getWidth()-30, 0, 30, 30), win));
	}
	
	public static JLabel addBackButton(JFrame win) {
//		return (new CloseButton(new Rectangle(win.getWidth()-30, 0, 30, 30), win));
		return null;
	}

}
