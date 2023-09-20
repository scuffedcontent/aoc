/*     */ package com.group_finity.mascot.menu;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.Toolkit;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ 
/*     */ public class JLongMenu extends JMenu {
/*  20 */   JLongMenu moreMenu = null;
/*     */   
/*  21 */   int maxItems = 15;
/*     */   
/*     */   public JLongMenu(String label) {
/*  24 */     super(label);
/*  25 */     JMenuItem getHeightMenu = new JMenuItem("Temporary");
/*  26 */     int menuItemHeight = (getHeightMenu.getPreferredSize()).height;
/*  27 */     int screenHeight = (Toolkit.getDefaultToolkit().getScreenSize()).height;
/*  29 */     this.maxItems = screenHeight / menuItemHeight - 2;
/*     */   }
/*     */   
/*     */   public JLongMenu(String label, int maxitems) {
/*  34 */     super(label);
/*  35 */     this.maxItems = maxitems;
/*     */   }
/*     */   
/*     */   public void setPopupMenuVisible(boolean b) {
/*  44 */     if (!isEnabled())
/*     */       return; 
/*  46 */     boolean isVisible = isPopupMenuVisible();
/*  47 */     if (b != isVisible) {
/*  50 */       isPopupMenuVisible();
/*  53 */       if (b == true && isShowing()) {
/*  54 */         Point p = getPopupMenuOrigin();
/*  55 */         getPopupMenu().show(this, p.x, p.y);
/*     */       } else {
/*  57 */         getPopupMenu().setVisible(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Point getPopupMenuOrigin() {
/*  69 */     int x = 0;
/*  70 */     int y = 0;
/*  71 */     JPopupMenu pm = getPopupMenu();
/*  73 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  74 */     Dimension s = getSize();
/*  75 */     Dimension pmSize = pm.getSize();
/*  78 */     if (pmSize.width == 0)
/*  79 */       pmSize = pm.getPreferredSize(); 
/*  81 */     Point position = getLocationOnScreen();
/*  83 */     Container parent = getParent();
/*  84 */     if (parent instanceof JPopupMenu) {
/*  88 */       if (getComponentOrientation() == ComponentOrientation.LEFT_TO_RIGHT) {
/*  90 */         if (position.x + s.width + pmSize.width < screenSize.width) {
/*  91 */           x = s.width;
/*     */         } else {
/*  93 */           x = 0 - pmSize.width;
/*     */         } 
/*  97 */       } else if (position.x < pmSize.width) {
/*  98 */         x = s.width;
/*     */       } else {
/* 100 */         x = 0 - pmSize.width;
/*     */       } 
/* 104 */       if (position.y + pmSize.height < screenSize.height) {
/* 105 */         y = 0;
/*     */       } else {
/* 107 */         y = s.height - pmSize.height;
/* 108 */         if (y < 0 - position.y)
/* 109 */           y = 0 - position.y; 
/*     */       } 
/*     */     } else {
/* 115 */       if (getComponentOrientation() == ComponentOrientation.LEFT_TO_RIGHT) {
/* 117 */         if (position.x + pmSize.width < screenSize.width) {
/* 118 */           x = 0;
/*     */         } else {
/* 120 */           x = s.width - pmSize.width;
/*     */         } 
/* 124 */       } else if (position.x + s.width < pmSize.width) {
/* 125 */         x = 0;
/*     */       } else {
/* 127 */         x = s.width - pmSize.width;
/*     */       } 
/* 131 */       if (position.y + s.height + pmSize.height < screenSize.height) {
/* 132 */         y = s.height;
/*     */       } else {
/* 134 */         y = 0 - pmSize.height;
/* 135 */         if (y < 0 - position.y)
/* 136 */           y = 0 - position.y; 
/*     */       } 
/*     */     } 
/* 139 */     return new Point(x, y);
/*     */   }
/*     */   
/*     */   public JMenuItem add(JMenuItem item) {
/* 143 */     if (this.moreMenu != null)
/* 145 */       return this.moreMenu.add(item); 
/* 148 */     if (getItemCount() < this.maxItems)
/* 150 */       return super.add(item); 
/* 155 */     this.moreMenu = new JLongMenu(Main.getInstance().getLanguageBundle().getString("More"), this.maxItems);
/* 157 */     super.add(this.moreMenu);
/* 158 */     return this.moreMenu.add(item);
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/menu/JLongMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */