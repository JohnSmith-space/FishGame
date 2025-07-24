import java.awt.*;
import java.util.*;

public class Fish {
    Image image = GameUtils.fishImageL;
    private int x = 640;
    private int y = 400;
    private int width = 54;
    private int height = 33;
    private int speed = 30;
    static int level = 1;
    boolean UP = false;
    boolean DOWN = false;
    boolean LEFT = false;
    boolean RIGHT = false;
    private double times = 1.5;

    public void paintSelf(Graphics g) {
        g.drawImage(image, x, y, (int)(width + GameUtils.exp*width/height*times), (int)(height + GameUtils.exp*times), null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, (int)(width + GameUtils.exp*width/height*times), (int)(height + GameUtils.exp*times));
    }

    public void move() {
        if (UP) {
            if (y>50) { y -= speed; }
        }
        if (DOWN) {
            if (y<750) { y += speed; }
        }
        if (LEFT) {
            if (x>30) { x -= speed; }
            image = GameUtils.fishImageL;
        }
        if (RIGHT) {
            if (x<1250) { x += speed; }
            image = GameUtils.fishImageR;
        }
    }

    public void eatFish() {
        Iterator<Enemy> it = GameUtils.EnemyList.iterator();
        synchronized (GameUtils.EnemyList) {
            while (it.hasNext()) {
                Enemy enemy = it.next();
                if (this.getRect().intersects(enemy.getRect())) {
                    GameUtils.exp += enemy.count;
                    if (enemy.dir > 0) {
                        enemy.x += 5400;
                    } else {
                        enemy.x -= 5400;
                    }
                    if (this.level < enemy.level) {
                        GameWindow.currentStatus = 2;
                        
                    }
                    it.remove();
                }
            }
        }
    }

    public void reset() {
        this.x = 600;
        this.y = 400;
        this.width = 53;
        this.height = 32;
        this.level = 1;
    }

    public double getTimes() {return times;}
    public void setTimes(double times) {this.times=times;}
}