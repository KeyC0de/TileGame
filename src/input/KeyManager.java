package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
	// holds boolean values for all keycodes that may be pressed by the keyboard
	private boolean[] keys, justPressed, ableToPress;
	public boolean up, down, left, right;
	public boolean attackUp, attackDown, attackLeft, attackRight;
	//public boolean inventoryKeyPress;
	//public boolean questKeyPress;
	
	public KeyManager()
	{
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		ableToPress = new boolean[keys.length];
	}
	
	public void update()
	{
		// implement toggle key functionality
		for (int i = 0; i < keys.length; i++)
		{
			if (!ableToPress[i] && !keys[i])// wait until key is released before it can be pressed again
				ableToPress[i] = true;
			else if (justPressed[i])		// else if key was just pressed
			{
				ableToPress[i] = false;		// then we can't press it again
				justPressed[i] = false;
			}
			if (ableToPress[i] && keys[i])	// if key is pressed & it's able to register
				justPressed[i] = true;		// register that the key was just pressed
		}
		
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		attackUp = keys[KeyEvent.VK_UP];
		attackDown = keys[KeyEvent.VK_DOWN];
		attackLeft = keys[KeyEvent.VK_LEFT];
		attackRight = keys[KeyEvent.VK_RIGHT];
	}
	
	/*
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		
		keyTimer += System.currentTimeMillis() - lastKeyTimer;
		lastKeyTimer = System.currentTimeMillis();
		if (keyTimer < keyCooldown)
			return;
		
		switch (e.getKeyCode()) 
		{
			// A. check for a toggle key press
			case KeyEvent.VK_I:	// inventory key
				if (!inventoryKeyPress) 
					inventoryKeyPress = toggleKeys[KeyEvent.VK_I] = true;
				else 
					inventoryKeyPress = toggleKeys[KeyEvent.VK_I] = false;
				break;
			case KeyEvent.VK_Q:	// quest key
				if (!questKeyPress) 
					questKeyPress = toggleKeys[KeyEvent.VK_Q] = true;
				else 
					questKeyPress = toggleKeys[KeyEvent.VK_Q] = false;
				break;
			// B. check for a normal key press
			default:
				keys[e.getKeyCode()] = true;
				break;
		}
		
		keyTimer = 0;
	}*/
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		if (keyCode < 0 || keyCode >= keys.length)
			return;
		keys[keyCode] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		if (keyCode < 0 || keyCode >= keys.length)
			return;
		keys[keyCode] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	public boolean keyToggled(int keyCode) 
	{
		if (keyCode < 0 || keyCode >= keys.length)	// error checking
			return false;
		return justPressed[keyCode];
	}

}
