package main;

// Launches the game

public class Launcher
{
	private static int height; 
	
	public static void main(String[] args)
	{
		
		if (args.length == 0)
		{
			height = 480;
		}
		
		Game game = new Game("Tile Game!", height);
		game.start();
	}
	
}

