/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class Sequence extends ComplexAction {
/* 14 */   private static final Logger log = Logger.getLogger(Sequence.class.getName());
/*    */   
/*    */   public static final String PARAMETER_LOOP = "Loop";
/*    */   
/*    */   private static final boolean DEFAULT_LOOP = false;
/*    */   
/*    */   public Sequence(ResourceBundle schema, VariableMap params, Action... actions) {
/* 22 */     super(schema, params, actions);
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws VariableException {
/* 28 */     seek();
/* 30 */     return super.hasNext();
/*    */   }
/*    */   
/*    */   protected void setCurrentAction(int currentAction) throws VariableException {
/* 35 */     super.setCurrentAction(isLoop().booleanValue() ? (currentAction % (getActions()).length) : currentAction);
/*    */   }
/*    */   
/*    */   private Boolean isLoop() throws VariableException {
/* 40 */     return eval(getSchema().getString("Loop"), Boolean.class, Boolean.valueOf(false));
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Sequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */