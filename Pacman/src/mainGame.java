import java.awt.*;     // Using AWT's Graphics and Color
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.*;  // Using Swing's components and container
import java.util.Random;

import static java.lang.Math.abs;

/** Test drawImage() thru ImageIcon */
@SuppressWarnings("serial")
public class mainGame extends JFrame implements KeyListener{
    //public static int counter=0;
    /*public static char map[][]={
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

    };*/
    /*
    *   14,13
    *   15,12
    *   15,13
    *   15,15
    * */
    public static char map[][]={
            "999999999HIGH SCORE999999999".toCharArray(),//0
            "999999999ssssssssss999999999".toCharArray(),//1
            "9999999999999999999999999999".toCharArray(),//2
            "1111111111111111111111111111".toCharArray(),//3
            "1422224222224114222224222241".toCharArray(),//4
            "1211112111112112111112111121".toCharArray(),//5
            "1311112111112112111112111131".toCharArray(),//6
            "1211112111112112111112111121".toCharArray(),//7
            "1422224224224224224224222241".toCharArray(),//8
            "1211112112111111112112111121".toCharArray(),//9
            "1211112112111111112112111121".toCharArray(),//10
            "1422224115005115005114222241".toCharArray(),//11
            "1111112111110110111112111111".toCharArray(),//12
            "9999912111110110111112199999".toCharArray(),//13
            "9999912115005005005112199999".toCharArray(),//14
            "9999912110111111110112199999".toCharArray(),//15
            "1111112110188888810112111111".toCharArray(),//16
            "w0000040051gggggg1500400000w".toCharArray(),//17
            "1111112110188888810112111111".toCharArray(),//18
            "9999912110111111110112199999".toCharArray(),//19
            "9999912115000000005112199999".toCharArray(),//20
            "9999912110111111110112199999".toCharArray(),//21
            "1111112110111111110112111111".toCharArray(),//22
            "1422224224224114224224222241".toCharArray(),//23
            "1211112111112112111112111121".toCharArray(),//24
            "1211112111112112111112111121".toCharArray(),//25
            "1624114224224004224224114261".toCharArray(),//26
            "1112112112111111112112112111".toCharArray(),//27
            "1112112112111111112112112111".toCharArray(),//28
            "1424224114224114224114224241".toCharArray(),//29
            "1211111111112112111111111121".toCharArray(),//30
            "1211111111112112111111111121".toCharArray(),//31
            "1422222222224224222222222241".toCharArray(),//32
            "1111111111111111111111111111".toCharArray(),//33
            "99rrrrrrrr999999999999999999".toCharArray(),//34
            "99rrrrrrrr999999999999999999".toCharArray()//35

    };
    // Define constants for the various dimensions
    //public static int ver=26;
    //public static int hor=13;
    public static int count=0;
    private static final int CHASE = 0, SCATTER=1, FRIGHTENED=2;
    //public static int x = (hor)*20;
    //public static int y = (ver)*20+20;
    public static int gameMode= 0;
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
    Ghost blinky = new Ghost(Ghost.BLINKY);
    Ghost inky = new Ghost(Ghost.INKY);
    Ghost pinky = new Ghost(Ghost.PINKY);
    Ghost clyde = new Ghost(Ghost.CLYDE);

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
                            pacman.imgPac=pacman.imgPacUp;
                            Pacman.counter=1;
                        }
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        if (pacman.direction==Pacman.DOWN)
                            break;
                        if(!(map[pacman.ver+1][pacman.hor]=='1'||map[pacman.ver+1][pacman.hor]=='8')) {
                            pacman.direction = Pacman.DOWN;
                            pacman.imgPac=pacman.imgPacDown;
                            Pacman.counter=1;
                        }
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        if (pacman.direction==Pacman.LEFT)
                            break;
                        if(!(map[pacman.ver][pacman.hor-1]=='1'||map[pacman.ver][pacman.hor-1]=='8')) {
                            pacman.imgPac=pacman.imgPacLeft;
                            pacman.direction = Pacman.LEFT;
                            Pacman.counter=1;
                        }
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        if (pacman.direction==Pacman.RIGHT)
                            break;
                        if(!(map[pacman.ver][pacman.hor+1]=='1'||map[pacman.ver][pacman.hor+1]=='8')) {
                            pacman.direction = Pacman.RIGHT;
                            pacman.imgPac=pacman.imgPacRight;
                            Pacman.counter=0;
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
        if(gameMode==CHASE) {
            blinky.update(map, pacman.hor, pacman.ver);
            if (pacman.direction == Pacman.LEFT && Pacman.hor - 4 >= 0)
                pinky.update(map, pacman.hor - 4, pacman.ver);
            else if (pacman.direction == Pacman.RIGHT && Pacman.hor + 4 <= 27)
                pinky.update(map, pacman.hor + 4, pacman.ver);
            else if (pacman.direction == Pacman.UP && Pacman.ver - 4 >= 0)
                pinky.update(map, pacman.hor, pacman.ver - 4);
            else if (pacman.direction == Pacman.DOWN && Pacman.ver + 4 <= 32)
                pinky.update(map, pacman.hor, pacman.ver + 4);
            else
                pinky.update(map, pacman.hor, pacman.ver);
            int x = 0, y = 0;
            if (pacman.direction == Pacman.LEFT) {
                x = pacman.hor - 4 - blinky.hor;
                y = pacman.ver - blinky.ver;
            } else if (pacman.direction == Pacman.RIGHT) {
                x = pacman.hor + 4 - blinky.hor;
                y = pacman.ver - blinky.ver;
            } else if (pacman.direction == Pacman.UP) {
                x = pacman.hor - blinky.hor;
                y = pacman.ver - 4 - blinky.ver;
            } else if (pacman.direction == Pacman.DOWN) {
                x = pacman.hor - blinky.hor;
                y = pacman.ver + 4 - blinky.ver;
            }
            if (pacman.hor + x < 0 || pacman.hor + x > 27)
                x = 0;
            if (pacman.ver + y < 0 || pacman.ver + y > 27)
                y = 0;
            inky.update(map, pacman.hor + x, pacman.ver + y);

            if (abs(pacman.hor - clyde.hor) + abs(pacman.ver - clyde.ver) >= 8)
                clyde.update(map, pacman.hor, pacman.ver);
            else
                clyde.update(map, Ghost.cscatterx, Ghost.cscattery);
        }else if(gameMode==SCATTER){
            blinky.update(map,Ghost.bscatterx,Ghost.bscattery);
            pinky.update(map,Ghost.pscatterx,Ghost.pscattery);
            inky.update(map,Ghost.iscatterx,Ghost.iscattery);
            clyde.update(map,Ghost.cscatterx,Ghost.cscattery);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //System.out.println(direction);
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
                            if(map[i][j]=='2'||map[i][j]=='4')
                                g.drawImage(imgPellet,j*20+8,i*20+25,10,10,null);
                            else if(map[i][j]=='3'||map[i][j]=='6')
                                g.drawImage(imgPower,j*20+8,i*20+25,10,10,null);
                }
            });

            t.start();

            try{
                t.join();
            }catch(Exception e){

            }
            g.drawImage(blinky.imgGhost,blinky.x,blinky.y,blinky.Dim,blinky.Dim, null);
            g.drawImage(inky.imgGhost,inky.x,inky.y,inky.Dim,inky.Dim, null);
            g.drawImage(pinky.imgGhost,pinky.x,pinky.y,pinky.Dim,pinky.Dim, null);
            g.drawImage(clyde.imgGhost,clyde.x,clyde.y,clyde.Dim,clyde.Dim, null);
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
