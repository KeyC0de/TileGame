package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import utility.Handler;

import entity.creature.Player;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> entitySorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) 
		{
			if (a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player)
	{
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	public void update()
	{
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext())
		{
			Entity e = it.next();
			e.update();
			// check if entity is still alive
			if (!e.isActive())
				it.remove();
		}
	}
	
	public void render(Graphics g)
	{
		for (Entity e : entities)
		{
			e.render(g);
		}
		player.postRender(g);	// render inventory
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	
	// GETTERS - SETTERS
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
