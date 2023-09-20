/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.Mascot;
/*    */ import com.group_finity.mascot.animation.Animation;
/*    */ import com.group_finity.mascot.exception.LostGroundException;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.util.ArrayList;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public abstract class ComplexAction extends ActionBase {
/* 18 */   private static final Logger log = Logger.getLogger(ComplexAction.class.getName());
/*    */   
/*    */   private final Action[] actions;
/*    */   
/*    */   private int currentAction;
/*    */   
/*    */   public ComplexAction(ResourceBundle schema, VariableMap params, Action... actions) {
/* 25 */     super(schema, new ArrayList<Animation>(), params);
/* 26 */     if (actions.length == 0)
/* 27 */       throw new IllegalArgumentException("actions.length==0"); 
/* 30 */     this.actions = actions;
/*    */   }
/*    */   
/*    */   public void init(Mascot mascot) throws VariableException {
/* 35 */     super.init(mascot);
/* 37 */     if (super.hasNext()) {
/* 38 */       setCurrentAction(0);
/* 39 */       seek();
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void seek() throws VariableException {
/* 44 */     if (super.hasNext())
/* 45 */       while (getCurrentAction() < (getActions()).length && 
/* 46 */         !getAction().hasNext())
/* 49 */         setCurrentAction(getCurrentAction() + 1);  
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws VariableException {
/* 56 */     boolean inrange = (getCurrentAction() < (getActions()).length);
/* 58 */     return (super.hasNext() && inrange && getAction().hasNext());
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 63 */     if (getAction().hasNext())
/* 64 */       getAction().next(); 
/*    */   }
/*    */   
/*    */   public Boolean isDraggable() throws VariableException {
/* 71 */     boolean draggable = true;
/* 72 */     if (this.actions[this.currentAction] != null && this.actions[this.currentAction] instanceof ActionBase)
/* 74 */       return ((ActionBase)this.actions[this.currentAction]).isDraggable(); 
/* 76 */     return Boolean.valueOf(draggable);
/*    */   }
/*    */   
/*    */   protected void setCurrentAction(int currentAction) throws VariableException {
/* 80 */     this.currentAction = currentAction;
/* 81 */     if (super.hasNext() && 
/* 82 */       getCurrentAction() < (getActions()).length)
/* 83 */       getAction().init(getMascot()); 
/*    */   }
/*    */   
/*    */   protected int getCurrentAction() {
/* 89 */     return this.currentAction;
/*    */   }
/*    */   
/*    */   protected Action[] getActions() {
/* 93 */     return this.actions;
/*    */   }
/*    */   
/*    */   protected Action getAction() {
/* 97 */     return getActions()[getCurrentAction()];
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/ComplexAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */