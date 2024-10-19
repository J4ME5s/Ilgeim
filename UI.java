package main;

import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font upheavtt;
    Font arial_40;
    Font arial_20;
    Color transparentBlack = new Color(0, 0, 0, 0.5f);
    BufferedImage heart_full, heart_half, heart_empty;
    public int modeSelector = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        // Setting up custom font
        try {
            InputStream is = getClass().getResourceAsStream("/font/upheavtt.ttf");
            upheavtt = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } // try
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
    } // UI

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // Title Screen
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        } // if

        if (gp.gameState == gp.playState) {
            // for later
            drawPlayerHp();
        } // if
        if (gp.gameState == gp.pauseState) {
            drawPausedScreen();
        } // if

        // Create heart object
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    } // draw

    private void drawTitleScreen() {

        // Game Title
        // g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        g2.setFont(upheavtt);
        g2.setFont(g2.getFont().deriveFont(125F));
        String text = "ILGEIM";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        // Shadowing
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);
        // Main Color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        // Draws arrow next to mode user is pointing at
        if (modeSelector == 0) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", (float) (x + gp.tileSize * 5.5), (float) y);
        } // if

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        // Draws arrow next to mode user is pointing at
        if (modeSelector == 1) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", (float) (x + gp.tileSize * 6), (float) y);
        } // if

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        // Draws arrow next to mode user is pointing at
        if (modeSelector == 2) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", (float) (x + gp.tileSize * 2.8), (float) y);
        } // if
    } // drawTitleScreen

    private void drawPlayerHp() {
        int x = 10;
        int y = 10;
        for (int i = 0; i < gp.player.maxHealth / 2; i++) {
            if (i == 5) {
                x = 10;
                y += (gp.tileSize - 4);
            } // if
            g2.drawImage(heart_full, x, y, null);
            x += (gp.tileSize - 4);
        } // for

    } // drawPlayerHp

    public void drawPausedScreen() {

        int rectX = gp.screenWidth / 2 - 180;
        int rectY = gp.screenHeight / 2 - 200;
        g2.setColor(transparentBlack);
        g2.fillRect(rectX, rectY, 360, 400);
        g2.setColor(Color.white);
        g2.draw(new Rectangle(rectX, rectY, 360, 400));

        drawPausedMenu();

    } // drawPausedScreen

    private void drawPausedMenu() {

        g2.setFont(upheavtt);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        g2.setColor(Color.WHITE);
        // Paused text
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2 - 130;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        // Resume Button
        text = "RESUME";
        x = getXforCenteredText(text);
        y += 90;
        g2.drawString(text, x, y);
        if (modeSelector == 0) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", (float) (x + gp.tileSize * 3.5), (float) y);
        } // if

        // Main Menu Button
        text = "MAIN MENU";
        x = getXforCenteredText(text);
        y += 70;
        g2.drawString(text, x, y);
        if (modeSelector == 1) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", (float) (x + gp.tileSize * 4.7), (float) y);
        } // if

        // Save & Quit Button
        text = "SAVE & QUIT";
        x = getXforCenteredText(text);
        y += 70;
        g2.drawString(text, x, y);
        if (modeSelector == 2) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", (float) (x + gp.tileSize * 5.3), (float) y);
        } // if
    } // drawPausedMenu

    public int getXforCenteredText(String text) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    } // getXforCenteredText
}
