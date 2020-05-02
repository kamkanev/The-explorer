package ui.customComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class MaximizeButton extends CustomBorderButton {
	
	private JFrame win;
	
	public MaximizeButton(Rectangle r, JFrame win) {
		
		super("^", r);
		this.win = win;
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setFont(new Font(Font.MONOSPACED, Font.BOLD, this.getWidth()));
		addOperation();
		
	}

	@Override
	protected void addOperation() {
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
//				win.setExtendedState(JFrame.MAXIMIZED_BOTH);
				if(win.getExtendedState() == 0) {
					win.setExtendedState(JFrame.MAXIMIZED_BOTH);
					System.err.println(win.getExtendedState());
				}else if(win.getExtendedState() == 6) {
					win.setExtendedState(0);
				}
			}
		});

	}

}
