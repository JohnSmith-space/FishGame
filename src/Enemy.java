import java.awt.*;

public class Enemy {
    Image img;
    int x, y, height, width, speed;
    int dir;
    int type, count, level;
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}