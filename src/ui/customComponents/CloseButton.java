package ui.customComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class CloseButton extends CustomBorderButton {
	
	private JFrame win;

	public CloseButton(Rectangle r, JFrame win) {
		super("X", r);
		this.win = win;
		setBackground(Color.RED);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addOperation();
		this.setFont(new Font(Font.MONOSPACED, Font.BOLD, this.getWidth()));
	}

	@Override
	protected void addOperation() {
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				win.dispose();
				System.exit(0);
//				win.setVisible(true);
				
			}
			
		});
		
	}
	
	

}
