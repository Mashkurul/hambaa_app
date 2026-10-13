package com.hambaa.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utility to generate the HAMBAA mint-green cow logo image.
 */
public class LogoGenerator {

    public static void generateLogo(File outputFile) {
        int width = 256;
        int height = 256;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw mint green circular background (#A7E8C9)
        g2d.setColor(new Color(0xA7, 0xE8, 0xC9));
        g2d.fill(new Ellipse2D.Double(0, 0, width, height));

        // Draw inner ring border (#238B63)
        g2d.setColor(new Color(0x23, 0x8B, 0x63));
        g2d.setStroke(new BasicStroke(6f));
        g2d.draw(new Ellipse2D.Double(8, 8, width - 16, height - 16));

        // Draw cow head silhouette in primary green (#238B63)
        // Cow Ears
        Polygon leftEar = new Polygon(new int[]{70, 20, 75}, new int[]{100, 80, 120}, 3);
        Polygon rightEar = new Polygon(new int[]{186, 236, 181}, new int[]{100, 80, 120}, 3);
        g2d.fill(leftEar);
        g2d.fill(rightEar);

        // Cow Horns in darker accent (#15533B)
        g2d.setColor(new Color(0x15, 0x53, 0x3B));
        Polygon leftHorn = new Polygon(new int[]{80, 55, 95}, new int[]{85, 45, 75}, 3);
        Polygon rightHorn = new Polygon(new int[]{176, 201, 161}, new int[]{85, 45, 75}, 3);
        g2d.fill(leftHorn);
        g2d.fill(rightHorn);

        // Main Cow Head
        g2d.setColor(new Color(0x23, 0x8B, 0x63));
        g2d.fillRoundRect(70, 75, 116, 115, 30, 30);

        // Snout (#F2FAF3)
        g2d.setColor(new Color(0xF2, 0xFA, 0xF3));
        g2d.fillOval(78, 135, 100, 55);

        // Nostrils
        g2d.setColor(new Color(0x23, 0x8B, 0x63));
        g2d.fillOval(100, 155, 16, 16);
        g2d.fillOval(140, 155, 16, 16);

        // Eyes (#F2FAF3)
        g2d.setColor(new Color(0xF2, 0xFA, 0xF3));
        g2d.fillOval(90, 100, 20, 20);
        g2d.fillOval(146, 100, 20, 20);

        // Pupils (#15533B)
        g2d.setColor(new Color(0x15, 0x53, 0x3B));
        g2d.fillOval(95, 105, 10, 10);
        g2d.fillOval(151, 105, 10, 10);

        // Forehead Spot
        g2d.setColor(new Color(0xF2, 0xFA, 0xF3));
        g2d.fillOval(113, 80, 30, 20);

        g2d.dispose();

        try {
            outputFile.getParentFile().mkdirs();
            ImageIO.write(image, "png", outputFile);
            System.out.println("Logo generated successfully at: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/images/hambaa-logo.png");
        generateLogo(file);
    }
}
