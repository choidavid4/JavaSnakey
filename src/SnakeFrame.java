import javax.swing.*;
import java.awt.event.*;

class SnakeFrame extends JFrame{
	LobbyPanel lobbyPanel = new LobbyPanel(this);
	GamePanel gamePanel = new GamePanel(this);
	LeaderboardPanel leaderboardPanel = new LeaderboardPanel(this);
	private boolean addedLeaderboard = false;
	
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

	public void gameToLeaderboard(){
		gamePanel.setVisible(false);
		leaderboardPanel.loadScoreList();
		leaderboardPanel.setVisible(true);
		leaderboardPanel.requestFocus();
	}
	
	public void leaderboardToGame(){
		leaderboardPanel.setVisible(false);
		gamePanel.setVisible(true);
		gamePanel.requestFocus();
		gamePanel.startGame();
	}
	
	public void leaderboardToLobby(){
		leaderboardPanel.setVisible(false);
		lobbyPanel.setVisible(true);
		lobbyPanel.requestFocus();
	}
	
	public void switchToLeaderboardPanel(){
		if(addedLeaderboard){
			lobbyPanel.setVisible(false);
			leaderboardPanel.loadScoreList();
			leaderboardPanel.setVisible(true);
			leaderboardPanel.requestFocus();
		}else{
			lobbyPanel.setVisible(false);
			gamePanel.setVisible(false);
			this.add(leaderboardPanel);
			leaderboardPanel.loadScoreList();
			leaderboardPanel.requestFocus();
			addedLeaderboard = true;
		}
		
		
	}
}