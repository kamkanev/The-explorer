package toolbox;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Images {
	
	public static Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	public static void setBackground(JFrame jf,String name, boolean isFull) {
		ImageIcon img = null;
		
		if(isFull) {
			img = new ImageIcon(getScaledImage(new ImageIcon("res/backgrounds/"+name).getImage(), jf.getWidth(), jf.getHeight()));
		}else {
			img = new ImageIcon("res/backgrounds/"+name);
		}
		
		JLabel background = new JLabel("", img, JLabel.CENTER);
		background.setName("back");
		background.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		
		
		jf.add(background);
		
	}
	
	public static void setBackground(JComponent jc,String name) {
		ImageIcon img = new ImageIcon("res/backgrounds/"+name);
		
		JLabel background = new JLabel("", img, JLabel.CENTER);
		background.setName("back");
		background.setBounds(0, 0, jc.getWidth(), jc.getHeight());
		
		
		jc.add(background);
		
	}

}
