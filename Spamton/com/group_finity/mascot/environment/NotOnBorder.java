/*    */ package com.group_finity.mascot.environment;
/*    */ 
/*    */ import java.awt.Point;
/*    */ 
/*    */ public class NotOnBorder implements Border {
/* 12 */   public static final NotOnBorder INSTANCE = new NotOnBorder();
/*    */   
/*    */   public boolean isOn(Point location) {
/* 20 */     return false;
/*    */   }
/*    */   
/*    */   public Point move(Point location) {
/* 25 */     return location;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/environment/NotOnBorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */