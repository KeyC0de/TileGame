package graphics;

import entity.Entity;
import tile.Tile;
import utility.Handler;

public class Camera 
{
	private Handler handler;
	private float x, y;	// absolute coords of camera location in the world
			// better said, after a single camera movement all others pixels acquire 
			// (-x, -y) coordinates
	
	public Camera(Handler handler, float x, float y)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
	}
	
	// check if camera is displaying map out of boundaries and if so adjust display
	public void checkForMapBounds()
	{
		// prevent viewing left map bounds
		if (x < 0) {
			x = 0;
		}
		// prevent viewing right map bounds
		if (x > handler.getWorld().getMapWidth() * Tile.TILE_WIDTH - 
				handler.getWindowWidth()) 
		{
			x = handler.getWorld().getMapWidth() * Tile.TILE_WIDTH - 
					handler.getWindowWidth();
		}
		
		// prevent viewing top map bounds
		if (y < 0) {
			y = 0;
		}
		// prevent viewing bottom map bounds
		else if (y > handler.getWorld().getMapHeight() * Tile.TILE_HEIGHT 
				- handler.getWindowHeight())
		{
			y = handler.getWorld().getMapHeight() * Tile.TILE_HEIGHT 
					- handler.getWindowHeight();
		}
		
	}
	
	// center camera on player
	public void centerOnEntity(Entity e)
	{
		x = e.getX() - handler.getWindowWidth()  / 2 + e.getWidth()  / 2;
		y = e.getY() - handler.getWindowHeight() / 2 + e.getHeight() / 2;
		//System.out.println("x = " + e.getX() + " y = " + e.getY());
		checkForMapBounds();
	}
	
	// move camera
	public void move(float xAmt, float yAmt)
	{
		x += xAmt;
		y += yAmt;
		checkForMapBounds();
	}
	
	
	// GETTERS - SETTERS

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
