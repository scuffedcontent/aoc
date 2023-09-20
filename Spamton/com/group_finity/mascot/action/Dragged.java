/*     */ package com.group_finity.mascot.action;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.Mascot;
/*     */ import com.group_finity.mascot.animation.Animation;
/*     */ import com.group_finity.mascot.environment.Location;
/*     */ import com.group_finity.mascot.exception.LostGroundException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import com.group_finity.mascot.script.VariableMap;
/*     */ import java.awt.Point;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class Dragged extends ActionBase {
/*  21 */   private static final Logger log = Logger.getLogger(Dragged.class.getName());
/*     */   
/*     */   private static final String VARIABLE_FOOTX = "FootX";
/*     */   
/*     */   private double footX;
/*     */   
/*     */   private double footDx;
/*     */   
/*     */   private int timeToRegist;
/*     */   
/*     */   private int scaling;
/*     */   
/*     */   public Dragged(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/*  35 */     super(schema, animations, context);
/*     */   }
/*     */   
/*     */   public void init(Mascot mascot) throws VariableException {
/*  40 */     super.init(mascot);
/*  42 */     this.scaling = Integer.parseInt(Main.getInstance().getProperties().getProperty("Scaling", "1"));
/*  44 */     setFootX(getEnvironment().getCursor().getX());
/*  45 */     setTimeToRegist(250);
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws VariableException {
/*  52 */     boolean intime = (getTime() < getTimeToRegist());
/*  53 */     boolean lukewarm = (Math.random() >= 0.1D);
/*  55 */     return (super.hasNext() && (intime || lukewarm));
/*     */   }
/*     */   
/*     */   protected void tick() throws LostGroundException, VariableException {
/*  62 */     getMascot().setLookRight(false);
/*  64 */     Location cursor = getEnvironment().getCursor();
/*  66 */     if (Math.abs(cursor.getX() - (getMascot().getAnchor()).x) >= 5)
/*  67 */       setTime(0); 
/*  70 */     int newX = cursor.getX();
/*  72 */     setFootDx((getFootDx() + (newX - getFootX()) * 0.1D) * 0.8D);
/*  73 */     setFootX(getFootX() + getFootDx());
/*  75 */     putVariable(getSchema().getString("FootX"), Double.valueOf(getFootX()));
/*  77 */     getAnimation().next(getMascot(), getTime());
/*  79 */     getMascot().setAnchor(new Point(cursor.getX(), cursor.getY() + 120 * this.scaling));
/*     */   }
/*     */   
/*     */   public void setTimeToRegist(int timeToRegist) {
/*  83 */     this.timeToRegist = timeToRegist;
/*     */   }
/*     */   
/*     */   private int getTimeToRegist() {
/*  87 */     return this.timeToRegist;
/*     */   }
/*     */   
/*     */   private void setFootX(double footX) {
/*  91 */     this.footX = footX;
/*     */   }
/*     */   
/*     */   private double getFootX() {
/*  95 */     return this.footX;
/*     */   }
/*     */   
/*     */   private void setFootDx(double footDx) {
/*  99 */     this.footDx = footDx;
/*     */   }
/*     */   
/*     */   private double getFootDx() {
/* 103 */     return this.footDx;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Dragged.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */