import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
        this.addKeyListener(new MyKeyAdapter());

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
            if (selectedMenuItem == i){
                drawTriangle(x - 30, y - 20, g);
            }
        }
    }

    private void drawTriangle(int x, int y, Graphics g){
        g.setColor(Color.white);
        int[] xPoints = {x, x + 20, x};
        int[] yPoints = {y, y+10, y+20};
        g.fillPolygon(xPoints, yPoints, 3);
    }

    private void drawCreator(Graphics g){
        g.setColor(Color.white);
        g.setFont(CREATOR_FONT);

        FontMetrics metrics = g.getFontMetrics();
        int x = SCREEN_WIDTH - metrics.stringWidth(CREATOR_MESSAGE) - 10;
        int y = SCREEN_WIDTH - metrics.getHeight();

        g.drawString(CREATOR_MESSAGE, x, y);
    }

    private class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP:
                    
                    decrementMenu();
                    //System.out.println(selectedMenuItem);
                    break;
                case KeyEvent.VK_DOWN:
                    
                    incrementMenu();
                    //System.out.println(selectedMenuItem);
                    break;
                case KeyEvent.VK_ENTER:
                    //switchPanels(selectedMenuItem);
                    break;
            }
            repaint();
        }
    }

    private void incrementMenu(){
        //Siempre escribir las clases y los metodos pensando en expansion. Mientras menos toquemos el source code mejor
        int lastItemIndex = MENU_ITEMS.length - 1;
        if(selectedMenuItem < lastItemIndex){ //3
            selectedMenuItem++;
        }else{
            selectedMenuItem = 0;
        }
    }

    private void decrementMenu(){
        int lastItemIndex = MENU_ITEMS.length - 1;
        if(selectedMenuItem > 0){
            selectedMenuItem--;
        }else{
            selectedMenuItem = lastItemIndex;
        }
    }

    public int getSelectedMenuItem(){
        return selectedMenuItem;
    }

}