/*    */ package com.group_finity.mascot;
/*    */ 
/*    */ public enum Platform {
/*  9 */   x86(20),
/*  9 */   x86_64(24);
/*    */   
/*    */   private final int bitmapSize;
/*    */   
/*    */   Platform(int bitmapSize) {
/* 14 */     this.bitmapSize = bitmapSize;
/*    */   }
/*    */   
/*    */   public int getBitmapSize() {
/* 18 */     return this.bitmapSize;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/Platform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */