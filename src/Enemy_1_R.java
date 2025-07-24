public class Enemy_1_R extends Enemy {
    Enemy_1_R() {
        this.x = 1280;
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 42;
        this.height = 42;
        this.speed = 1;
        this.count = 1;
        this.img = GameUtils.octopussini;
        this.dir = -1;
        this.level = 1;
    }
}
