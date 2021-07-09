import javax.swing.*;
import java.awt.event.*;

class SnakeFrame extends JFrame{
	public SnakeFrame(){
        this.add(new LobbyPanel());
        this.setTitle("Java Snakey"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}