import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
public class GamePanel extends JPanel implements Runnable{
    
    final int WIDTH = 1066;
    final int HEIGHT = 600;
    public double player_X = 100;
    public double player_Y;
    final double player_Width = 70;
    final double player_Height = 35;
    public double gravity = 0.4;
    public double velocity; 
    public int backgroundx = 0;
    public int backgroundx2 = 1066;
    public int[] rör_x = {700,1100,1500};
    public int[] rör_y = {
        ThreadLocalRandom.current().nextInt(-400,-100),
        ThreadLocalRandom.current().nextInt(-400,-100),
        ThreadLocalRandom.current().nextInt(-400,-100)};
    Thread GameThread;
    public Keys keylistener;
    GamePanel(){
        keylistener = new Keys();
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(keylistener);
    }

    public void StartGame(){
        GameThread = new Thread(this);
        GameThread.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        Image player = new ImageIcon("Flappy birb.png").getImage();
        Image background = new ImageIcon("spelbakgrund.jpg").getImage();
        Image rör = new ImageIcon("rörbild.png").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, backgroundx, 0, null);
        g2d.drawImage(background, backgroundx2, 0, null);
        g2d.drawImage(player,(int)player_X,(int)player_Y,null);
        g2d.drawImage(rör,rör_x[0],rör_y[0],null);
        g2d.drawImage(rör,rör_x[1],rör_y[1],null);
        g2d.drawImage(rör,rör_x[2],rör_y[2],null);
        
    }

    public void jump(){

        if (keylistener.jump == true) {
            velocity= -7;
            keylistener.jump = false;
        } 

    }

    public void rörreset(int arraynumber){

        if (rör_x[arraynumber] < -120) {
            rör_x[arraynumber] = 1066;
            rör_y[arraynumber] = ThreadLocalRandom.current().nextInt(-400,-100);
        }

    }

    public void update(){
        if (player_Y >= HEIGHT - player_Height - 60) {
            velocity = 0;
        } else{
            player_Y += velocity;
            velocity += gravity;
            backgroundx -= 2;
            backgroundx2 -= 2;
        }

        
        if (backgroundx <= -1066) {
            backgroundx = 1066;
        }
        if (backgroundx2 <= -1066) {
            backgroundx2 = 1066;
        }
        jump();
        rör_x[0]-=6;
        rör_x[1]-=6;
        rör_x[2]-=6;
        
        rörreset(0);
        rörreset(1);
        rörreset(2);
        

    }

    @Override
    public void run() {
        while (GameThread != null) {

            repaint();
            update();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
    }
}
