package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    } // KeyHandler

    @Override
    public void keyTyped(KeyEvent e) {
    } // keyTyped

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // Title State
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.modeSelector--;
                if (gp.ui.modeSelector < 0) {
                    gp.ui.modeSelector = 2;
                } // if
            } // if
            if (code == KeyEvent.VK_S) {
                gp.ui.modeSelector++;
                if (gp.ui.modeSelector > 2) {
                    gp.ui.modeSelector = 0;
                } // if
            } // if
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.modeSelector == 0) {
                    gp.gameState = gp.playState;
                } // if
                if (gp.ui.modeSelector == 1) {
                    // Implement loading a save file
                } // if
                if (gp.ui.modeSelector == 2) {
                    System.exit(0);
                } // if
            } // if
        } // if

        // Play State
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        } // if
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        } // if
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        } // if
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        } // if
        // Checking if user tried to pause
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            } // if
        } // if

        // Pause State
        if (gp.gameState == gp.pauseState) {
            pauseStateHandler(e);
        } // if

    } // keyPressed

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        } // if
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        } // if
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        } // if
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        } // if

    } // keyReleased

    private void pauseStateHandler(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            gp.ui.modeSelector--;
            if (gp.ui.modeSelector < 0) {
                gp.ui.modeSelector = 2;
            } // if
        } // if
        if (code == KeyEvent.VK_S) {
            gp.ui.modeSelector++;
            if (gp.ui.modeSelector > 2) {
                gp.ui.modeSelector = 0;
            } // if
        } // if
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.modeSelector == 0) {
                gp.gameState = gp.playState;
            } // if
            if (gp.ui.modeSelector == 1) {
                gp.gameState = gp.titleState;
            } // if
            if (gp.ui.modeSelector == 2) {
                System.exit(0);
            } // if
        } // if

    }
}
