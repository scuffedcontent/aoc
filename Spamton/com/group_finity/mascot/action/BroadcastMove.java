/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.animation.Animation;
/*    */ import com.group_finity.mascot.exception.LostGroundException;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.util.List;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class BroadcastMove extends Move {
/* 18 */   private static final Logger log = Logger.getLogger(BroadcastMove.class.getName());
/*    */   
/*    */   public static final String PARAMETER_AFFORDANCE = "Affordance";
/*    */   
/*    */   private static final String DEFAULT_AFFORDANCE = "";
/*    */   
/*    */   public BroadcastMove(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/* 26 */     super(schema, animations, context);
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 32 */     super.tick();
/* 34 */     getMascot().getAffordances().add(getAffordance());
/*    */   }
/*    */   
/*    */   private String getAffordance() throws VariableException {
/* 39 */     return eval(getSchema().getString("Affordance"), String.class, "");
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/BroadcastMove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */