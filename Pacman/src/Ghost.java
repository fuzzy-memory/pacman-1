import javax.swing.*;
import java.awt.*;
import java.net.URL;
//import java.util.Random;

import static java.lang.Math.abs;

public class Ghost implements java.io.Serializable{
    public static final int LEFT = 1;
    public static final int RIGHT = 3;
    public static final int UP = 0;
    public static final int DOWN = 2;
    public static final int PINKY=0, INKY=1,BLINKY=2,CLYDE=3;
    public static final int cscatterx=3,cscattery=32;
    public static final int bscatterx=24,bscattery=4;
    public static final int iscatterx=24,iscattery=32;
    public static final int pscatterx=3,pscattery=4;
    public int counter=0;
    public int ver, dec_ver=0;
    public boolean isFrightened = false, activeCount=false, inHouse, eaten=false, wall=true, flashing=false;
    public int hor, dec_hor=0;
    public int x;
    public int y;
    public int direction=LEFT;
    public int Dim=20;
    int pelletCount, eatenPelletCount;

    ImageIcon iconGhost = null;
    public Image imgGhost;
    public Image imgGhostRight;
    public Image imgGhostUp;
    public Image imgGhostDown;
    public Image imgGhostLeft;
    public Image imgGhostScared;
    public Image imgGhostFlashing;
    public Image imgEyes;
    private String eyesFile = "eyes.gif";
    private String ghostFlashingFile = "seizure.gif";
    private String ghostScaredFile ="scared.gif";
    private String inkyRightFile = "inkyRight.gif";
    private String blinkyRightFile = "blinkyRight.gif";
    private String pinkyRightFile = "pinkyRight.gif";
    private String clydeRightFile = "clydeRight.gif";
    private String inkyLeftFile = "inkyLeft.gif";
    private String blinkyLeftFile = "blinkyLeft.gif";
    private String pinkyLeftFile = "pinkyLeft.gif";
    private String clydeLeftFile = "clydeLeft.gif";
    private String inkyUpFile = "inkyUp.gif";
    private String blinkyUpFile = "blinkyUp.gif";
    private String pinkyUpFile = "pinkyUp.gif";
    private String clydeUpFile = "clydeUp.gif";
    private String inkyDownFile = "inkyDown.gif";
    private String blinkyDownFile = "blinkyDown.gif";
    private String pinkyDownFile = "pinkyDown.gif";
    private String clydeDownFile = "clydeDown.gif";
    int startx, starty;
    Ghost(int vers){
        if(vers==PINKY){
            pelletCount=0;
            eatenPelletCount=7;
            activeCount=true;
            startx=15;starty=16;
            inHouse=true;
            URL imgURL = getClass().getClassLoader().getResource(pinkyRightFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + pinkyRightFile);
            }
            imgGhostRight = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(pinkyLeftFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + pinkyLeftFile);
            }
            imgGhostLeft = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(pinkyDownFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + pinkyDownFile);
            }
            imgGhostDown = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(pinkyUpFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + pinkyUpFile);
            }
            imgGhostUp = iconGhost.getImage();
        }else if(vers==INKY){
            inHouse=true;
            pelletCount=30;
            eatenPelletCount=17;
            startx=14;starty=16;
            activeCount=true;
            URL imgURL = getClass().getClassLoader().getResource(inkyRightFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + inkyRightFile);
            }
            imgGhostRight = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(inkyLeftFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + inkyLeftFile);
            }
            imgGhostLeft = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(inkyDownFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + inkyDownFile);
            }
            imgGhostDown = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(inkyUpFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + inkyUpFile);
            }
            imgGhostUp = iconGhost.getImage();
        }else if(vers==BLINKY){
            pelletCount=0;
            inHouse=false;
            startx=13;starty=14;

            URL imgURL = getClass().getClassLoader().getResource(blinkyRightFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + blinkyRightFile);
            }
            imgGhostRight = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(blinkyLeftFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + blinkyLeftFile);
            }
            imgGhostLeft = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(blinkyDownFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + blinkyDownFile);
            }
            imgGhostDown = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(blinkyUpFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + blinkyUpFile);
            }
            imgGhostUp = iconGhost.getImage();
        }else if(vers==CLYDE){
            pelletCount=60;
            startx=16;starty=16;
            inHouse=true;
            URL imgURL = getClass().getClassLoader().getResource(clydeRightFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + clydeRightFile);
            }
            imgGhostRight = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(clydeLeftFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + clydeLeftFile);
            }
            imgGhostLeft = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(clydeDownFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + clydeDownFile);
            }
            imgGhostDown = iconGhost.getImage();
            imgURL = getClass().getClassLoader().getResource(clydeUpFile);
            if (imgURL != null) {
                iconGhost = new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + clydeUpFile);
            }
            imgGhostUp = iconGhost.getImage();
        }
        URL imgURL = getClass().getClassLoader().getResource(ghostScaredFile);
        if (imgURL != null) {
            iconGhost = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + ghostScaredFile);
        }
        imgGhostScared=iconGhost.getImage();
        imgURL = getClass().getClassLoader().getResource(ghostFlashingFile);
        if (imgURL != null) {
            iconGhost = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + ghostFlashingFile);
        }
        imgGhostFlashing = iconGhost.getImage();
        imgURL = getClass().getClassLoader().getResource(eyesFile);
        if (imgURL != null) {
            iconGhost = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + eyesFile);
        }
        imgEyes = iconGhost.getImage();
        imgGhost = imgGhostLeft;
        ver=starty;
        hor=startx;
        x=hor*20;
        y=(ver+1)*20;
    }

    public void intersectionCalc(char[][] map, int targetx, int targety){
        int decision = direction;
        if(dec_ver!=ver || dec_hor!=hor) {
            decision = direction;
            int dist[] = {0, 0, 0, 0};
            //int curdistance = abs(targetx-hor)+abs(targety-ver);
            dist[UP] = abs(targetx - hor) + abs(targety - ver + 1);
            dist[DOWN] = abs(targetx - hor) + abs(targety - ver - 1);
            dist[LEFT] = abs(targetx - hor + 1) + abs(targety - ver);
            dist[RIGHT] = abs(targetx - hor - 1) + abs(targety - ver);
            if (direction == LEFT || map[ver][hor + 1] == '1')
                dist[RIGHT] = 999;
            if (direction == RIGHT || map[ver][hor - 1] == '1')
                dist[LEFT] = 999;
            if (direction == UP || map[ver + 1][hor] == '1')
                dist[DOWN] = 999;
            if (direction == DOWN || map[ver - 1][hor] == '1')
                dist[UP] = 999;
            for (int i = 0; i < 4; i++)
                if (dist[i] < dist[decision])
                    decision = i;
            direction = decision;
            dec_ver=ver; dec_hor=hor;
        }
        //System.out.println(map[ver][hor]+" "+ dist[0]+" "+dist[1]+" "+dist[2]+" "+dist[3]+" "+ map[ver+1][hor]);
        loadImage(decision);
    }

    void update(char[][] map,int targetx,int targety){

        move(map,wall);
        if(isFrightened&&!eaten) {
            if (map[ver][hor] == '4' || map[ver][hor] == '5' || map[ver][hor] == '6')
                frightened(map);
        }else if((map[ver][hor]=='4'||map[ver][hor]=='5'||map[ver][hor]=='6') && !eaten) {
            intersectionCalc(map, targetx, targety);
        }else if((map[ver][hor]=='4'||map[ver][hor]=='5'||map[ver][hor]=='6'||map[ver][hor]=='8'||map[ver][hor]=='7') && eaten) {
            intersectionCalc(map, 14, 18);
            wall=false;
            //System.out.println(ver+ " "+ hor);
        }
        if((ver>15&&ver<19)&&(hor>11&&hor<17)&&eaten) {
            eaten = false;
            isFrightened = false;
            wall=true;
            setPelletCount(0);
        }
        //System.out.println(map[ver][hor]+" "+ver+" "+hor);
    }
    void loadImage(int decision){
        if(!eaten){
            if(decision==LEFT)
                imgGhost=imgGhostLeft;
            else if(decision==RIGHT)
                imgGhost=imgGhostRight;
            else if(decision==UP)
                imgGhost=imgGhostUp;
            else if(decision==DOWN)
                imgGhost=imgGhostDown;
        }else{
            imgGhost=imgEyes;
        }
    }

    void frightened(char[][] map){
        if(dec_ver!=ver ||dec_hor!=hor) {
            int decision = (int) (Math.random() * 3);
            if (decision == LEFT && map[ver][hor - 1] == '1') {
                if (map[ver - 1][hor] != '1')
                    decision = UP;
                else if (map[ver][hor + 1] != '1')
                    decision = RIGHT;
                else if (map[ver + 1][hor] != '1')
                    decision = DOWN;
            } else if (decision == RIGHT && map[ver][hor + 1] == '1') {
                if (map[ver + 1][hor] != '1')
                    decision = DOWN;
                else if (map[ver][hor - 1] != '1')
                    decision = LEFT;
                else if (map[ver - 1][hor] != '1')
                    decision = UP;
            } else if (decision == UP && map[ver - 1][hor] == '1') {
                if (map[ver][hor + 1] != '1')
                    decision = RIGHT;
                else if (map[ver + 1][hor] != '1')
                    decision = DOWN;
                else if (map[ver][hor] != '1')
                    decision = UP;
            } else if (decision == DOWN && map[ver + 1][hor] == '1') {
                if (map[ver][hor - 1] != '1')
                    decision = LEFT;
                else if (map[ver - 1][hor] != '1')
                    decision = UP;
                else if (map[ver][hor + 1] != '1')
                    decision = RIGHT;
            }
            direction = decision;
            dec_ver = ver;
            dec_hor = hor;
            //System.out.println(ver + " " + hor + " " + direction);
            if(!flashing)
                imgGhost = imgGhostScared;
        }
    }
    void move(char[][] map, boolean wallConsidered){
        counter++;
        if(wallConsidered) {
            if (counter % 21 == 0) {
                if (direction == LEFT && !(map[ver][hor - 1] == '1' || map[ver][hor - 1] == '8'))
                    hor--;
                else if (direction == RIGHT && !(map[ver][hor + 1] == '1' || map[ver][hor + 1] == '8'))
                    hor++;
                else if (direction == UP && !(map[ver - 1][hor] == '1' || map[ver - 1][hor] == '8'))
                    ver--;
                else if (direction == DOWN && !(map[ver + 1][hor] == '1' || map[ver + 1][hor] == '8'))
                    ver++;
                if (direction == LEFT && map[ver][hor] == 'w')
                    hor = 27;
                else if (direction == RIGHT && map[ver][hor] == 'w')
                    hor = 0;
                counter = 1;
                x = (hor) * 20;
                y = (ver) * 20 + 20;
            } else {
                if (direction == LEFT && !(map[ver][hor - 1] == '1' || map[ver][hor - 1] == '8'))
                    x -= 1;
                else if (direction == RIGHT && !(map[ver][hor + 1] == '1' || map[ver][hor + 1] == '8'))
                    x += 1;
                else if (direction == UP && !(map[ver - 1][hor] == '1' || map[ver - 1][hor] == '8'))
                    y -= 1;
                else if (direction == DOWN && !(map[ver + 1][hor] == '1' || map[ver + 1][hor] == '8'))
                    y += 1;
            }
        }else{
            if (counter % 10 == 0) {
                if (direction == LEFT && !(map[ver][hor - 1] == '1'))
                    hor--;
                else if (direction == RIGHT && !(map[ver][hor + 1] == '1'))
                    hor++;
                else if (direction == UP && !(map[ver - 1][hor] == '1'))
                    ver--;
                else if (direction == DOWN && !(map[ver + 1][hor] == '1'))
                    ver++;
                if (direction == LEFT && map[ver][hor] == 'w')
                    hor = 27;
                else if (direction == RIGHT && map[ver][hor] == 'w')
                    hor = 0;
                counter = 1;
                x = (hor) * 20;
                y = (ver) * 20 + 20;
            } else {
                if (direction == LEFT && !(map[ver][hor - 1] == '1'))
                    x -= 2;
                else if (direction == RIGHT && !(map[ver][hor + 1] == '1'))
                    x += 2;
                else if (direction == UP && !(map[ver - 1][hor] == '1'))
                    y -= 2;
                else if (direction == DOWN && !(map[ver + 1][hor] == '1'))
                    y += 2;
            }
        }
    }

    void decrementPelletCount(){
        if(pelletCount==0){
            hor =13; ver =14;
            activeCount=false;
            inHouse=false;
        }
        if(activeCount)
            pelletCount--;

    }
    void setActiveCount(boolean x){
        activeCount=x;
    }
    void setPelletCount(int x){
        pelletCount=x;
        if(pelletCount==0){
            hor =13; ver =14;
            activeCount=false;
            inHouse=false;
            direction=LEFT;
            imgGhost=imgGhostLeft;
        }

    }
    void setFrightened(boolean x){
        isFrightened=x;
        if(x&&!eaten)
            imgGhost=imgGhostScared;
        else
            loadImage(direction);
        if(!x)
            flashing=false;
    }
    void reset(){
        ver=starty;
        hor=startx;
        direction=LEFT;
        imgGhost=imgGhostLeft;
        inHouse=true;
    }
}
