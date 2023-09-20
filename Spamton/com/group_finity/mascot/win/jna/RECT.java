/*    */ package com.group_finity.mascot.win.jna;
/*    */ 
/*    */ import com.sun.jna.Structure;
/*    */ 
/*    */ public class RECT extends Structure {
/*    */   public int left;
/*    */   
/*    */   public int top;
/*    */   
/*    */   public int right;
/*    */   
/*    */   public int bottom;
/*    */   
/*    */   public int Width() {
/* 17 */     return this.right - this.left;
/*    */   }
/*    */   
/*    */   public int Height() {
/* 20 */     return this.bottom - this.top;
/*    */   }
/*    */   
/*    */   public void OffsetRect(int dx, int dy) {
/* 23 */     this.left += dx;
/* 24 */     this.right += dx;
/* 25 */     this.top += dy;
/* 26 */     this.bottom += dy;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/jna/RECT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */