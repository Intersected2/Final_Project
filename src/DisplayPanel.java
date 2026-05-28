import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.MouseInfo;
import java.awt.*;

public class DisplayPanel extends JPanel implements MouseListener, KeyListener, ActionListener, MouseMotionListener{

    private int score;
    private int xpos;
    private int ypos;
    private int ranposx;
    private int ranposy;
    private int distance;
    private int mousex;
    private int mousey;
    private Point mpos; //mouse pos
//    private boolean yellowColor;
//    private int marioX;
//    private int marioY;
//    private BufferedImage background;
//    private BufferedImage mario;
    private Timer timer;

    public DisplayPanel() {
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
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(background, 0, 0, null);
//        g.drawImage(mario, marioX, marioY, null);

        // set font and color of text
        g.setFont(new Font("Arial", Font.BOLD, 16));
//        if (yellowColor) {
//            g.setColor(Color.YELLOW);
//        } else {
//            g.setColor(Color.BLACK);
//        }
        g.drawString("Score: " + score, 50, 30);
        g.drawString(String.valueOf(mousex) + " " + String.valueOf(mousey), 400, 30);
        g.setColor(Color.RED);
        g.fillOval(xpos,ypos,50,50);
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
    // unimplemented because if you move your mouse while clicking, this method isn't
    // called, so mouseReleased is best

    @Override
    public void mousePressed(MouseEvent e) { } // unimplemented

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
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
//        if (keyCode == KeyEvent.VK_A) {  // A key; VK_A equals 65
//            marioX -= 5;
//            try {
//                mario = ImageIO.read(new File("src/marioleft.png"));
//            } catch (IOException error) { }
//            repaint();
//        }
//        if (keyCode == KeyEvent.VK_D) {  // D key; VK_D equals 65
//            marioX += 5;
//            try {
//                mario = ImageIO.read(new File("src/marioright.png"));
//            } catch (IOException error) { }
//            repaint();
//        }
    }
    @Override
    public void keyReleased(KeyEvent e) { }  // unimplemented


    @Override
    public void actionPerformed(ActionEvent e) {
//        mpos = MouseInfo.getPointerInfo().getLocation();
//        mousex = (int) MouseInfo.getPointerInfo().getLocation().getX();
//        mousey = (int) MouseInfo.getPointerInfo().getLocation().getY();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mpos = e.getPoint();
        mousex = (int) mpos.getX();
        mousey = (int) mpos.getY();
    }
}
