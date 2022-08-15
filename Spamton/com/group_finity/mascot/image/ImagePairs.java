/*    */ package com.group_finity.mascot.image;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ public class ImagePairs {
/* 13 */   public static Hashtable<String, ImagePair> imagepairs = new Hashtable<String, ImagePair>();
/*    */   
/*    */   public static void load(String filename, ImagePair imagepair) {
/* 16 */     if (!imagepairs.containsKey(filename))
/* 17 */       imagepairs.put(filename, imagepair); 
/*    */   }
/*    */   
/*    */   public static ImagePair getImagePair(String filename) {
/* 21 */     if (!imagepairs.containsKey(filename))
/* 22 */       return null; 
/* 23 */     ImagePair ip = imagepairs.get(filename);
/* 24 */     return ip;
/*    */   }
/*    */   
/*    */   public static boolean contains(String filename) {
/* 28 */     return imagepairs.containsKey(filename);
/*    */   }
/*    */   
/*    */   public static MascotImage getImage(String filename, boolean isLookRight) {
/* 32 */     if (!imagepairs.containsKey(filename))
/* 33 */       return null; 
/* 34 */     return ((ImagePair)imagepairs.get(filename)).getImage(isLookRight);
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/image/ImagePairs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */