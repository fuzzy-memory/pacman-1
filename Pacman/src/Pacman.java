import java.awt.*;     // Using AWT's Graphics and Color
import java.net.URL;
import javax.swing.*;
public class Pacman {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static int counter=0;
    public static int ver=26;
    public static int hor=13;
    public static int x = (hor)*20;
    public static int y = (ver)*20+20;
    public static int direction=LEFT;
    public static int Dim=20;
    ImageIcon iconPac = null;
    public Image imgPac;
    private Image imgPacRight, imgPacLeft, imgPacUp, imgPacDown;
    private String pacRightFile = "PacRight.gif";
    private String pacLeftFile = "PacLeft.gif";
    private String pacUpFile = "PacUp.gif";
    private String pacDownFile = "PacDown.gif";
    Pacman(){
        URL imgURL = getClass().getClassLoader().getResource(pacRightFile);
        if (imgURL != null) {
            iconPac = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + pacRightFile);
        }
        imgPacRight = iconPac.getImage();
        imgURL = getClass().getClassLoader().getResource(pacLeftFile);
        if (imgURL != null) {
            iconPac = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + pacLeftFile);
        }
        imgPacLeft = iconPac.getImage();
        imgURL = getClass().getClassLoader().getResource(pacUpFile);
        if (imgURL != null) {
            iconPac = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + pacUpFile);
        }
        imgPacUp = iconPac.getImage();
        imgURL = getClass().getClassLoader().getResource(pacDownFile);
        if (imgURL != null) {
            iconPac = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + pacDownFile);
        }
        imgPacDown = iconPac.getImage();

        imgPac = imgPacLeft;
    }
    void update(char[][] map){
        counter++;
        if(counter%21==0) {
            if (direction == LEFT && !(map[ver][hor - 1] == '1' || map[ver][hor - 1] == '8'))
                hor--;
            else if (direction == RIGHT && !(map[ver][hor + 1] == '1' || map[ver][hor + 1] == '8'))
                hor++;
            else if (direction == UP && !(map[ver - 1][hor] == '1' || map[ver - 1][hor] == '8'))
                ver--;
            else if (direction == DOWN && !(map[ver + 1][hor] == '1' || map[ver + 1][hor] == '8'))
                ver++;
            if (direction == LEFT && map[ver][hor - 1] == 'w')
                hor = 27;
            else if (direction == RIGHT && map[ver][hor + 1] == 'w')
                hor = 0;
            x=(hor)*20;
            y=(ver)*20+20;
        }else{
            if (direction == 0 && !(map[ver][hor - 1] == '1' || map[ver][hor - 1] == '8'))
                x-=1;
            else if (direction == 1 && !(map[ver][hor + 1] == '1' || map[ver][hor + 1] == '8'))
                x+=1;
            else if (direction == 2 && !(map[ver - 1][hor] == '1' || map[ver - 1][hor] == '8'))
                y-=1;
            else if (direction == 3 && !(map[ver + 1][hor] == '1' || map[ver + 1][hor] == '8'))
                y+=1;
        }
        if(map[ver][hor]=='2'){
            map[ver][hor]='0';
        }
        if(map[ver][hor]=='3'){
            map[ver][hor]=0;
        }

    }
}
