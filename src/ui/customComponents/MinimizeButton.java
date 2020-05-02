package ui.customComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class MinimizeButton extends CustomBorderButton {
	
	private JFrame win;
	
	public MinimizeButton(Rectangle r, JFrame win) {
		super("-", r);
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
//				win.setState(JFrame.ICONIFIED);
				win.setExtendedState(MINIMIZE_BUTTON);
			}
			
		});

	}

}
