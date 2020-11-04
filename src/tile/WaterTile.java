package tile;

import graphics.Assets;

public class WaterTile extends Tile 
{
	
	public WaterTile(int id)
	{
		super(Assets.water[0], id);
	}
	
	public boolean isPassable() 
	{
		return true;
	}
	
}
