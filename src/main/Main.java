package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Durch schließen des Fensters wird das Pogramm beendet
		window.setResizable(false);
		window.setTitle("SpaceInvaders");	//Name des offenen Fensters
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); //Sorgt dafür das Fenster groß genug ist das alles passt
		
		window.setLocationRelativeTo(null);		//Fenster wird immer Mittig des Bildschirms geöffnet
		window.setVisible(true); 				//Fenster wird sichtbar
		
		gamePanel.setupGame();	
		gamePanel.startGameThread();
	}
	
}
