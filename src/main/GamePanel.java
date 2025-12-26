package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Enemie;
import entity.EnemieLaser;
import entity.EnemiesSpawner;
import entity.Player;
import entity.Projectile;


public class GamePanel extends JPanel implements Runnable{

	// Bildschirm Einstellungen
	
	public final int tileSize = 128;
	public final int maxScreenCol = 7; //16
	public final int maxScreenRow = 10; //12
	public final int screenWidht = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//FPS
	private int fps = 60;
	
	InputHandler input = new InputHandler();
	Thread gameThread;
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	Player player = new Player(this, input);
	EnemiesSpawner eSpawn = new EnemiesSpawner(this);
	UI ui = new UI(this, input);
	
	//GameState
	public int gameState;
	public final int titelState = 0;
	public final int playState = 1;
	public final int gameOverState = 2;
	
	//Objecte
	public ArrayList<Projectile> projectiles;
	public ArrayList<Enemie>  enemies;
	public ArrayList<EnemieLaser> lasers;
	
	public int highScore = 0;
	Font arial_40;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidht, screenHeight));
		this.setBackground(Color.darkGray);
		this.setDoubleBuffered(true);
		this.addKeyListener(input);
		this.setFocusable(true);
	}


	public void setupGame() {
		
		gameState = titelState;
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemie>();
		lasers = new ArrayList<EnemieLaser>();
		
		eSpawn.spawn();
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() { 
		
		double drawInterval = 1000000000 / fps; //0.01666 sec
		double delta = 0;
		long lastTime = System.nanoTime();	//Greift auf Zeit des Systems zu
		long currentTime;
		long timer = 0;		//Variablen zum messen der FPS
		int drawCount = 0;
			
		while(gameThread != null) {
			
			currentTime = System.nanoTime();	//Greift auf Zeit des Systems zu
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();	
				delta --;
				drawCount++; 
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}	
		}
	}
	
	public void update() {
		
		if(gameState == playState) {
			
			player.update();

			eSpawn.update();
			
			for(Projectile p : projectiles) {
				p.update();
			}
			
			for(Enemie p : enemies) {
				p.update();
			}
			
			for(EnemieLaser p : lasers) {
				p.update();
			}
			
			//Entfernt Projektiele aus Arraylist wenn sie aus dem Bildschirm fliegen
			checkProjectileExit();
			
			//Überprüfe ob Gegner getroffen
			checkEnemieHit();
			
			checkLaserExit();
			
			checkPlayerHit();
		}
		
		if(gameState == titelState || gameState == gameOverState) {
			ui.update();
		}

		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); // super is a reference to the parent 
		Graphics2D g2 = (Graphics2D)g;
		
		//Titel
		if(gameState == titelState) {
			ui.draw(g2);
		}
			
		if(gameState == gameOverState) {
			ui.draw(g2);
		}
		
		if(gameState == playState) {
			
			player.draw(g2);
			
			for(Projectile p : projectiles) {
				p.draw(g2);
			}
			for(Enemie p : enemies) {
				p.draw(g2);
			}
			for(EnemieLaser p : lasers) {
				p.draw(g2);
			}
			
			//Malt einen String 
			arial_40 = new Font("Arial", Font.PLAIN, 40);
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			String scoreBoard = "Score: " + highScore;
			g2.drawString(scoreBoard, 600 ,1180);
		}	
		g2.dispose();
	}
	
	public void instantiateProjectile(int x, int y) {
		
		projectiles.add(new Projectile(this, x, y));
	}
	
	public void instantiateEnemie(int x, int y) {
		
		enemies.add(new Enemie(this, x, y ));
	}
	
	public void instantiateEnemieLaser(int x, int y) {
		
		lasers.add(new EnemieLaser(this, x, y ));
	}
	
	private void checkEnemieHit() {
		
		for(int i = 0; i < projectiles.size(); i++) {
			for(int x = 0; x < enemies.size(); x++) {
				if(projectiles.get(i).currentPositionX == enemies.get(x).currentPositionX) {
					if(projectiles.get(i).currentPositionY == enemies.get(x).currentPositionY) {
						enemies.remove(x);
						projectiles.remove(i);
						
						highScore += 25;
					}
				}
			}		
		}
	}
	
	
	private void checkPlayerHit() {
		
		for(int i = 0; i < lasers.size(); i++) {
			if( lasers.get(i).currentPositionX == player.currentPosX) {
				if(lasers.get(i).currentPositionY == player.currentPosY) {
					lasers.remove(i);
					
					player.health -= 1;
					
					System.out.println(player.health);
					
					if(player.health < 1) {
						gameState = gameOverState;
						player.health = 3;
						highScore = 0;
					}
				}
				
			}
		}
	}

	private void checkLaserExit() {
		
		for(int i = 0; i < lasers.size(); i++) {
			if(lasers.get(i).currentPositionY > 1280) {
				projectiles.remove(i);
			}
		}
	}
	
	private void checkProjectileExit() {
		
		for(int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).y < 0) {
				projectiles.remove(i);
			}
		}
	}
}
