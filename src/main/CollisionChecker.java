package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		
		this.gp = gp;
	}

	
	public void checkWorldBarrier(Entity entity) {
		
		int linkeGrenze = 0;
		int rechteGrenze = gp.screenWidht;
		int obereGrenze = 0; 
		int untereGrenze = gp.screenHeight;
		
		int leftCollision = entity.x - entity.speed;
		int rightCollision = entity.x + gp.tileSize + entity.speed;
		int topCollision = entity.y - entity.speed;
		int bottomCollision = entity.y + gp.tileSize * 2 + entity.speed;
		
		if(leftCollision < linkeGrenze) {
			entity.canMoveLeft = false;
		}else if(leftCollision > linkeGrenze) {
			entity.canMoveLeft = true;
		}
		
		if(rightCollision > rechteGrenze) {
			entity.canMoveRight = false;
		}else if(rightCollision < rechteGrenze) {
			entity.canMoveRight = true;
		}
		
		if(bottomCollision > untereGrenze) {
			entity.canMoveDown = false;
		}else if(bottomCollision < untereGrenze) {
			entity.canMoveDown = true;
		}
		
		if(topCollision < obereGrenze) {
			entity.canMoveUp = false;
		}else if(topCollision > obereGrenze) {
			entity.canMoveUp = true;
		}
		
	}
	

}
