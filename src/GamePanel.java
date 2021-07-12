import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GamePanel extends JPanel implements ActionListener{
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 25;
    //Must be a square window.
    public static final int GAME_UNITS = (int) (SCREEN_WIDTH/UNIT_SIZE) * (SCREEN_HEIGHT/UNIT_SIZE);
    public static final int HORIZONTAL_UNITS = SCREEN_WIDTH/UNIT_SIZE;
    public static final int VERTICAL_UNITS = SCREEN_HEIGHT/UNIT_SIZE;
    public static final int DELAY = 100;
    public static final int INITIAL_SNAKE_SIZE = 6;
    private boolean running = false;
    private int appleX;
    private int appleY;
    private Timer timer = new Timer(DELAY, this);
    private char direction;
    private int[] snakeX = new int[GAME_UNITS];
    private int[] snakeY = new int[GAME_UNITS];
    private int snakeSize;
    private int score;
	JFrame parentFrame;
   

    GamePanel(JFrame frame){
		parentFrame = frame;
		
        //Si el panel no es focuseable, no va a escuchar las teclas
        this.setFocusable(true);
		this.requestFocus();
        this.addKeyListener(new MyKeyAdapter());
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        //Timer is a class from Swing that fires up an ActionEvent every given interval of miliseconds. In this case, timer activates this class every quarter of a second.
        System.out.println("Initialized Game Panel");
    }
    public void startGame(){
        snakeSize = INITIAL_SNAKE_SIZE;
        score = 0;
        for(int i = 0; i < snakeSize; i++){
            snakeX[i] = 0;
            snakeY[i] = 0;
        }
        direction = 'R';
        timer.start();
        newApple();
        
    }

    public void actionPerformed(ActionEvent ev){
        move();
        checkCollision();
        eatApple();
        repaint();
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        /*
        //Drawing a grid
        for(int i = 0; i < HORIZONTAL_UNITS; i++){
            //vertical line
            g.drawLine(i * (UNIT_SIZE), 0, i * (UNIT_SIZE), SCREEN_HEIGHT);
            g.drawLine(0, i * (UNIT_SIZE), SCREEN_WIDTH, i * (UNIT_SIZE));
        }
        */

        //Drawing apple with appleX and appleY
        g.setColor(Color.red);
        g.fillOval(appleX , appleY, UNIT_SIZE, UNIT_SIZE);
        //Drawing snake head
        g.setColor(Color.green);
        g.fillRect(snakeX[0], snakeY[0], UNIT_SIZE, UNIT_SIZE);
        //snake Body
        for(int i = 1; i < snakeSize; i++){
            g.fillRect(snakeX[i], snakeY[i], UNIT_SIZE, UNIT_SIZE);
        }

        //Draw score String
        g.setColor(Color.white);
        g.setFont(new Font("MS Gothic", Font.PLAIN, 25));
        FontMetrics fontSize = g.getFontMetrics();
        int fontX = SCREEN_WIDTH - fontSize.stringWidth("Score: " + score) - 10;
        int fontY = fontSize.getHeight();
        g.drawString("Score: " + score, fontX, fontY);
        
        if(!timer.isRunning()){
            //print game over screen
            g.setColor(Color.white);
            g.setFont(new Font("MS Gothic", Font.PLAIN, 58));
            String[] gameOverMessages = {"Maybe next time!", "Sorry Snakey!", "Don't lose hope yet!", "GG WP", "Time for shedding", "Nice Keyboard", "Ow :(", "Ooh That hurts!"};
            String message = gameOverMessages[random(gameOverMessages.length)];
            fontSize = g.getFontMetrics();
            fontX = (SCREEN_WIDTH - fontSize.stringWidth(message)) / 2 ;
            fontY = (SCREEN_HEIGHT - fontSize.getHeight()) /2;
            g.drawString(message, fontX, fontY);

            g.setFont(new Font("MS Gothic", Font.PLAIN, 24));
            message = "Press R to restart";
            fontSize = g.getFontMetrics();
            fontX = (SCREEN_WIDTH - fontSize.stringWidth(message)) / 2 ;
            fontY = fontY + fontSize.getHeight() + 20;
            g.drawString(message, fontX, fontY);
        }
    }

    public void drawString(Color color, Font font, String message, int fontX, int fontY, Graphics g){
        
    }

    
   
    public void newApple(){
        //numero random entre 0 y 23 * unit size
        int x = random(HORIZONTAL_UNITS) * UNIT_SIZE;
        int y = random(VERTICAL_UNITS) * UNIT_SIZE;
        Point provisional = new Point(x,y);
        Point snakePos = new Point();
        boolean newApplePermission = true;
        for(int i = 0; i < snakeSize; i++){
            snakePos.setLocation(snakeX[i], snakeY[i]);
            if(provisional.equals(snakePos)){
                newApplePermission = false;
            }
        }
        //System.out.println(provisional);
        //System.out.println(newApplePermission);
        if(newApplePermission){
            appleX = x;
            appleY = y;
        }else{
            newApple();
        }
    }

    public void checkCollision(){
        if(snakeX[0] >= (SCREEN_WIDTH) || snakeX[0] < 0 || snakeY[0] >= (SCREEN_HEIGHT) || snakeY[0] < 0){
            gameOver();
        }
        for(int i = 1; i < snakeSize; i++){
            if((snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i])){
                gameOver();
            }
        }
    }
    public void eatApple(){
        if(snakeX[0] == appleX && snakeY[0] == appleY){
            snakeSize++;
            score++;
            newApple();
        }
    }
    public void move(){
        //Este metodo se ejecuta cada vez que timer nos lo permite
        //Hay que recorrer la serpiente de atras para adelante
        for(int i = snakeSize; i > 0; i--){
            snakeX[i] = snakeX[i-1]; 
            snakeY[i] = snakeY[i-1]; 
        }
        //System.out.println("snakeX[0] = " + snakeX[0]);
        switch(direction){
            case 'R':
                snakeX[0] += UNIT_SIZE;
                break;
            case 'L':
                snakeX[0] -= UNIT_SIZE;
                break;
            case 'U':
                snakeY[0] -= UNIT_SIZE;
                break;
            case 'D':
                snakeY[0] += UNIT_SIZE;
                break;
        }
    }
    public void gameOver(){
        timer.stop();
        
    }


    public int random(int range){
        //returns an int from 0 to range
        return (int)(Math.random() * range);
    }
    
    class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent k){
            
            switch(k.getKeyCode()){
                case (KeyEvent.VK_DOWN):
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
                case (KeyEvent.VK_UP):
                    if(direction != 'D' ){
                        direction = 'U';
                    }
                    break;
                case (KeyEvent.VK_LEFT):
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case (KeyEvent.VK_RIGHT):
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case (KeyEvent.VK_R):
                    if(!timer.isRunning()){
                        startGame();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
            }
            //System.out.println(direction);
        }
    }
    

    
}