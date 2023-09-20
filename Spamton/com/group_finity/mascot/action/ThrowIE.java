/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.Main;
/*    */ import com.group_finity.mascot.animation.Animation;
/*    */ import com.group_finity.mascot.environment.Area;
/*    */ import com.group_finity.mascot.exception.LostGroundException;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.awt.Point;
/*    */ import java.util.List;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class ThrowIE extends Animate {
/* 20 */   private static final Logger log = Logger.getLogger(ThrowIE.class.getName());
/*    */   
/*    */   public static final String PARAMETER_INITIALVX = "InitialVX";
/*    */   
/*    */   private static final int DEFAULT_INITIALVX = 32;
/*    */   
/*    */   public static final String PARAMETER_INITIALVY = "InitialVY";
/*    */   
/*    */   private static final int DEFAULT_INITIALVY = -10;
/*    */   
/*    */   public static final String PARAMETER_GRAVITY = "Gravity";
/*    */   
/*    */   private static final double DEFAULT_GRAVITY = 0.5D;
/*    */   
/*    */   public ThrowIE(ResourceBundle schema, List<Animation> animations, VariableMap params) {
/* 36 */     super(schema, animations, params);
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws VariableException {
/* 42 */     if (!Boolean.parseBoolean(Main.getInstance().getProperties().getProperty("Throwing", "true")))
/* 43 */       return false; 
/* 45 */     boolean ieVisible = getEnvironment().getActiveIE().isVisible();
/* 47 */     return (super.hasNext() && ieVisible);
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 53 */     super.tick();
/* 55 */     Area activeIE = getEnvironment().getActiveIE();
/* 57 */     if (activeIE.isVisible())
/* 58 */       if (getMascot().isLookRight()) {
/* 59 */         getEnvironment().moveActiveIE(new Point(activeIE.getLeft() + getInitialVx(), activeIE.getTop() + 
/* 60 */               getInitialVy() + (int)(getTime() * getGravity())));
/*    */       } else {
/* 62 */         getEnvironment().moveActiveIE(new Point(activeIE.getLeft() - getInitialVx(), activeIE.getTop() + 
/* 63 */               getInitialVy() + (int)(getTime() * getGravity())));
/*    */       }  
/*    */   }
/*    */   
/*    */   private int getInitialVx() throws VariableException {
/* 71 */     return ((Number)eval(getSchema().getString("InitialVX"), Number.class, Integer.valueOf(32))).intValue();
/*    */   }
/*    */   
/*    */   private int getInitialVy() throws VariableException {
/* 76 */     return ((Number)eval(getSchema().getString("InitialVY"), Number.class, Integer.valueOf(-10))).intValue();
/*    */   }
/*    */   
/*    */   private double getGravity() throws VariableException {
/* 81 */     return ((Number)eval(getSchema().getString("Gravity"), Number.class, Double.valueOf(0.5D))).doubleValue();
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/ThrowIE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */