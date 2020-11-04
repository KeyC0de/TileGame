package entity.staticEntity;

import java.awt.Graphics;

import graphics.Assets;
import item.Item;
import tile.Tile;
import utility.Handler;

public class Rock extends StaticEntity
{
	
	public Rock(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		
		hitbox.x = 3;
		hitbox.y = (int) (height / 2f);
		hitbox.width = width - 6;
		hitbox.height = (int) (height - height / 2f);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock[0], (int)(x - handler.getCamera().getX()), 
				(int)(y - handler.getCamera().getY()), width, height, null);
	}

	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int)x, (int)y));
	}

}
