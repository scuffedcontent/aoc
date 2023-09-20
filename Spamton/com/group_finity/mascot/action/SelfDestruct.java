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
/*    */ public class SelfDestruct extends Animate {
/* 22 */   private static final Logger log = Logger.getLogger(SelfDestruct.class.getName());
/*    */   
/*    */   public SelfDestruct(ResourceBundle schema, List<Animation> animations, VariableMap params) {
/* 26 */     super(schema, animations, params);
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 32 */     super.tick();
/* 34 */     if (getTime() == getAnimation().getDuration() - 1)
/* 36 */       getMascot().dispose(); 
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/SelfDestruct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */