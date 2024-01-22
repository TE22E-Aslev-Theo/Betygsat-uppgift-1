import javax.swing.JPanel;
import java.awt.*;
public class GamePanel extends JPanel{
    
    final int WIDTH = 900;
    final int HEIGHT = 600;

    GamePanel(){

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setFocusable(true);
        
    }

}
