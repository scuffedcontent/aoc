/*     */ package com.group_finity.mascot.environment;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ 
/*     */ public class Area {
/*     */   private boolean visible = true;
/*     */   
/*     */   private int left;
/*     */   
/*     */   private int top;
/*     */   
/*     */   private int right;
/*     */   
/*     */   private int bottom;
/*     */   
/*     */   private int dleft;
/*     */   
/*     */   private int dtop;
/*     */   
/*     */   private int dright;
/*     */   
/*     */   private int dbottom;
/*     */   
/*  30 */   private final Wall leftBorder = new Wall(this, false);
/*     */   
/*  32 */   private final FloorCeiling topBorder = new FloorCeiling(this, false);
/*     */   
/*  34 */   private final Wall rightBorder = new Wall(this, true);
/*     */   
/*  36 */   private final FloorCeiling bottomBorder = new FloorCeiling(this, true);
/*     */   
/*     */   public boolean isVisible() {
/*  39 */     return this.visible;
/*     */   }
/*     */   
/*     */   public void setVisible(boolean visible) {
/*  43 */     this.visible = visible;
/*     */   }
/*     */   
/*     */   public int getLeft() {
/*  47 */     return this.left;
/*     */   }
/*     */   
/*     */   public void setLeft(int left) {
/*  51 */     this.left = left;
/*     */   }
/*     */   
/*     */   public int getTop() {
/*  55 */     return this.top;
/*     */   }
/*     */   
/*     */   public void setTop(int top) {
/*  59 */     this.top = top;
/*     */   }
/*     */   
/*     */   public int getRight() {
/*  63 */     return this.right;
/*     */   }
/*     */   
/*     */   public void setRight(int right) {
/*  67 */     this.right = right;
/*     */   }
/*     */   
/*     */   public int getBottom() {
/*  71 */     return this.bottom;
/*     */   }
/*     */   
/*     */   public void setBottom(int bottom) {
/*  75 */     this.bottom = bottom;
/*     */   }
/*     */   
/*     */   public int getDleft() {
/*  79 */     return this.dleft;
/*     */   }
/*     */   
/*     */   public void setDleft(int dleft) {
/*  83 */     this.dleft = dleft;
/*     */   }
/*     */   
/*     */   public int getDtop() {
/*  87 */     return this.dtop;
/*     */   }
/*     */   
/*     */   public void setDtop(int dtop) {
/*  91 */     this.dtop = dtop;
/*     */   }
/*     */   
/*     */   public int getDright() {
/*  95 */     return this.dright;
/*     */   }
/*     */   
/*     */   public void setDright(int dright) {
/*  99 */     this.dright = dright;
/*     */   }
/*     */   
/*     */   public int getDbottom() {
/* 103 */     return this.dbottom;
/*     */   }
/*     */   
/*     */   public void setDbottom(int dbottom) {
/* 107 */     this.dbottom = dbottom;
/*     */   }
/*     */   
/*     */   public Wall getLeftBorder() {
/* 111 */     return this.leftBorder;
/*     */   }
/*     */   
/*     */   public FloorCeiling getTopBorder() {
/* 115 */     return this.topBorder;
/*     */   }
/*     */   
/*     */   public Wall getRightBorder() {
/* 119 */     return this.rightBorder;
/*     */   }
/*     */   
/*     */   public FloorCeiling getBottomBorder() {
/* 123 */     return this.bottomBorder;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 127 */     return getRight() - getLeft();
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 131 */     return getBottom() - getTop();
/*     */   }
/*     */   
/*     */   public void set(Rectangle value) {
/* 136 */     setDleft(value.x - getLeft());
/* 137 */     setDtop(value.y - getTop());
/* 138 */     setDright(value.x + value.width - getRight());
/* 139 */     setDbottom(value.y + value.height - getBottom());
/* 141 */     setLeft(value.x);
/* 142 */     setTop(value.y);
/* 143 */     setRight(value.x + value.width);
/* 144 */     setBottom(value.y + value.height);
/*     */   }
/*     */   
/*     */   public boolean contains(int x, int y) {
/* 149 */     return (getLeft() <= x && x <= getRight() && getTop() <= y && y <= getBottom());
/*     */   }
/*     */   
/*     */   public Rectangle toRectangle() {
/* 153 */     return new Rectangle(this.left, this.top, this.right - this.left, this.bottom - this.top);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 158 */     return "Area [left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + "]";
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/environment/Area.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */