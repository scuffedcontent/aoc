/*    */ package com.group_finity.mascot.environment;
/*    */ 
/*    */ import java.awt.Point;
/*    */ 
/*    */ public class Wall implements Border {
/*    */   private Area area;
/*    */   
/*    */   private boolean right;
/*    */   
/*    */   public Wall(Area area, boolean right) {
/* 17 */     this.area = area;
/* 18 */     this.right = right;
/*    */   }
/*    */   
/*    */   public Area getArea() {
/* 22 */     return this.area;
/*    */   }
/*    */   
/*    */   public boolean isRight() {
/* 26 */     return this.right;
/*    */   }
/*    */   
/*    */   public int getX() {
/* 30 */     return isRight() ? getArea().getRight() : getArea().getLeft();
/*    */   }
/*    */   
/*    */   public int getTop() {
/* 34 */     return getArea().getTop();
/*    */   }
/*    */   
/*    */   public int getBottom() {
/* 38 */     return getArea().getBottom();
/*    */   }
/*    */   
/*    */   public int getDX() {
/* 42 */     return isRight() ? getArea().getDright() : getArea().getDleft();
/*    */   }
/*    */   
/*    */   public int getDTop() {
/* 46 */     return getArea().getDtop();
/*    */   }
/*    */   
/*    */   public int getDBottom() {
/* 50 */     return getArea().getDbottom();
/*    */   }
/*    */   
/*    */   public int getHeight() {
/* 54 */     return getArea().getHeight();
/*    */   }
/*    */   
/*    */   public boolean isOn(Point location) {
/* 59 */     return (getArea().isVisible() && getX() == location.x && getTop() <= location.y && location.y <= 
/* 60 */       getBottom());
/*    */   }
/*    */   
/*    */   public Point move(Point location) {
/* 65 */     if (!getArea().isVisible())
/* 66 */       return location; 
/* 69 */     int d = getBottom() - getDBottom() - getTop() - getDTop();
/* 70 */     if (d == 0)
/* 71 */       return location; 
/* 75 */     Point newLocation = new Point(location.x + getDX(), (location.y - getTop() - getDTop()) * (getBottom() - getTop()) / d + getTop());
/* 77 */     if (Math.abs(newLocation.x - location.x) >= 80 || Math.abs(newLocation.y - location.y) >= 80)
/* 78 */       return location; 
/* 80 */     return newLocation;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/environment/Wall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */