/*    */ package com.group_finity.mascot.generic.jna;
/*    */ 
/*    */ import com.sun.jna.examples.WindowUtils;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JWindow;
/*    */ 
/*    */ public class TranslucentFrame extends JWindow {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/* 20 */   private float alpha = 1.0F;
/*    */   
/*    */   public TranslucentFrame() {
/* 23 */     super(WindowUtils.getAlphaCompatibleGraphicsConfiguration());
/* 24 */     init();
/*    */   }
/*    */   
/*    */   private void init() {
/* 28 */     System.setProperty("sun.java2d.noddraw", "true");
/* 29 */     System.setProperty("sun.java2d.opengl", "true");
/*    */   }
/*    */   
/*    */   public void setVisible(boolean b) {
/* 34 */     super.setVisible(b);
/* 35 */     if (b)
/* 36 */       WindowUtils.setWindowTransparent(this, true); 
/*    */   }
/*    */   
/*    */   protected void addImpl(Component comp, Object constraints, int index) {
/* 42 */     super.addImpl(comp, constraints, index);
/* 43 */     if (comp instanceof JComponent) {
/* 44 */       JComponent jcomp = (JComponent)comp;
/* 45 */       jcomp.setOpaque(false);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setAlpha(float alpha) {
/* 50 */     WindowUtils.setWindowAlpha(this, alpha);
/*    */   }
/*    */   
/*    */   public float getAlpha() {
/* 54 */     return this.alpha;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/generic/jna/TranslucentFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */