package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import utility.Handler;

public class UIManager {

	public Handler handler;
	private ArrayList<UIObject> objects;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	public void update()
	{
		for (UIObject o : objects)
			o.update();
	}
	
	public void render(Graphics g)
	{
		for (UIObject o : objects)
			o.render(g);
	}
	
	public void onMouseMove(MouseEvent e)
	{
		for (UIObject o : objects)
			o.onMouseMove(e);
	}
	
	public void onMouseRelease(MouseEvent e)
	{
		for (UIObject o : objects)
			o.onMouseRelease(e);
	}
	
	public void addObject(UIObject o) 
	{
		objects.add(o);
	}
	
	public void removeObject(UIObject o)
	{
		objects.remove(o);
	}
	
	
	// GETTERS - SETTERS
	
	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}
