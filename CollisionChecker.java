package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    } // CollisionChecker

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } // if
                checkTeleporter(tileNum1, tileNum2, entityLeftCol, entityRightCol, entityTopRow, 0);
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } // if
                checkTeleporter(tileNum1, tileNum2, entityLeftCol, entityRightCol, entityBottomRow, 0);
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } // if
                checkTeleporter(tileNum1, tileNum2, entityLeftCol, 0, entityTopRow, entityBottomRow);
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } // if
                checkTeleporter(tileNum1, tileNum2, entityRightCol, 0, entityTopRow, entityBottomRow);
                break;
        } // switch
    } // checkTile

    private void checkTeleporter(int tileNum1, int tileNum2, int col1, int col2, int row1, int row2) {
        if (tileNum1 == 6 || tileNum2 == 6) {
            if (row1 == 1 && (col1 == 12 || col2 == 12)) {
                // Top Teleporter
                gp.player.worldX = gp.tileSize * 12;
                gp.player.worldY = gp.tileSize * 11;
            } else if (row1 == 23 && (col1 == 12 || col2 == 12)) {
                // Bottom Teleporter
                gp.player.worldX = gp.tileSize * 12;
                gp.player.worldY = gp.tileSize * 11;
            } else if (col1 == 1 && (row1 == 11 || row2 == 11)) {
                // Left Teleporter
                gp.player.worldX = gp.tileSize * 12;
                gp.player.worldY = gp.tileSize * 11;
            } else if (col1 == 23 && (row1 == 11 || row2 == 11)) {
                // Right Teleporter
                gp.player.worldX = gp.tileSize * 12;
                gp.player.worldY = gp.tileSize * 11;
            } // if
        } // if
    } // checkTeleporter
} // CollisionChecker
