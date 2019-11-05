import java.awt.*;     // Using AWT's Graphics and Color
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.*;
public class startScreen extends JFrame {

    private DrawCanvas canvas;
    private Image imgCross;   // drawImage() uses an Image object
    private String mapFile = "login.gif";
    public static String user="";

    startScreen(){
        ImageIcon iconCross = null;
        URL imgURL = getClass().getClassLoader().getResource(mapFile);
        if (imgURL != null) {
            iconCross = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + mapFile);
        }
        imgCross = iconCross.getImage();
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(560, 720));
        setContentPane(canvas);  // use JPanel as content-pane
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();  // pack the components of "super" JFrame
        setTitle("Pac-Man");
        setVisible(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                Character pressed = e.getKeyChar();
                if(Character.isLetterOrDigit(pressed)){
                    user+=pressed;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
                    if(user.length()>0)
                        user = user.substring(0,user.length()-1);
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                 ;
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //setFont(raleway);
        /*String fonts[]
                = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);
        }*/

        ActionListener updateP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //System.out.println("Executed");
                repaint();  // Refresh the JFrame, callback paintComponent()
            }
        };
        new Timer(10,updateP).start();
    }
    private class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.BLACK);
            g.drawImage(imgCross,0, 0, 560, 720, null);
            //x = 200, y = 500
            Font raleway = new Font("Raleway Light", Font.BOLD,28);
            g.setFont(raleway);
            g.setColor(Color.WHITE);
            g.drawString(user,200,505);
        }
    }
    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new startScreen(); // Let the constructor do the job
            }
        });
    }
}
