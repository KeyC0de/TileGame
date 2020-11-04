package inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import graphics.Assets;
import graphics.Text;
import item.Item;
import utility.Handler;

public class Inventory {
	
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	// display properties
	private int x = 64, y = 48, 
			width = 512, height = 384;
	private int	invListCenterX = x + 171,	// center position of inventory list	 
				invListCenterY = y + height / 2 + 5;
	private int invListSpacing = 30;		// vertical spacing between each inventory item
	private int invImageX = 452, invImageY = 82;		// coords for item image
	private int invImageWidth = 64, invImageHeight = 64;
	private int invCountX = 484, invCountY = 174;
	private int selectedItem = 0;			// index of inventoryItems list
	
	public Inventory(Handler handler)
	{
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		// add some items to the inventory from start
		addItem(Item.rockItem.createNew(5));
		addItem(Item.woodItem.createNew(3));
	}
	
	public void update() 
	{
		// enable / disable inventory menu
		if (handler.getKeyManager().keyToggled(KeyEvent.VK_I))
			active = !active;
		
		// navigate the inventory items list
		if (handler.getKeyManager().keyToggled(KeyEvent.VK_W))
			selectedItem--;
		if (handler.getKeyManager().keyToggled(KeyEvent.VK_S))
			selectedItem++;
		
		// wrap around the list if need be
		if (selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if (selectedItem >= inventoryItems.size())
			selectedItem = 0;
	}
	
	public void render(Graphics g)
	{
		if (!active)
			return;
		
		// draw inventory menu
		g.drawImage(Assets.inventory, x, y, width, height, null);

		// draw items in inventory list menu
		int len = inventoryItems.size();
		if (len == 0)
			return;
		
		for (int i = -5; i < 6; i++)	// [-5,5]
		{
			if (selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			if (i == 0)
			{
				// emphasize the selected item
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", 
					invListCenterX, invListCenterY + i * invListSpacing, true, 
					Color.YELLOW, Assets.MorrisRoman);
			}
			else {
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), 
						invListCenterX, invListCenterY + i * invListSpacing, true, 
						Color.WHITE, Assets.MorrisRoman);
			}
		}

		// display item's image
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		// display item's count
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.MorrisRoman);
	}
	
	public void addItem(Item item)
	{
		// if the item already exists in our inventory just increase its count
		for (Item i : inventoryItems) {
			if (i.getId() == item.getId())
			{
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		// otherwise add the item to our inventory
		inventoryItems.add(item);
	}

	
	// GETTERS - SETTERS
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	public boolean isActive() {
		return active;
	}

}

