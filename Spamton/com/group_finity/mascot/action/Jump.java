/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.animation.Animation;
/*    */ import com.group_finity.mascot.exception.LostGroundException;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.awt.Point;
/*    */ import java.util.List;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class Jump extends ActionBase {
/* 18 */   private static final Logger log = Logger.getLogger(Jump.class.getName());
/*    */   
/*    */   public static final String PARAMETER_TARGETX = "TargetX";
/*    */   
/*    */   private static final int DEFAULT_PARAMETERX = 0;
/*    */   
/*    */   public static final String PARAMETER_TARGETY = "TargetY";
/*    */   
/*    */   private static final int DEFAULT_PARAMETERY = 0;
/*    */   
/*    */   public static final String PARAMETER_VELOCITY = "VelocityParam";
/*    */   
/*    */   private static final double DEFAULT_VELOCITY = 20.0D;
/*    */   
/*    */   public Jump(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/* 35 */     super(schema, animations, context);
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws VariableException {
/* 40 */     int targetX = getTargetX();
/* 41 */     int targetY = getTargetY();
/* 43 */     double distanceX = (targetX - (getMascot().getAnchor()).x);
/* 44 */     double distanceY = (targetY - (getMascot().getAnchor()).y) - Math.abs(distanceX) / 2.0D;
/* 46 */     double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
/* 48 */     return (super.hasNext() && distance != 0.0D);
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 54 */     int targetX = getTargetX();
/* 55 */     int targetY = getTargetY();
/* 57 */     getMascot().setLookRight(((getMascot().getAnchor()).x < targetX));
/* 59 */     double distanceX = (targetX - (getMascot().getAnchor()).x);
/* 60 */     double distanceY = (targetY - (getMascot().getAnchor()).y) - Math.abs(distanceX) / 2.0D;
/* 62 */     double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
/* 64 */     double velocity = getVelocity();
/* 66 */     if (distance != 0.0D) {
/* 67 */       int velocityX = (int)(velocity * distanceX / distance);
/* 68 */       int velocityY = (int)(velocity * distanceY / distance);
/* 70 */       getMascot().setAnchor(new Point((getMascot().getAnchor()).x + velocityX, 
/* 71 */             (getMascot().getAnchor()).y + velocityY));
/* 72 */       getAnimation().next(getMascot(), getTime());
/*    */     } 
/* 75 */     if (distance <= velocity)
/* 76 */       getMascot().setAnchor(new Point(targetX, targetY)); 
/*    */   }
/*    */   
/*    */   private double getVelocity() throws VariableException {
/* 83 */     return ((Number)eval(getSchema().getString("VelocityParam"), Number.class, Double.valueOf(20.0D))).doubleValue();
/*    */   }
/*    */   
/*    */   private int getTargetX() throws VariableException {
/* 88 */     return ((Number)eval(getSchema().getString("TargetX"), Number.class, Integer.valueOf(0))).intValue();
/*    */   }
/*    */   
/*    */   private int getTargetY() throws VariableException {
/* 93 */     return ((Number)eval(getSchema().getString("TargetY"), Number.class, Integer.valueOf(0))).intValue();
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Jump.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */