package item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import utility.Handler;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> items;
	
	public ItemManager(Handler handler) {
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	public void update()
	{
		Iterator<Item> it = items.iterator();
		while (it.hasNext()) {
			Item i = it.next();
			i.update();
			// if item has been picked up remove it from the game world
			if (i.isPickedUp())
				it.remove();
		}
	}
	
	public void render(Graphics g) 
	{
		for (Item i : items)
			i.render(g);
	}
	
	public void addItem(Item i)
	{
		i.setHandler(handler);
		items.add(i);
	}
	
	// GETTERS - SETTERS
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
