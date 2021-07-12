import javax.swing.*;
import java.awt.event.*;

class SnakeFrame extends JFrame{
	LobbyPanel lobbyPanel = new LobbyPanel(this);
	GamePanel gamePanel = new GamePanel(this);
	
	public SnakeFrame(){
		
        this.add(lobbyPanel);
		
        this.setTitle("Java Snakey"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
		
		this.add(gamePanel);
    }
	
	public void switchToGamePanel(){
		lobbyPanel.setVisible(false);
		gamePanel.setVisible(true);
		gamePanel.requestFocus();
		gamePanel.startGame();
	}
	
	public void switchToLobbyPanel(){
		//Goes back from gamePanel to LobbyPanel
		gamePanel.gameOver();
		gamePanel.setVisible(false);
		lobbyPanel.setVisible(true);
		lobbyPanel.requestFocus();
	}
}