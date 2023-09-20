/*     */ package com.group_finity.mascot.win;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.image.NativeImage;
/*     */ import com.group_finity.mascot.win.jna.BITMAP;
/*     */ import com.group_finity.mascot.win.jna.BITMAPINFOHEADER;
/*     */ import com.group_finity.mascot.win.jna.Gdi32;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.ImageProducer;
/*     */ 
/*     */ class WindowsNativeImage implements NativeImage {
/*     */   private final BufferedImage managedImage;
/*     */   
/*     */   private final Pointer nativeHandle;
/*     */   
/*     */   private static Pointer createNative(int width, int height) {
/*  35 */     BITMAPINFOHEADER bmi = new BITMAPINFOHEADER();
/*  36 */     bmi.biSize = 40;
/*  37 */     bmi.biWidth = width;
/*  38 */     bmi.biHeight = height;
/*  39 */     bmi.biPlanes = 1;
/*  40 */     bmi.biBitCount = 32;
/*  42 */     Pointer hBitmap = Gdi32.INSTANCE.CreateDIBSection(Pointer.NULL, bmi, 0, Pointer.NULL, Pointer.NULL, 0);
/*  45 */     return hBitmap;
/*     */   }
/*     */   
/*     */   private static void flushNative(Pointer nativeHandle, int[] rgb, int scaling) {
/*  55 */     BITMAP bmp = new BITMAP();
/*  56 */     Gdi32.INSTANCE.GetObjectW(nativeHandle, Main.getInstance().getPlatform().getBitmapSize() + Native.POINTER_SIZE, bmp);
/*  59 */     int width = bmp.bmWidth;
/*  60 */     int height = bmp.bmHeight;
/*  61 */     int destPitch = (bmp.bmWidth * bmp.bmBitsPixel + 31) / 32 * 4;
/*  62 */     int destIndex = destPitch * (height - 1);
/*  63 */     int srcColIndex = 0;
/*  64 */     int srcRowIndex = 0;
/*  66 */     for (int y = 0; y < height; y++) {
/*  68 */       for (int x = 0; x < width; x++) {
/*  75 */         bmp.bmBits.setInt((destIndex + x * 4), ((rgb[srcColIndex / scaling] & 0xFF000000) == 0) ? 0 : rgb[srcColIndex / scaling]);
/*  78 */         srcColIndex++;
/*     */       } 
/*  81 */       destIndex -= destPitch;
/*  84 */       srcRowIndex++;
/*  85 */       if (srcRowIndex != scaling) {
/*  87 */         srcColIndex -= width;
/*     */       } else {
/*  91 */         srcRowIndex = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void freeNative(Pointer nativeHandle) {
/* 102 */     Gdi32.INSTANCE.DeleteObject(nativeHandle);
/*     */   }
/*     */   
/*     */   public WindowsNativeImage(BufferedImage image) {
/* 116 */     int scaling = Integer.parseInt(Main.getInstance().getProperties().getProperty("Scaling", "1"));
/* 118 */     this.managedImage = image;
/* 119 */     this.nativeHandle = createNative(getManagedImage().getWidth() * scaling, getManagedImage().getHeight() * scaling);
/* 121 */     int[] rbgValues = new int[getManagedImage().getWidth() * getManagedImage().getHeight() * scaling * scaling];
/* 122 */     getManagedImage().getRGB(0, 0, getManagedImage().getWidth(), getManagedImage().getHeight(), rbgValues, 0, 
/* 123 */         getManagedImage().getWidth());
/* 125 */     flushNative(getNativeHandle(), rbgValues, scaling);
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 130 */     super.finalize();
/* 131 */     freeNative(getNativeHandle());
/*     */   }
/*     */   
/*     */   public void update() {}
/*     */   
/*     */   public void flush() {
/* 143 */     getManagedImage().flush();
/*     */   }
/*     */   
/*     */   public Pointer getHandle() {
/* 147 */     return getNativeHandle();
/*     */   }
/*     */   
/*     */   public Graphics getGraphics() {
/* 151 */     return getManagedImage().createGraphics();
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 155 */     return getManagedImage().getHeight();
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 159 */     return getManagedImage().getWidth();
/*     */   }
/*     */   
/*     */   public int getHeight(ImageObserver observer) {
/* 163 */     return getManagedImage().getHeight(observer);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, ImageObserver observer) {
/* 167 */     return getManagedImage().getProperty(name, observer);
/*     */   }
/*     */   
/*     */   public ImageProducer getSource() {
/* 171 */     return getManagedImage().getSource();
/*     */   }
/*     */   
/*     */   public int getWidth(ImageObserver observer) {
/* 175 */     return getManagedImage().getWidth(observer);
/*     */   }
/*     */   
/*     */   private BufferedImage getManagedImage() {
/* 179 */     return this.managedImage;
/*     */   }
/*     */   
/*     */   private Pointer getNativeHandle() {
/* 183 */     return this.nativeHandle;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/WindowsNativeImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */