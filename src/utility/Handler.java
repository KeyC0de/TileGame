package utility;

import entity.creature.Player;
import graphics.Camera;
import input.KeyManager;
import input.MouseManager;
import main.Game;
import ui.UIManager;
import world.World;

// Allows us to pass any game variables around in a uniform way by passing just one 
// object - a Handler object, instead of the Game, World and other objects

public class Handler 
{
	private Game game;
	private World level;
	private Player player;
	private UIManager uiManager;

	public Handler(Game game)
	{
		this.game = game;
	}
	
	
	// GETTERS
	
	public Camera getCamera()
	{
		return game.getCamera();
	}
	
	public KeyManager getKeyManager()
	{
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}
	
	public int getWindowWidth()
	{
		return game.getWindowWidth();
	}
	
	public int getWindowHeight()
	{
		return game.getWindowHeight();
	}
	
	public Game getGame() {
		return game;
	}
	
	public World getWorld() {
		return level;
	}

	public void setWorld(World World) {
		this.level = World;
	}
	
	public int getSpawnX() {
		return level.getSpawnX();
	}

	public int getSpawnY() {
		return level.getSpawnY();		
	}
	
	public UIManager getUiManager() {
		return uiManager;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
