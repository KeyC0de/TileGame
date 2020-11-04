package entity.creature;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Entity;
import graphics.Animation;
import graphics.Assets;
import inventory.Inventory;
import utility.Handler;

public class Player extends Creature
{
	Handler handler;
	
	// Animations
	private Animation animMoveDown, animMoveUp, animMoveRight, animMoveLeft;
	private BufferedImage playerIdleStance = Assets.playerIdleDown;	// player idle stance

	// Attack timer and cooldown
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	// Inventory
	private Inventory inventory;
	
	public Player(Handler handler, float x, float y) 
	{
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		handler.setPlayer(this);
		this.handler = handler;
		// specify collision box in pixels relative to tile
		hitbox.x = 16;
		hitbox.y = 24;
		hitbox.width = 32;
		hitbox.height = 40;
		
		// Animation sets
		animMoveDown = new Animation(500, Assets.playerMoveDown);
		animMoveUp = new Animation(500, Assets.playerMoveUp);
		animMoveRight = new Animation(500, Assets.playerMoveRight);
		animMoveLeft = new Animation(500, Assets.playerMoveLeft);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void update() 
	{
		// Proceed with the animations
		animMoveDown.update();
		animMoveUp.update();
		animMoveRight.update();
		animMoveLeft.update();
		
		// Movement
		getInput();
		move();
		handler.getCamera().centerOnEntity(this);
		
		// Combat
		checkAttacks();
		
		// inventory
		inventory.update();
	}
	
	private void checkAttacks()
	{
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)	// if cooldown hasn't passed yet don't attack
			return;
		
		if (inventory.isActive())
			return;
		
		Rectangle hitbox = getHitbox(0, 0);
		Rectangle ar = new Rectangle();	// attackRectangle
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		// determine upper left (x,y) coordinates
		if (handler.getKeyManager().attackUp) {
			ar.x = hitbox.x + hitbox.width / 2 - arSize / 2;
			ar.y = hitbox.y - arSize;
		}
		else if (handler.getKeyManager().attackDown) {
			ar.x = hitbox.x + hitbox.width / 2 - arSize / 2;
			ar.y = hitbox.y + hitbox.height;
		}
		else if (handler.getKeyManager().attackLeft) {
			ar.x = hitbox.x - arSize;
			ar.y = hitbox.y + hitbox.height / 2 - arSize / 2;
		}
		else if (handler.getKeyManager().attackRight) {
			ar.x = hitbox.x + hitbox.width;
			ar.y = hitbox.y + hitbox.height / 2 - arSize / 2;
		}
		else
			return;
		
		attackTimer = 0;	// reset attack timer
		
		for (Entity e : handler.getWorld().getEntityManager().getEntities())
		{
			if (e.equals(this))
				continue;
			if (e.getHitbox(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}
	}
	
	
	@Override
	public void render(Graphics g) 
	{
		g.drawImage(getPlayerFrame(), 
				(int)(x - handler.getCamera().getX()), 
				(int)(y - handler.getCamera().getY()), 
				width, height, null);
		
		/*
		// demonstrate collision box on the player
		g.setColor(Color.red);
		g.fillRect((int)(x + hitbox.x - handler.getCamera().getxOffset()),  
					(int)(y + hitbox.y - handler.getCamera().getyOffset()), 
					hitbox.width, 
					hitbox.height);
		*/
	}
	
	// draw the inventory after every other entity
	public void postRender(Graphics g)
	{
		inventory.render(g);
	}

	private void getInput()
	{
		xMove = 0;
		yMove = 0;
		
		if (inventory.isActive())
			return;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}
	
	// retuns proper sprite depending on Player action and orientation 
	private BufferedImage getPlayerFrame()
	{
		if (xMove > 0) {
			playerIdleStance = Assets.playerIdleRight;
			return animMoveRight.getAnimationFrame();
		}
		else if (xMove < 0) {
			playerIdleStance = Assets.playerIdleLeft;
			return animMoveLeft.getAnimationFrame();
		}
		else if (yMove > 0) {
			playerIdleStance = Assets.playerIdleDown;
			return animMoveDown.getAnimationFrame();
		}
		else if (yMove < 0) {
			playerIdleStance = Assets.playerIdleUp;
			return animMoveUp.getAnimationFrame();
		}
		else 
			return playerIdleStance;
	}

	@Override
	public void die() {
		System.out.println("WASTED!");
		System.out.println("GAME OVER");
	}
	
	
	// GETTERS - SETTERS 
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
