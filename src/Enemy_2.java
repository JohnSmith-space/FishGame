public class Enemy_2 extends Enemy {
    Enemy_2(int dir) {
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 63;
        this.height = 84;
        this.speed = 1;
        this.count = 2;
        this.dir = dir;
        this.level = 2;
        if (dir == 1){
            this.x = -width;
            this.img = GameUtils.enemy2L;
        }
        else {
            this.x = 1280;
            this.img = GameUtils.enemy2R;
        }
    }
}