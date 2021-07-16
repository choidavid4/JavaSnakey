import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

public class LeaderboardPanel extends JPanel{
	public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
	public static final Font TITLE_FONT = new Font("Arial", 0, 42);
	public static final Font LINE_FONT = new Font("Arial", 0 , 32);
	public static final Font HEADER_FONT = new Font("Arial", Font.BOLD , 32);
	ArrayList<Score> scoreList = new ArrayList<Score>();
	SnakeFrame parentFrame;
	
	
	
	public LeaderboardPanel(JFrame frame){
		parentFrame = (SnakeFrame) frame;
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.addKeyListener(new MyKeyAdapter());
		
		JLabel title = new JLabel("Top 10 Snakeys");
		title.setForeground(Color.white);
		title.setFont(TITLE_FONT);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(SCREEN_WIDTH, 100));
		
		this.add(title, BorderLayout.NORTH);
		
		GridLayout grid = new GridLayout(11, 2);
		JPanel scores = new JPanel(grid);
		scores.setBackground(new Color(0,0,0,0));
		scores.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JLabel nameLabel = getLabelItem("Name");
		JLabel scoreLabel = getLabelItem("Score");
		nameLabel.setFont(HEADER_FONT);
		scoreLabel.setFont(HEADER_FONT);
		
		scores.add(nameLabel);
		scores.add(scoreLabel);
		
		loadScoreList();
		
		for(int i = 0; i < 10; i++){
			Score score1 = scoreList.get(i);
			JLabel nameLabel1 = getLabelItem(score1.name);
			JLabel scoreLabel1 = getLabelItem(String.valueOf(score1.score));
			
			scores.add(nameLabel1);
			scores.add(scoreLabel1);
		}
		
		
		this.add(scores, BorderLayout.CENTER);
		
	}
	
	private JLabel getLabelItem(String text){
		JLabel aux = new JLabel(text, SwingConstants.CENTER);
		
		aux.setForeground(Color.white);
		aux.setFont(LINE_FONT);
		
		return aux;
	}
	
	public void loadScoreList(){
		try{
			scoreList.clear();
			BufferedReader buffer = new BufferedReader(new FileReader(new File("scores.data")));
			String line;
			String[] nameScore;
			Score aux;
			while((line = buffer.readLine()) != null){
				nameScore = line.split(",");
				aux = new Score(nameScore[0], Integer.parseInt(nameScore[1]));
				scoreList.add(aux);
			}
			System.out.println("ArrayList loaded successfully");
		}catch(Exception ex){
			System.out.println("Error trying to read file");
		}
	}
	
	class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent ev){
			int keyCode = ev.getKeyCode();
			if (keyCode == KeyEvent.VK_ESCAPE){
				parentFrame.leaderboardToLobby();
			}
		}
	}
}

class Score implements Comparable{
	String name;
	int score;
	
	public Score(String name, int score){
		this.name = name;
		this.score = score;
	}
	
	public String toString(){
		return "name: " + this.name + " score: " + this.score;
	}
	
	public int getScore(){
		return score;
	}
	
	public String getName(){
		return name;
	}
	
	public int compareTo(Object o){
		Score b = (Score) o;
		return this.score - b.score;
	}
}

/*
class LeaderboardPanelTestDrive{
	public static void main(String[] args){
		JFrame frame = new JFrame("LeaderboardPanelTestDrive");
		
		frame.add(new LeaderboardPanel());
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}*/