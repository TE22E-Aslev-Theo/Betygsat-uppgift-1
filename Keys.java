import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener{

    public boolean jump; 

    @Override
    public void keyTyped(KeyEvent e) {
        //används inte 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keycode = e.getKeyChar();

        if (keycode == KeyEvent.VK_SPACE) {
            jump = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char keycode = e.getKeyChar();

        if (keycode == KeyEvent.VK_SPACE) {
            jump = false;
        }
    }


    
}
