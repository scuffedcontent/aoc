/*    */ package com.group_finity.mascot.environment;
/*    */ 
/*    */ import java.awt.Point;
/*    */ 
/*    */ public class Location {
/*    */   private int x;
/*    */   
/*    */   private int y;
/*    */   
/*    */   private int dx;
/*    */   
/*    */   private int dy;
/*    */   
/*    */   public int getX() {
/* 18 */     return this.x;
/*    */   }
/*    */   
/*    */   public void setX(int x) {
/* 21 */     this.x = x;
/*    */   }
/*    */   
/*    */   public int getY() {
/* 24 */     return this.y;
/*    */   }
/*    */   
/*    */   public void setY(int y) {
/* 27 */     this.y = y;
/*    */   }
/*    */   
/*    */   public int getDx() {
/* 30 */     return this.dx;
/*    */   }
/*    */   
/*    */   public void setDx(int dx) {
/* 33 */     this.dx = dx;
/*    */   }
/*    */   
/*    */   public int getDy() {
/* 36 */     return this.dy;
/*    */   }
/*    */   
/*    */   public void setDy(int dy) {
/* 39 */     this.dy = dy;
/*    */   }
/*    */   
/*    */   public void set(Point value) {
/* 43 */     setDx((getDx() + value.x - getX()) / 2);
/* 44 */     setDy((getDy() + value.y - getY()) / 2);
/* 46 */     setX(value.x);
/* 47 */     setY(value.y);
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/environment/Location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */