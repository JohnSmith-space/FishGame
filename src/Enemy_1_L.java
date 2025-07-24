public class Enemy_1_L extends Enemy {
    Enemy_1_L() {
        this.x = -42;
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 42;
        this.height = 42;
        this.speed = 1;
        this.count = 1;
        this.img = GameUtils.blueberrinni;
        this.dir = 1;
        this.level = 1;
    }
}
