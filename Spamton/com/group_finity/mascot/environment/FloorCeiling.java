/*    */ package com.group_finity.mascot.environment;
/*    */ 
/*    */ import java.awt.Point;
/*    */ 
/*    */ public class FloorCeiling implements Border {
/*    */   private Area area;
/*    */   
/*    */   private boolean bottom;
/*    */   
/*    */   public FloorCeiling(Area area, boolean bottom) {
/* 17 */     this.area = area;
/* 18 */     this.bottom = bottom;
/*    */   }
/*    */   
/*    */   public Area getArea() {
/* 22 */     return this.area;
/*    */   }
/*    */   
/*    */   public boolean isBottom() {
/* 26 */     return this.bottom;
/*    */   }
/*    */   
/*    */   public int getY() {
/* 30 */     return isBottom() ? getArea().getBottom() : getArea().getTop();
/*    */   }
/*    */   
/*    */   public int getLeft() {
/* 34 */     return getArea().getLeft();
/*    */   }
/*    */   
/*    */   public int getRight() {
/* 38 */     return getArea().getRight();
/*    */   }
/*    */   
/*    */   public int getDY() {
/* 42 */     return isBottom() ? getArea().getDbottom() : getArea().getDtop();
/*    */   }
/*    */   
/*    */   public int getDLeft() {
/* 46 */     return getArea().getDleft();
/*    */   }
/*    */   
/*    */   public int getDRight() {
/* 50 */     return getArea().getDright();
/*    */   }
/*    */   
/*    */   public int getWidth() {
/* 54 */     return getArea().getWidth();
/*    */   }
/*    */   
/*    */   public boolean isOn(Point location) {
/* 59 */     return (getArea().isVisible() && getY() == location.y && getLeft() <= location.x && location.x <= 
/* 60 */       getRight());
/*    */   }
/*    */   
/*    */   public Point move(Point location) {
/* 66 */     if (!getArea().isVisible())
/* 67 */       return location; 
/* 70 */     int d = getRight() - getDRight() - getLeft() - getDLeft();
/* 71 */     if (d == 0)
/* 72 */       return location; 
/* 76 */     Point newLocation = new Point((location.x - getLeft() - getDLeft()) * (getRight() - getLeft()) / d + getLeft(), location.y + getDY());
/* 78 */     if (Math.abs(newLocation.x - location.x) >= 80 || newLocation.y - location.y > 20 || newLocation.y - location.y < -80)
/* 80 */       return location; 
/* 83 */     return newLocation;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/environment/FloorCeiling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */