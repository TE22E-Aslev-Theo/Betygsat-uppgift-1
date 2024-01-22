import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
public class GamePanel extends JPanel implements Runnable{
    
    final int WIDTH = 1066;
    final int HEIGHT = 600;
    public double player_X;
    public double player_Y;
    final double player_Width = 70;
    final double player_Height = 35;
    public double gravity = 0.4;
    public double velocity; 
    public int backgroundx = 0;
    public int backgroundx2 = 1066;
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, backgroundx, 0, null);
        g2d.drawImage(background, backgroundx2, 0, null);
        g2d.drawImage(player,(int)player_X,(int)player_Y,null);
    }

    public void jump(){

        if (keylistener.jump == true) {
            velocity= -13;
            keylistener.jump = false;
        } 

    }

    public void update(){
        if (player_Y >= HEIGHT - player_Height - 72) {
            velocity = 0;
        } else{
            player_Y += velocity;
            velocity += gravity;
        }

        backgroundx -= 2;
        backgroundx2 -= 2;
        if (backgroundx <= -1066) {
            backgroundx = 1066;
        }
        if (backgroundx2 <= -1066) {
            backgroundx2 = 1066;
        }
        jump();

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
