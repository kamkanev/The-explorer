package ui.customComponents;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import toolbox.Images;

public class BackButton extends CustomBorderButton {
	
	private JFrame win;
	private JFrame back;
	
	public BackButton(Rectangle r, JFrame win, JFrame back) {
		super(new ImageIcon(Images.getScaledImage(new ImageIcon("res/icons/arrows.png").getImage(), r.width, r.height)), JLabel.CENTER, r);
		setOpaque(false);
		this.win = win;
		this.back = back;
		addOperation();
	}

	@Override
	protected void addOperation() {
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(back != null) {
					back.setVisible(true);
					win.dispose();
					back.setFocusable(true);
				}else {
					System.err.println("There is no window to go back to!");
				}
			}
			
		});
		
	}

}
