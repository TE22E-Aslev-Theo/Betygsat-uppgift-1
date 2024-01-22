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
    Thread GameThread;
    GamePanel(){
        Keys keylistener = new Keys();
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(player,(int)player_X,(int)player_Y,null);
    }
    public void update(){
        if (player_Y >= HEIGHT - player_Height) {
            velocity = 0;
        } else{
            player_Y += velocity;
            velocity += gravity;
        }
        
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
