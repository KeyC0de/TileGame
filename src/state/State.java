package state;

import java.awt.Graphics;

import utility.Handler;

// A game has a few states - Game, Main Menu, Settings
public abstract class State 
{
	private static State currentState = null;	// switches between states
	protected Handler handler;
	
	public State(Handler handler)
	{
		this.handler = handler;
	}
	
	// STATICS
	public static void setState(State state)
	{
		currentState = state;
	}
	
	public static State getCurrentState()
	{
		return currentState;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
}
