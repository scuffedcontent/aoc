/*     */ package com.group_finity.mascot.generic;
/*     */ 
/*     */ import com.group_finity.mascot.image.NativeImage;
/*     */ import com.group_finity.mascot.image.TranslucentWindow;
/*     */ import com.sun.jna.examples.WindowUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JWindow;
/*     */ 
/*     */ class GenericTranslucentWindow extends JWindow implements TranslucentWindow {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private GenericNativeImage image;
/*     */   
/*     */   private JPanel panel;
/*     */   
/*  33 */   private float alpha = 1.0F;
/*     */   
/*     */   public GenericTranslucentWindow() {
/*  36 */     super(WindowUtils.getAlphaCompatibleGraphicsConfiguration());
/*  37 */     init();
/*  39 */     this.panel = new JPanel() {
/*     */         private static final long serialVersionUID = 1L;
/*     */         
/*     */         protected void paintComponent(Graphics g) {
/*  47 */           g.drawImage(GenericTranslucentWindow.this.getImage().getManagedImage(), 0, 0, null);
/*     */         }
/*     */       };
/*  50 */     setContentPane(this.panel);
/*     */   }
/*     */   
/*     */   private void init() {
/*  54 */     System.setProperty("sun.java2d.noddraw", "true");
/*  55 */     System.setProperty("sun.java2d.opengl", "true");
/*     */   }
/*     */   
/*     */   public void setVisible(boolean b) {
/*  60 */     super.setVisible(b);
/*  61 */     if (b)
/*  62 */       WindowUtils.setWindowTransparent(this, true); 
/*     */   }
/*     */   
/*     */   protected void addImpl(Component comp, Object constraints, int index) {
/*  68 */     super.addImpl(comp, constraints, index);
/*  69 */     if (comp instanceof JComponent) {
/*  70 */       JComponent jcomp = (JComponent)comp;
/*  71 */       jcomp.setOpaque(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAlpha(float alpha) {
/*  76 */     WindowUtils.setWindowAlpha(this, alpha);
/*     */   }
/*     */   
/*     */   public float getAlpha() {
/*  80 */     return this.alpha;
/*     */   }
/*     */   
/*     */   public JWindow asJWindow() {
/*  85 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  90 */     return "LayeredWindow[hashCode=" + hashCode() + ",bounds=" + getBounds() + "]";
/*     */   }
/*     */   
/*     */   public GenericNativeImage getImage() {
/*  94 */     return this.image;
/*     */   }
/*     */   
/*     */   public void setImage(NativeImage image) {
/*  98 */     this.image = (GenericNativeImage)image;
/*     */   }
/*     */   
/*     */   public void updateImage() {
/* 102 */     WindowUtils.setWindowMask(this, getImage().getIcon());
/* 103 */     validate();
/* 104 */     repaint();
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/generic/GenericTranslucentWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */