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
/*    */ public class Transform extends Animate {
/* 22 */   private static final Logger log = Logger.getLogger(Transform.class.getName());
/*    */   
/*    */   public static final String PARAMETER_TRANSFORMBEHAVIOUR = "TransformBehaviour";
/*    */   
/*    */   private static final String DEFAULT_TRANSFORMBEHAVIOUR = "";
/*    */   
/*    */   public static final String PARAMETER_TRANSFORMMASCOT = "TransformMascot";
/*    */   
/*    */   private static final String DEFAULT_TRANSFORMMASCOT = "";
/*    */   
/*    */   public Transform(ResourceBundle schema, List<Animation> animations, VariableMap params) {
/* 34 */     super(schema, animations, params);
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 40 */     super.tick();
/* 42 */     if (getTime() == getAnimation().getDuration() - 1)
/* 44 */       transform(); 
/*    */   }
/*    */   
/*    */   private void transform() throws VariableException {
/* 50 */     String childType = (Main.getInstance().getConfiguration(getTransformMascot()) != null) ? getTransformMascot() : getMascot().getImageSet();
/*    */     try {
/* 54 */       getMascot().setImageSet(childType);
/* 55 */       getMascot().setBehavior(Main.getInstance().getConfiguration(childType).buildBehavior(getTransformBehavior()));
/* 57 */     } catch (BehaviorInstantiationException e) {
/* 59 */       log.log(Level.SEVERE, "Fatal Exception", (Throwable)e);
/* 60 */       Main.showError(Main.getInstance().getLanguageBundle().getString("FailedCreateNewShimejiErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 62 */     } catch (CantBeAliveException e) {
/* 64 */       log.log(Level.SEVERE, "Fatal Exception", (Throwable)e);
/* 65 */       Main.showError(Main.getInstance().getLanguageBundle().getString("FailedCreateNewShimejiErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/*    */     } 
/*    */   }
/*    */   
/*    */   private String getTransformBehavior() throws VariableException {
/* 71 */     return eval(getSchema().getString("TransformBehaviour"), String.class, "");
/*    */   }
/*    */   
/*    */   private String getTransformMascot() throws VariableException {
/* 76 */     return eval(getSchema().getString("TransformMascot"), String.class, "");
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Transform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */