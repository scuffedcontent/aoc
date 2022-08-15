/*     */ package com.group_finity.mascot.menu;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.MenuSelectionManager;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.swing.event.PopupMenuEvent;
/*     */ import javax.swing.event.PopupMenuListener;
/*     */ 
/*     */ public class MenuScroller {
/*     */   private JPopupMenu menu;
/*     */   
/*     */   private Component[] menuItems;
/*     */   
/*     */   private MenuScrollItem upItem;
/*     */   
/*     */   private MenuScrollItem downItem;
/*     */   
/*  44 */   private final MenuScrollListener menuListener = new MenuScrollListener();
/*     */   
/*     */   private int scrollCount;
/*     */   
/*     */   private int interval;
/*     */   
/*     */   private int topFixedCount;
/*     */   
/*     */   private int bottomFixedCount;
/*     */   
/*  49 */   private int firstIndex = 0;
/*     */   
/*  50 */   private int keepVisibleIndex = -1;
/*     */   
/*     */   public static MenuScroller setScrollerFor(JMenu menu) {
/*  60 */     return new MenuScroller(menu);
/*     */   }
/*     */   
/*     */   public static MenuScroller setScrollerFor(JPopupMenu menu) {
/*  71 */     return new MenuScroller(menu);
/*     */   }
/*     */   
/*     */   public static MenuScroller setScrollerFor(JMenu menu, int scrollCount) {
/*  84 */     return new MenuScroller(menu, scrollCount);
/*     */   }
/*     */   
/*     */   public static MenuScroller setScrollerFor(JPopupMenu menu, int scrollCount) {
/*  97 */     return new MenuScroller(menu, scrollCount);
/*     */   }
/*     */   
/*     */   public static MenuScroller setScrollerFor(JMenu menu, int scrollCount, int interval) {
/* 111 */     return new MenuScroller(menu, scrollCount, interval);
/*     */   }
/*     */   
/*     */   public static MenuScroller setScrollerFor(JPopupMenu menu, int scrollCount, int interval) {
/* 125 */     return new MenuScroller(menu, scrollCount, interval);
/*     */   }
/*     */   
/*     */   public static MenuScroller setScrollerFor(JMenu menu, int scrollCount, int interval, int topFixedCount, int bottomFixedCount) {
/* 145 */     return new MenuScroller(menu, scrollCount, interval, topFixedCount, bottomFixedCount);
/*     */   }
/*     */   
/*     */   public static MenuScroller setScrollerFor(JPopupMenu menu, int scrollCount, int interval, int topFixedCount, int bottomFixedCount) {
/* 166 */     return new MenuScroller(menu, scrollCount, interval, topFixedCount, bottomFixedCount);
/*     */   }
/*     */   
/*     */   public MenuScroller(JMenu menu) {
/* 178 */     this(menu, 15);
/*     */   }
/*     */   
/*     */   public MenuScroller(JPopupMenu menu) {
/* 189 */     this(menu, 15);
/*     */   }
/*     */   
/*     */   public MenuScroller(JMenu menu, int scrollCount) {
/* 202 */     this(menu, scrollCount, 150);
/*     */   }
/*     */   
/*     */   public MenuScroller(JPopupMenu menu, int scrollCount) {
/* 215 */     this(menu, scrollCount, 150);
/*     */   }
/*     */   
/*     */   public MenuScroller(JMenu menu, int scrollCount, int interval) {
/* 229 */     this(menu, scrollCount, interval, 0, 0);
/*     */   }
/*     */   
/*     */   public MenuScroller(JPopupMenu menu, int scrollCount, int interval) {
/* 243 */     this(menu, scrollCount, interval, 0, 0);
/*     */   }
/*     */   
/*     */   public MenuScroller(JMenu menu, int scrollCount, int interval, int topFixedCount, int bottomFixedCount) {
/* 262 */     this(menu.getPopupMenu(), scrollCount, interval, topFixedCount, bottomFixedCount);
/*     */   }
/*     */   
/*     */   public MenuScroller(JPopupMenu menu, int scrollCount, int interval, int topFixedCount, int bottomFixedCount) {
/* 281 */     if (scrollCount <= 0 || interval <= 0)
/* 282 */       throw new IllegalArgumentException(Main.getInstance().getLanguageBundle().getString("ScrollCountIntervalBelowZeroErrorMessage")); 
/* 284 */     if (topFixedCount < 0 || bottomFixedCount < 0)
/* 285 */       throw new IllegalArgumentException(Main.getInstance().getLanguageBundle().getString("CountsCannotBeNegativeErrorMessage")); 
/* 288 */     this.upItem = new MenuScrollItem(MenuIcon.UP, -1);
/* 289 */     this.downItem = new MenuScrollItem(MenuIcon.DOWN, 1);
/* 290 */     setScrollCount(scrollCount);
/* 291 */     setInterval(interval);
/* 292 */     setTopFixedCount(topFixedCount);
/* 293 */     setBottomFixedCount(bottomFixedCount);
/* 295 */     this.menu = menu;
/* 296 */     menu.addPopupMenuListener(this.menuListener);
/*     */   }
/*     */   
/*     */   public int getInterval() {
/* 305 */     return this.interval;
/*     */   }
/*     */   
/*     */   public void setInterval(int interval) {
/* 315 */     if (interval <= 0)
/* 316 */       throw new IllegalArgumentException(Main.getInstance().getLanguageBundle().getString("IntervalBelowZeroErrorMessage")); 
/* 318 */     this.upItem.setInterval(interval);
/* 319 */     this.downItem.setInterval(interval);
/* 320 */     this.interval = interval;
/*     */   }
/*     */   
/*     */   public int getscrollCount() {
/* 329 */     return this.scrollCount;
/*     */   }
/*     */   
/*     */   public void setScrollCount(int scrollCount) {
/* 339 */     if (scrollCount <= 0)
/* 340 */       throw new IllegalArgumentException(Main.getInstance().getLanguageBundle().getString("ScrollCountErrorMessage")); 
/* 342 */     this.scrollCount = scrollCount;
/* 343 */     MenuSelectionManager.defaultManager().clearSelectedPath();
/*     */   }
/*     */   
/*     */   public int getTopFixedCount() {
/* 352 */     return this.topFixedCount;
/*     */   }
/*     */   
/*     */   public void setTopFixedCount(int topFixedCount) {
/* 361 */     if (this.firstIndex <= topFixedCount) {
/* 362 */       this.firstIndex = topFixedCount;
/*     */     } else {
/* 364 */       this.firstIndex += topFixedCount - this.topFixedCount;
/*     */     } 
/* 366 */     this.topFixedCount = topFixedCount;
/*     */   }
/*     */   
/*     */   public int getBottomFixedCount() {
/* 375 */     return this.bottomFixedCount;
/*     */   }
/*     */   
/*     */   public void setBottomFixedCount(int bottomFixedCount) {
/* 384 */     this.bottomFixedCount = bottomFixedCount;
/*     */   }
/*     */   
/*     */   public void keepVisible(JMenuItem item) {
/* 396 */     if (item == null) {
/* 397 */       this.keepVisibleIndex = -1;
/*     */     } else {
/* 399 */       int index = this.menu.getComponentIndex(item);
/* 400 */       this.keepVisibleIndex = index;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void keepVisible(int index) {
/* 413 */     this.keepVisibleIndex = index;
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 421 */     if (this.menu != null) {
/* 422 */       this.menu.removePopupMenuListener(this.menuListener);
/* 423 */       this.menu = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void finalize() throws Throwable {
/* 436 */     dispose();
/*     */   }
/*     */   
/*     */   private void refreshMenu() {
/* 440 */     if (this.menuItems != null && this.menuItems.length > 0) {
/* 441 */       this.firstIndex = Math.max(this.topFixedCount, this.firstIndex);
/* 442 */       this.firstIndex = Math.min(this.menuItems.length - this.bottomFixedCount - this.scrollCount, this.firstIndex);
/* 444 */       this.upItem.setEnabled((this.firstIndex > this.topFixedCount));
/* 445 */       this.downItem.setEnabled((this.firstIndex + this.scrollCount < this.menuItems.length - this.bottomFixedCount));
/* 447 */       this.menu.removeAll();
/*     */       int i;
/* 448 */       for (i = 0; i < this.topFixedCount; i++)
/* 449 */         this.menu.add(this.menuItems[i]); 
/* 451 */       if (this.topFixedCount > 0)
/* 452 */         this.menu.add(new JSeparator()); 
/* 455 */       this.menu.add(this.upItem);
/* 456 */       for (i = this.firstIndex; i < this.scrollCount + this.firstIndex; i++)
/* 457 */         this.menu.add(this.menuItems[i]); 
/* 459 */       this.menu.add(this.downItem);
/* 461 */       if (this.bottomFixedCount > 0)
/* 462 */         this.menu.add(new JSeparator()); 
/* 464 */       for (i = this.menuItems.length - this.bottomFixedCount; i < this.menuItems.length; i++)
/* 465 */         this.menu.add(this.menuItems[i]); 
/* 468 */       JComponent parent = (JComponent)this.upItem.getParent();
/* 469 */       parent.revalidate();
/* 470 */       parent.repaint();
/*     */     } 
/*     */   }
/*     */   
/*     */   private class MenuScrollListener implements PopupMenuListener {
/*     */     private MenuScrollListener() {}
/*     */     
/*     */     public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
/* 478 */       setMenuItems();
/* 479 */       MenuScroller.this.refreshMenu();
/*     */     }
/*     */     
/*     */     public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
/* 484 */       restoreMenuItems();
/*     */     }
/*     */     
/*     */     public void popupMenuCanceled(PopupMenuEvent e) {
/* 489 */       restoreMenuItems();
/*     */     }
/*     */     
/*     */     private void setMenuItems() {
/* 493 */       MenuScroller.this.menuItems = MenuScroller.this.menu.getComponents();
/* 494 */       if (MenuScroller.this.keepVisibleIndex >= MenuScroller.this.topFixedCount && MenuScroller.this
/* 495 */         .keepVisibleIndex <= MenuScroller.this.menuItems.length - MenuScroller.this.bottomFixedCount && (MenuScroller.this
/* 496 */         .keepVisibleIndex > MenuScroller.this.firstIndex + MenuScroller.this.scrollCount || MenuScroller.this
/* 497 */         .keepVisibleIndex < MenuScroller.this.firstIndex)) {
/* 498 */         MenuScroller.this.firstIndex = Math.min(MenuScroller.this.firstIndex, MenuScroller.this.keepVisibleIndex);
/* 499 */         MenuScroller.this.firstIndex = Math.max(MenuScroller.this.firstIndex, MenuScroller.this.keepVisibleIndex - MenuScroller.this.scrollCount + 1);
/*     */       } 
/* 501 */       if (MenuScroller.this.menuItems.length > MenuScroller.this.topFixedCount + MenuScroller.this.scrollCount + MenuScroller.this.bottomFixedCount)
/* 502 */         MenuScroller.this.refreshMenu(); 
/*     */     }
/*     */     
/*     */     private void restoreMenuItems() {
/* 507 */       MenuScroller.this.menu.removeAll();
/* 508 */       for (Component component : MenuScroller.this.menuItems)
/* 509 */         MenuScroller.this.menu.add(component); 
/*     */     }
/*     */   }
/*     */   
/*     */   private class MenuScrollTimer extends Timer {
/*     */     public MenuScrollTimer(int increment, int interval) {
/* 517 */       super(interval, new ActionListener(MenuScroller.this, increment) {
/*     */             public void actionPerformed(ActionEvent e) {
/* 521 */               MenuScroller.this.firstIndex = MenuScroller.this.firstIndex + increment;
/* 522 */               MenuScroller.this.refreshMenu();
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   private class MenuScrollItem extends JMenuItem implements ChangeListener {
/*     */     private MenuScroller.MenuScrollTimer timer;
/*     */     
/*     */     public MenuScrollItem(MenuScroller.MenuIcon icon, int increment) {
/* 534 */       setIcon(icon);
/* 535 */       setDisabledIcon(icon);
/* 536 */       this.timer = new MenuScroller.MenuScrollTimer(increment, MenuScroller.this.interval);
/* 537 */       addChangeListener(this);
/*     */     }
/*     */     
/*     */     public void setInterval(int interval) {
/* 541 */       this.timer.setDelay(interval);
/*     */     }
/*     */     
/*     */     public void stateChanged(ChangeEvent e) {
/* 546 */       if (isArmed() && !this.timer.isRunning())
/* 547 */         this.timer.start(); 
/* 549 */       if (!isArmed() && this.timer.isRunning())
/* 550 */         this.timer.stop(); 
/*     */     }
/*     */   }
/*     */   
/*     */   private enum MenuIcon implements Icon {
/* 557 */     UP((String)new int[] { 9, 1, 9 }),
/* 558 */     DOWN((String)new int[] { 1, 9, 1 });
/*     */     
/* 559 */     final int[] xPoints = new int[] { 1, 5, 9 };
/*     */     
/*     */     final int[] yPoints;
/*     */     
/*     */     MenuIcon(int... yPoints) {
/* 563 */       this.yPoints = yPoints;
/*     */     }
/*     */     
/*     */     public void paintIcon(Component c, Graphics g, int x, int y) {
/* 568 */       Dimension size = c.getSize();
/* 569 */       Graphics g2 = g.create(size.width / 2 - 5, size.height / 2 - 5, 10, 10);
/* 570 */       g2.setColor(Color.GRAY);
/* 571 */       g2.drawPolygon(this.xPoints, this.yPoints, 3);
/* 572 */       if (c.isEnabled()) {
/* 573 */         g2.setColor(Color.BLACK);
/* 574 */         g2.fillPolygon(this.xPoints, this.yPoints, 3);
/*     */       } 
/* 576 */       g2.dispose();
/*     */     }
/*     */     
/*     */     public int getIconWidth() {
/* 581 */       return 0;
/*     */     }
/*     */     
/*     */     public int getIconHeight() {
/* 586 */       return 10;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/menu/MenuScroller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */