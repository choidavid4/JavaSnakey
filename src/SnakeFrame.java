import javax.swing.*;
import java.awt.event.*;

class SnakeFrame extends JFrame{
	public static LobbyPanel lobbyPanel = new LobbyPanel();
	public static GamePanel gamePanel = new GamePanel();
	
	public SnakeFrame(){
		
        this.add(lobbyPanel);
		
        this.setTitle("Java Snakey"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
		
		this.add(gamePanel);
		gamePanel.setVisible(false);
    }
	
	public void goToGamePanel(){
		lobbyPanel.setVisible(false);
		gamePanel.setVisible(true);
		gamePanel.startGame();
	}
}