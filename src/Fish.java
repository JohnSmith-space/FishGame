import java.awt.*;

public class Fish {
    Image image = GameUtils.fishImageL;
    private int x = 640;
    private int y = 400;
    private int width = 54;
    private int height = 33;
    private int speed = 30;
    int level = 1;
    int exp = 0;
    boolean UP = false;
    boolean DOWN = false;
    boolean LEFT = false;
    boolean RIGHT = false;
    private double times = 1;

    public void paintSelf(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        if (UP) {
            if (y > 50) y -= speed;
        }
        if (DOWN) {
            if (y < 750) y += speed;
        }
        if (LEFT) {
            if (x > 30) x -= speed;
            image = GameUtils.fishImageL;
        }
        if (RIGHT) {
            if (x < 1250) x += speed; 
            image = GameUtils.fishImageR;
        }
    }

    private void setSize() {
        this.width = 54 + (int)(exp * width / height * times);
        this.height = 33 + (int)(exp * times);
    }

    private void setLevel() {
        switch (GameWindow.gameMode) {
            case GameWindow.CLASSIC:
                if      (exp <   0) GameWindow.gameOver();
                else if (exp <  25) level = 1;
                else if (exp <  50) level = 2;
                else if (exp < 100) level = 3;
                else if (exp < 200) level = 4;
                else    GameWindow.gameWin();
                break;
            case GameWindow.SGAME_1:
                if      (exp <  0) GameWindow.gameOver();
                else if (exp < 10) level = 1;
                else if (exp < 20) level = 2;
                else if (exp < 35) level = 3;
                else if (exp < 60) level = 4;
                else    GameWindow.currentStatus = GameWindow.SGAME_M;
                break;
            case GameWindow.SGAME_2:
                if      (exp <   0) GameWindow.gameOver();
                else if (exp <  50) level = 1;
                else if (exp < 100) level = 2;
                else if (exp < 200) level = 3;
                else if (exp < 400) level = 4;
                else    GameWindow.gameWin(); 
                break;
        }
    }

    public boolean eatFish(Enemy enemy) {
        if (this.getRect().intersects(enemy.getRect())) {
            if (this.level < enemy.level) {
                GameWindow.gameOver();
            } else {
                exp += enemy.count;
                setSize();
                setLevel();
                if (enemy.dir > 0) {
                    enemy.x += 5400;
                } else {
                    enemy.x -= 5400;
                }
            }
            return true;
        }
        return false;
    }

    public void reset() {
        this.x = 600;
        this.y = 400;
        this.width = 54;
        this.height = 33;
        this.level = 1;
        this.exp = 0;
    }

    public double getTimes() {
        return times;
    }
    public void setTimes(double times) {
        this.times=times;
    }

}