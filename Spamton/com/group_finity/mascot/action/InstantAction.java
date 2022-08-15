/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.Mascot;
/*    */ import com.group_finity.mascot.animation.Animation;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.util.ArrayList;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public abstract class InstantAction extends ActionBase {
/* 17 */   private static final Logger log = Logger.getLogger(InstantAction.class.getName());
/*    */   
/*    */   public InstantAction(ResourceBundle schema, VariableMap params) {
/* 20 */     super(schema, new ArrayList<Animation>(), params);
/*    */   }
/*    */   
/*    */   public final void init(Mascot mascot) throws VariableException {
/* 26 */     super.init(mascot);
/* 28 */     if (super.hasNext())
/* 29 */       apply(); 
/*    */   }
/*    */   
/*    */   protected abstract void apply() throws VariableException;
/*    */   
/*    */   public final boolean hasNext() throws VariableException {
/* 37 */     if (super.hasNext());
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   protected final void tick() {}
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/InstantAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */