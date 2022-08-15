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
/*    */ public class Turn extends BorderedAction {
/* 17 */   private static final Logger log = Logger.getLogger(Turn.class.getName());
/*    */   
/*    */   public static final String PARAMETER_LOOKRIGHT = "LookRight";
/*    */   
/*    */   private boolean turning = false;
/*    */   
/*    */   public Turn(ResourceBundle schema, List<Animation> animations, VariableMap params) {
/* 25 */     super(schema, animations, params);
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 31 */     getMascot().setLookRight(isLookRight().booleanValue());
/* 33 */     super.tick();
/* 35 */     if (getBorder() != null && !getBorder().isOn(getMascot().getAnchor()))
/* 37 */       throw new LostGroundException(); 
/* 40 */     getAnimation().next(getMascot(), getTime());
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws VariableException {
/* 46 */     this.turning = (this.turning || isLookRight().booleanValue() != getMascot().isLookRight());
/* 47 */     boolean intime = (getTime() < getAnimation().getDuration());
/* 49 */     return (super.hasNext() && intime && this.turning);
/*    */   }
/*    */   
/*    */   private Boolean isLookRight() throws VariableException {
/* 54 */     return eval(getSchema().getString("LookRight"), Boolean.class, Boolean.valueOf(!getMascot().isLookRight()));
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Turn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */