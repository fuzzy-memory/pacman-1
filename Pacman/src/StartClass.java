import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartClass extends JFrame implements Runnable, KeyListener {
    private Image image, character, background;
    private Graphics second;
    private URL base;
    StartClass() {
        String imgNoughtFilename = "Map.jpg";
        ImageIcon icon = null;
        base = ((getClass()).getClassLoader()).getResource(imgNoughtFilename);
        if (base != null) {
            icon = new ImageIcon(base);
        } else {
            System.out.println("Couldn't find file: " + imgNoughtFilename);
        }
        final Image img = icon.getImage();
        Container cp = getContentPane();
        cp.setSize(200,200);
        cp.setLayout(new BorderLayout());
// Extend a JLabel and override paintComponet() to drawImage()
        JLabel lbl4 = new JLabel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);  // paint background
                g.drawImage(img, 0, 0, 200, 200, null);
            }
        };
        lbl4.setPreferredSize(new Dimension(200, 200));
        cp.add(lbl4);
        setVisible(true);

    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, this);
        //g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
        //g.drawImage(character, robot.getCenterX() - 61, robot.getCenterY() - 63, this);

    }
    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                break;

            case KeyEvent.VK_DOWN:
                break;

            case KeyEvent.VK_LEFT:
                break;

            case KeyEvent.VK_RIGHT:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                break;

            case KeyEvent.VK_DOWN:
                break;

            case KeyEvent.VK_LEFT:
                break;

            case KeyEvent.VK_RIGHT:
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void run(){
        while (true) {
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
