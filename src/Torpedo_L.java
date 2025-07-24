public class Torpedo_L extends Enemy {
    public Torpedo_L() {
        this.x = -120;
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 120;
        this.height = 32;
        this.speed = 1;
        this.count = -5;
        this.img = GameUtils.torpedoL;
        this.dir = 1;
        this.level = 0;
    }
}