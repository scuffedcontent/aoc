/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.Mascot;
/*    */ import com.group_finity.mascot.animation.Animation;
/*    */ import com.group_finity.mascot.environment.Border;
/*    */ import com.group_finity.mascot.exception.LostGroundException;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.util.List;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public abstract class BorderedAction extends ActionBase {
/* 19 */   private static final Logger log = Logger.getLogger(BorderedAction.class.getName());
/*    */   
/*    */   private static final String PARAMETER_BORDERTYPE = "BorderType";
/*    */   
/* 23 */   public static final String DEFAULT_BORDERTYPE = null;
/*    */   
/*    */   public static final String BORDERTYPE_CEILING = "Ceiling";
/*    */   
/*    */   public static final String BORDERTYPE_WALL = "Wall";
/*    */   
/*    */   public static final String BORDERTYPE_FLOOR = "Floor";
/*    */   
/*    */   private Border border;
/*    */   
/*    */   public BorderedAction(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/* 35 */     super(schema, animations, context);
/*    */   }
/*    */   
/*    */   public void init(Mascot mascot) throws VariableException {
/* 40 */     super.init(mascot);
/* 42 */     String borderType = getBorderType();
/* 44 */     if (getSchema().getString("Ceiling").equals(borderType)) {
/* 45 */       setBorder(getEnvironment().getCeiling());
/* 46 */     } else if (getSchema().getString("Wall").equals(borderType)) {
/* 47 */       setBorder(getEnvironment().getWall());
/* 48 */     } else if (getSchema().getString("Floor").equals(borderType)) {
/* 49 */       setBorder(getEnvironment().getFloor());
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 55 */     if (getBorder() != null)
/* 56 */       getMascot().setAnchor(getBorder().move(getMascot().getAnchor())); 
/*    */   }
/*    */   
/*    */   private String getBorderType() throws VariableException {
/* 61 */     return eval(getSchema().getString("BorderType"), String.class, DEFAULT_BORDERTYPE);
/*    */   }
/*    */   
/*    */   private void setBorder(Border border) {
/* 65 */     this.border = border;
/*    */   }
/*    */   
/*    */   protected Border getBorder() {
/* 69 */     return this.border;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/BorderedAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */