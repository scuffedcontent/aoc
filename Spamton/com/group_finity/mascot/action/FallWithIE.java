/*    */ package com.group_finity.mascot.action;
/*    */ 
/*    */ import com.group_finity.mascot.Main;
/*    */ import com.group_finity.mascot.animation.Animation;
/*    */ import com.group_finity.mascot.environment.Area;
/*    */ import com.group_finity.mascot.exception.LostGroundException;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import com.group_finity.mascot.script.VariableMap;
/*    */ import java.awt.Point;
/*    */ import java.util.List;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class FallWithIE extends Fall {
/* 21 */   private static final Logger log = Logger.getLogger(FallWithIE.class.getName());
/*    */   
/*    */   public static final String PARAMETER_IEOFFSETX = "IeOffsetX";
/*    */   
/*    */   private static final int DEFAULT_IEOFFSETX = 0;
/*    */   
/*    */   public static final String PARAMETER_IEOFFSETY = "IeOffsetY";
/*    */   
/*    */   private static final int DEFAULT_IEOFFSETY = 0;
/*    */   
/*    */   public FallWithIE(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/* 33 */     super(schema, animations, context);
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws VariableException {
/* 39 */     if (!Boolean.parseBoolean(Main.getInstance().getProperties().getProperty("Throwing", "true")))
/* 40 */       return false; 
/* 42 */     return super.hasNext();
/*    */   }
/*    */   
/*    */   protected void tick() throws LostGroundException, VariableException {
/* 48 */     Area activeIE = getEnvironment().getActiveIE();
/* 49 */     if (!activeIE.isVisible()) {
/* 50 */       log.log(Level.INFO, "IE Not Visible ({0},{1})", new Object[] { getMascot(), this });
/* 51 */       throw new LostGroundException();
/*    */     } 
/* 54 */     int offsetX = getIEOffsetX();
/* 55 */     int offsetY = getIEOffsetY();
/* 57 */     if (getMascot().isLookRight()) {
/* 58 */       if ((getMascot().getAnchor()).x - offsetX != activeIE.getLeft() || 
/* 59 */         (getMascot().getAnchor()).y + offsetY != activeIE.getBottom()) {
/* 60 */         log.log(Level.INFO, "Lost Ground ({0},{1})", new Object[] { getMascot(), this });
/* 61 */         throw new LostGroundException();
/*    */       } 
/* 64 */     } else if ((getMascot().getAnchor()).x + offsetX != activeIE.getRight() || 
/* 65 */       (getMascot().getAnchor()).y + offsetY != activeIE.getBottom()) {
/* 66 */       log.log(Level.INFO, "Lost Ground ({0},{1})", new Object[] { getMascot(), this });
/* 67 */       throw new LostGroundException();
/*    */     } 
/* 71 */     super.tick();
/* 73 */     if (activeIE.isVisible())
/* 74 */       if (getMascot().isLookRight()) {
/* 75 */         getEnvironment().moveActiveIE(new Point((getMascot().getAnchor()).x - offsetX, (getMascot().getAnchor()).y + offsetY - activeIE
/* 76 */               .getHeight()));
/*    */       } else {
/* 78 */         getEnvironment().moveActiveIE(new Point((getMascot().getAnchor()).x + offsetX - activeIE.getWidth(), 
/* 79 */               (getMascot().getAnchor()).y + offsetY - activeIE.getHeight()));
/*    */       }  
/*    */   }
/*    */   
/*    */   private int getIEOffsetX() throws VariableException {
/* 87 */     return ((Number)eval(getSchema().getString("IeOffsetX"), Number.class, Integer.valueOf(0))).intValue();
/*    */   }
/*    */   
/*    */   private int getIEOffsetY() throws VariableException {
/* 92 */     return ((Number)eval(getSchema().getString("IeOffsetY"), Number.class, Integer.valueOf(0))).intValue();
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/FallWithIE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */