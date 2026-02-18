public class Enemy_1 extends Enemy {
    Enemy_1(int dir) {
        this.width = 42;
        this.height = 42;
        this.x = (dir == 1) ? -width : 1280;
        this.y = (int) (Math.random() * 700 + 50);
        this.speed = 1;
        this.count = 1;
        this.dir = dir;
        this.level = 1;
        this.img = (dir == 1) ? GameUtils.enemy1L : GameUtils.enemy1R;
    }
}
