package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//Parent- Klasse für alle Entitäten (Spieler, Gegner und Gegner- Varianten)
public class Entity {

	public int x, y;
	public int speed;
	
	public BufferedImage character, exhaust1, exhaust2, placeHolder, projectileImage1, projectileImage2, projectileImage3;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public boolean canMoveLeft, canMoveRight, canMoveDown, canMoveUp, canShoot;
}
