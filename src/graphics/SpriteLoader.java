package graphics;

import java.awt.image.BufferedImage;

public class SpriteLoader 
{	
	private BufferedImage sheet;
	
	public SpriteLoader(BufferedImage sheet)
	{
		this.sheet = sheet;
	}
	
	// crop sprite sheet into individual component sprites
	public BufferedImage crop(int x, int y, int w, int h)
	{
		return sheet.getSubimage(x, y, w, h);
	}
}
