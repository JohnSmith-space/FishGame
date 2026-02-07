import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GameUtils { 
    public static Image bgImage = null;
    public static Image fishImageL = null;
    public static Image fishImageR = null;
    public static Image enemy1L = null;
    public static Image enemy1R = null;
    public static Image enemy2L = null;
    public static Image enemy2R = null;
    public static Image enemy3L = null;
    public static Image enemy3R = null;
    public static Image ballena = null;
    public static Image torpedoL = null;
    public static Image torpedoR = null;
    public static Image submarine = null;
    static {
        try {
            bgImage = ImageIO.read(GameUtils.class.getResourceAsStream("/img/bg.gif"));
            fishImageL = ImageIO.read(GameUtils.class.getResourceAsStream("/img/fish_l.gif"));
            fishImageR = ImageIO.read(GameUtils.class.getResourceAsStream("/img/fish_r.gif"));
            enemy1L = ImageIO.read(GameUtils.class.getResourceAsStream("/img/enemy_1_l.gif"));
            enemy1R = ImageIO.read(GameUtils.class.getResourceAsStream("/img/enemy_1_r.gif"));
            enemy2L = ImageIO.read(GameUtils.class.getResourceAsStream("/img/enemy_2_l.gif"));
            enemy2R = ImageIO.read(GameUtils.class.getResourceAsStream("/img/enemy_2_r.gif"));
            enemy3L = ImageIO.read(GameUtils.class.getResourceAsStream("/img/enemy_3_l.gif"));
            enemy3R = ImageIO.read(GameUtils.class.getResourceAsStream("/img/enemy_3_r.gif"));
            ballena = ImageIO.read(GameUtils.class.getResourceAsStream("/img/boss.gif"));
            torpedoL = ImageIO.read(GameUtils.class.getResourceAsStream("/img/torpedo_l.gif"));
            torpedoR = ImageIO.read(GameUtils.class.getResourceAsStream("/img/torpedo_r.gif"));
            submarine = ImageIO.read(GameUtils.class.getResourceAsStream("/img/submarine.gif"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
}