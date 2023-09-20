/*     */ package com.group_finity.mascot.action;
/*     */ 
/*     */ import com.group_finity.mascot.Mascot;
/*     */ import com.group_finity.mascot.animation.Animation;
/*     */ import com.group_finity.mascot.environment.MascotEnvironment;
/*     */ import com.group_finity.mascot.exception.LostGroundException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import com.group_finity.mascot.script.Variable;
/*     */ import com.group_finity.mascot.script.VariableMap;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public abstract class ActionBase implements Action {
/*  21 */   private static final Logger log = Logger.getLogger(ActionBase.class.getName());
/*     */   
/*     */   public static final String PARAMETER_DURATION = "Duration";
/*     */   
/*     */   private static final boolean DEFAULT_CONDITION = true;
/*     */   
/*     */   public static final String PARAMETER_CONDITION = "Condition";
/*     */   
/*     */   private static final int DEFAULT_DURATION = 2147483647;
/*     */   
/*     */   public static final String PARAMETER_DRAGGABLE = "Draggable";
/*     */   
/*     */   private static final boolean DEFAULT_DRAGGABLE = true;
/*     */   
/*     */   private Mascot mascot;
/*     */   
/*     */   private int startTime;
/*     */   
/*     */   private List<Animation> animations;
/*     */   
/*     */   private VariableMap variables;
/*     */   
/*     */   private ResourceBundle schema;
/*     */   
/*     */   public ActionBase(ResourceBundle schema, List<Animation> animations, VariableMap context) {
/*  47 */     this.schema = schema;
/*  48 */     this.animations = animations;
/*  49 */     this.variables = context;
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     try {
/*  55 */       return "Action (" + getClass().getSimpleName() + "," + getName() + ")";
/*  56 */     } catch (VariableException e) {
/*  57 */       return "Action (" + getClass().getSimpleName() + "," + null + ")";
/*     */     } 
/*     */   }
/*     */   
/*     */   public void init(Mascot mascot) throws VariableException {
/*  63 */     setMascot(mascot);
/*  64 */     setTime(0);
/*  66 */     getVariables().put("mascot", mascot);
/*  67 */     getVariables().put("action", this);
/*  69 */     getVariables().init();
/*  71 */     for (Animation animation : this.animations)
/*  72 */       animation.init(); 
/*     */   }
/*     */   
/*     */   public void next() throws LostGroundException, VariableException {
/*  78 */     initFrame();
/*  80 */     getMascot().getAffordances().clear();
/*  81 */     tick();
/*     */   }
/*     */   
/*     */   private void initFrame() {
/*  86 */     getVariables().initFrame();
/*  88 */     for (Animation animation : getAnimations())
/*  89 */       animation.initFrame(); 
/*     */   }
/*     */   
/*     */   protected List<Animation> getAnimations() {
/*  94 */     return this.animations;
/*     */   }
/*     */   
/*     */   protected abstract void tick() throws LostGroundException, VariableException;
/*     */   
/*     */   public boolean hasNext() throws VariableException {
/* 102 */     boolean effective = isEffective().booleanValue();
/* 103 */     boolean intime = (getTime() < getDuration());
/* 105 */     return (effective && intime);
/*     */   }
/*     */   
/*     */   public Boolean isDraggable() throws VariableException {
/* 110 */     return eval(this.schema.getString("Draggable"), Boolean.class, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   private Boolean isEffective() throws VariableException {
/* 115 */     return eval(this.schema.getString("Condition"), Boolean.class, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   private int getDuration() throws VariableException {
/* 120 */     return ((Number)eval(this.schema.getString("Duration"), Number.class, Integer.valueOf(2147483647))).intValue();
/*     */   }
/*     */   
/*     */   private void setMascot(Mascot mascot) {
/* 124 */     this.mascot = mascot;
/*     */   }
/*     */   
/*     */   protected Mascot getMascot() {
/* 128 */     return this.mascot;
/*     */   }
/*     */   
/*     */   protected int getTime() {
/* 132 */     return getMascot().getTime() - this.startTime;
/*     */   }
/*     */   
/*     */   protected void setTime(int time) {
/* 136 */     this.startTime = getMascot().getTime() - time;
/*     */   }
/*     */   
/*     */   private String getName() throws VariableException {
/* 141 */     return eval(this.schema.getString("Name"), String.class, null);
/*     */   }
/*     */   
/*     */   protected Animation getAnimation() throws VariableException {
/* 145 */     for (Animation animation : getAnimations()) {
/* 146 */       if (animation.isEffective(getVariables()))
/* 147 */         return animation; 
/*     */     } 
/* 151 */     return null;
/*     */   }
/*     */   
/*     */   protected VariableMap getVariables() {
/* 155 */     return this.variables;
/*     */   }
/*     */   
/*     */   protected void putVariable(String key, Object value) {
/* 159 */     synchronized (getVariables()) {
/* 160 */       getVariables().put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected <T> T eval(String name, Class<T> type, T defaultValue) throws VariableException {
/* 166 */     synchronized (getVariables()) {
/* 167 */       Variable variable = (Variable)getVariables().getRawMap().get(name);
/* 168 */       if (variable != null)
/* 169 */         return type.cast(variable.get(getVariables())); 
/*     */     } 
/* 173 */     return defaultValue;
/*     */   }
/*     */   
/*     */   protected MascotEnvironment getEnvironment() {
/* 177 */     return getMascot().getEnvironment();
/*     */   }
/*     */   
/*     */   protected ResourceBundle getSchema() {
/* 182 */     return this.schema;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/ActionBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */