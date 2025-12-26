package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemie extends Entity {

	
	private GamePanel gp;
	int spawnPositionX;
	int spawnPositionY;
	
	public int currentPositionX;
	public int currentPositionY;
	
	private boolean richtungsWechsel = false;

	public Enemie(GamePanel gp, int x, int y) {
		
		this.gp = gp;
		
		spawnPositionX = x;
		spawnPositionY =y;
		
		setDefaultValues();
		getImage();
	}
	
	public void setDefaultValues(){
		
		speed = 10;
		
		x = spawnPositionX;
		y = spawnPositionY;
		
		canMoveLeft = false;
		canMoveRight = true;
		
	}
	
	public void getImage() {
		
		try {
			
			character = ImageIO.read(getClass().getResourceAsStream("/enemies/GegnerRaumschiffGrün_1_Pixelt.png"));
			
		}catch(IOException e) {
			e.printStackTrace();			
		}
	}
	
	public void update() {
		
		gp.cChecker.checkWorldBarrier(this);
		
		if(canMoveRight == true && richtungsWechsel == false) {
			x += speed;
		}else if(canMoveRight == false) {
			richtungsWechsel = true;
		}

		if(richtungsWechsel == true && canMoveLeft) {
			x -= speed;
		}else if(canMoveLeft == false) {
			richtungsWechsel = false;
		}
		
		tryShoot();
		
		currentPositionX = x / gp.tileSize;
		currentPositionY = y / gp.tileSize;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		image = character;
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
	private void tryShoot() {
		
		int number1 = (int)(Math.random() * 100 +1);
		if(number1 == 100) {
			Shoot();
		}
	}
	
	private void Shoot() {
		gp.instantiateEnemieLaser(x, y);
	}
}
