package tile;

import graphics.Assets;

public class TreeTile extends Tile
{
	
	public TreeTile(int id)
	{
		super(Assets.tree, id);
	}
	
	@Override
	public boolean isPassable()
	{
		return true;
	}
	
}
