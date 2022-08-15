/*     */ package com.group_finity.mascot.action;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.Mascot;
/*     */ import com.group_finity.mascot.animation.Animation;
/*     */ import com.group_finity.mascot.exception.BehaviorInstantiationException;
/*     */ import com.group_finity.mascot.exception.CantBeAliveException;
/*     */ import com.group_finity.mascot.exception.LostGroundException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import com.group_finity.mascot.script.VariableMap;
/*     */ import java.awt.Point;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class ScanMove extends BorderedAction {
/*  24 */   private static final Logger log = Logger.getLogger(ScanMove.class.getName());
/*     */   
/*     */   private static final String PARAMETER_AFFORDANCE = "Affordance";
/*     */   
/*     */   private static final String DEFAULT_AFFORDANCE = "";
/*     */   
/*     */   public static final String PARAMETER_BEHAVIOUR = "Behaviour";
/*     */   
/*     */   private static final String DEFAULT_BEHAVIOUR = "";
/*     */   
/*     */   public static final String PARAMETER_TARGETBEHAVIOUR = "TargetBehaviour";
/*     */   
/*     */   private static final String DEFAULT_TARGETBEHAVIOUR = "";
/*     */   
/*     */   private WeakReference<Mascot> target;
/*     */   
/*     */   public ScanMove(ResourceBundle schema, List<Animation> animations, VariableMap params) {
/*  42 */     super(schema, animations, params);
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws VariableException {
/*  48 */     if (getMascot().getManager() == null)
/*  49 */       return super.hasNext(); 
/*  51 */     if (this.target == null)
/*  53 */       this.target = getMascot().getManager().getMascotWithAffordance(getAffordance()); 
/*  56 */     return (super.hasNext() && this.target != null && this.target.get() != null && ((Mascot)this.target.get()).getAffordances().contains(getAffordance()));
/*     */   }
/*     */   
/*     */   protected void tick() throws LostGroundException, VariableException {
/*  62 */     super.tick();
/*  64 */     if (getBorder() != null && !getBorder().isOn(getMascot().getAnchor())) {
/*  66 */       log.log(Level.INFO, "Lost Ground ({0},{1})", new Object[] { getMascot(), this });
/*  67 */       throw new LostGroundException();
/*     */     } 
/*  70 */     int targetX = (((Mascot)this.target.get()).getAnchor()).x;
/*  71 */     int targetY = (((Mascot)this.target.get()).getAnchor()).y;
/*  73 */     boolean down = false;
/*  75 */     if ((getMascot().getAnchor()).x != targetX)
/*  77 */       getMascot().setLookRight(((getMascot().getAnchor()).x < targetX)); 
/*  79 */     down = ((getMascot().getAnchor()).y < targetY);
/*  81 */     getAnimation().next(getMascot(), getTime());
/*  83 */     if ((getMascot().isLookRight() && (getMascot().getAnchor()).x >= targetX) || (
/*  84 */       !getMascot().isLookRight() && (getMascot().getAnchor()).x <= targetX))
/*  86 */       getMascot().setAnchor(new Point(targetX, (getMascot().getAnchor()).y)); 
/*  88 */     if ((down && (getMascot().getAnchor()).y >= targetY) || (!down && 
/*  89 */       (getMascot().getAnchor()).y <= targetY))
/*  91 */       getMascot().setAnchor(new Point((getMascot().getAnchor()).x, targetY)); 
/*  94 */     boolean noMoveX = false;
/*  95 */     boolean noMoveY = false;
/*  97 */     if ((getMascot().getAnchor()).x == targetX)
/*  99 */       noMoveX = true; 
/* 102 */     if ((getMascot().getAnchor()).y == targetY)
/* 104 */       noMoveY = true; 
/* 107 */     if (noMoveX && noMoveY)
/*     */       try {
/* 111 */         getMascot().setBehavior(Main.getInstance().getConfiguration(getMascot().getImageSet()).buildBehavior(getBehavior()));
/* 112 */         ((Mascot)this.target.get()).setBehavior(Main.getInstance().getConfiguration(((Mascot)this.target.get()).getImageSet()).buildBehavior(getTargetBehavior()));
/* 114 */       } catch (BehaviorInstantiationException e) {
/* 116 */         log.log(Level.SEVERE, "Fatal Exception", (Throwable)e);
/* 117 */         Main.showError(Main.getInstance().getLanguageBundle().getString("FailedCreateNewShimejiErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 119 */       } catch (CantBeAliveException e) {
/* 121 */         log.log(Level.SEVERE, "Fatal Exception", (Throwable)e);
/* 122 */         Main.showError(Main.getInstance().getLanguageBundle().getString("FailedCreateNewShimejiErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/*     */       }  
/*     */   }
/*     */   
/*     */   private String getAffordance() throws VariableException {
/* 129 */     return eval(getSchema().getString("Affordance"), String.class, "");
/*     */   }
/*     */   
/*     */   private String getBehavior() throws VariableException {
/* 134 */     return eval(getSchema().getString("Behaviour"), String.class, "");
/*     */   }
/*     */   
/*     */   private String getTargetBehavior() throws VariableException {
/* 139 */     return eval(getSchema().getString("TargetBehaviour"), String.class, "");
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/ScanMove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */