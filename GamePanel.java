import javax.swing.JPanel;
import java.awt.*;
public class GamePanel extends JPanel implements Runnable{
    
    final int WIDTH = 1066;
    final int HEIGHT = 600;

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

    }
    public void update(){


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
