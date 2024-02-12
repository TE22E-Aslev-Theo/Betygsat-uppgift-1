
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.ThreadLocalRandom;
public class GamePanel extends JPanel implements Runnable, ActionListener{
    public boolean exit = false;
    public String name;
    final int WIDTH = 1066;
    final int HEIGHT = 600;
    public double player_X = -100;
    public double player_Y = 300;
    final double player_Width = 70;
    final double player_Height = 35;
    int poängräknarey = -100;
    public double gravity = 0.6;
    public double velocity; 
    public double rörvelocity;
    public JButton startbutton = new JButton("Start");
    public JButton modebutton1 = new JButton("Easy");
    public JButton modebutton2 = new JButton("Medium");
    public JButton modebutton3 = new JButton("Hard");
    public JButton exitButton = new JButton("Exit");
    public int molnfart;
    public int backgroundx = 0;
    public int backgroundx2 = 1066;
    public int[] rör_x = {1500,1900,2300};
    public int poäng;
    public boolean[] harpasserat = {false,false,false};
    public boolean start = false;
    public int[] rör_y = {
        ThreadLocalRandom.current().nextInt(-350,-150),
        ThreadLocalRandom.current().nextInt(-350,-150),
        ThreadLocalRandom.current().nextInt(-350,-150)
    };
    Thread GameThread;
    public Keys keylistener;
    public JTextField Highscorenumber[] = {null,null,null,null,null,null,null,null,null,null};
    public int Highscore[] = {0,0,0,0,0,0,0,0,0,0};
    public mouselistener mouselistener = new mouselistener();

    GamePanel(){
        for (int i = 0; i < Highscore.length; i++) {
            Highscorenumber[i] = new JTextField();
            Highscorenumber[i].setText((i+1) +". " + Highscore[i]);
            Highscorenumber[i].setEditable(false);
            Highscorenumber[i].setBounds(850,20 + (i*50), 200,50 );
            Highscorenumber[i].setVisible(true);
        }
        keylistener = new Keys();
        startbutton.setBounds(0, 50, 150, 50);
        startbutton.setBackground(Color.WHITE);
        startbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        startbutton.setOpaque(true);
        startbutton.setVisible(true);
        startbutton.addActionListener(this);
        exitButton.setBounds(0, 500, 150, 50);
        exitButton.setBackground(Color.WHITE);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        exitButton.setOpaque(true);
        exitButton.setVisible(true);
        exitButton.addActionListener(this);
        modebutton3.setBounds(0, 500, 150, 50);
        modebutton3.setBackground(Color.WHITE);
        modebutton3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        modebutton3.setOpaque(true);
        modebutton3.setVisible(false);
        modebutton3.addActionListener(this);
        modebutton2.setBounds(0, 450, 150, 50);
        modebutton2.setBackground(Color.WHITE);
        modebutton2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        modebutton2.setOpaque(true);
        modebutton2.setVisible(false);
        modebutton2.addActionListener(this);
        modebutton1.setBounds(0, 400, 150, 50);
        modebutton1.setBackground(Color.WHITE);
        modebutton1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        modebutton1.setOpaque(true);
        modebutton1.setVisible(false);
        modebutton1.addActionListener(this);
        
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(keylistener);
        this.addMouseListener(mouselistener);
    }
    
    public void StartGame(){
        GameThread = new Thread(this);
        GameThread.start();
    }
    public void Highscore_comparer(){

        if (Highscore[9] <= poäng){
            Highscore[9] = poäng;
        }
    }
    public void sortHighscore(){
        for(int i = 0; i<Highscore.length-1; i++ ){
            for(int j = 0; j <Highscore.length-1; j++){
                if (Highscore[j] < Highscore[j+1]) {
                    int k = Highscore[j];
                    Highscore[j] = Highscore[j + 1];
                    Highscore[j+1] = k;
                }
            }
        } 
    }
    
