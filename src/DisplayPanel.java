import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.MouseInfo;
import java.awt.*;
import java.security.Key;

public class DisplayPanel extends JPanel implements MouseListener, KeyListener, ActionListener, MouseMotionListener{
    public int fortesting;
    private boolean start;
    private int score;
    private int xpos;
    private int ypos;
    private int ranposx;
    private int ranposy;
    private double distance;
    private int radius;
    private int mousex;
    private int mousey;
    private Point mpos; //mouse pos
    private int timercount; //counts the ms for the ingame timer not the timer object
//    private boolean yellowColor;
//    private int marioX;
//    private int marioY;
//    private BufferedImage background;
//    private BufferedImage mario;
    private Timer timer;
    private Timer clockTimer;

    public DisplayPanel() {
        radius = 25;
        score = 0;
//        yellowColor = true;
//        marioX = 50;
//        marioY = 435;
//        try {
//            background = ImageIO.read(new File("src/background.png"));
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            mario = ImageIO.read(new File("src/marioright.png"));
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        requestFocusInWindow(); // see comment above
        changepos();
        timer = new Timer(10, this);
        clockTimer = new Timer(1000, this);
        timer.start();
        clockTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 50, 30);
        g.drawString(String.valueOf(mousex) + " " + String.valueOf(mousey), 400, 30);
        g.drawString("Time: " + (timercount), 200, 30);
        g.setColor(Color.RED);
        g.fillOval(xpos,ypos,radius * 2,radius * 2);
        startscreen(g);
    }
    public void changepos(){
        ranposx = (int) (Math.random() * 861);
        ranposy = (int) (Math.random() * 471);
        xpos = ranposx;
        ypos = ranposy;
        requestFocusInWindow();
    }
    @Override
    public void mouseClicked(MouseEvent e) { } // unimplemented

    @Override
    public void mousePressed(MouseEvent e) { } // unimplemented

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && inradofcircle() && start){
            score++;
            repaint();
            changepos();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { } // unimplemented

    @Override
    public void mouseExited(MouseEvent e) { } // unimplemented

    @Override
    public void keyTyped(KeyEvent e) { } // unimplemented

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_M && start == false){ //turns the game on or off

            start = true;
            System.out.println(start);
        }else if (keyCode == KeyEvent.VK_M && start == true){
            start = false;
            System.out.println(start);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) { }  // unimplemented

    public void timerlogic(){ //sets up the timer ui
        if (start == false){
            timercount = 0;
            score = 0;
        }else if (start == true){
            timercount++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) { //
            repaint();
            inradofcircle();
        }
        if (e.getSource() == clockTimer) {
            timerlogic();
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) { //returns mouse coords
        mpos = e.getPoint();
        mousex = (int) mpos.getX();
        mousey = (int) mpos.getY();
    }
    private boolean inradofcircle(){ // finds the distance between the center of the circle and mouse location
        double ramx = 0;
        double ramy = 0;
        ramx = Math.abs(mousex - radius - xpos);
        ramx = ramx * ramx;
        ramy = Math.abs(mousey - radius - ypos);
        ramy = ramy * ramy;
        distance = Math.sqrt(ramx + ramy);
        if (distance < radius){
            return true;
        }else {
            return false;
        }
    }
    private void startscreen(Graphics g){
        super.paintComponent(g);
        if (!start){ //for if start is false
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN , 30));
            g.drawString("Press \"m\" to start", 200, 350);
        }
    }
}
