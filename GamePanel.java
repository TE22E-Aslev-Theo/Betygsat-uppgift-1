import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.concurrent.ThreadLocalRandom;
public class GamePanel extends JPanel implements Runnable{
    
    final int WIDTH = 1066;
    final int HEIGHT = 600;
    public double player_X = 100;
    public double player_Y;
    final double player_Width = 70;
    final double player_Height = 35;
    public double gravity = 0.6;
    public double velocity; 
    public int backgroundx = 0;
    public int backgroundx2 = 1066;
    public int[] rör_x = {700,1100,1500};
    public int poäng;
    public boolean[] harpasserat = {false,false,false};
    JLabel pointcounter; 
    public int[] rör_y = {
        ThreadLocalRandom.current().nextInt(-350,-150),
        ThreadLocalRandom.current().nextInt(-350,-150),
        ThreadLocalRandom.current().nextInt(-350,-150)
    };
    Thread GameThread;
    public Keys keylistener;
    
    GamePanel(){
        pointcounter = new JLabel();
        pointcounter.setText("");
        pointcounter.setBounds(500,50,166,70);
        pointcounter.setVisible(true);
        pointcounter.setBackground(Color.WHITE);
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
        Font stringFont = new Font( "SansSerif", Font.PLAIN, 75 );
        Rectangle övrerör1  = new Rectangle(rör_x[0],rör_y[0],100,435);
        Rectangle nedrerör1  = new Rectangle(rör_x[0],rör_y[0] + 560,100,435);
        Rectangle övrerör2  = new Rectangle(rör_x[1],rör_y[1],100,435);
        Rectangle nedrerör2  = new Rectangle(rör_x[1],rör_y[1] + 560,100,435);
        Rectangle övrerör3  = new Rectangle(rör_x[2],rör_y[2],100,435);
        Rectangle nedrerör3  = new Rectangle(rör_x[2],rör_y[2] + 560,100,435);
        Rectangle fågel = new Rectangle((int)player_X,(int) player_Y,65,30);
        Rectangle2D.Double poängräknare = new Rectangle2D.Double(500,50,166,70);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, backgroundx, 0, null);
        g2d.drawImage(background, backgroundx2, 0, null);
        g2d.drawImage(player,(int)player_X,(int)player_Y,null);
        g2d.drawImage(rör,rör_x[0],rör_y[0],null);
        g2d.drawImage(rör,rör_x[1],rör_y[1],null);
        g2d.drawImage(rör,rör_x[2],rör_y[2],null);
        g2d.setFont(stringFont);
        g2d.drawString(String.valueOf(poäng),((int)poängräknare.getX()) + 60,((int)poängräknare.getY()) + 67);

        if(övrerör1.intersects(fågel) || nedrerör1.intersects(fågel)){
            GameThread = null;
        }
        if(övrerör2.intersects(fågel) || nedrerör2.intersects(fågel)){
            GameThread = null;
        }
        if(övrerör3.intersects(fågel) || nedrerör3.intersects(fågel)){
            GameThread = null;
        }
        
    }

    public void jump(){
        if (keylistener.jump == true) {
            velocity= -8;
            keylistener.jump = false;
        } 
    }

    public void poäng(int i){
        if (!harpasserat[i] && (double)rör_x[i] <= player_X) {
            harpasserat[i] = true;
            poäng++;
        }
    }

    public void rörreset(int i ){
        if (rör_x[i] < -120) {
            rör_x[i] = 1066;
            rör_y[i] = ThreadLocalRandom.current().nextInt(-350,-150);
            harpasserat[i] = false;
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
            rör_x[0]-=7;
            rör_x[1]-=7;
            rör_x[2]-=7;
        }

        
        if (backgroundx <= -1066) {
            backgroundx = 1066;
        }
        if (backgroundx2 <= -1066) {
            backgroundx2 = 1066;
        }
        jump();
        rörreset(0);
        rörreset(1);
        rörreset(2);
        poäng(0);
        poäng(1);
        poäng(2);

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
