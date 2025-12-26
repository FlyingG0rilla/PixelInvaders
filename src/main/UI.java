package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {

	GamePanel gp;
	InputHandler input;
	
	Font arial_40;
	
	private int spriteCounter = 0;
	private int spriteNum = 1;
	
	public BufferedImage title, comment, playerShip, exhaust1, exhaust2, gameOver;
	
	public UI(GamePanel gp, InputHandler input) {
		this.gp = gp;
		this.input = input;
		
		getImages();
	}
	
	public void getImages() {
		
		try {
			
			title = ImageIO.read(getClass().getResourceAsStream("/menu/TitelBild.png"));
			comment = ImageIO.read(getClass().getResourceAsStream("/menu/TitelBildKomentar_2.png"));
			
			playerShip = ImageIO.read(getClass().getResourceAsStream("/player/SpielerRaumschiff_1_Pixel_128.png"));
			
			exhaust1 = ImageIO.read(getClass().getResourceAsStream("/player/SpielerRaumschiff_Auspuff_1.png"));
			
			exhaust2 = ImageIO.read(getClass().getResourceAsStream("/player/SpielerRaumschiff_Auspuff_2.png"));
			
			gameOver = ImageIO.read(getClass().getResourceAsStream("/menu/GameOverText.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update() {
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

		
		if(input.spacePressed == true && gp.gameState == gp.titelState) {
			gp.gameState = gp.playState;
		}
		
		if(input.downPressed == true && gp.gameState == gp.gameOverState) {
			gp.gameState = gp.titelState;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		//Titel
		if(gp.gameState == gp.titelState) {
			BufferedImage image = null;
			BufferedImage image2 = null;
			BufferedImage image3 = null;
			BufferedImage image4 = null;
			
			image = title;
			image2 = comment;
			image4 = playerShip;
			
			g2.drawImage(image, 100, 360, 663, 48, null);
			g2.drawImage(image2, 100, 420, 526, 47, null);
			
			if(spriteNum == 1) {
				image3 = exhaust1;
			}
			
			if(spriteNum == 2) {
				image3 = exhaust2;
			}
				
			g2.drawImage(image4, 352, 952, gp.tileSize, gp.tileSize, null);
			
			g2.drawImage(image3, 352, 1080, gp.tileSize, gp.tileSize, null);
			
		}
		
		if(gp.gameState == gp.gameOverState) {
			
			BufferedImage image4 = null;
			image4 = gameOver;
			
			g2.drawImage(image4, 160, 500, 576 , 72, null);
			
			arial_40 = new Font("Arial", Font.PLAIN, 60);
			
			g2.setFont(arial_40);
			
			g2.setColor(Color.white);
			
			String scoreBoard = "Score: " + gp.highScore;
			
			int yPos =  + 650;
			int xPos = 320;
			
			g2.drawString(scoreBoard, xPos ,yPos);
		}
		
	}
}
