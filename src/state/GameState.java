package state;

import java.awt.Graphics;

import utility.Handler;
import world.World;

// Updates and Renders the Game State (our main state)

public class GameState extends State 
{
	private World world;
	
	public GameState(Handler handler)
	{
		super(handler);
		world = new World(handler, "resources/worlds/world2.txt");
	}

	@Override
	public void update() 
	{
		world.update();
	}

	@Override
	public void render(Graphics g) 
	{
		world.render(g);
	}

	
	// GETTERS - SETTERS
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
