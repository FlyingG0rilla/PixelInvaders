package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.InputHandler;

public class Player extends Entity{

	GamePanel gp;
	InputHandler input;
	
	private int xOffset;
	private int yOffset;
	
	private int projectileXOffset;
	private int projectileYOffset;
	
	public int currentPosX;
	public int currentPosY;
	
	public int health;
	public BufferedImage health0, health1, health2, health3;
	
	public Player(GamePanel gp, InputHandler input) {
		
		this.gp = gp;
		this.input = input;

		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		x = 352;
		y = 952;
		
		xOffset = x;
		yOffset = y + gp.tileSize;
		
		speed = 14;
		direction = "up";
		
		canMoveLeft = true;
		canMoveRight = true;
		canMoveDown = true;
		canMoveUp = true;
		
		canShoot = true;
		
		health = 3;
	}
	
	public void getPlayerImage() {
		
		try {
			
			character = ImageIO.read(getClass().getResourceAsStream("/player/SpielerRaumschiff_1_Pixel_128.png"));
			
			exhaust1 = ImageIO.read(getClass().getResourceAsStream("/player/SpielerRaumschiff_Auspuff_1.png"));
			
			exhaust2 = ImageIO.read(getClass().getResourceAsStream("/player/SpielerRaumschiff_Auspuff_2.png"));
			
			placeHolder = ImageIO.read(getClass().getResourceAsStream("/tiles/PlatzhalterRot.png"));
			
			health0 = ImageIO.read(getClass().getResourceAsStream("/player/SpielerLeben_0_Herzen.png"));
			
			health1 = ImageIO.read(getClass().getResourceAsStream("/player/SpielerLeben_1_Herzen.png"));
			
			health2 = ImageIO.read(getClass().getResourceAsStream("/player/SpielerLeben_2_Herzen.png"));
			
			health3 = ImageIO.read(getClass().getResourceAsStream("/player/SpielerLeben_3_Herzen.png"));
			
		}catch(IOException e) {
			e.printStackTrace();			
		}
	}
	
	public void update() {
		
		if(input.upPressed == true && canMoveUp == true) {
			y -= speed;	
			direction = "up";
		}
		else if(input.downPressed == true && canMoveDown == true) {
			y += speed;
			direction = "down";
		}
		else if(input.leftPressed == true && canMoveLeft == true) {
			x -= speed;
			direction = "left";
		}
		else if(input.rightPressed == true && canMoveRight == true) {
			x += speed;
			direction = "right";
		}
		if(input.spacePressed == true) {
			
			shoot();
		}
		if(input.spacePressed == false) {
			
			canShoot = true;
		}
		currentPosX = x / gp.tileSize;
		currentPosY = y / gp.tileSize;
		
		xOffset = x;
		yOffset = y + gp.tileSize;
		
		projectileXOffset = x;
		projectileYOffset = y - gp.tileSize;
		
		gp.cChecker.checkWorldBarrier(this);
		
		spriteCounter++;
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public void shoot() {
		
		if(canShoot == true) {
			gp.instantiateProjectile(projectileXOffset, projectileYOffset);
			canShoot = false;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		BufferedImage image2 = null;
		BufferedImage image3 = null;
		BufferedImage image4 = null;
		
		image = character;
		image3 = placeHolder;
		
		if(spriteNum == 1) {
			image2 = exhaust1;
		}
		
		if(spriteNum == 2) {
			image2 = exhaust2;
		}
		
		if(health == 3) {
			image4 = health3;
		}else if(health == 2) {
			image4 = health2;
		}else if(health == 1) {
			image4 = health1;
		}else if(health < 1) {
			image4 = health0;
		}
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
		g2.drawImage(image2, xOffset, yOffset, gp.tileSize, gp.tileSize, null);
		
		g2.drawImage(image4, 40, 1180, 200, 60, null);
		
	}
}
