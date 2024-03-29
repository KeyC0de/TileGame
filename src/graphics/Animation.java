package graphics;

import java.awt.image.BufferedImage;

public class Animation 
{
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames)
	{
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void update()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		// change pose (animation frame) if enough time has passed
		if (timer > speed)
		{
			index++;
			timer = 0;
			if (index >= frames.length)
				index = 0;
		}
	}
	
	public BufferedImage getAnimationFrame()
	{
		return frames[index];
	}
	
}
