public class Boss extends Enemy {
    public Boss() {
        this.x = -1200;
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 150;
        this.height = 90;
        this.speed = 2;
        this.count = 8;
        this.img = GameUtils.ballena;
        this.dir = 1;
        this.level = 4;
    }
}
