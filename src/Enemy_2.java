public class Enemy_2 extends Enemy {
    Enemy_2(int dir) {
        this.width = 63;
        this.height = 84;
        this.x = (dir == 1) ? -width : 1280;
        this.y = (int) (Math.random() * 700 + 50);
        this.speed = 1;
        this.count = 2;
        this.dir = dir;
        this.level = 2;
        this.img = (dir == 1) ? GameUtils.enemy2L : GameUtils.enemy2R;
    }
}