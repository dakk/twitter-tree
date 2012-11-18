package dgsf.twitterTreeSwing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageComponent extends JPanel 
{
	private BufferedImage image;
	
	public ImageComponent(String path) throws IOException
	{
		this.image = ImageIO.read(new File(path));
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(this.image, 0, 0, null);
	}

}
