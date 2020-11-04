package world;

import java.awt.Graphics;

import entity.EntityManager;
import entity.creature.Player;
import entity.staticEntity.Rock;
import entity.staticEntity.Tree;
import item.ItemManager;
import tile.Tile;
import utility.Handler;
import utility.Utils;

// Worlds are our worlds, our terrain
public class World 
{
	private Handler handler;
	private int mapWidth, mapHeight;	// in units of tiles
	private int spawnX, spawnY;	// player's spawn coordinates
	private int[][] tiles;		// holds world's data (in units of tiles)
	
	// Entities
	private EntityManager entityManager;
	
	// Items
	private ItemManager itemManager;
	
	// initialize the world and all entities from map file
	public World(Handler handler, String path)
	{
		handler.setWorld(this);
		this.handler = handler;
		loadWorld(path);
		entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY));
		itemManager = new ItemManager(handler);
		entityManager.addEntity(new Tree(handler, 200, 250));
		entityManager.addEntity(new Tree(handler, 200, 194));
		entityManager.addEntity(new Tree(handler, 200, 130));
		entityManager.addEntity(new Rock(handler, 200, 66));
		entityManager.addEntity(new Rock(handler, 300, 200));
	}
	
	public void update()
	{
		itemManager.update();
		entityManager.update();
	}
	
	// renders view "frustum"
	public void render(Graphics g)
	{
		int xVisibleStart = (int) Math.max(0, handler.getCamera().getX() 
								/ Tile.TILE_WIDTH);
		int xVisibleEnd = (int) Math.min(mapWidth, (handler.getCamera().getX() 
								+ handler.getWindowWidth()) / Tile.TILE_WIDTH + 1);
		int yVisibleStart = (int) Math.max(0, handler.getCamera().getY() 
								/ Tile.TILE_HEIGHT);
		int yVisibleEnd = (int) Math.min(mapHeight, (handler.getCamera().getY() 
								+ handler.getWindowHeight()) / Tile.TILE_HEIGHT + 1);
		
		for (int y = yVisibleStart; y < yVisibleEnd; y++) {
			for (int x = xVisibleStart; x < xVisibleEnd; x++) 
			{
				getTile(x, y).render(g, 
						(int)(x * Tile.TILE_WIDTH - handler.getCamera().getX()), 
						(int)(y * Tile.TILE_HEIGHT - handler.getCamera().getY()));
			}
		}
		
		itemManager.render(g);
		entityManager.render(g);
	}
	
	// get a tile on the world / map
	public Tile getTile(int x, int y)
	{
		if (x < 0 || y < 0 || x >= mapWidth || y >= mapHeight)
			return Tile.waterTile;
		
		Tile tile = Tile.tiles[tiles[x][y]];
		if (tile == null)
			return Tile.waterTile;
		return tile;
	}
	
	// reading the world from a file.
	// Each token represents a game's parameter
	private void loadWorld(String path)
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");	// split file in tokens of separate numbers
		mapWidth = Utils.parseInt(tokens[0]);
		mapHeight = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[mapWidth][mapHeight];
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * mapWidth) + 4]); // we already parsed the first 4 data
			}
		}
		
	}
	
	
	// GETTERS - SETTERS
	
	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}
	
	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public Handler getHandler() {
		return handler;
	}
	
}
