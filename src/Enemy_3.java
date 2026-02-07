public class Enemy_3 extends Enemy {
    Enemy_3(int dir) {
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 120;
        this.height = 90;
        this.speed = 1;
        this.count = 4;
        this.dir = dir;
        this.level = 3;
        if (dir == 1){
            this.x = -width;
            this.img = GameUtils.enemy3L;
        }
        else {
            this.x = 1280;
            this.img = GameUtils.enemy3R;
        }
    }
}
