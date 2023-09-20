/*     */ package com.group_finity.mascot.environment;
/*     */ 
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.MouseInfo;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class Environment {
/*  30 */   private static Rectangle screenRect = new Rectangle(new Point(0, 0), Toolkit.getDefaultToolkit().getScreenSize());
/*     */   
/*  32 */   private static Map<String, Rectangle> screenRects = new HashMap<String, Rectangle>();
/*     */   
/*     */   static {
/*  36 */     Thread thread = new Thread() {
/*     */         public void run() {
/*     */           try {
/*     */             while (true) {
/*  41 */               Environment.updateScreenRect();
/*  42 */               Thread.sleep(5000L);
/*     */             } 
/*  44 */           } catch (InterruptedException interruptedException) {
/*     */             return;
/*     */           } 
/*     */         }
/*     */       };
/*  49 */     thread.setDaemon(true);
/*  50 */     thread.setPriority(1);
/*  51 */     thread.start();
/*     */   }
/*     */   
/*     */   private static void updateScreenRect() {
/*  56 */     Rectangle virtualBounds = new Rectangle();
/*  58 */     Map<String, Rectangle> screenRects = new HashMap<String, Rectangle>();
/*  60 */     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*  61 */     GraphicsDevice[] gs = ge.getScreenDevices();
/*  63 */     for (int j = 0; j < gs.length; j++) {
/*  64 */       GraphicsDevice gd = gs[j];
/*  65 */       screenRects.put(gd.getIDstring(), gd.getDefaultConfiguration().getBounds());
/*  66 */       virtualBounds = virtualBounds.union(gd.getDefaultConfiguration().getBounds());
/*     */     } 
/*  69 */     Environment.screenRects = screenRects;
/*  71 */     screenRect = virtualBounds;
/*     */   }
/*     */   
/*     */   private static Rectangle getScreenRect() {
/*  75 */     return screenRect;
/*     */   }
/*     */   
/*     */   private static Point getCursorPos() {
/*  79 */     return MouseInfo.getPointerInfo().getLocation();
/*     */   }
/*     */   
/*  82 */   public ComplexArea complexScreen = new ComplexArea();
/*     */   
/*  84 */   public Area screen = new Area();
/*     */   
/*  86 */   public Location cursor = new Location();
/*     */   
/*     */   protected Environment() {
/*  89 */     tick();
/*     */   }
/*     */   
/*     */   public void tick() {
/*  93 */     this.screen.set(getScreenRect());
/*  94 */     this.complexScreen.set(screenRects);
/*  95 */     this.cursor.set(getCursorPos());
/*     */   }
/*     */   
/*     */   public Area getScreen() {
/*  99 */     return this.screen;
/*     */   }
/*     */   
/*     */   public Collection<Area> getScreens() {
/* 103 */     return this.complexScreen.getAreas();
/*     */   }
/*     */   
/*     */   public ComplexArea getComplexScreen() {
/* 107 */     return this.complexScreen;
/*     */   }
/*     */   
/*     */   public Location getCursor() {
/* 111 */     return this.cursor;
/*     */   }
/*     */   
/*     */   public boolean isScreenTopBottom(Point location) {
/* 117 */     int count = 0;
/* 119 */     for (Area area : getScreens()) {
/* 120 */       if (area.getTopBorder().isOn(location))
/* 121 */         count++; 
/* 123 */       if (area.getBottomBorder().isOn(location))
/* 124 */         count++; 
/*     */     } 
/* 129 */     if (count == 0) {
/* 130 */       if (getWorkArea().getTopBorder().isOn(location))
/* 131 */         return true; 
/* 133 */       if (getWorkArea().getBottomBorder().isOn(location))
/* 134 */         return true; 
/*     */     } 
/* 138 */     return (count == 1);
/*     */   }
/*     */   
/*     */   public boolean isScreenLeftRight(Point location) {
/* 144 */     int count = 0;
/* 146 */     for (Area area : getScreens()) {
/* 147 */       if (area.getLeftBorder().isOn(location))
/* 148 */         count++; 
/* 150 */       if (area.getRightBorder().isOn(location))
/* 151 */         count++; 
/*     */     } 
/* 155 */     if (count == 0) {
/* 156 */       if (getWorkArea().getLeftBorder().isOn(location))
/* 157 */         return true; 
/* 159 */       if (getWorkArea().getRightBorder().isOn(location))
/* 160 */         return true; 
/*     */     } 
/* 164 */     return (count == 1);
/*     */   }
/*     */   
/*     */   protected abstract Area getWorkArea();
/*     */   
/*     */   public abstract Area getActiveIE();
/*     */   
/*     */   public abstract void moveActiveIE(Point paramPoint);
/*     */   
/*     */   public abstract void restoreIE();
/*     */   
/*     */   public abstract void refreshCache();
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/environment/Environment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */