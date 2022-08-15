/*     */ package com.group_finity.mascot.win;
/*     */ 
/*     */ import com.group_finity.mascot.image.NativeImage;
/*     */ import com.group_finity.mascot.image.TranslucentWindow;
/*     */ import com.group_finity.mascot.win.jna.BLENDFUNCTION;
/*     */ import com.group_finity.mascot.win.jna.Gdi32;
/*     */ import com.group_finity.mascot.win.jna.POINT;
/*     */ import com.group_finity.mascot.win.jna.RECT;
/*     */ import com.group_finity.mascot.win.jna.SIZE;
/*     */ import com.group_finity.mascot.win.jna.User32;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import java.awt.Graphics;
/*     */ import javax.swing.JWindow;
/*     */ 
/*     */ class WindowsTranslucentWindow extends JWindow implements TranslucentWindow {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private WindowsNativeImage image;
/*     */   
/*     */   public JWindow asJWindow() {
/*  33 */     return this;
/*     */   }
/*     */   
/*     */   private void paint(Pointer imageHandle, int alpha) {
/*  45 */     Pointer hWnd = Native.getComponentPointer(this);
/*  47 */     if (User32.INSTANCE.IsWindow(hWnd) != 0) {
/*  49 */       int exStyle = User32.INSTANCE.GetWindowLongW(hWnd, -20);
/*  50 */       if ((exStyle & 0x80000) == 0)
/*  51 */         User32.INSTANCE.SetWindowLongW(hWnd, -20, exStyle | 0x80000); 
/*  55 */       Pointer clientDC = User32.INSTANCE.GetDC(hWnd);
/*  56 */       Pointer memDC = Gdi32.INSTANCE.CreateCompatibleDC(clientDC);
/*  57 */       Pointer oldBmp = Gdi32.INSTANCE.SelectObject(memDC, imageHandle);
/*  59 */       User32.INSTANCE.ReleaseDC(hWnd, clientDC);
/*  62 */       RECT windowRect = new RECT();
/*  63 */       User32.INSTANCE.GetWindowRect(hWnd, windowRect);
/*  66 */       BLENDFUNCTION bf = new BLENDFUNCTION();
/*  67 */       bf.BlendOp = 0;
/*  68 */       bf.BlendFlags = 0;
/*  69 */       bf.SourceConstantAlpha = (byte)alpha;
/*  70 */       bf.AlphaFormat = 1;
/*  72 */       POINT lt = new POINT();
/*  73 */       lt.x = windowRect.left;
/*  74 */       lt.y = windowRect.top;
/*  75 */       SIZE size = new SIZE();
/*  76 */       size.cx = windowRect.Width();
/*  77 */       size.cy = windowRect.Height();
/*  78 */       POINT zero = new POINT();
/*  79 */       User32.INSTANCE.UpdateLayeredWindow(hWnd, Pointer.NULL, lt, size, memDC, zero, 0, bf, 2);
/*  85 */       Gdi32.INSTANCE.SelectObject(memDC, oldBmp);
/*  86 */       Gdi32.INSTANCE.DeleteDC(memDC);
/*     */     } 
/*     */   }
/*     */   
/* 105 */   private int alpha = 255;
/*     */   
/*     */   public String toString() {
/* 109 */     return "LayeredWindow[hashCode=" + hashCode() + ",bounds=" + getBounds() + "]";
/*     */   }
/*     */   
/*     */   public void paint(Graphics g) {
/* 114 */     if (getImage() != null)
/* 116 */       paint(getImage().getHandle(), getAlpha()); 
/*     */   }
/*     */   
/*     */   private WindowsNativeImage getImage() {
/* 121 */     return this.image;
/*     */   }
/*     */   
/*     */   public void setImage(NativeImage image) {
/* 125 */     this.image = (WindowsNativeImage)image;
/*     */   }
/*     */   
/*     */   public int getAlpha() {
/* 129 */     return this.alpha;
/*     */   }
/*     */   
/*     */   public void setAlpha(int alpha) {
/* 133 */     this.alpha = alpha;
/*     */   }
/*     */   
/*     */   public void updateImage() {
/* 138 */     repaint();
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/WindowsTranslucentWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */