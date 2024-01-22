import javax.swing.JFrame;

public class Main extends JFrame{
    public static void main(String[] args) {
        GamePanel gamepanel = new GamePanel();
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("drib yppalf ");
        frame.setResizable(false);
        frame.add(gamepanel);
        frame.pack();
        gamepanel.StartGame();
        frame.setVisible(true);
        
    }
}