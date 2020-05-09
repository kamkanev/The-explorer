package toolbox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Fonts {
	
	public static Font loadFont(String name, int style, float size) throws FontFormatException, IOException {
		String fName = "res/fonts/"+name+".ttf";
	    Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fName)).deriveFont(style, size);
	    GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fName)));
	    
	    return font;
	}
	
	public static Font loadFont(String name, float size) throws FontFormatException, IOException {
		String fName = "res/fonts/"+name+".ttf";
	    Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fName)).deriveFont(Font.PLAIN, size);
	    GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fName)));
	    
	    return font;
	}
	
	public static Font loadFont(String name) throws FontFormatException, IOException {
		String fName = "res/fonts/"+name+".ttf";
	    Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fName)).deriveFont(12);
	    GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fName)));
	    
	    return font;
	}
	
	public static Font loadFont(String name, int style) throws FontFormatException, IOException {
		String fName = "res/fonts/"+name+".ttf";
	    Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fName)).deriveFont(style, 12);
	    GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fName)));
	    
	    return font;
	}
	
	public static float getFontSizeByScreen(JFrame jf, int devider) {
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = jf.getWidth();
		double height = jf.getHeight();
		
		float size = (float) (width / devider);
		
		return size;
	}
	
	public static float getFontSizeByComponetSize(JComponent jc, int devider) {
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = jc.getWidth();
		double height = jc.getHeight();
		
		float size = (float) (width / devider);
		
		return size;
	}

}
