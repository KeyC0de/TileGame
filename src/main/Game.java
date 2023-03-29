package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import graphics.Assets;
import graphics.Camera;
import input.KeyManager;
import input.MouseManager;
import state.GameState;
import state.MenuState;
import state.State;
import utility.Display;
import utility.FrameTimer;
import utility.Handler;


public class Game implements Runnable 
{
	private Display display;
	private int width;
	private int height;
	private float aspectRatio;
	public String title;
	
	private boolean running;
	private Thread thread;
	
	// specifies how frame buffers are handled
	private BufferStrategy bs;
	// performs actual drawing - g is our magical paintbrush
	private Graphics g;
	
	//private int x = 0;
	//private BufferedImage testImage;
	//private SpriteSheet sheet;
	
	// Game States
	public State gameState;
	private State menuState;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	// Camera
	private Camera camera;
	
	// Utils
	private Handler handler;
	//private FrameTimer ft;
	
	public Game(String title, int h)
	{
		this.title = title;
		height = h;
		aspectRatio = (float) (4 / 3.0);
		width = (int)(h * aspectRatio);
		running = false;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	// Initialize all our game objects
	private void init()
	{
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		//ft = new FrameTimer();
		camera = new Camera(handler, 0, 0);
		gameState = new GameState(this.handler);
		menuState = new MenuState(this.handler);
		State.setState(gameState);
		
		// examples:
		//testImage = ImageLoader.loadImage("/sprites/spriteSheet.png");
	}
	
	// updating variables, states etc //
	private void update()
	{
		keyManager.update();
		
		if (State.getCurrentState() != null)
			State.getCurrentState().update();	
	}
	
	// drawing graphics onto the screen //
	private void render()
	{
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		// acquire graphics context
		g = bs.getDrawGraphics();
		// Clear Screen first
		g.clearRect(0, 0, width, height);
		// Start Drawing
		
		//g.setColor(Color.red);
		//g.fillRect(10, 50, 50, 70);
		//g.setColor(Color.green);
		//g.fillRect(0, 0, 10, 10);
		
		// draws a sausage like dog
//		g.setColor(Color.gray);
//		g.fillRect(30, 50, 80, 15);
//		g.fillRect(20, 55, 10, 5);
//		g.fillRect(30, 60, 7, 15);
//		g.fillRect(39, 60, 5, 15);
//		g.fillRect(98, 60, 5, 15);
//		g.fillRect(105, 60, 5, 15);
//		g.fillRect(105, 40, 13, 13);
		
		// draw image
//		g.drawImage(testImage, 0, 0, null);
//		
//		// draw sprites from spriteSheet
//		g.drawImage(Assets.player, 200, 200, null);
//		g.drawImage(Assets.nails, 232, 232, null);
//		g.drawImage(Assets.water, 264, 264, null);
//		g.drawImage(Assets.happy, 296, 296, null);
		
		//g.drawImage(Assets.water, 10+x, 10, null);
		
		// Invoke State's render method
		if (State.getCurrentState() != null)
			State.getCurrentState().render(g);
		
		// End Drawing
		bs.show();
		g.dispose();	// dispose of the graphics context
	}
	
	
	/*public void run()
	{
		init(); // initializes everything we'll need to use for rendering
		
		int fps = 30;				// theoretical fps
		double msPerLoop = 1000 / fps;
		//double deltaTime = 0.0f;
		double frameTime;
		double lastFrameTime = ft.getLastFrameTime();		// gets current time
		double elapsedTime = 0.0f;
		double remainingTime;
		int timesThroughLoop = 0;	// actual FPS
		
		/// GAME LOOP //
		while(running)
		{
			frameTime = ft.getLastFrameTime();
			elapsedTime += frameTime - lastFrameTime;
			//deltaTime += (frameTime - lastFrameTime) / nsPerLoop;
			lastFrameTime = frameTime;
			//System.out.println("now=" + frameTime + " , lastFrameTime=" + lastFrameTime);
			
			//if (deltaTime >= 1) 		// don't enter loop unless suffient amount of time has passed
			//{
				update();
				render();
				timesThroughLoop++;
			//	deltaTime--;
			//}
			remainingTime = msPerLoop - frameTime;
			if (remainingTime > 0)
			{
				try {
				    Thread.sleep((long)remainingTime);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}	
				
			}
			
			// FPS counter: if 1 second passed display FPS
			if (elapsedTime >= 1000)
			{
				System.out.println("FPS: " + timesThroughLoop);
				timesThroughLoop = 0;
				elapsedTime = 0;
			}
		}
		
		stop();
	}*/
	
	
	public void run()
	{
		init(); // initializes everything we'll need to use for rendering
		
		int fps = 30;							// theoretical fps
		double nsPerLoop = 1000000000 / fps;	// nanosecs taken per loop run
		double deltaTime = 0;
		long now;
		long lastFrameTime = System.nanoTime();	// gets current time
		long elapsedTIme = 0;				
		int timesThroughLoop = 0;				// actual FPS
		
		/// GAME LOOP ///
		while(running)
		{
			now = System.nanoTime();
			elapsedTIme += now - lastFrameTime;
			deltaTime += (now - lastFrameTime) / nsPerLoop;
			lastFrameTime = now;
			//System.out.println("now=" + now + " , lastFrameTime=" + lastFrameTime);
			
			if (deltaTime >= 1) 		// don't enter loop unless suffient amount of time has passed
			{
				update();
				render();
				timesThroughLoop++;
				deltaTime--;
			}
			
			// FPS counter: if 1 second passed display
			if (elapsedTIme >= 1000000000)
			{
				//System.out.println("Ticks / Frames per second: " + timesThroughLoop);
				timesThroughLoop = 0;
				elapsedTIme = 0;
			}
		}
		
		stop();
	}
	
	
	public synchronized void start()
	{
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();	// calls run()
	}
	
	public synchronized void stop()
	{
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	// GETTERS - SETTERS

	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	
	public Camera getCamera()
	{
		return camera;
	}
	
	public int getWindowWidth()
	{
		return width;
	}

	public int getWindowHeight()
	{
		return height;
	}
	
	public State getGameState() {
		return gameState;
	}

	public State getMenuState() {
		return menuState;
	}

}

