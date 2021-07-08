import javax.swing.*;

class SnakeFrame extends JFrame{
	public SnakeFrame(){
        this.add(new GamePanel()); 
        this.setTitle("Snake Game"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}