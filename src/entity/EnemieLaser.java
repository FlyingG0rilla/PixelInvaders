package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class EnemieLaser extends Entity{

	GamePanel gp;
	public int currentPositionX;
	public int currentPositionY;

	public EnemieLaser(GamePanel gp, int xSpawn, int ySpawn) {
		
		this.gp = gp;
		
		setValue(xSpawn, ySpawn);
		getImage();
	}
	
	
	
	public void setValue(int xSpawn, int ySpawn) {
		
		speed = 16;
		x = xSpawn;
		y = ySpawn;
	}
	
	public void getImage() {
		
		try {	
			projectileImage1 = ImageIO.read(getClass().getResourceAsStream("/effects/Gegner_Projectiel_1.png"));
			projectileImage2 = ImageIO.read(getClass().getResourceAsStream("/effects/Gegner_Projectiel_2.png"));
			projectileImage3 = ImageIO.read(getClass().getResourceAsStream("/effects/Gegner_Projectiel_3.png"));
			
		}catch(IOException e) {
			e.printStackTrace();			
		}
	}
	
	public void update() {
		
		y = y + speed;
		
		currentPositionX = x / gp.tileSize;
		currentPositionY = y / gp.tileSize;
		
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
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		image = projectileImage1;
		
		if(spriteNum == 1) {
			image = projectileImage2;
		}
		
		if(spriteNum == 2) {
			image = projectileImage3;
		}
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
	}
	
}
