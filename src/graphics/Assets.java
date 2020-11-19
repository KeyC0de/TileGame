package graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets 
{
	private static final int width = 32, height = 32;
	private static final int itemWidth = 32, itemHeight = 32;
	private static final int inventoryWidth = 512, inventoryHeight = 384;
	
	public static BufferedImage[] playerMoveUp, playerMoveDown, playerMoveRight, playerMoveLeft;
	public static BufferedImage playerIdleUp, playerIdleDown, playerIdleRight, playerIdleLeft;
	public static BufferedImage[] grass;
	public static BufferedImage[] rock;
	public static BufferedImage tree;
	public static BufferedImage[] water;
	public static BufferedImage[] road;
	public static BufferedImage[] enemy;
	public static BufferedImage[] startButton;
	public static BufferedImage woodItem;
	public static BufferedImage rockItem;
	public static BufferedImage inventory;
	
	public static Font MorrisRoman;
	
	public static void init()
	{
		SpriteLoader playerSheet = new SpriteLoader(ImageLoader.loadImage
("resources/sprites/player.png"));
		SpriteLoader terrainSheet = new SpriteLoader(ImageLoader.loadImage
("resources/sprites/terrain.png"));
		//SpriteLoader enemiesSheet = new SpriteLoader(ImageLoader.loadImage
//("resources/sprites/enemies.png"));
		SpriteLoader startButton1Sheet = new SpriteLoader(ImageLoader.loadImage
("resources/sprites/startButton1.png"));
		SpriteLoader startButton2Sheet = new SpriteLoader(ImageLoader.loadImage
("resources/sprites/startButton2.png"));
		SpriteLoader itemSheet = new SpriteLoader(ImageLoader.loadImage
("resources/sprites/itemsSprite.png"));
		SpriteLoader inventorySheet = new SpriteLoader(ImageLoader.loadImage
("resources/sprites/inventorySprite.png"));
		
		playerMoveUp = new BufferedImage[2];
		playerMoveDown = new BufferedImage[2];
		playerMoveRight = new BufferedImage[2];
		playerMoveLeft = new BufferedImage[2];
		
		playerIdleDown = playerSheet.crop(0, 0, width, height);
		playerIdleUp = playerSheet.crop(width, 0, width, height);
		playerIdleRight = playerSheet.crop(2 * width, 0, width, height);
		playerIdleLeft = playerSheet.crop(0, height, width, height);
		playerMoveUp[0] = playerSheet.crop(0, 2 * height, width, height);
		playerMoveUp[1] = playerSheet.crop(width, 2 * height, width, height);
		playerMoveDown[0] = playerSheet.crop(2 * width, height, width, height);
		playerMoveDown[1] = playerSheet.crop(3 * width, height, width, height);
		playerMoveRight[0] = playerSheet.crop(3 * width, 0, width, height);
		playerMoveRight[1] = playerSheet.crop(2 * width, 2 * height, width, height);
		playerMoveLeft[0] = playerSheet.crop(width, height, width, height);
		playerMoveLeft[1] = playerSheet.crop(3 * width, 2 * height, width, height);
		
		grass = new BufferedImage[2];
		rock = new BufferedImage[2];
		water = new BufferedImage[2];
		road = new BufferedImage[2];
		
		grass[0] = terrainSheet.crop(0, 0, width, height);
		grass[1] = terrainSheet.crop(width, 0, width, height);
		rock[0] = terrainSheet.crop(2 * width, 0, width, height);
		rock[1] = terrainSheet.crop(3 * width, 0, width, height);
		tree = terrainSheet.crop(0, height, width, height);
		water[0] = terrainSheet.crop(2 * width, height, width, height);
		water[1] = terrainSheet.crop(3 * width, height, width, height);
		road[0] = terrainSheet.crop(0, 2 * height, width, height);
		road[1] = terrainSheet.crop(width, 2 * height, width, height);
		
		//enemy = new BufferedImage[8];
//
		//enemy[0] = enemiesSheet.crop(0, 0, width, height);
		//enemy[1] = enemiesSheet.crop(width, 0, width, height);
		//enemy[2] = enemiesSheet.crop(0, height, width, height);
		//enemy[3] = enemiesSheet.crop(width, height, width, height);
		//enemy[4] = enemiesSheet.crop(2 * width, height, width, height);
		//enemy[5] = enemiesSheet.crop(3 * width, height, width, height);
		//enemy[6] = enemiesSheet.crop(0, 2 * height, width, height);
		//enemy[7] = enemiesSheet.crop(width, 2 * height, width, height);
		
		startButton = new BufferedImage[2];
		
		startButton[0] = startButton1Sheet.crop(0, 0, 60, 30);
		startButton[1] = startButton2Sheet.crop(0, 0, 60, 30);
		
		rockItem = itemSheet.crop(0, 0, itemWidth, itemHeight);
		woodItem = itemSheet.crop(32, 0, itemWidth, itemHeight);
		
		inventory = inventorySheet.crop(0, 0, inventoryWidth, inventoryHeight);
		
		MorrisRoman = FontLoader.loadFont
			("resources/fonts/MorrisRoman-Black.ttf", 28);
	}
	
}
