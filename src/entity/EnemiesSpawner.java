package entity;

import java.util.ArrayList;

import main.GamePanel;

public class EnemiesSpawner {

	private GamePanel gp;
	
	private int counter;
	

	public EnemiesSpawner(GamePanel gp) {
		
		this.gp = gp;
		
		
	}
		
	public void update() {
		
		counter ++;
		
		if(counter > 10) {
			checkForEnemies();
			counter = 0;
		}
	}
	
	private void checkForEnemies() {
		if(gp.enemies.size() == 0){
			spawn();
		}
	}
	
	
	public void spawn() {
		
		int spawnPointX;
		int spawnPointY;
		
		for(int j = 0; j < 3; j++) {
			spawnPointY = j * gp.tileSize;
			for(int i = 0; i < gp.maxScreenCol; i++) {
				int number1 = (int)(Math.random() * 30 +1);
				if(number1 == 1) {
					spawnPointX = i * (gp.tileSize - 2);
					gp.instantiateEnemie(spawnPointX, spawnPointY);
				}
			}
		}
	}
}
