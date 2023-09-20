/*     */ package com.group_finity.mascot.environment;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ComplexArea {
/*  17 */   private Map<String, Area> areas = new HashMap<String, Area>();
/*     */   
/*     */   public void set(Map<String, Rectangle> rectangles) {
/*  20 */     retain(rectangles.keySet());
/*  21 */     for (Map.Entry<String, Rectangle> e : rectangles.entrySet())
/*  22 */       set(e.getKey(), e.getValue()); 
/*     */   }
/*     */   
/*     */   public void set(String name, Rectangle value) {
/*  28 */     for (Area area1 : this.areas.values()) {
/*  29 */       if (area1.getLeft() == value.x && area1
/*  30 */         .getTop() == value.y && area1
/*  31 */         .getWidth() == value.width && area1
/*  32 */         .getHeight() == value.height)
/*     */         return; 
/*     */     } 
/*  37 */     Area area = this.areas.get(name);
/*  38 */     if (area == null) {
/*  39 */       area = new Area();
/*  40 */       this.areas.put(name, area);
/*     */     } 
/*  42 */     area.set(value);
/*     */   }
/*     */   
/*     */   public void retain(Collection<String> deviceNames) {
/*  47 */     for (Iterator<String> i = this.areas.keySet().iterator(); i.hasNext(); ) {
/*  48 */       String key = i.next();
/*  49 */       if (!deviceNames.contains(key))
/*  50 */         i.remove(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public FloorCeiling getBottomBorder(Point location) {
/*  57 */     FloorCeiling ret = null;
/*  59 */     for (Area area : this.areas.values()) {
/*  60 */       if (area.getBottomBorder().isOn(location))
/*  61 */         ret = area.getBottomBorder(); 
/*     */     } 
/*  65 */     for (Area area : this.areas.values()) {
/*  66 */       if (area.getTopBorder().isOn(location))
/*  67 */         ret = null; 
/*     */     } 
/*  71 */     return ret;
/*     */   }
/*     */   
/*     */   public FloorCeiling getTopBorder(Point location) {
/*  76 */     FloorCeiling ret = null;
/*  78 */     for (Area area : this.areas.values()) {
/*  79 */       if (area.getTopBorder().isOn(location))
/*  80 */         ret = area.getTopBorder(); 
/*     */     } 
/*  84 */     for (Area area : this.areas.values()) {
/*  85 */       if (area.getBottomBorder().isOn(location))
/*  86 */         ret = null; 
/*     */     } 
/*  90 */     return ret;
/*     */   }
/*     */   
/*     */   public Wall getLeftBorder(Point location) {
/*  95 */     Wall ret = null;
/*  97 */     for (Area area : this.areas.values()) {
/*  98 */       if (area.getLeftBorder().isOn(location))
/*  99 */         ret = area.getRightBorder(); 
/*     */     } 
/* 102 */     for (Area area : this.areas.values()) {
/* 103 */       if (area.getRightBorder().isOn(location))
/* 104 */         ret = null; 
/*     */     } 
/* 107 */     return ret;
/*     */   }
/*     */   
/*     */   public Wall getRightBorder(Point location) {
/* 112 */     Wall ret = null;
/* 114 */     for (Area area : this.areas.values()) {
/* 115 */       if (area.getRightBorder().isOn(location))
/* 116 */         ret = area.getRightBorder(); 
/*     */     } 
/* 119 */     for (Area area : this.areas.values()) {
/* 120 */       if (area.getLeftBorder().isOn(location))
/* 121 */         ret = null; 
/*     */     } 
/* 124 */     return ret;
/*     */   }
/*     */   
/*     */   public Collection<Area> getAreas() {
/* 128 */     return this.areas.values();
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/environment/ComplexArea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */