package toolbox;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

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

}
