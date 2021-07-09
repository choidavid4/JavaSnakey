import javax.swing.*;
import java.awt.event.*;

class SnakeFrame extends JFrame{
    public static final GamePanel GAME_PANEL = new GamePanel();
    public static final LobbyPanel LOBBY_PANEL = new LobbyPanel();
    //public static final JPanel SCOREBOARD_PANEL;
    private static int enteredMenuItem = -1;
    private static JPanel currentPanel = new JPanel();

	public SnakeFrame(){
        this.addKeyListener(new MyKeyAdapter());
        //this.setFocusable(true);
        this.add(LOBBY_PANEL);
        this.setTitle("Java Snakey"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent ev){
            if(ev.getKeyCode() == KeyEvent.VK_ENTER){
                int selected = LOBBY_PANEL.getSelectedMenuItem();
                System.out.println("Listened to enter key on SnakeFrame. Selected menu item = " + selected);
                changePanel(selected); 
            }
        }
    }

    public void changePanel(int selected){
        switch(selected){
            case 0:
                this.removeAll();
                this.add(new GamePanel());
                break;
            case 1:
                this.getContentPane().removeAll();
                //this.add(SCOREBOARD_PANEL);
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

}