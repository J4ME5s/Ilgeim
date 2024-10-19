package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage image, int newWidth, int newHeight) {

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g2 = newImage.createGraphics();
        g2.drawImage(image, 0, 0, newWidth, newHeight, null);

        return newImage;
    } // scaleImage

} // UtilityTool
