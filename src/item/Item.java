package item;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.Assets;
import utility.Handler;

// Similar to Tile class

public class Item {

	protected Handler handler;
	
	public static Item[] items = new Item[256];
	public static Item woodItem = new Item(0, "Wood", Assets.woodItem);
	public static Item rockItem = new Item(1, "Rock", Assets.rockItem);
	public static final int ITEM_WIDTH = 16, ITEM_HEIGHT = 16;
	protected final int id;
	protected String name;
	protected BufferedImage texture;
	protected int x, y;				// window coordinates of item
	protected int count;			// each item will contain a "stack" of items
	protected boolean pickedUp = false;
	protected Rectangle hitbox;
	
	public Item(int id, String name, BufferedImage texture)
	{
		this.id = id;
		this.name = name;
		this.texture = texture;
		count = 1;
		
		items[id] = this;
		hitbox = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);
	}
	
	public void update()
	{
		if (handler.getPlayer().getHitbox(0f, 0f).intersects(hitbox))
		{
			pickedUp = true;
			handler.getPlayer().getInventory().addItem(this);
		}
	}
	

	public void render(Graphics g)
	{
		if (handler == null)
			return;
		render(g, (int)(x - handler.getCamera().getX()), 
				(int)(y - handler.getCamera().getY()));
	}
	
	// creates a copy of this Item class
	public Item createNew(int x, int y)
	{
		Item i = new Item(id, name, texture);
		i.setPosition(x, y);
		return i;
	}
	
	// for debugging purposes only
	public Item createNew(int count)
	{
		Item i = new Item(id, name, texture);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
	
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		hitbox.x = x;
		hitbox.y = y;
	}
	
	// GETTERS - SETTERS
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
	}
	
	public boolean isPickedUp() {
		return pickedUp;
	}
	
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

}
