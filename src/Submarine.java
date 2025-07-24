public class Submarine extends Enemy {
    public Submarine() {
        this.x = 3000;
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 225;
        this.height = 50;
        this.speed = 2;
        this.count = 0;
        this.img = GameUtils.submarine;
        this.dir = -1;
        this.level = 5;
    }
}
