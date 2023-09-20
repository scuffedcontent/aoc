/*    */ package com.group_finity.mascot.generic;
/*    */ 
/*    */ import com.group_finity.mascot.image.NativeImage;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.ImageObserver;
/*    */ import java.awt.image.ImageProducer;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ class GenericNativeImage implements NativeImage {
/*    */   private final BufferedImage managedImage;
/*    */   
/*    */   private final Icon icon;
/*    */   
/*    */   public GenericNativeImage(BufferedImage image) {
/* 32 */     this.managedImage = image;
/* 33 */     this.icon = new ImageIcon(image);
/*    */   }
/*    */   
/*    */   protected void finalize() throws Throwable {
/* 38 */     super.finalize();
/*    */   }
/*    */   
/*    */   public void flush() {
/* 42 */     getManagedImage().flush();
/*    */   }
/*    */   
/*    */   public Graphics getGraphics() {
/* 46 */     return getManagedImage().createGraphics();
/*    */   }
/*    */   
/*    */   public int getHeight() {
/* 50 */     return getManagedImage().getHeight();
/*    */   }
/*    */   
/*    */   public int getWidth() {
/* 54 */     return getManagedImage().getWidth();
/*    */   }
/*    */   
/*    */   public int getHeight(ImageObserver observer) {
/* 58 */     return getManagedImage().getHeight(observer);
/*    */   }
/*    */   
/*    */   public Object getProperty(String name, ImageObserver observer) {
/* 62 */     return getManagedImage().getProperty(name, observer);
/*    */   }
/*    */   
/*    */   public ImageProducer getSource() {
/* 66 */     return getManagedImage().getSource();
/*    */   }
/*    */   
/*    */   public int getWidth(ImageObserver observer) {
/* 70 */     return getManagedImage().getWidth(observer);
/*    */   }
/*    */   
/*    */   BufferedImage getManagedImage() {
/* 74 */     return this.managedImage;
/*    */   }
/*    */   
/*    */   public Icon getIcon() {
/* 78 */     return this.icon;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/generic/GenericNativeImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */