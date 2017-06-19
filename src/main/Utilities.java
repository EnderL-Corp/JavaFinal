package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Static methods for image overlay. Images must be in JavaFinal/Sprites/.
 * 
 * @author Srihari Subramanian
 * 
 */
public class Utilities {
	
	/**
	 * Will return an ImageIcon of an overlay of Sprites/(imageA) and Sprites/(imageB)
	 * @param imageA the String representing the image to overlay upon
	 * @param imageB the String representing the image to overlay
	 * @return the final image
	 */
	public static ImageIcon combineImages(String imageA, String imageB) {
		BufferedImage image = null;
		BufferedImage overlay = null;
		
		try {
			image = ImageIO.read(new File("Sprites/" + imageA));
			overlay = ImageIO.read(new File("Sprites/" + imageB));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int w = Math.max(image.getWidth(), overlay.getWidth());
		int h = Math.max(image.getHeight(), overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics g = combined.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 0, 0, null);

		return new ImageIcon(combined);
	}

	/**
	 * Will return an ImageIcon of an overlay of imageA and Sprites/(imageB)
	 * @param imageA the ImageIcon representing the image to overlay upon
	 * @param imageB the String representing the image to overlay
	 * @return the final image
	 */
	public static ImageIcon combineImages(ImageIcon imageA, String imageB) {
		BufferedImage image = null;
		BufferedImage overlay = null;
		
		try {
			Image img = imageA.getImage();
			image = (BufferedImage) image;
			overlay = ImageIO.read(new File("Sprites/" + imageB));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int w = Math.max(imageA.getIconWidth(), overlay.getWidth());
		int h = Math.max(imageA.getIconHeight(), overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics g = combined.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 0, 0, null);

		return new ImageIcon(combined);
	}
}
