package entity.staticEntity;

import java.awt.Graphics;

import graphics.Assets;
import item.Item;
import tile.Tile;
import utility.Handler;

public class Tree extends StaticEntity {
	
	public Tree(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		
		hitbox.x = 10;
		hitbox.y = (int) (height / 1.5f);
		hitbox.width = width - 20;
		hitbox.height = (int) (height - height / 1.5f);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int)(x - handler.getCamera().getX()), 
				(int)(y - handler.getCamera().getY()), width, height, null);
	}

	@Override
	public void die() {
		// spawn an item after the tree dies
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)x, (int)y));
	}

}
