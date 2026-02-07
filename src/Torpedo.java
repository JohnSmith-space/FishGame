public class Torpedo extends Enemy {
    public Torpedo(int dir) {
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 120;
        this.height = 32;
        this.speed = 1;
        this.count = -5;
        this.dir = dir;
        this.level = 0;
        if (dir == 1){
            this.x = -120;
            this.img = GameUtils.torpedoL;
        }
        else {
            this.x = 1280;
            this.img = GameUtils.torpedoR;
        }
    }
}