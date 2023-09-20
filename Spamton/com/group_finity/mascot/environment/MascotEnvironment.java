/*     */ package com.group_finity.mascot.environment;
/*     */ 
/*     */ import com.group_finity.mascot.Mascot;
/*     */ import com.group_finity.mascot.NativeFactory;
/*     */ import java.awt.Point;
/*     */ 
/*     */ public class MascotEnvironment {
/*  15 */   private Environment impl = NativeFactory.getInstance().getEnvironment();
/*     */   
/*     */   private Mascot mascot;
/*     */   
/*     */   private Area currentWorkArea;
/*     */   
/*     */   public MascotEnvironment(Mascot mascot) {
/*  22 */     this.mascot = mascot;
/*     */   }
/*     */   
/*     */   public Area getWorkArea() {
/*  27 */     if (this.currentWorkArea != null) {
/*  29 */       if (this.currentWorkArea != this.impl.getWorkArea() && this.currentWorkArea.toRectangle().contains(this.impl.getWorkArea().toRectangle()) && 
/*  30 */         this.impl.getWorkArea().contains((this.mascot.getAnchor()).x, (this.mascot.getAnchor()).y)) {
/*  31 */         this.currentWorkArea = this.impl.getWorkArea();
/*  32 */         return this.currentWorkArea;
/*     */       } 
/*  37 */       if (this.currentWorkArea.contains((this.mascot.getAnchor()).x, (this.mascot.getAnchor()).y))
/*  38 */         return this.currentWorkArea; 
/*     */     } 
/*  42 */     if (this.impl.getWorkArea().contains((this.mascot.getAnchor()).x, (this.mascot.getAnchor()).y)) {
/*  43 */       this.currentWorkArea = this.impl.getWorkArea();
/*  44 */       return this.currentWorkArea;
/*     */     } 
/*  47 */     for (Area area : this.impl.getScreens()) {
/*  48 */       if (area.contains((this.mascot.getAnchor()).x, (this.mascot.getAnchor()).y)) {
/*  49 */         this.currentWorkArea = area;
/*  50 */         return this.currentWorkArea;
/*     */       } 
/*     */     } 
/*  54 */     this.currentWorkArea = this.impl.getWorkArea();
/*  55 */     return this.currentWorkArea;
/*     */   }
/*     */   
/*     */   public Area getActiveIE() {
/*  59 */     return this.impl.getActiveIE();
/*     */   }
/*     */   
/*     */   public Border getCeiling() {
/*  63 */     return getCeiling(false);
/*     */   }
/*     */   
/*     */   public Border getCeiling(boolean ignoreSeparator) {
/*  67 */     if (getActiveIE().getBottomBorder().isOn(this.mascot.getAnchor()))
/*  68 */       return getActiveIE().getBottomBorder(); 
/*  70 */     if (getWorkArea().getTopBorder().isOn(this.mascot.getAnchor()) && (
/*  71 */       !ignoreSeparator || isScreenTopBottom()))
/*  72 */       return getWorkArea().getTopBorder(); 
/*  75 */     return NotOnBorder.INSTANCE;
/*     */   }
/*     */   
/*     */   public ComplexArea getComplexScreen() {
/*  79 */     return this.impl.getComplexScreen();
/*     */   }
/*     */   
/*     */   public Location getCursor() {
/*  83 */     return this.impl.getCursor();
/*     */   }
/*     */   
/*     */   public Border getFloor() {
/*  87 */     return getFloor(false);
/*     */   }
/*     */   
/*     */   public Border getFloor(boolean ignoreSeparator) {
/*  91 */     if (getActiveIE().getTopBorder().isOn(this.mascot.getAnchor()))
/*  92 */       return getActiveIE().getTopBorder(); 
/*  94 */     if (getWorkArea().getBottomBorder().isOn(this.mascot.getAnchor()) && (
/*  95 */       !ignoreSeparator || isScreenTopBottom()))
/*  96 */       return getWorkArea().getBottomBorder(); 
/*  99 */     return NotOnBorder.INSTANCE;
/*     */   }
/*     */   
/*     */   public Area getScreen() {
/* 103 */     return this.impl.getScreen();
/*     */   }
/*     */   
/*     */   public Border getWall() {
/* 107 */     return getWall(false);
/*     */   }
/*     */   
/*     */   public Border getWall(boolean ignoreSeparator) {
/* 111 */     if (this.mascot.isLookRight()) {
/* 112 */       if (getActiveIE().getLeftBorder().isOn(this.mascot.getAnchor()))
/* 113 */         return getActiveIE().getLeftBorder(); 
/* 116 */       if (getWorkArea().getRightBorder().isOn(this.mascot.getAnchor()) && (
/* 117 */         !ignoreSeparator || isScreenLeftRight()))
/* 118 */         return getWorkArea().getRightBorder(); 
/*     */     } else {
/* 122 */       if (getActiveIE().getRightBorder().isOn(this.mascot.getAnchor()))
/* 123 */         return getActiveIE().getRightBorder(); 
/* 126 */       if (getWorkArea().getLeftBorder().isOn(this.mascot.getAnchor()) && (
/* 127 */         !ignoreSeparator || isScreenLeftRight()))
/* 128 */         return getWorkArea().getLeftBorder(); 
/*     */     } 
/* 133 */     return NotOnBorder.INSTANCE;
/*     */   }
/*     */   
/*     */   public void moveActiveIE(Point point) {
/* 137 */     this.impl.moveActiveIE(point);
/*     */   }
/*     */   
/*     */   public void restoreIE() {
/* 141 */     this.impl.restoreIE();
/*     */   }
/*     */   
/*     */   private boolean isScreenTopBottom() {
/* 145 */     return this.impl.isScreenTopBottom(this.mascot.getAnchor());
/*     */   }
/*     */   
/*     */   private boolean isScreenLeftRight() {
/* 149 */     return this.impl.isScreenLeftRight(this.mascot.getAnchor());
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/environment/MascotEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */