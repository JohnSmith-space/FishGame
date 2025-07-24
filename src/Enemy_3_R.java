public class Enemy_3_R extends Enemy {
    Enemy_3_R() {
        this.x = 1280;
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 100;
        this.height = 75;
        this.speed = 1;
        this.count = 4;
        this.img = GameUtils.tralala;
        this.dir = -1;
        this.level = 3;
    }
}
