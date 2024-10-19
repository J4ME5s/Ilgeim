package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    } // Player

    public void setDefaultValues() {
        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 11;
        speed = 4;
        direction = "down";

        // Player Status
        maxHealth = 20;
        health = maxHealth;
    } // setDefaultValues

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/SpriteUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/SpriteUp2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/SpriteDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/SpriteDown2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/SpriteLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/SpriteLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/SpriteRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/SpriteRight2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } // Try
    } // getPlayerImage

    public void update() {

        // This if makes player be still when no keys are pressed
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            // Sets direction and moves player's position
            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }
            else if (keyH.rightPressed) {
                direction = "right";
            } // if

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // If collision is false, player can move
            if (!collisionOn) {

                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                } // switch

            } // if

            // Switches spriteNum after some time
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                } // if
                spriteCounter = 0;
            } // if

        } // if

    } // update

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                } // if
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                } // if
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                } // if
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                } // if
                break;
        } // switch
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    } // draw
} // Player
