public class Torpedo extends Enemy {
    public Torpedo(int dir) {
        this.width = 120;
        this.height = 32;
        this.x = (dir == 1) ? -width : 1280;
        this.y = (int) (Math.random() * 700 + 50);
        this.speed = 1;
        this.count = -5;
        this.dir = dir;
        this.img = (dir == 1) ? GameUtils.torpedoL : GameUtils.torpedoR;
        this.level = 0;;
    }
}