    public void Lose(){
        harpasserat[0] = false;
        harpasserat[1] = false;
        harpasserat[2] = false;
        rör_x[0] = 1500; 
        rör_x[1] = 1900;
        rör_x[2] = 2300;
        player_Y = 300;
        player_X = -100;
        sortHighscore();
        Highscore_comparer();
        sortHighscore();
        for (int i = 0; i < Highscore.length; i++) {
            Highscorenumber[i].setText((i+1) +". " + Highscore[i]);
        }
        poängräknarey = -100;
        poäng = 0;
        velocity = 0;
        repaint();
        GameThread = null;
        startbutton.setVisible(true);
        
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
        Rectangle2D.Double poängräknare = new Rectangle2D.Double(500,poängräknarey,166,70);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, backgroundx, 0, null);
        g2d.drawImage(background, backgroundx2, 0, null);
        g2d.drawImage(player,(int)player_X,(int)player_Y,null);
        g2d.drawImage(rör,rör_x[0],rör_y[0],null);
        g2d.drawImage(rör,rör_x[1],rör_y[1],null);
        g2d.drawImage(rör,rör_x[2],rör_y[2],null);
        g2d.setFont(stringFont);
        g2d.drawString(String.valueOf(poäng),((int)poängräknare.getX())+ 50,((int)poängräknare.getY()) + 10 );
        if(övrerör1.intersects(fågel) || nedrerör1.intersects(fågel)){
            Lose();
        }
        if(övrerör2.intersects(fågel) || nedrerör2.intersects(fågel)){
            Lose();
        }
        if(övrerör3.intersects(fågel) || nedrerör3.intersects(fågel)){
            Lose();
        }    
    }
    public void jump(){
        
        if (keylistener.jump == true || mouselistener.jump == true) {
            velocity= -8;
            keylistener.jump = false;
            mouselistener.jump = false;
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
    public void reset(){
            rör_y[0] = ThreadLocalRandom.current().nextInt(-350,-150);
            rör_y[1] = ThreadLocalRandom.current().nextInt(-350,-150);
            rör_y[2] = ThreadLocalRandom.current().nextInt(-350,-150);
            rör_x[0] = 1500;
            rör_x[1] = 1900;
            rör_x[2] = 2300;
    }
    public void update(){
        if (poängräknarey <= 50 && start) {
            poängräknarey += 4;
        }
        if (player_Y >= HEIGHT - player_Height - 60) {
            Lose();
        } else{
            player_Y += velocity;
            velocity += gravity;
            backgroundx -= molnfart;
            backgroundx2 -= molnfart;
            rör_x[0]-=(poäng/20) + rörvelocity;
            rör_x[1]-=(poäng/20) + rörvelocity;
            rör_x[2]-=(poäng/20) + rörvelocity;
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
        while (GameThread != null && start) {
            repaint();
            update();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }  
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (GameThread == null) {
            
            for (int i = 0; i < Highscorenumber.length; i++) {
                Highscorenumber[i].setVisible(true);
            }
            reset();

            if (e.getSource() == exitButton){
                System.exit(0);
            }
            if (e.getSource() == startbutton) {
                startbutton.setVisible(false);    
                modebutton1.setVisible(true);
                modebutton2.setVisible(true);
                modebutton3.setVisible(true);
            } 
            if (!startbutton.isVisible()) {
                if (e.getSource() == modebutton1) {
                    modebutton3.setVisible(false);
                    modebutton2.setVisible(false);
                    modebutton1.setVisible(false);
                    
                    for (int i = 0; i < Highscorenumber.length; i++) {
                        Highscorenumber[i].setVisible(true);
                    }
                    StartGame();
                    player_X = 150;
                    start = true;
                    reset();
                    rörvelocity = 7;
                    molnfart = 2;
                }
                if (e.getSource() == modebutton2) {
                    modebutton3.setVisible(false);
                    modebutton2.setVisible(false);
                    modebutton1.setVisible(false);
                   
                    for (int i = 0; i < Highscorenumber.length; i++) {
                        Highscorenumber[i].setVisible(true);
                    }
                    StartGame();
                    player_X = 150;
                    start = true;
                    reset();
                    rörvelocity = 10;
                    molnfart = 3;
                } 
                if (e.getSource() == modebutton3) {
                    modebutton3.setVisible(false);
                    modebutton2.setVisible(false);
                    modebutton1.setVisible(false);
                    
                    for (int i = 0; i < Highscorenumber.length; i++) {
                        Highscorenumber[i].setVisible(true);
                    }
                    StartGame();
                    player_X = 150;
                    start = true;
                    reset();
                    rörvelocity = 15;
                    molnfart = 4;
                } 
            }
        } 
    }
}
