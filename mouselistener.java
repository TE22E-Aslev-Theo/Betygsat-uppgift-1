import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouselistener implements MouseListener{

    public boolean jump;
    public boolean jumped;

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       if (!jumped) {
            jump = true;
            jumped = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        jump = false;
        jumped = false;
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}
