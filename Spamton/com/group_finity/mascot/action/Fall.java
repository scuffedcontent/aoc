/*     */ package com.group_finity.mascot.action;
/*     */ 
/*     */ import com.group_finity.mascot.Mascot;
/*     */ import com.group_finity.mascot.animation.Animation;
/*     */ import com.group_finity.mascot.exception.LostGroundException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import com.group_finity.mascot.script.VariableMap;
/*     */ import java.awt.Point;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class Fall extends ActionBase {
/*  19 */   private static final Logger log = Logger.getLogger(Fall.class.getName());
/*     */   
/*     */   public static final String PARAMETER_INITIALVX = "InitialVX";
/*     */   
/*     */   private static final int DEFAULT_INITIALVX = 0;
/*     */   
/*     */   private static final String PARAMETER_INITIALVY = "InitialVY";
/*     */   
/*     */   private static final int DEFAULT_INITIALVY = 0;
/*     */   
/*     */   public static final String PARAMETER_RESISTANCEX = "ResistanceX";
/*     */   
/*     */   private static final double DEFAULT_RESISTANCEX = 0.05D;
/*     */   
/*     */   public static final String PARAMETER_RESISTANCEY = "ResistanceY";
/*     */   
/*     */   private static final double DEFAULT_RESISTANCEY = 0.1D;
/*     */   
/*     */   public static final String PARAMETER_GRAVITY = "Gravity";
/*     */   
/*     */   private static final double DEFAULT_GRAVITY = 2.0D;
/*     */   
/*     */   private double velocityX;
/*     */   
/*     */   private double velocityY;
/*     */   
/*     */   private double modX;
/*     */   
/*     */   private double modY;
/*     */   
/*     */   public Fall(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/*  51 */     super(schema, animations, context);
/*     */   }
/*     */   
/*     */   public void init(Mascot mascot) throws VariableException {
/*  56 */     super.init(mascot);
/*  58 */     setVelocityX(getInitialVx());
/*  59 */     setVelocityY(getInitialVy());
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws VariableException {
/*  65 */     Point pos = getMascot().getAnchor();
/*  66 */     boolean onBorder = false;
/*  67 */     if (getEnvironment().getFloor().isOn(pos))
/*  68 */       onBorder = true; 
/*  70 */     if (getEnvironment().getWall().isOn(pos))
/*  71 */       onBorder = true; 
/*  73 */     return (super.hasNext() && !onBorder);
/*     */   }
/*     */   
/*     */   protected void tick() throws LostGroundException, VariableException {
/*  79 */     if (getVelocityX() != 0.0D)
/*  80 */       getMascot().setLookRight((getVelocityX() > 0.0D)); 
/*  83 */     setVelocityX(getVelocityX() - getVelocityX() * getResistanceX());
/*  84 */     setVelocityY(getVelocityY() - getVelocityY() * getResistanceY() + getGravity());
/*  86 */     setModX(getModX() + getVelocityX() % 1.0D);
/*  87 */     setModY(getModY() + getVelocityY() % 1.0D);
/*  89 */     int dx = (int)getVelocityX() + (int)getModX();
/*  90 */     int dy = (int)getVelocityY() + (int)getModY();
/*  92 */     setModX(getModX() % 1.0D);
/*  93 */     setModY(getModY() % 1.0D);
/*  95 */     int dev = Math.max(Math.abs(dx), Math.abs(dy));
/*  96 */     if (dev < 1)
/*  97 */       dev = 1; 
/* 100 */     Point start = getMascot().getAnchor();
/*     */     int i;
/* 102 */     label28: for (i = 0; i <= dev; i++) {
/* 103 */       int x = start.x + dx * i / dev;
/* 104 */       int y = start.y + dy * i / dev;
/* 106 */       getMascot().setAnchor(new Point(x, y));
/* 107 */       if (dy > 0)
/* 109 */         for (int j = -80; j <= 0; j++) {
/* 110 */           getMascot().setAnchor(new Point(x, y + j));
/* 111 */           if (getEnvironment().getFloor(true).isOn(getMascot().getAnchor()))
/*     */             break label28; 
/*     */         }  
/* 116 */       if (getEnvironment().getWall(true).isOn(getMascot().getAnchor()))
/*     */         break; 
/*     */     } 
/* 121 */     getAnimation().next(getMascot(), getTime());
/*     */   }
/*     */   
/*     */   private int getInitialVx() throws VariableException {
/* 127 */     return ((Number)eval(getSchema().getString("InitialVX"), Number.class, Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   private int getInitialVy() throws VariableException {
/* 132 */     return ((Number)eval(getSchema().getString("InitialVY"), Number.class, Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   private double getGravity() throws VariableException {
/* 137 */     return ((Number)eval(getSchema().getString("Gravity"), Number.class, Double.valueOf(2.0D))).doubleValue();
/*     */   }
/*     */   
/*     */   private double getResistanceX() throws VariableException {
/* 142 */     return ((Number)eval(getSchema().getString("ResistanceX"), Number.class, Double.valueOf(0.05D))).doubleValue();
/*     */   }
/*     */   
/*     */   private double getResistanceY() throws VariableException {
/* 147 */     return ((Number)eval(getSchema().getString("ResistanceY"), Number.class, Double.valueOf(0.1D))).doubleValue();
/*     */   }
/*     */   
/*     */   private void setVelocityY(double velocityY) {
/* 151 */     this.velocityY = velocityY;
/*     */   }
/*     */   
/*     */   private double getVelocityY() {
/* 155 */     return this.velocityY;
/*     */   }
/*     */   
/*     */   private void setVelocityX(double velocityX) {
/* 159 */     this.velocityX = velocityX;
/*     */   }
/*     */   
/*     */   private double getVelocityX() {
/* 163 */     return this.velocityX;
/*     */   }
/*     */   
/*     */   private void setModX(double modX) {
/* 167 */     this.modX = modX;
/*     */   }
/*     */   
/*     */   private double getModX() {
/* 171 */     return this.modX;
/*     */   }
/*     */   
/*     */   private void setModY(double modY) {
/* 175 */     this.modY = modY;
/*     */   }
/*     */   
/*     */   private double getModY() {
/* 179 */     return this.modY;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Fall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */