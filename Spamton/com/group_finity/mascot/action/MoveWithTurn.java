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
/*     */ public class MoveWithTurn extends BorderedAction {
/*  20 */   private static final Logger log = Logger.getLogger(MoveWithTurn.class.getName());
/*     */   
/*     */   private static final String PARAMETER_TARGETX = "TargetX";
/*     */   
/*     */   private static final int DEFAULT_TARGETX = 2147483647;
/*     */   
/*     */   private static final String PARAMETER_TARGETY = "TargetY";
/*     */   
/*     */   private static final int DEFAULT_TARGETY = 2147483647;
/*     */   
/*     */   private boolean turning = false;
/*     */   
/*     */   public MoveWithTurn(ResourceBundle schema, List<Animation> animations, VariableMap params) {
/*  34 */     super(schema, animations, params);
/*  35 */     if (animations.size() < 2)
/*  37 */       throw new IllegalArgumentException("animations.size<2"); 
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws VariableException {
/*  45 */     int targetX = getTargetX();
/*  46 */     int targetY = getTargetY();
/*  48 */     boolean noMoveX = false;
/*  49 */     boolean noMoveY = false;
/*  51 */     if (targetX != Integer.MIN_VALUE)
/*  53 */       if ((getMascot().getAnchor()).x == targetX)
/*  55 */         noMoveX = true;  
/*  59 */     if (targetY != Integer.MIN_VALUE)
/*  61 */       if ((getMascot().getAnchor()).y == targetY)
/*  63 */         noMoveY = true;  
/*  67 */     return (super.hasNext() && !noMoveX && !noMoveY);
/*     */   }
/*     */   
/*     */   protected void tick() throws LostGroundException, VariableException {
/*  74 */     super.tick();
/*  76 */     if (getBorder() != null && !getBorder().isOn(getMascot().getAnchor())) {
/*  78 */       log.log(Level.INFO, "Lost Ground ({0},{1})", new Object[] { getMascot(), this });
/*  79 */       throw new LostGroundException();
/*     */     } 
/*  82 */     int targetX = getTargetX();
/*  83 */     int targetY = getTargetY();
/*  85 */     boolean down = false;
/*  87 */     if (targetX != Integer.MAX_VALUE)
/*  89 */       if ((getMascot().getAnchor()).x != targetX) {
/*  92 */         this.turning = (this.turning || (((getMascot().getAnchor()).x < targetX)) != getMascot().isLookRight());
/*  93 */         getMascot().setLookRight(((getMascot().getAnchor()).x < targetX));
/*     */       }  
/*  96 */     if (targetY != Integer.MAX_VALUE)
/*  98 */       down = ((getMascot().getAnchor()).y < targetY); 
/* 102 */     if (this.turning && getTime() >= getAnimation().getDuration())
/* 104 */       this.turning = false; 
/* 107 */     getAnimation().next(getMascot(), getTime());
/* 109 */     if (targetX != Integer.MAX_VALUE)
/* 111 */       if ((getMascot().isLookRight() && (getMascot().getAnchor()).x >= targetX) || (
/* 112 */         !getMascot().isLookRight() && (getMascot().getAnchor()).x <= targetX))
/* 114 */         getMascot().setAnchor(new Point(targetX, (getMascot().getAnchor()).y));  
/* 117 */     if (targetY != Integer.MAX_VALUE)
/* 119 */       if ((down && (getMascot().getAnchor()).y >= targetY) || (!down && 
/* 120 */         (getMascot().getAnchor()).y <= targetY))
/* 122 */         getMascot().setAnchor(new Point((getMascot().getAnchor()).x, targetY));  
/*     */   }
/*     */   
/*     */   protected Animation getAnimation() throws VariableException {
/* 132 */     if (this.turning)
/* 134 */       return getAnimations().get(getAnimations().size() - 1); 
/* 140 */     List<Animation> animations = getAnimations();
/* 141 */     for (int index = 0; index < animations.size() - 1; index++) {
/* 143 */       if (((Animation)animations.get(index)).isEffective(getVariables()))
/* 145 */         return animations.get(index); 
/*     */     } 
/* 150 */     return null;
/*     */   }
/*     */   
/*     */   private int getTargetX() throws VariableException {
/* 155 */     return ((Number)eval(getSchema().getString("TargetX"), Number.class, Integer.valueOf(2147483647))).intValue();
/*     */   }
/*     */   
/*     */   private int getTargetY() throws VariableException {
/* 160 */     return ((Number)eval(getSchema().getString("TargetY"), Number.class, Integer.valueOf(2147483647))).intValue();
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/MoveWithTurn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */