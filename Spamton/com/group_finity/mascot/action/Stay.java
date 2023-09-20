/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.animation.Animation;
/*    */ import com.group_finity.mascot.exception.LostGroundException;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.util.List;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class Stay extends BorderedAction {
/* 18 */   private static final Logger log = Logger.getLogger(Stay.class.getName());
/*    */   
/*    */   public Stay(ResourceBundle schema, List<Animation> animations, VariableMap params) {
/* 22 */     super(schema, animations, params);
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 28 */     super.tick();
/* 30 */     if (getBorder() != null && !getBorder().isOn(getMascot().getAnchor())) {
/* 31 */       log.log(Level.INFO, "Lost Ground ({0},{1})", new Object[] { getMascot(), this });
/* 32 */       throw new LostGroundException();
/*    */     } 
/* 35 */     getAnimation().next(getMascot(), getTime());
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Stay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */