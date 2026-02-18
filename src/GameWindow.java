import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.*;

public class GameWindow extends JFrame {
    public static final int MAINWIN = 0;
    public static final int GAMEWIN = -1;
    public static final int GAMEOVER = -2;
    public static final int PAUSE = -3;
    public static final int CLASSIC = 1;
    public static final int SGAME_1 = 2;
    public static final int SGAME_M = 3;
    public static final int SGAME_2 = 4;
    private final Font TITLE_FONT = new Font("Arial", Font.PLAIN, 50);
    private final Font SUBTITLE_FONT = new Font("Arial", Font.PLAIN, 30);
    private final Font PROMPT_FONT = new Font("Arial", Font.PLAIN, 35);

    public static int currentStatus = 0;
    public static int gameMode = 0;
    BufferStrategy offScreenImage;
    Graphics gImage;

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
    static long pauseStart = 0, pauseEnd = 0, pauseLast = 0;
    
    /** Used for generating fishes. */
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
        offScreenImage = getBufferStrategy();
        if (offScreenImage == null) {
            createBufferStrategy(2);
            offScreenImage = getBufferStrategy();
        }
        gImage = offScreenImage.getDrawGraphics();
        if (gImage == null) return;
        gImage.drawImage(GameUtils.bgImage, 0, 0, 1280, 800, null);
        switch (currentStatus) {
            case MAINWIN:
                gImage.setColor(Color.yellow);
                gImage.setFont(TITLE_FONT);
                gImage.drawString("Press Key to Select a Mode", 350, 550);
                gImage.setFont(SUBTITLE_FONT);
                gImage.drawString("1- Classic Mode", 400, 650);
                gImage.drawString("2- SheepGame Mode", 400, 700);
                break;
            case CLASSIC:
                paintFishes();
                drawBossLine();
                showExp();
                break;
            case SGAME_1:
                paintFishes();
                drawBossLine();
                showExp();
                break;
            case SGAME_2:
                paintFishes();
                drawBossLine();
                showExp();
                break;
            case SGAME_M:
                gImage.setColor(Color.orange);
                gImage.setFont(TITLE_FONT);
                gImage.drawString("The difficulty increased!", 450, 300);
                showGameOverInfo();
                break;
            case GAMEOVER: 
                gImage.setColor(Color.red);
                gImage.setFont(TITLE_FONT);
                gImage.drawString("GAME OVER", 450, 300);
                showGameOverInfo();
                break;
            case GAMEWIN:
                gImage.setColor(Color.green);
                gImage.setFont(TITLE_FONT);
                gImage.drawString("GAME WIN", 450, 300);
                showGameOverInfo();
                break;
            case PAUSE:
                gImage.setColor(Color.orange);
                gImage.setFont(TITLE_FONT);
                gImage.drawString("PAUSED", 450, 300);
                gImage.setFont(PROMPT_FONT);
                gImage.drawString("Press space to continue, and press esc to exit.", 350, 400);
                break;
        }
        offScreenImage.show();
        gImage.dispose();
    }

    public void launch() {
        Music music = new Music("music/bgm.wav");
        music.start();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1 && (currentStatus == GAMEOVER || currentStatus == GAMEWIN)) {
                    reGame();
                    currentStatus = MAINWIN;
                }
                if (e.getButton() == MouseEvent.BUTTON1 && currentStatus == SGAME_M) {
                    reGame();
                    fish.setTimes(0.5);
                    currentStatus = gameMode = SGAME_2;
                    repaint();
                    start = System.currentTimeMillis();
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) fish.UP    = true;
                if (e.getKeyCode() == KeyEvent.VK_S) fish.DOWN  = true;
                if (e.getKeyCode() == KeyEvent.VK_A) fish.LEFT  = true;
                if (e.getKeyCode() == KeyEvent.VK_D) fish.RIGHT = true;
                fish.move();
            }
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) fish.UP    = false;
                if (e.getKeyCode() == KeyEvent.VK_S) fish.DOWN  = false;
                if (e.getKeyCode() == KeyEvent.VK_A) fish.LEFT  = false;
                if (e.getKeyCode() == KeyEvent.VK_D) fish.RIGHT = false;
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (currentStatus == CLASSIC || currentStatus == SGAME_1 || currentStatus == SGAME_2) {
                        currentStatus = PAUSE;
                        pauseStart = System.currentTimeMillis();
                    } else if (currentStatus == PAUSE) {
                        synchronized(this) {
                            pauseEnd = System.currentTimeMillis();
                            pauseLast += pauseEnd - pauseStart;
                            currentStatus = gameMode;
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_1 && currentStatus == MAINWIN) {
                    fish.setTimes(1);
                    currentStatus = gameMode = CLASSIC;
                    repaint();
                    start = System.currentTimeMillis();
                }
                if (e.getKeyCode() == KeyEvent.VK_2 && currentStatus == MAINWIN) {
                    fish.setTimes(2.5);
                    currentStatus = gameMode = SGAME_1;
                    repaint();
                    start = System.currentTimeMillis();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE && currentStatus == PAUSE) {
                    reGame();
                    currentStatus = MAINWIN;
                }
            }
        });
        while (true) {
            switch (currentStatus) {
                case CLASSIC:
                    create1();
                    setAndEat();
                    break;
                case SGAME_1:
                    create2();
                    setAndEat();
                    break;
                case SGAME_2:
                    create3();
                    setAndEat();
                    break;
            }
            repaint();
            ++time;
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

public void create1() {
        double random = Math.random();
        switch (fish.level) {
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
                        enemy3 = new Enemy_3(1);
                    } else {
                        enemy3 = new Enemy_3(-1);
                    }
                }
            case 2:
                if (time % 320 == 23) {
                    if (random < 0.5) {
                        enemy3 = new Enemy_3(1);
                    } else {
                        enemy3 = new Enemy_3(-1);
                    }
                }
                if (time % 80 == 51 || time % 160 == 91) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2(1);
                    } else {
                        enemy2 = new Enemy_2(-1);
                    }
                }
            case 1:
                if (time % 120 == 32) {
                    if (random < 0.5) {
                        torpedo = new Torpedo(1);
                    } else {
                        torpedo = new Torpedo(-1);
                    }
                }
                if (time % 160 == 11) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2(1);
                    } else {
                        enemy2 = new Enemy_2(-1);
                    }
                }
                if (time % 10 == 0) {
                    if (random < 0.5) {
                        enemy1 = new Enemy_1(1);
                    } else {
                        enemy1 = new Enemy_1(-1);
                    }
                }
                break;
        }
    }

    public void create2() {
        double random = Math.random();
        switch (fish.level) {
            case 4:
                if (time % 600 == 512) {
                    boss = new Boss();
                    isBoss = true;
                }
            case 3:
                if (time % 320 == 183) {
                    if (random < 0.5) {
                        enemy3 = new Enemy_3(1);
                    } else {
                        enemy3 = new Enemy_3(-1);
                    }
                }
            case 2:
                if (time % 80 == 51 || time % 160 == 91) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2(1);
                    } else {
                        enemy2 = new Enemy_2(-1);
                    }
                }
            case 1:
                if (time % 10 == 0) {
                    if (random < 0.5) {
                        enemy1 = new Enemy_1(1);
                    } else {
                        enemy1 = new Enemy_1(-1);
                    }
                }
                break;
        }
    }

    public void create3() {
        double random = Math.random();
        switch (fish.level) {
            case 4:
                if (time % 600 == 512) {
                    boss = new Boss();
                    isBoss = true;
                }
                if (time % 600 == 423) {
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
                        enemy3 = new Enemy_3(1);
                    } else {
                        enemy3 = new Enemy_3(-1);
                    }
                }
            case 2:
                if (time % 320 == 23) {
                    if (random < 0.5) {
                        enemy3 = new Enemy_3(1);
                    } else {
                        enemy3 = new Enemy_3(-1);
                    }
                }
                if (time % 80 == 51 || time % 160 == 91) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2(1);
                    } else {
                        enemy2 = new Enemy_2(-1);
                    }
                }
            case 1:
                if (time % 30 == 13) {
                    if (random < 0.5) {
                        torpedo = new Torpedo(1);
                    } else {
                        torpedo = new Torpedo(-1);
                    }
                }
                if (time % 160 == 11) {
                    if (random < 0.5) {
                        enemy2 = new Enemy_2(1);
                    } else {
                        enemy2 = new Enemy_2(-1);
                    }
                }
                if (time % 10 == 0) {
                    if (random < 0.5) {
                        enemy1 = new Enemy_1(1);
                    } else {
                        enemy1 = new Enemy_1(-1);
                    }
                }
                break;
        }
    }

    public void setAndEat() {
        synchronized (GameUtils.enemyList) {
            if (enemy1  != null)    GameUtils.enemyList.add(enemy1);
            if (enemy2  != null)    GameUtils.enemyList.add(enemy2);
            if (enemy3  != null)    GameUtils.enemyList.add(enemy3);
            if (boss    != null)    GameUtils.enemyList.add(boss);
            if (torpedo != null)    GameUtils.enemyList.add(torpedo);
            if (submarine != null)  GameUtils.enemyList.add(submarine);
            Iterator<Enemy> it = GameUtils.enemyList.iterator();
            while (it.hasNext() && isGameRunning()) {
                Enemy enemy = it.next();
                enemy.x += enemy.dir * enemy.speed;
                if (fish.eatFish(enemy)) {
                    it.remove();
                }
            }
        }
    }

    public void paintFishes() {
        fish.paintSelf(gImage);
        synchronized (GameUtils.enemyList) {
            for (Enemy e : GameUtils.enemyList) {
                e.paintSelf(gImage);
            }
        }
    }

    private void reGame() {
        GameUtils.enemyList.clear();
        time = 0;
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

    public static void gameOver() {
        GameWindow.currentStatus = GAMEOVER;
    }
    public static void gameWin() {
        GameWindow.currentStatus = GAMEWIN;
    }
    public static boolean isGameRunning() {
        return (gameMode > 0 && gameMode != SGAME_M);
    }

    void drawBossLine() {
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
    }

    void showGameOverInfo() {
        if (end == 0) end = System.currentTimeMillis();
        gImage.drawString("Time taken: " + (end-start-pauseLast)/60000 + "min" + (end-start-pauseLast)/1000%60 + "s", 450, 400);
        gImage.setFont(PROMPT_FONT);
        gImage.drawString("Click the left mouse button to continue...", 400, 500);
    }
    void showExp() {
        gImage.setColor(Color.yellow);
        gImage.setFont(PROMPT_FONT);
        gImage.drawString("Exp: " + fish.exp + "  Level: " + fish.level, 100, 120);
    }
}
