import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GameUtils { 
    public static Image bgImage = null;
    public static Image fishImageL = null;
    public static Image fishImageR = null;
    public static Image blueberrinni = null;
    public static Image octopussini = null;
    public static Image bananitta = null;
    public static Image dolfinitta = null;
    public static Image tralalelo = null;
    public static Image tralala = null;
    public static Image ballena = null;
    public static Image torpedoL = null;
    public static Image torpedoR = null;
    public static Image submarine = null;
    static {
        try {
            bgImage = ImageIO.read(new File("D:/FishGame/src/img/bg.gif"));
            fishImageL = ImageIO.read(new File("D:/FishGame/src/img/fish_l.gif"));
            fishImageR = ImageIO.read(new File("D:/FishGame/src/img/fish_r.gif"));
            blueberrinni = ImageIO.read(new File("D:/FishGame/src/img/enemy_1_l.gif"));
            octopussini = ImageIO.read(new File("D:/FishGame/src/img/enemy_1_r.gif"));
            bananitta = ImageIO.read(new File("D:/FishGame/src/img/enemy_2_l.gif"));
            dolfinitta = ImageIO.read(new File("D:/FishGame/src/img/enemy_2_r.gif"));
            tralalelo = ImageIO.read(new File("D:/FishGame/src/img/enemy_3_l.gif"));
            tralala = ImageIO.read(new File("D:/FishGame/src/img/enemy_3_r.gif"));
            ballena = ImageIO.read(new File("D:/FishGame/src/img/boss.gif"));
            torpedoL = ImageIO.read(new File("D:/FishGame/src/img/torpedo_l.gif"));
            torpedoR = ImageIO.read(new File("D:/FishGame/src/img/torpedo_r.gif"));
            submarine = ImageIO.read(new File("D:/FishGame/src/img/submarine.gif"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    static int exp = 0;
    public static ArrayList<Enemy> EnemyList = new ArrayList<Enemy>();
}