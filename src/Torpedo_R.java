public class Torpedo_R extends Enemy {
    public Torpedo_R() {
        this.x = 1280;
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 120;
        this.height = 32;
        this.speed = 1;
        this.count = -5;
        this.img = GameUtils.torpedoR;
        this.dir = -1;
        this.level = 0;
    }
}
