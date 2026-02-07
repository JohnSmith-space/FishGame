public class Enemy_1 extends Enemy {
    Enemy_1(int dir) {
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 42;
        this.height = 42;
        this.speed = 1;
        this.count = 1;
        this.dir = dir;
        this.level = 1;
        if (dir == 1){
            this.x = -width;
            this.img = GameUtils.enemy1L;
        }
        else {
            this.x = 1280;
            this.img = GameUtils.enemy1R;
        }
    }
}
