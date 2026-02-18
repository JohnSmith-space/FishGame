public class Enemy_3 extends Enemy {
    Enemy_3(int dir) {
        this.width = 120;
        this.height = 90;
        this.x = (dir == 1) ? -width : 1280;
        this.y = (int) (Math.random() * 700 + 50);
        this.speed = 1;
        this.count = 4;
        this.dir = dir;
        this.level = 3;
        this.img = (dir == 1) ? GameUtils.enemy3L : GameUtils.enemy3R;
    }
}
