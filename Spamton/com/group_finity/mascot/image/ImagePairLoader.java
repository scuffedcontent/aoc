/*    */ package com.group_finity.mascot.image;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Point;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class ImagePairLoader {
/*    */   public static void load(String name, String rightName, Point center, int scaling) throws IOException {
/*    */     BufferedImage rightImage;
/* 25 */     if (ImagePairs.contains(name + ((rightName == null) ? "" : rightName)))
/*    */       return; 
/* 28 */     BufferedImage leftImage = premultiply(ImageIO.read(ImagePairLoader.class.getResource(name)));
/* 30 */     if (rightName == null) {
/* 31 */       rightImage = flip(leftImage);
/*    */     } else {
/* 33 */       rightImage = premultiply(ImageIO.read(ImagePairLoader.class.getResource(rightName)));
/*    */     } 
/* 36 */     ImagePair ip = new ImagePair(new MascotImage(leftImage, new Point(center.x * scaling, center.y * scaling)), new MascotImage(rightImage, new Point((rightImage.getWidth() - center.x) * scaling, center.y * scaling)));
/* 37 */     ImagePairs.load(name + ((rightName == null) ? "" : rightName), ip);
/*    */   }
/*    */   
/*    */   private static BufferedImage flip(BufferedImage src) {
/* 45 */     BufferedImage copy = new BufferedImage(src.getWidth(), src.getHeight(), (src.getType() == 0) ? 2 : src.getType());
/* 47 */     for (int y = 0; y < src.getHeight(); y++) {
/* 48 */       for (int x = 0; x < src.getWidth(); x++)
/* 49 */         copy.setRGB(copy.getWidth() - x - 1, y, src.getRGB(x, y)); 
/*    */     } 
/* 52 */     return copy;
/*    */   }
/*    */   
/*    */   private static BufferedImage premultiply(BufferedImage source) {
/* 80 */     BufferedImage returnImage = new BufferedImage(source.getWidth(), source.getHeight(), (source.getType() == 0) ? 3 : source.getType());
/* 84 */     for (int y = 0; y < returnImage.getHeight(); y++) {
/* 86 */       for (int x = 0; x < returnImage.getWidth(); x++) {
/* 88 */         Color colour = new Color(source.getRGB(x, y), true);
/* 89 */         float[] components = colour.getComponents(null);
/* 90 */         components[0] = components[3] * components[0];
/* 91 */         components[1] = components[3] * components[1];
/* 92 */         components[2] = components[3] * components[2];
/* 93 */         colour = new Color(components[0], components[1], components[2], components[3]);
/* 94 */         returnImage.setRGB(x, y, colour.getRGB());
/*    */       } 
/*    */     } 
/* 98 */     return returnImage;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/image/ImagePairLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */