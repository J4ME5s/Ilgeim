package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 25;
    public final int maxWorldRow = 25;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public UI ui = new UI(this);

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    } // GamePanel

    public void setUpGame() {
        gameState = titleState;
    } // setUpGame

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    } // startGameThread

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // 1. UPDATE: update info such as character pos
                update();
                // 2. DRAW: draw the screen with updated info
                repaint();
                delta--;
                drawCount++;
            } // if

            if (timer >= 1000000000) {
                // Displays the FPS on the console
                // System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            } // if

        } // while

    } // run

    public void update() {
        // Only update player if game is in playState
        if (gameState == playState) {
            player.update();
        } else if (gameState == pauseState) {
            // Do nothing
        } // if
    } // update

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        }
        // Others
        else {
            // Paint tiles
            tileM.draw(g2);

            // Paint player
            player.draw(g2);

            // Paint UI
            ui.draw(g2);

            g2.dispose();
        } // if

    } // paintComponent
} // GamePanel
