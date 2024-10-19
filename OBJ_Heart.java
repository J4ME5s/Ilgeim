package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        this.gp = gp;

        name = "Heart";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heartFull.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heartHalf.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heartEmpty.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        } // try


    } // OBJ_Heart
} // OBJ_Heart
