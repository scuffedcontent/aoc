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
/*    */ public class Regist extends ActionBase {
/* 18 */   private static final Logger log = Logger.getLogger(Regist.class.getName());
/*    */   
/*    */   public Regist(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/* 22 */     super(schema, animations, context);
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws VariableException {
/* 28 */     boolean notMoved = (Math.abs(getEnvironment().getCursor().getX() - (getMascot().getAnchor()).x) < 5);
/* 30 */     return (super.hasNext() && notMoved);
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 36 */     Animation animation = getAnimation();
/* 37 */     animation.next(getMascot(), getTime());
/* 39 */     if (getTime() + 1 >= getAnimation().getDuration()) {
/* 40 */       getMascot().setLookRight((Math.random() < 0.5D));
/* 42 */       log.log(Level.INFO, "Lost Ground ({0},{1})", new Object[] { getMascot(), this });
/* 43 */       throw new LostGroundException();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Regist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */