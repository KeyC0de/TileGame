package tile;

import graphics.Assets;

public class RockTile extends Tile
{
	
	public RockTile(int id)
	{
		super(Assets.rock[0], id);
	}
	
	@Override
	public boolean isPassable()
	{
		return true;
	}
	
}
