/*     */ package com.group_finity.mascot.action;
/*     */ 
/*     */ import com.group_finity.mascot.animation.Animation;
/*     */ import com.group_finity.mascot.exception.LostGroundException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import com.group_finity.mascot.script.VariableMap;
/*     */ import java.awt.Point;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class Move extends BorderedAction {
/*  19 */   private static final Logger log = Logger.getLogger(Move.class.getName());
/*     */   
/*     */   private static final String PARAMETER_TARGETX = "TargetX";
/*     */   
/*     */   private static final int DEFAULT_TARGETX = 2147483647;
/*     */   
/*     */   private static final String PARAMETER_TARGETY = "TargetY";
/*     */   
/*     */   private static final int DEFAULT_TARGETY = 2147483647;
/*     */   
/*     */   public Move(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/*  31 */     super(schema, animations, context);
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws VariableException {
/*  37 */     int targetX = getTargetX();
/*  38 */     int targetY = getTargetY();
/*  40 */     boolean noMoveX = false;
/*  41 */     boolean noMoveY = false;
/*  43 */     if (targetX != Integer.MIN_VALUE && 
/*  44 */       (getMascot().getAnchor()).x == targetX)
/*  45 */       noMoveX = true; 
/*  49 */     if (targetY != Integer.MIN_VALUE && 
/*  50 */       (getMascot().getAnchor()).y == targetY)
/*  51 */       noMoveY = true; 
/*  55 */     return (super.hasNext() && !noMoveX && !noMoveY);
/*     */   }
/*     */   
/*     */   protected void tick() throws LostGroundException, VariableException {
/*  61 */     super.tick();
/*  63 */     if (getBorder() != null && !getBorder().isOn(getMascot().getAnchor())) {
/*  64 */       log.log(Level.INFO, "Lost Ground ({0},{1})", new Object[] { getMascot(), this });
/*  65 */       throw new LostGroundException();
/*     */     } 
/*  68 */     int targetX = getTargetX();
/*  69 */     int targetY = getTargetY();
/*  71 */     boolean down = false;
/*  73 */     if (targetX != Integer.MAX_VALUE && 
/*  74 */       (getMascot().getAnchor()).x != targetX)
/*  75 */       getMascot().setLookRight(((getMascot().getAnchor()).x < targetX)); 
/*  78 */     if (targetY != Integer.MAX_VALUE)
/*  79 */       down = ((getMascot().getAnchor()).y < targetY); 
/*  82 */     getAnimation().next(getMascot(), getTime());
/*  84 */     if (targetX != Integer.MAX_VALUE && ((
/*  85 */       getMascot().isLookRight() && (getMascot().getAnchor()).x >= targetX) || (
/*  86 */       !getMascot().isLookRight() && (getMascot().getAnchor()).x <= targetX)))
/*  87 */       getMascot().setAnchor(new Point(targetX, (getMascot().getAnchor()).y)); 
/*  90 */     if (targetY != Integer.MAX_VALUE && ((
/*  91 */       down && (getMascot().getAnchor()).y >= targetY) || (!down && 
/*  92 */       (getMascot().getAnchor()).y <= targetY)))
/*  93 */       getMascot().setAnchor(new Point((getMascot().getAnchor()).x, targetY)); 
/*     */   }
/*     */   
/*     */   private int getTargetX() throws VariableException {
/* 101 */     return ((Number)eval(getSchema().getString("TargetX"), Number.class, Integer.valueOf(2147483647))).intValue();
/*     */   }
/*     */   
/*     */   private int getTargetY() throws VariableException {
/* 106 */     return ((Number)eval(getSchema().getString("TargetY"), Number.class, Integer.valueOf(2147483647))).intValue();
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Move.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */