package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode(); //Speichert Id von getdrückter Taste als int
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		
		if(code == KeyEvent.VK_S) {	
			downPressed = true;
		}
		
		if(code == KeyEvent.VK_A) {			
			leftPressed = true;
		}
		
		if(code == KeyEvent.VK_D) {	
			rightPressed = true;
		}
		
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		
		if(code == KeyEvent.VK_ESCAPE) {
			spacePressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode(); //Speichert Id von getdrückter Taste als int
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {	
			downPressed = false;
		}
		
		if(code == KeyEvent.VK_A) {			
			leftPressed = false;
		}
		
		if(code == KeyEvent.VK_D) {	
			rightPressed = false;
		}
		
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
	}

}
