public class Enemy_3_L extends Enemy {
    Enemy_3_L() {
        this.x = -100;
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 100;
        this.height = 75;
        this.speed = 1;
        this.count = 4;
        this.img = GameUtils.tralalelo;
        this.dir = 1;
        this.level = 3;
    }
}
