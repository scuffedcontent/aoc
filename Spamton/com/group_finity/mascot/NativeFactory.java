/*    */ package com.group_finity.mascot;
/*    */ 
/*    */ import com.group_finity.mascot.environment.Environment;
/*    */ import com.group_finity.mascot.image.NativeImage;
/*    */ import com.group_finity.mascot.image.TranslucentWindow;
/*    */ import com.sun.jna.Platform;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public abstract class NativeFactory {
/*    */   private static final NativeFactory instance;
/*    */   
/*    */   static {
/* 15 */     String subpkg = "generic";
/* 17 */     if (Platform.isWindows()) {
/* 19 */       subpkg = "win";
/* 21 */     } else if (Platform.isMac()) {
/* 23 */       subpkg = "mac";
/*    */     } 
/* 26 */     String basepkg = NativeFactory.class.getName();
/* 28 */     basepkg = basepkg.substring(0, basepkg.lastIndexOf('.'));
/*    */     try {
/* 32 */       Class<? extends NativeFactory> impl = (Class)Class.forName(basepkg + "." + subpkg + ".NativeFactoryImpl");
/* 34 */       instance = impl.newInstance();
/* 36 */     } catch (ClassNotFoundException e) {
/* 37 */       throw new RuntimeException(e);
/* 38 */     } catch (InstantiationException e) {
/* 39 */       throw new RuntimeException(e);
/* 40 */     } catch (IllegalAccessException e) {
/* 41 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract TranslucentWindow newTransparentWindow();
/*    */   
/*    */   public abstract NativeImage newNativeImage(BufferedImage paramBufferedImage);
/*    */   
/*    */   public abstract Environment getEnvironment();
/*    */   
/*    */   public static NativeFactory getInstance() {
/* 46 */     return instance;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/NativeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */