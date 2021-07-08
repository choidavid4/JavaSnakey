import java.awt.*;
import javax.swing.*;

class LobbyPanel extends JPanel{
    public static final String TITLE_MESSAGE = "Java Snakey";
    public static final String CREATOR_MESSAGE = "Created by @deivchoi in 2021";
    public static final Font TITLE_FONT = new Font("Playball", 0 , 62);
    public static final Font MENU_FONT = new Font("Arial", 0 , 28);
    public static final Font CREATOR_FONT = new Font("Arial", 0 , 14);
    public static final int SCREEN_WIDTH = GamePanel.SCREEN_WIDTH;
    public static final int SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;
    public static final String[] MENU_ITEMS = {"Play", "Leaderboards", "Exit"};
    private int selectedMenuItem = 0;


    public LobbyPanel(){
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        //Lo hacemos focusable para que tome teclas
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g){
        //Tenemos que llamar al paintComponent() de super para no perder el background color seteado en JPanel.
        super.paintComponent(g);
        drawTitle(g);
        drawMenu(g);
        drawCreator(g);
    }

    private void drawTitle(Graphics g){
        g.setColor(Color.white);
        g.setFont(TITLE_FONT);

        FontMetrics metrics = g.getFontMetrics();
        int x = (SCREEN_WIDTH - metrics.stringWidth(TITLE_MESSAGE)) / 2;
        int y = metrics.getHeight() + 100;

        g.drawString(TITLE_MESSAGE, x, y);
    }

    private void drawMenu(Graphics g){
        g.setColor(Color.white);
        g.setFont(MENU_FONT);

        FontMetrics metrics = g.getFontMetrics();
        for(int i = 0; i < MENU_ITEMS.length; i++){
            int x = (SCREEN_WIDTH - metrics.stringWidth(MENU_ITEMS[i])) / 2; //Horizontal center
            int y = metrics.getHeight() + 300 + (i * (metrics.getHeight() + 20));
            g.drawString(MENU_ITEMS[i], x, y);
        }
    }

    private void drawCreator(Graphics g){
        g.setColor(Color.white);
        g.setFont(CREATOR_FONT);

        FontMetrics metrics = g.getFontMetrics();
        int x = SCREEN_WIDTH - metrics.stringWidth(CREATOR_MESSAGE) - 10;
        int y = SCREEN_WIDTH - metrics.getHeight();

        g.drawString(CREATOR_MESSAGE, x, y);
    }
}