package state;

import java.awt.Graphics;

import graphics.Assets;
import ui.UIImageButton;
import ui.UIManager;
import utility.Handler;
import ui.UIListener;

public class MenuState extends State 
{
	private UIManager uiManager;

	public MenuState(Handler handler)
	{
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(this.uiManager);
		
		// adding UIObjects here
		uiManager.addObject(new UIImageButton(288.0f, 224.0f, 64, 32, Assets.startButton, 
			new UIListener() {
				// destroy the UIManager so it doesn't update & render anymore
				@Override
				public void onClick() {
					handler.getMouseManager().setUIManager(null);
					State.setState(handler.getGame().gameState);
				}
			}
		));
	}
	
	@Override
	public void update() 
	{
		uiManager.update();
		// if both mouse buttons are pressed switch to the game state
		if (handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed())
			State.setState(handler.getGame().getGameState());
		
		/*// temporarily just go directly to the GameState, skip the MenuState!
		handler.getMouseManager().setUIManager(null);
		State.setState(handler.getGame().gameState);*/
	}

	@Override
	public void render(Graphics g) 
	{
		uiManager.render(g);
		/*
		g.setColor(Color.RED);
		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 50, 50);
		*/
	}

}
