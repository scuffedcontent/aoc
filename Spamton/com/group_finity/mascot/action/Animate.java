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
/*    */ public class Animate extends BorderedAction {
/* 17 */   private static final Logger log = Logger.getLogger(Animate.class.getName());
/*    */   
/*    */   public Animate(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/* 21 */     super(schema, animations, context);
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 27 */     super.tick();
/* 29 */     if (getBorder() != null && !getBorder().isOn(getMascot().getAnchor()))
/* 30 */       throw new LostGroundException(); 
/* 33 */     getAnimation().next(getMascot(), getTime());
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws VariableException {
/* 40 */     boolean intime = (getTime() < getAnimation().getDuration());
/* 42 */     return (super.hasNext() && intime);
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Animate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */