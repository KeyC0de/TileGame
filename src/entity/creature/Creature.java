package entity.creature;

import entity.Entity;
import tile.Tile;
import utility.Handler;

public abstract class Creature extends Entity 
{
	protected static final float DEFAULT_SPEED = 4.0f;
	protected static final int DEFAULT_CREATURE_WIDTH = 64,
								DEFAULT_CREATURE_HEIGHT = 64;
	protected float speed;			// "step" of displacement
	protected float xMove, yMove;	// position displacements
	
	public Creature(Handler handler, float x, float y, int w, int h) 
	{
		super(handler, x, y, w, h);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	// a Creature (movable) type entity must check for collision detection before it can move 
	public void move()
	{
		if (!checkEntityCollisions(xMove, 0f))
			moveX();
		if (!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	// moves on the x-axis
	public void moveX()
	{
		if (xMove > 0) { // Moving right
			int tx = (int)(x + xMove + hitbox.x + hitbox.width) / Tile.TILE_WIDTH;
			
			if (!checkForCollisionWithTile(tx, 
					(int)(y + hitbox.y) / Tile.TILE_HEIGHT) &&					// check upper right corner of bounding box
				!checkForCollisionWithTile(tx, 
					(int)(y + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT))// check lower right corner of bounding box
			{	// if there's no collision object on our way, its good to move
				x += xMove;
			}
			/*else {
				x = tx * Tile.TILE_WIDTH - hitbox.x - hitbox.width - 1;
			}*/
		} 
		else if (xMove < 0) { // moving left
			int tx = (int)(x + xMove + hitbox.x) / Tile.TILE_WIDTH;
			
			if (!checkForCollisionWithTile(tx, 
					(int)(y + hitbox.y) / Tile.TILE_HEIGHT) &&
				!checkForCollisionWithTile(tx, 
					(int)(y + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT))
			{
				x += xMove;
			}
			/*else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - hitbox.x;
			}*/
		}
	}
	
	// moves on the y-axis
	public void moveY()
	{
		if (yMove < 0) { // Moving Up
			int ty = (int)((y + 8) / Tile.TILE_HEIGHT);
			
			if (!checkForCollisionWithTile((int)((x + hitbox.x) / Tile.TILE_WIDTH), ty) &&
				!checkForCollisionWithTile((int)((x + hitbox.x + hitbox.width) / Tile.TILE_WIDTH), ty) )
			{
				y += yMove;
			}
			/*else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - hitbox.y;
			}*/
		}
		else if (yMove > 0) { // Moving Down
			int ty = (int)((y + yMove + hitbox.y + hitbox.height)/ Tile.TILE_HEIGHT); 
			
			if (!checkForCollisionWithTile((int)((x + hitbox.x) / Tile.TILE_WIDTH), ty) &&
					!checkForCollisionWithTile((int)((x + hitbox.x + hitbox.width) / Tile.TILE_WIDTH), ty) )
			{
					y += yMove;
			}
			else {
				y = ty * Tile.TILE_HEIGHT - hitbox.y - hitbox.height - 1;
			}
		}
		
	}
	
	// determines whether the player has collided with a solid tile
	protected boolean checkForCollisionWithTile(int x, int y)
	{
		return handler.getWorld().getTile(x, y).isPassable();
	}
	
	
	// GETTERS AND SETTERS
	
	public float getxMove() {
		return xMove;
	}
	
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	
	public float getyMove() {
		return yMove;
	}
	
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
}
