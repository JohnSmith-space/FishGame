public class Enemy_2_R extends Enemy {
    Enemy_2_R() {
        this.x = 1280;
        this.y = (int) (Math.random() * 700 + 50);
        this.width = 60;
        this.height = 80;
        this.speed = 1;
        this.count = 2;
        this.img = GameUtils.dolfinitta;
        this.dir = -1;
        this.level = 2;
    }
}
