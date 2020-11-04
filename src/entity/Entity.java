package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import utility.Handler;


public abstract class Entity 
{
	public static final int DEFAULT_HEALTH = 3;
	
	protected Handler handler;
	protected float x, y;			// window coordinates of entity
	protected int width, height;	// dimensions of entity
	protected Rectangle hitbox;		// the collision box
	protected int health;
	protected boolean active = true;
	
	public Entity(Handler handler, float x, float y, int w, int h)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		health = DEFAULT_HEALTH;
		hitbox = new Rectangle(0, 0, width, height); // there will be a collision box for each tile
	}

	public abstract void update();
	public abstract void render(Graphics g);
	
	// checking for collision between game entities
	// VERY INEFFICIENT METHOD
	public boolean checkEntityCollisions(float xOffset, float yOffset)
	{
		//Iterator<Entity> it = handler.getWorld().getEntityManager().getEntities();
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) 
		{
			// not checking collisions against itself!
			if (e.equals(this))
				continue;
			
			if (e.getHitbox(0f, 0f).intersects(getHitbox(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	// returns a new rectangle that represents the hitbox of the entity
	// the offset values are there if we want to offset the hitbox boundaries by a small amount
	public Rectangle getHitbox(float xOffset, float yOffset) 
	{
		return new Rectangle((int) (x + hitbox.x + xOffset), 
				(int)(y + hitbox.y + yOffset), hitbox.width, hitbox.height);
	}
	
	public void hurt(int amt) {
		health -= amt;
		if (health <= 0) {
			this.active = false;
			die();
		}
	}
	
	// Each entity dies differently
	public abstract void die();
	
	
	// SETTERS - GETTERS

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

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
