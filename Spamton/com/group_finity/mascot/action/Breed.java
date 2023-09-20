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
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class Breed extends Animate {
/*  23 */   private static final Logger log = Logger.getLogger(Breed.class.getName());
/*     */   
/*     */   public static final String PARAMETER_BORNX = "BornX";
/*     */   
/*     */   private static final int DEFAULT_BORNX = 0;
/*     */   
/*     */   public static final String PARAMETER_BORNY = "BornY";
/*     */   
/*     */   private static final int DEFAULT_BORNY = 0;
/*     */   
/*     */   public static final String PARAMETER_BORNBEHAVIOUR = "BornBehaviour";
/*     */   
/*     */   private static final String DEFAULT_BORNBEHAVIOUR = "";
/*     */   
/*     */   public static final String PARAMETER_BORNMASCOT = "BornMascot";
/*     */   
/*     */   private static final String DEFAULT_BORNMASCOT = "";
/*     */   
/*     */   private int scaling;
/*     */   
/*     */   public Breed(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/*  45 */     super(schema, animations, context);
/*     */   }
/*     */   
/*     */   public void init(Mascot mascot) throws VariableException {
/*  51 */     super.init(mascot);
/*  53 */     this.scaling = Integer.parseInt(Main.getInstance().getProperties().getProperty("Scaling", "1"));
/*     */   }
/*     */   
/*     */   protected void tick() throws LostGroundException, VariableException {
/*  59 */     super.tick();
/*  61 */     if (Boolean.parseBoolean(Main.getInstance().getProperties().getProperty("Breeding", "true")) && getTime() == getAnimation().getDuration() - 1)
/*  62 */       breed(); 
/*     */   }
/*     */   
/*     */   private void breed() throws VariableException {
/*  68 */     String childType = (Main.getInstance().getConfiguration(getBornMascot()) != null) ? getBornMascot() : getMascot().getImageSet();
/*  70 */     Mascot mascot = new Mascot(childType);
/*  72 */     log.log(Level.INFO, "Breed Mascot ({0},{1},{2})", new Object[] { getMascot(), this, mascot });
/*  74 */     if (getMascot().isLookRight()) {
/*  75 */       mascot.setAnchor(new Point((getMascot().getAnchor()).x - getBornX() * this.scaling, 
/*  76 */             (getMascot().getAnchor()).y + getBornY() * this.scaling));
/*     */     } else {
/*  78 */       mascot.setAnchor(new Point((getMascot().getAnchor()).x + getBornX() * this.scaling, 
/*  79 */             (getMascot().getAnchor()).y + getBornY() * this.scaling));
/*     */     } 
/*  81 */     mascot.setLookRight(getMascot().isLookRight());
/*     */     try {
/*  84 */       mascot.setBehavior(Main.getInstance().getConfiguration(childType).buildBehavior(getBornBehaviour()));
/*  86 */       getMascot().getManager().add(mascot);
/*  88 */     } catch (BehaviorInstantiationException e) {
/*  89 */       log.log(Level.SEVERE, "Fatal Exception", (Throwable)e);
/*  90 */       Main.showError(Main.getInstance().getLanguageBundle().getString("FailedCreateNewShimejiErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/*  91 */       mascot.dispose();
/*  92 */     } catch (CantBeAliveException e) {
/*  93 */       log.log(Level.SEVERE, "Fatal Exception", (Throwable)e);
/*  94 */       Main.showError(Main.getInstance().getLanguageBundle().getString("FailedCreateNewShimejiErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/*  95 */       mascot.dispose();
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getBornX() throws VariableException {
/* 101 */     return ((Number)eval(getSchema().getString("BornX"), Number.class, Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   private int getBornY() throws VariableException {
/* 106 */     return ((Number)eval(getSchema().getString("BornY"), Number.class, Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   private String getBornBehaviour() throws VariableException {
/* 111 */     return eval(getSchema().getString("BornBehaviour"), String.class, "");
/*     */   }
/*     */   
/*     */   private String getBornMascot() throws VariableException {
/* 116 */     return eval(getSchema().getString("BornMascot"), String.class, "");
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Breed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */