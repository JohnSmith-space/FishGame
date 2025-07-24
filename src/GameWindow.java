import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public static int currentStatus = 0;
    public static int gameMode = 0;
    Image offScreenImage;
    Fish fish = new Fish();

    Enemy enemy1;
    Enemy enemy2;
    Enemy enemy3;
    Enemy torpedo;
    Boss boss;
    Submarine submarine;
    boolean isBoss = false;
    boolean isSubmarine = false;
    static long start = 0, end = 0;

    int time = 0;

    public GameWindow() {
        this.setTitle("FishGame");
        this.setSize(1280, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void paint(Graphics g) {
        offScreenImage = createImage(1280, 800);
        Graphics gImage = offScreenImage.getGraphics(); 
        gImage.drawImage(GameUtils.bgImage, 0, 0, 1280, 800, null);
        switch (currentStatus) {
            case 0:
                gImage.setColor(Color.yellow);
                gImage.setFont(new Font("Arial", Font.PLAIN, 50));
                gImage.drawString("Press to Start Game", 400, 550);
                gImage.setFont(new Font("Arial", Font.PLAIN, 30));
                gImage.drawString("Left- Classical Mode", 400, 650);
                gImage.drawString("Right- SheepGame Mode", 400, 700);
                break;
            case 1:
                create();
                fish.paintSelf(gImage);
                for (Enemy enemy : GameUtils.EnemyList) {
                    enemy.paintSelf(gImage);
                }
                if (isBoss) {
                    if (boss.x < 0) {
                        gImage.setColor(Color.yellow);
                        gImage.fillRect(boss.x, boss.y + 50, 2400, boss.height / 20);
                    }
                }
                if (isSubmarine) {
                    if (submarine.x > 1280) {
                        gImage.setColor(Color.red);
                        gImage.fillRect(submarine.x - 3000, submarine.y + 30, 3000, submarine.height / 15);
                    }
                }
                gImage.setColor(Color.yellow);
                gImage.setFont(new Font("Arial", Font.PLAIN, 35));
                gImage.drawString("Exp: " + GameUtils.exp + "  Level: " + Fish.level, 100, 120);
                break;
            case 2: 
                gImage.setColor(Color.red);
                gImage.setFont(new Font("Arial", Font.PLAIN, 50));
                gImage.drawString("GAME OVER", 450, 300);
                if (end == 0) { end = System.currentTimeMillis(); }
                gImage.drawString("Time taken: " + (end-start)/60000 + "min" + (end-start)/1000%60 + "s", 450, 400); 
                break;
            case 3:
                gImage.setColor(Color.green);
                gImage.setFont(new Font("Arial", Font.PLAIN, 50));
                gImage.drawString("GAME WIN", 450, 300);
                if (end == 0) { end = System.currentTimeMillis(); }
                gImage.drawString("Time taken: " + (end-start)/60000 + "min" + (end-start)/1000%60 + "s", 450, 400);
                break;
            case 4:
                gImage.setColor(Color.orange);
                gImage.setFont(new Font("Arial", Font.PLAIN, 50));
                gImage.drawString("PAUSED", 450, 300);
                break;
            case 5:
                create2();
                fish.paintSelf(gImage);
                for (Enemy enemy : GameUtils.EnemyList) {
                    enemy.paintSelf(gImage);
                }
                if (isBoss) {
                    if (boss.x < 0) {
                        gImage.setColor(Color.yellow);
                        gImage.fillRect(boss.x, boss.y + 50, 2400, boss.height / 20);
                    }
                }
                gImage.setColor(Color.yellow);
                gImage.setFont(new Font("Arial", Font.PLAIN, 35));
                gImage.drawString("Exp: " + GameUtils.exp + "  Level: " + Fish.level, 100, 120);
                break;
            case 6:
                create3();
                fish.paintSelf(gImage);
                for (Enemy enemy : GameUtils.EnemyList) {
                    enemy.paintSelf(gImage);
                }
                if (isBoss) {
                    if (boss.x < 0) {
                        gImage.setColor(Color.yellow);
                        gImage.fillRect(boss.x, boss.y + 50, 2400, boss.height / 20);
                    }
                }
                if (isSubmarine) {
                    if (submarine.x > 1280) {
                        gImage.setColor(Color.red);
                        gImage.fillRect(submarine.x - 3000, submarine.y + 30, 3000, submarine.height / 15);
                    }
                }
                gImage.setColor(Color.yellow);
                gImage.setFont(new Font("Arial", Font.PLAIN, 35));
                gImage.drawString("Exp: " + GameUtils.exp + "  Level: " + Fish.level, 100, 120);
                break;
            case 7:
                gImage.setColor(Color.orange);
                gImage.setFont(new Font("Arial", Font.PLAIN, 50));
                gImage.drawString("The difficulty increased!", 450, 300);
                break;
            default:
        }
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void launch() {
        Music music = new Music("D:/FishGame/src/music/bgm.wav");
        music.start();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1 && currentStatus == 0) {
                    fish.setTimes(1.5);
                    currentStatus = gameMode = 1;
                    repaint();
                    start = System.currentTimeMillis();
                }
                if (e.getButton() == MouseEvent.BUTTON3 && currentStatus == 0) {
                    fish.setTimes(3.0);
                    currentStatus = gameMode = 5;
                    repaint();
                    start = System.currentTimeMillis();
                }
                if (e.getButton() == MouseEvent.BUTTON1 && (currentStatus == 2 || currentStatus == 3)) {
                    reGame();
                    currentStatus = 0;
                    start = System.currentTimeMillis();
                }
                if (e.getButton() == MouseEvent.BUTTON1 && currentStatus == 7) {
                    reGame();
                    fish.setTimes(0.8);
                    currentStatus = gameMode = 6;
                    repaint();
                    start = System.currentTimeMillis();
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 87) {
                    fish.UP = true;
                }
                if (e.getKeyCode() == 83) {
                    fish.DOWN = true;
                }
                if (e.getKeyCode() == 65) {
                    fish.LEFT = true;
                }
                if (e.getKeyCode() == 68) {
                    fish.RIGHT = true;
                }
                fish.move();
            }
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 87) {
                    fish.UP = false;
                }
                if (e.getKeyCode() == 83) {
                    fish.DOWN = false;
                }
                if (e.getKeyCode() == 65) {
                    fish.LEFT = false;
                }
                if (e.getKeyCode() == 68) {
                    fish.RIGHT = false;
                }
                               
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32) {
                    if (currentStatus == 1 || currentStatus == 5 || currentStatus == 6) {
                        currentStatus = 4;
                    } else if (currentStatus == 4) {
                        currentStatus = gameMode;
                    }
                }
            }
        });
        while (true) {
            repaint();
            fish.eatFish();
            setLevel(fish, currentStatus);
            time++;
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void create(){
        try {
            double random = Math.random();
            switch (Fish.level) {
            case 4:
                if (time % 600 == 512) {
                    boss = new Boss();
                    isBoss = true;
                }
                if(time % 600 == 423) {
                    submarine = new Submarine();
                    isSubmarine = true;
                }
            case 3:
                if (time % 600 == 212) {
                    boss = new Boss();
                    isBoss = true;
                }
                if (time % 320 == 183) {
                    if (random < 0.5) {
                        enemy3 = new Enemy_3_L();
                    } else {
                        enemy3 = new Enemy_3_R();
                    }
                }
            case 2:
                if (time % 320 == 23) {
                    if (random < 0.5) {
                        enemy3 = new Enemy_3_L();
                    } else {
                        enemy3 = new Enemy_3_R();
                    }
                }
                if (time % 80 == 51 || time % 160 == 91) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2_L();
                    } else {
                        enemy2 = new Enemy_2_R();
                    }
                }
            case 1:
                if (time % 120 == 32) {
                    if (random < 0.5) {
                        torpedo = new Torpedo_L();
                    } else {
                        torpedo = new Torpedo_R();
                    }
                }
                if (time % 160 == 11) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2_L();
                    } else {
                        enemy2 = new Enemy_2_R();
                    }
                }
                if (time % 10 == 0) {
                    if (random < 0.5) {
                        enemy1 = new Enemy_1_L();
                    } else {
                        enemy1 = new Enemy_1_R();
                    }
                }
                break;
            default:
            }
            if (enemy1 != null) {
                GameUtils.EnemyList.add(enemy1);
            }
            if (enemy2 != null) {
                GameUtils.EnemyList.add(enemy2);
            }
            if (enemy3 != null) {
                GameUtils.EnemyList.add(enemy3);
            }
            if (boss != null) {
                GameUtils.EnemyList.add(boss);
            }
            if (torpedo != null) {
                GameUtils.EnemyList.add(torpedo);
            }
            if (submarine != null) {
                GameUtils.EnemyList.add(submarine);
            }
            for (Enemy enemy : GameUtils.EnemyList) {
                enemy.x = enemy.x + enemy.dir * enemy.speed;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void create2() {
        try {
            double random = Math.random();
            switch (Fish.level) {
            case 4:
                if (time % 600 == 512) {
                    boss = new Boss();
                    isBoss = true;
                }
            case 3:
                if (time % 320 == 183) {
                    if (random < 0.5) {
                        enemy3 = new Enemy_3_L();
                    } else {
                        enemy3 = new Enemy_3_R();
                    }
                }
            case 2:
                if (time % 80 == 51 || time % 160 == 91) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2_L();
                    } else {
                        enemy2 = new Enemy_2_R();
                    }
                }
            case 1:
                if (time % 10 == 0) {
                    if (random < 0.5) {
                        enemy1 = new Enemy_1_L();
                    } else {
                        enemy1 = new Enemy_1_R();
                    }
                }
                break;
            default:
            }
            if (enemy1 != null) {
                GameUtils.EnemyList.add(enemy1);
            }
            if (enemy2 != null) {
                GameUtils.EnemyList.add(enemy2);
            }
            if (enemy3 != null) {
                GameUtils.EnemyList.add(enemy3);
            }
            if (boss != null) {
                GameUtils.EnemyList.add(boss);
            }
            for (Enemy enemy : GameUtils.EnemyList) {
                enemy.x = enemy.x + enemy.dir * enemy.speed;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create3() {
        try {
            double random = Math.random();
            switch (Fish.level) {
            case 4:
                if (time % 600 == 512) {
                    boss = new Boss();
                    isBoss = true;
                }
                if(time % 600 == 423) {
                    submarine = new Submarine();
                    isSubmarine = true;
                }
            case 3:
                if (time % 600 == 212) {
                    boss = new Boss();
                    isBoss = true;
                }
                if (time % 320 == 183) {
                    if (random < 0.5) {
                        enemy3 = new Enemy_3_L();
                    } else {
                        enemy3 = new Enemy_3_R();
                    }
                }
            case 2:
                if (time % 320 == 23) {
                    if (random < 0.5) {
                        enemy3 = new Enemy_3_L();
                    } else {
                        enemy3 = new Enemy_3_R();
                    }
                }
                if (time % 80 == 51 || time % 160 == 91) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2_L();
                    } else {
                        enemy2 = new Enemy_2_R();
                    }
                }
            case 1:
                if (time % 30 == 13) {
                    if (random < 0.5) {
                        torpedo = new Torpedo_L();
                    } else {
                        torpedo = new Torpedo_R();
                    }
                }
                if (time % 160 == 11) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2_L();
                    } else {
                        enemy2 = new Enemy_2_R();
                    }
                }
                if (time % 10 == 0) {
                    if (random < 0.5) {
                        enemy1 = new Enemy_1_L();
                    } else {
                        enemy1 = new Enemy_1_R();
                    }
                }
                break;
            default:
            }
            if (enemy1 != null) {
                GameUtils.EnemyList.add(enemy1);
            }
            if (enemy2 != null) {
                GameUtils.EnemyList.add(enemy2);
            }
            if (enemy3 != null) {
                GameUtils.EnemyList.add(enemy3);
            }
            if (boss != null) {
                GameUtils.EnemyList.add(boss);
            }
            if (torpedo != null) {
                GameUtils.EnemyList.add(torpedo);
            }
            if (submarine != null) {
                GameUtils.EnemyList.add(submarine);
            }
            for (Enemy enemy : GameUtils.EnemyList) {
                enemy.x = enemy.x + enemy.dir * enemy.speed;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setLevel(Fish fish, int status) {
        if (status == 1) {
            if (GameUtils.exp < 0) {
                GameWindow.currentStatus = 2;
            } else if (GameUtils.exp < 25) {
                Fish.level = 1;
            } else if (GameUtils.exp < 50) {
                Fish.level = 2;
            } else if (GameUtils.exp < 100) {
                Fish.level = 3;
            } else if (GameUtils.exp < 200) {
                Fish.level = 4;
            } else {
                GameWindow.currentStatus = 3;
            }
        } else if (status == 5) {
            if (GameUtils.exp < 0) {
                GameWindow.currentStatus = 2;
            } else if (GameUtils.exp < 10) {
                Fish.level = 1;
            } else if (GameUtils.exp < 20) {
                Fish.level = 2;
            } else if (GameUtils.exp < 35) {
                Fish.level = 3;
            } else if (GameUtils.exp < 60) {
                Fish.level = 4;
            } else {
                GameWindow.currentStatus = 7;
            }
        } else if (currentStatus == 6) {
            if (GameUtils.exp < 0) {
                GameWindow.currentStatus = 2;
            } else if (GameUtils.exp < 50) {
                Fish.level = 1;
            } else if (GameUtils.exp < 100) {
                Fish.level = 2;
            } else if (GameUtils.exp < 200) {
                Fish.level = 3;
            } else if (GameUtils.exp < 400) {
                Fish.level = 4;
            } else {
                GameWindow.currentStatus = 3;
            }
        }
    }

    private void reGame() {
        GameUtils.EnemyList.clear();
        time = 0;
        GameUtils.exp = 0;
        fish.reset(); 
        enemy1 = null;
        enemy2 = null;
        enemy3 = null;
        boss = null;
        submarine = null;
        isBoss = false;
        isSubmarine = false;
        start = end = 0;
    }

}
