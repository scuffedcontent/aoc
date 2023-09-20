/*    */ package com.group_finity.mascot.generic;
/*    */ 
/*    */ import com.group_finity.mascot.NativeFactory;
/*    */ import com.group_finity.mascot.environment.Environment;
/*    */ import com.group_finity.mascot.image.NativeImage;
/*    */ import com.group_finity.mascot.image.TranslucentWindow;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class NativeFactoryImpl extends NativeFactory {
/* 17 */   private Environment environment = new GenericEnvironment();
/*    */   
/*    */   public Environment getEnvironment() {
/* 20 */     return this.environment;
/*    */   }
/*    */   
/*    */   public NativeImage newNativeImage(BufferedImage src) {
/* 24 */     return new GenericNativeImage(src);
/*    */   }
/*    */   
/*    */   public TranslucentWindow newTransparentWindow() {
/* 28 */     return new GenericTranslucentWindow();
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/generic/NativeFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */