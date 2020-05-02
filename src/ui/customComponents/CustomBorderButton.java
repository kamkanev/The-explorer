package ui.customComponents;

import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public abstract class CustomBorderButton extends JLabel {
	
	public static final int BACK_BUTTON = -1;
	public static final int CLOSE_BUTTON = 0;
	public static final int MINIMIZE_BUTTON = 1;
	public static final int MAXIMIZE_BUTTON = 2;

	public CustomBorderButton() {
		
	}

	public CustomBorderButton(String arg0) {
		super(arg0);
	
	}

	public CustomBorderButton(Icon image) {
		super(image);
		
	}

	public CustomBorderButton(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		
	}

	public CustomBorderButton(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		
	}

	public CustomBorderButton(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		
	}
	
	public CustomBorderButton(Icon image, int horizontalAlignment, Rectangle r) {
		super(image, horizontalAlignment);
		setOpaque(true);
		setBounds(r);
		setFocusable(false);
		
	}
	
	public CustomBorderButton(String text, Rectangle r) {
		super(text, JLabel.CENTER);
		setOpaque(true);
		setBounds(r);
		setFocusable(false);
	}
	
	abstract protected void addOperation();

}
