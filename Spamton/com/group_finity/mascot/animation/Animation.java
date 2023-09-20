/*    */ package com.group_finity.mascot.animation;
/*    */ 
/*    */ import com.group_finity.mascot.Mascot;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.Variable;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ 
/*    */ public class Animation {
/*    */   private Variable condition;
/*    */   
/*    */   private final Pose[] poses;
/*    */   
/*    */   public Animation(Variable condition, Pose... poses) {
/* 21 */     if (poses.length == 0)
/* 22 */       throw new IllegalArgumentException("poses.length==0"); 
/* 25 */     this.condition = condition;
/* 26 */     this.poses = poses;
/*    */   }
/*    */   
/*    */   public boolean isEffective(VariableMap variables) throws VariableException {
/* 30 */     return ((Boolean)getCondition().get(variables)).booleanValue();
/*    */   }
/*    */   
/*    */   public void init() {
/* 34 */     getCondition().init();
/*    */   }
/*    */   
/*    */   public void initFrame() {
/* 38 */     getCondition().initFrame();
/*    */   }
/*    */   
/*    */   public void next(Mascot mascot, int time) {
/* 42 */     getPoseAt(time).next(mascot);
/*    */   }
/*    */   
/*    */   public Pose getPoseAt(int time) {
/* 47 */     time %= getDuration();
/* 49 */     for (Pose pose : getPoses()) {
/* 50 */       time -= pose.getDuration();
/* 51 */       if (time < 0)
/* 52 */         return pose; 
/*    */     } 
/* 56 */     return null;
/*    */   }
/*    */   
/*    */   public int getDuration() {
/* 61 */     int duration = 0;
/* 62 */     for (Pose pose : getPoses())
/* 63 */       duration += pose.getDuration(); 
/* 66 */     return duration;
/*    */   }
/*    */   
/*    */   private Variable getCondition() {
/* 70 */     return this.condition;
/*    */   }
/*    */   
/*    */   private Pose[] getPoses() {
/* 74 */     return this.poses;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/animation/Animation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */