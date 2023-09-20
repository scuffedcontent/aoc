/*    */ package com.group_finity.mascot.generic;
/*    */ 
/*    */ import com.group_finity.mascot.environment.Area;
/*    */ import com.group_finity.mascot.environment.Environment;
/*    */ import java.awt.Point;
/*    */ 
/*    */ class GenericEnvironment extends Environment {
/* 16 */   private Area activeIE = new Area();
/*    */   
/*    */   public void tick() {
/* 20 */     super.tick();
/* 21 */     this.activeIE.setVisible(false);
/*    */   }
/*    */   
/*    */   public void moveActiveIE(Point point) {}
/*    */   
/*    */   public void restoreIE() {}
/*    */   
/*    */   public Area getWorkArea() {
/* 35 */     return getScreen();
/*    */   }
/*    */   
/*    */   public Area getActiveIE() {
/* 40 */     return this.activeIE;
/*    */   }
/*    */   
/*    */   public void refreshCache() {}
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/generic/GenericEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */