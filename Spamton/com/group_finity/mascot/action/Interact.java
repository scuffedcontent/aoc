/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.Main;
/*    */ import com.group_finity.mascot.animation.Animation;
/*    */ import com.group_finity.mascot.exception.BehaviorInstantiationException;
/*    */ import com.group_finity.mascot.exception.CantBeAliveException;
/*    */ import com.group_finity.mascot.exception.LostGroundException;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.util.List;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class Interact extends Animate {
/* 22 */   private static final Logger log = Logger.getLogger(Interact.class.getName());
/*    */   
/*    */   public static final String PARAMETER_BEHAVIOUR = "Behaviour";
/*    */   
/*    */   private static final String DEFAULT_BEHAVIOUR = "";
/*    */   
/*    */   public Interact(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/* 30 */     super(schema, animations, context);
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws VariableException {
/* 36 */     return (super.hasNext() && getMascot().getManager().hasOverlappingMascotsAtPoint(getMascot().getAnchor()));
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 42 */     super.tick();
/* 44 */     if (getTime() == getAnimation().getDuration() - 1 && !getBehavior().trim().isEmpty())
/*    */       try {
/* 48 */         getMascot().setBehavior(Main.getInstance().getConfiguration(getMascot().getImageSet()).buildBehavior(getBehavior()));
/* 50 */       } catch (BehaviorInstantiationException e) {
/* 52 */         log.log(Level.SEVERE, "Fatal Exception", (Throwable)e);
/* 53 */         Main.showError(Main.getInstance().getLanguageBundle().getString("FailedCreateNewShimejiErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 55 */       } catch (CantBeAliveException e) {
/* 57 */         log.log(Level.SEVERE, "Fatal Exception", (Throwable)e);
/* 58 */         Main.showError(Main.getInstance().getLanguageBundle().getString("FailedCreateNewShimejiErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/*    */       }  
/*    */   }
/*    */   
/*    */   private String getBehavior() throws VariableException {
/* 65 */     return eval(getSchema().getString("Behaviour"), String.class, "");
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Interact.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */