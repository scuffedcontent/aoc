/*    */ package com.group_finity.mascot.win;
/*    */ 
/*    */ import com.group_finity.mascot.NativeFactory;
/*    */ import com.group_finity.mascot.environment.Environment;
/*    */ import com.group_finity.mascot.image.NativeImage;
/*    */ import com.group_finity.mascot.image.TranslucentWindow;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class NativeFactoryImpl extends NativeFactory {
/* 17 */   private Environment environment = new WindowsEnvironment();
/*    */   
/*    */   public Environment getEnvironment() {
/* 21 */     return this.environment;
/*    */   }
/*    */   
/*    */   public NativeImage newNativeImage(BufferedImage src) {
/* 26 */     return new WindowsNativeImage(src);
/*    */   }
/*    */   
/*    */   public TranslucentWindow newTransparentWindow() {
/* 31 */     return new WindowsTranslucentWindow();
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/NativeFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */