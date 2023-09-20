/*     */ package com.group_finity.mascot.win;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.environment.Area;
/*     */ import com.group_finity.mascot.environment.Environment;
/*     */ import com.group_finity.mascot.win.jna.Gdi32;
/*     */ import com.group_finity.mascot.win.jna.RECT;
/*     */ import com.group_finity.mascot.win.jna.User32;
/*     */ import com.sun.jna.Pointer;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ 
/*     */ class WindowsEnvironment extends Environment {
/*     */   private static Rectangle getWorkAreaRect() {
/*  24 */     RECT rect = new RECT();
/*  25 */     User32.INSTANCE.SystemParametersInfoW(48, 0, rect, 0);
/*  26 */     return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/*     */   }
/*     */   
/*  28 */   private static HashMap<Pointer, Boolean> ieCache = new LinkedHashMap<Pointer, Boolean>();
/*     */   
/*  30 */   private static String[] windowTitles = null;
/*     */   
/*     */   static int f;
/*     */   
/*     */   private static boolean isIE(Pointer ie) {
/*  35 */     Boolean cache = ieCache.get(ie);
/*  36 */     if (cache != null)
/*  38 */       return cache.booleanValue(); 
/*  41 */     char[] title = new char[1024];
/*  43 */     int titleLength = User32.INSTANCE.GetWindowTextW(ie, title, 1024);
/*  45 */     if (windowTitles == null)
/*  47 */       windowTitles = Main.getInstance().getProperties().getProperty("InteractiveWindows", "").split("/"); 
/*  50 */     for (int index = 0; index < windowTitles.length; index++) {
/*  52 */       if (!windowTitles[index].trim().isEmpty() && (new String(title, 0, titleLength)).contains(windowTitles[index])) {
/*  54 */         ieCache.put(ie, Boolean.valueOf(true));
/*  55 */         return true;
/*     */       } 
/*     */     } 
/*  70 */     ieCache.put(ie, Boolean.valueOf(false));
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   private static Pointer findActiveIE() {
/*  78 */     Pointer ie = User32.INSTANCE.GetWindow(User32.INSTANCE.GetForegroundWindow(), 0);
/*  80 */     while (User32.INSTANCE.IsWindow(ie) != 0) {
/*  83 */       if (User32.INSTANCE.IsWindowVisible(ie) != 0) {
/*  85 */         if ((User32.INSTANCE.GetWindowLongW(ie, -16) & 0x1000000) != 0)
/*  87 */           return null; 
/*  90 */         if (isIE(ie) && User32.INSTANCE.IsIconic(ie) == 0)
/*     */           break; 
/*     */       } 
/*  96 */       ie = User32.INSTANCE.GetWindow(ie, 2);
/*     */     } 
/* 100 */     if (User32.INSTANCE.IsWindow(ie) == 0)
/* 102 */       return null; 
/* 105 */     return ie;
/*     */   }
/*     */   
/*     */   private static Rectangle getActiveIERect() {
/* 111 */     Pointer ie = findActiveIE();
/* 113 */     RECT out = new RECT();
/* 114 */     User32.INSTANCE.GetWindowRect(ie, out);
/* 115 */     RECT in = new RECT();
/* 116 */     if (getWindowRgnBox(ie, in) == 0) {
/* 118 */       in.left = 0;
/* 119 */       in.top = 0;
/* 120 */       out.right -= out.left;
/* 121 */       out.bottom -= out.top;
/*     */     } 
/* 124 */     return new Rectangle(out.left + in.left, out.top + in.top, in.Width(), in.Height());
/*     */   }
/*     */   
/*     */   private static int getWindowRgnBox(Pointer window, RECT rect) {
/* 130 */     Pointer hRgn = Gdi32.INSTANCE.CreateRectRgn(0, 0, 0, 0);
/*     */     try {
/* 133 */       if (User32.INSTANCE.GetWindowRgn(window, hRgn) == 0)
/* 135 */         return 0; 
/* 137 */       Gdi32.INSTANCE.GetRgnBox(hRgn, rect);
/* 138 */       return 1;
/*     */     } finally {
/* 142 */       Gdi32.INSTANCE.DeleteObject(hRgn);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean moveIE(Pointer ie, Rectangle rect) {
/* 149 */     if (ie == null)
/* 151 */       return false; 
/* 154 */     RECT out = new RECT();
/* 155 */     User32.INSTANCE.GetWindowRect(ie, out);
/* 156 */     RECT in = new RECT();
/* 157 */     if (getWindowRgnBox(ie, in) == 0) {
/* 159 */       in.left = 0;
/* 160 */       in.top = 0;
/* 161 */       out.right -= out.left;
/* 162 */       out.bottom -= out.top;
/*     */     } 
/* 165 */     User32.INSTANCE.MoveWindow(ie, rect.x - in.left, rect.y - in.top, rect.width + out.Width() - in.Width(), rect.height + out
/* 166 */         .Height() - in.Height(), 1);
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   private static void restoreAllIEs() {
/* 174 */     RECT workArea = new RECT();
/* 175 */     User32.INSTANCE.SystemParametersInfoW(48, 0, workArea, 0);
/* 177 */     Pointer ie = User32.INSTANCE.GetWindow(User32.INSTANCE.GetForegroundWindow(), 0);
/* 179 */     while (User32.INSTANCE.IsWindow(ie) != 0) {
/* 181 */       if (isIE(ie)) {
/* 184 */         RECT rect = new RECT();
/* 185 */         User32.INSTANCE.GetWindowRect(ie, rect);
/* 186 */         if (rect.right <= workArea.left + 100 || rect.bottom <= workArea.top + 100 || rect.left >= workArea.right - 100 || rect.top >= workArea.bottom - 100) {
/* 190 */           rect.OffsetRect(workArea.left + 100 - rect.left, workArea.top + 100 - rect.top);
/* 191 */           User32.INSTANCE.MoveWindow(ie, rect.left, rect.top, rect.Width(), rect.Height(), 1);
/* 192 */           User32.INSTANCE.BringWindowToTop(ie);
/*     */         } 
/*     */         break;
/*     */       } 
/* 198 */       ie = User32.INSTANCE.GetWindow(ie, 2);
/*     */     } 
/*     */   }
/*     */   
/* 201 */   public static Area workArea = new Area();
/*     */   
/* 202 */   public static Area activeIE = new Area();
/*     */   
/*     */   public void tick() {
/* 207 */     super.tick();
/* 208 */     workArea.set(getWorkAreaRect());
/* 210 */     Rectangle ieRect = getActiveIERect();
/* 211 */     activeIE.setVisible((ieRect != null && ieRect.intersects(getScreen().toRectangle())));
/* 212 */     activeIE.set((ieRect == null) ? new Rectangle(-1, -1, 0, 0) : ieRect);
/*     */   }
/*     */   
/*     */   public void moveActiveIE(Point point) {
/* 219 */     moveIE(findActiveIE(), new Rectangle(point.x, point.y, activeIE.getWidth(), activeIE.getHeight()));
/*     */   }
/*     */   
/*     */   public void restoreIE() {
/* 225 */     restoreAllIEs();
/*     */   }
/*     */   
/*     */   public Area getWorkArea() {
/* 231 */     return workArea;
/*     */   }
/*     */   
/*     */   public Area getActiveIE() {
/* 237 */     return activeIE;
/*     */   }
/*     */   
/*     */   public void refreshCache() {
/* 243 */     ieCache.clear();
/* 244 */     windowTitles = null;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/WindowsEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */