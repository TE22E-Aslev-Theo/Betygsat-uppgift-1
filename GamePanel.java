import javax.swing.JPanel;
import java.awt.*;
public class GamePanel extends JPanel implements Runnable{
    
    final int WIDTH = 900;
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


    @Override
    public void run() {

        while (GameThread != null) {
            
        } 
        
    }

}
