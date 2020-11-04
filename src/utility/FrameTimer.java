package utility;

public class FrameTimer {
	private long now;
	
	public FrameTimer()
	{
		now = System.nanoTime();
	}
	
	public double getLastFrameTime()
	{
		long before = now;
		now = System.nanoTime();
		double frameTime = (now - before) % 1000000;
		//System.out.println(frameTime);
		return frameTime;
	}
}
