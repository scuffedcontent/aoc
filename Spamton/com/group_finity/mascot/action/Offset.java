/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.awt.Point;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class Offset extends InstantAction {
/* 15 */   private static final Logger log = Logger.getLogger(Offset.class.getName());
/*    */   
/*    */   public static final String PARAMETER_OFFSETX = "X";
/*    */   
/*    */   private static final int DEFAULT_OFFSETX = 0;
/*    */   
/*    */   public static final String PARAMETER_OFFSETY = "Y";
/*    */   
/*    */   private static final int DEFAULT_OFFSETY = 0;
/*    */   
/*    */   public Offset(ResourceBundle schema, VariableMap params) {
/* 27 */     super(schema, params);
/*    */   }
/*    */   
/*    */   protected void apply() throws VariableException {
/* 32 */     getMascot().setAnchor(new Point(
/* 33 */           (getMascot().getAnchor()).x + getOffsetX(), (getMascot().getAnchor()).y + getOffsetY()));
/*    */   }
/*    */   
/*    */   private int getOffsetX() throws VariableException {
/* 38 */     return ((Number)eval(getSchema().getString("X"), Number.class, Integer.valueOf(0))).intValue();
/*    */   }
/*    */   
/*    */   private int getOffsetY() throws VariableException {
/* 43 */     return ((Number)eval(getSchema().getString("Y"), Number.class, Integer.valueOf(0))).intValue();
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Offset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */