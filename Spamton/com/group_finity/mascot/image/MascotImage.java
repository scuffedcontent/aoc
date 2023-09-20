/*    */ package com.group_finity.mascot.image;
/*    */ 
/*    */ import com.group_finity.mascot.NativeFactory;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Point;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class MascotImage {
/*    */   private final NativeImage image;
/*    */   
/*    */   private final Point center;
/*    */   
/*    */   private final Dimension size;
/*    */   
/*    */   public MascotImage(NativeImage image, Point center, Dimension size) {
/* 23 */     this.image = image;
/* 24 */     this.center = center;
/* 25 */     this.size = size;
/*    */   }
/*    */   
/*    */   public MascotImage(BufferedImage image, Point center) {
/* 29 */     this(NativeFactory.getInstance().newNativeImage(image), center, new Dimension(image.getWidth(), image.getHeight()));
/*    */   }
/*    */   
/*    */   public NativeImage getImage() {
/* 33 */     return this.image;
/*    */   }
/*    */   
/*    */   public Point getCenter() {
/* 37 */     return this.center;
/*    */   }
/*    */   
/*    */   public Dimension getSize() {
/* 41 */     return this.size;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/image/MascotImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */