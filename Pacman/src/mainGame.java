import java.awt.*;     // Using AWT's Graphics and Color
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.*;  // Using Swing's components and container
import java.util.Random;

/** Test drawImage() thru ImageIcon */
@SuppressWarnings("serial")
public class mainGame extends JFrame implements KeyListener{
    public static int counter=0;
    public static char map[][]={
            "999999999HIGH SCORE999999999".toCharArray(),
            "999999999ssssssssss999999999".toCharArray(),
            "9999999999999999999999999999".toCharArray(),
            "1111111111111111111111111111".toCharArray(),
            "1222222222222112222222222221".toCharArray(),
            "1211112111112112111112111121".toCharArray(),
            "1311112111112112111112111131".toCharArray(),
            "1211112111112112111112111121".toCharArray(),
            "1222222222222222222222222221".toCharArray(),
            "1211112112111111112112111121".toCharArray(),
            "1211112112111111112112111121".toCharArray(),
            "1222222110000110000112222221".toCharArray(),
            "1111112111110110111112111111".toCharArray(),
            "9999912111110110111112199999".toCharArray(),
            "9999912110000gg0000112199999".toCharArray(),
            "9999912110111111110112199999".toCharArray(),
            "1111112110188888810112111111".toCharArray(),
            "w0000020001gggggg1000200000w".toCharArray(),
            "1111112110188888810112111111".toCharArray(),
            "9999912110111111110112199999".toCharArray(),
            "9999912110000000000112199999".toCharArray(),
            "9999912110111111110112199999".toCharArray(),
            "1111112110111111110112111111".toCharArray(),
            "1222222222222112222222222221".toCharArray(),
            "1211112111112112111112111121".toCharArray(),
            "1211112111112112111112111121".toCharArray(),
            "1322112222222002222222112231".toCharArray(),
            "1112112112111111112112112111".toCharArray(),
            "1112112112111111112112112111".toCharArray(),
            "1222222112222112222112222221".toCharArray(),
            "1211111111112112111111111121".toCharArray(),
            "1211111111112112111111111121".toCharArray(),
            "1222222222222222222222222221".toCharArray(),
            "1111111111111111111111111111".toCharArray(),
            "99rrrrrrrr999999999999999999".toCharArray(),
            "99rrrrrrrr999999999999999999".toCharArray()

    };
    // Define constants for the various dimensions
    //public static int ver=26;
    //public static int hor=13;
    public static int count=0;
    //public static int x = (hor)*20;
    //public static int y = (ver)*20+20;
    public static int direction = 0;
    public static int dist= 1;

    public static final int PADDING = 20;  // padding from the border


    private DrawCanvas canvas;    // The drawing canvas (an inner class extends JPanel)
    //private Random random = new Random(); // for picking images in random

    // Images
    private String mapFile = "Map.jpg";
    private String pelletFile = "pellet.jpg";
    private String powerFile = "power.jpg";
    //private String pacRightFile = "PacRight.gif";
    private Image imgCross;   // drawImage() uses an Image object
    //private Image imgPac;
    private Image imgPellet;
    private Image imgPower;

    Pacman pacman =  new Pacman();
    // Constructor to set up the GUI components and event handlers
    public mainGame() {
        // Prepare the ImageIcon and Image objects for drawImage()
        ImageIcon iconCross = null;
        ImageIcon iconPac = null;
        ImageIcon iconPellet = null;
        ImageIcon iconPower =  null;
        URL imgURL = getClass().getClassLoader().getResource(mapFile);
        if (imgURL != null) {
            iconCross = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + mapFile);
        }
        imgCross = iconCross.getImage();
        

        imgURL = getClass().getClassLoader().getResource(pelletFile);
        if (imgURL != null) {
            iconPellet = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + pelletFile);
        }
        imgPellet = iconPellet.getImage();
        imgURL = getClass().getClassLoader().getResource(powerFile);
        if (imgURL != null) {
            iconPower = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + powerFile);
        }
        imgPower = iconPower.getImage();
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(560, 720));
        setContentPane(canvas);  // use JPanel as content-pane
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();  // pack the components of "super" JFrame
        setTitle("Test drawImage()");
        setVisible(true);
        ActionListener updateTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                update();   // update the (x, y) position
                repaint();  // Refresh the JFrame, callback paintComponent()
            }
        };
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_UP: {
                        if (pacman.direction==Pacman.UP)
                            break;
                        if(!(map[pacman.ver-1][pacman.hor]=='1'||map[pacman.ver-1][pacman.hor]=='8')) {
                            pacman.direction = Pacman.UP;
                            counter=0;
                        }
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        if (pacman.direction==Pacman.DOWN)
                            break;
                        if(!(map[pacman.ver+1][pacman.hor]=='1'||map[pacman.ver+1][pacman.hor]=='8')) {
                            pacman.direction = Pacman.DOWN;
                            counter=0;
                        }
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        if (pacman.direction==Pacman.LEFT)
                            break;
                        if(!(map[pacman.ver][pacman.hor-1]=='1'||map[pacman.ver][pacman.hor-1]=='8')) {
                            pacman.direction = Pacman.LEFT;
                            counter=0;
                        }
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        if (pacman.direction==Pacman.RIGHT)
                            break;
                        if(!(map[pacman.ver][pacman.hor+1]=='1'||map[pacman.ver][pacman.hor+1]=='8')) {
                            pacman.direction = Pacman.RIGHT;
                            counter=0;
                        }
                        break;
                    }
                }
                //counter=0;
                //System.out.println(pacman.direction);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        // Allocate a Timer to run updateTask's actionPerformed() after every delay msec
        new Timer(10, updateTask).start();
    }

    public void update() {
        pacman.update(map);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println(direction);
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    // Define inner class DrawCanvas, which is a JPanel used for custom drawing
    private class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.BLACK);  // Set background color for this JPanel
            // Drawing Images (picked in random)
            g.drawImage(imgCross,0, 0, 560, 720, null);

            Thread t =new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<36;i++)
                        for(int j=0;j<28;j++)
                            if(map[i][j]=='2')
                                g.drawImage(imgPellet,j*20+8,i*20+25,10,10,null);
                            else if(map[i][j]=='3')
                                g.drawImage(imgPower,j*20+8,i*20+25,10,10,null);
                }
            });

            t.start();

            try{
                t.join();
            }catch(Exception e){

            }
            g.drawImage(pacman.imgPac,pacman.x,pacman.y,pacman.Dim,pacman.Dim,null);

        }
    }

    // The entry main method
    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new mainGame(); // Let the constructor do the job
            }
        });
    }
}