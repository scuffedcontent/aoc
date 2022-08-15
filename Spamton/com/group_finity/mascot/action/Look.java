/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class Look extends InstantAction {
/* 14 */   private static final Logger log = Logger.getLogger(Look.class.getName());
/*    */   
/*    */   public static final String PARAMETER_LOOKRIGHT = "LookRight";
/*    */   
/*    */   public Look(ResourceBundle schema, VariableMap params) {
/* 20 */     super(schema, params);
/*    */   }
/*    */   
/*    */   protected void apply() throws VariableException {
/* 26 */     getMascot().setLookRight(isLookRight().booleanValue());
/*    */   }
/*    */   
/*    */   private Boolean isLookRight() throws VariableException {
/* 31 */     return eval(getSchema().getString("LookRight"), Boolean.class, Boolean.valueOf(!getMascot().isLookRight()));
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Look.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */