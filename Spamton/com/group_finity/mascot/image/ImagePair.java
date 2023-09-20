/*    */ package com.group_finity.mascot.image;
/*    */ 
/*    */ public class ImagePair {
/*    */   private MascotImage leftImage;
/*    */   
/*    */   private MascotImage rightImage;
/*    */   
/*    */   public ImagePair(MascotImage leftImage, MascotImage rightImage) {
/* 22 */     this.leftImage = leftImage;
/* 23 */     this.rightImage = rightImage;
/*    */   }
/*    */   
/*    */   public MascotImage getImage(boolean lookRight) {
/* 29 */     return lookRight ? getRightImage() : getLeftImage();
/*    */   }
/*    */   
/*    */   private MascotImage getLeftImage() {
/* 33 */     return this.leftImage;
/*    */   }
/*    */   
/*    */   private MascotImage getRightImage() {
/* 37 */     return this.rightImage;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/image/ImagePair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */