package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile 
{
	// STATICS
	public static Tile[] tiles = new Tile[256];	// will hold the tiles in the level
	public static Tile grass1Tile = new GrassTile(0);
	public static Tile grass2Tile = new GrassTile(1);
	public static Tile rockTile = new RockTile(2);
	public static Tile treeTile = new TreeTile(3);
	public static Tile waterTile = new WaterTile(4);
	public static Tile road1Tile = new RoadTile(5);
	public static Tile road2Tile = new RoadTile(6);
	
	// CLASS
	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void update()
	{
		
	}
	
	// Tile gets a position of the screen
	public void render(Graphics g, int x, int y)
	{
		// draws each PIXEL
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	// determines whether it is walkable
	public boolean isPassable()
	{
		return false;
	}
	
	public int getId()
	{
		return id;
	}
}
