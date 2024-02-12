import javax.swing.JFrame;
public class Main extends JFrame{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePanel gamepanel = new GamePanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("flappy bird");
        frame.setResizable(false);
        frame.add(gamepanel.modebutton1);
        frame.add(gamepanel.modebutton2);
        frame.add(gamepanel.modebutton3);
        frame.add(gamepanel.startbutton);
        frame.add(gamepanel.exitButton);
        for (int i = 0; i < gamepanel.Highscorenumber.length; i++) {
            frame.add(gamepanel.Highscorenumber[i]);
        }
        frame.add(gamepanel);
        frame.pack(); 
        frame.setVisible(true);    

        


    }
}