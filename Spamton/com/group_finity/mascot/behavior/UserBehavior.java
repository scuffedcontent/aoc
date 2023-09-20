/*     */ package com.group_finity.mascot.behavior;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.Mascot;
/*     */ import com.group_finity.mascot.action.Action;
/*     */ import com.group_finity.mascot.action.ActionBase;
/*     */ import com.group_finity.mascot.config.Configuration;
/*     */ import com.group_finity.mascot.environment.MascotEnvironment;
/*     */ import com.group_finity.mascot.exception.BehaviorInstantiationException;
/*     */ import com.group_finity.mascot.exception.CantBeAliveException;
/*     */ import com.group_finity.mascot.exception.LostGroundException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ public class UserBehavior implements Behavior {
/*  28 */   private static final Logger log = Logger.getLogger(UserBehavior.class.getName());
/*     */   
/*     */   public static final String BEHAVIOURNAME_FALL = "Fall";
/*     */   
/*     */   public static final String BEHAVIOURNAME_DRAGGED = "Dragged";
/*     */   
/*     */   public static final String BEHAVIOURNAME_THROWN = "Thrown";
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final Configuration configuration;
/*     */   
/*     */   private final Action action;
/*     */   
/*     */   private Mascot mascot;
/*     */   
/*     */   private boolean hidden;
/*     */   
/*     */   public UserBehavior(String name, Action action, Configuration configuration, boolean hidden) {
/*  47 */     this.name = name;
/*  48 */     this.configuration = configuration;
/*  49 */     this.action = action;
/*  50 */     this.hidden = hidden;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  56 */     return "Behavior(" + getName() + ")";
/*     */   }
/*     */   
/*     */   public synchronized void init(Mascot mascot) throws CantBeAliveException {
/*  62 */     setMascot(mascot);
/*  64 */     log.log(Level.INFO, "Default Behavior({0},{1})", new Object[] { getMascot(), this });
/*     */     try {
/*  67 */       getAction().init(mascot);
/*  68 */       if (!getAction().hasNext())
/*     */         try {
/*  70 */           mascot.setBehavior(getConfiguration().buildBehavior(getName(), mascot));
/*  71 */         } catch (BehaviorInstantiationException e) {
/*  72 */           throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("FailedInitialiseFollowingBehaviourErrorMessage"), e);
/*     */         }  
/*  75 */     } catch (VariableException e) {
/*  76 */       throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("VariableEvaluationErrorMessage"), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Configuration getConfiguration() {
/*  82 */     return this.configuration;
/*     */   }
/*     */   
/*     */   private Action getAction() {
/*  86 */     return this.action;
/*     */   }
/*     */   
/*     */   private String getName() {
/*  90 */     return this.name;
/*     */   }
/*     */   
/*     */   public synchronized void mousePressed(MouseEvent event) throws CantBeAliveException {
/* 100 */     if (SwingUtilities.isLeftMouseButton(event)) {
/* 103 */       boolean draggable = true;
/* 104 */       if (this.action != null && this.action instanceof ActionBase)
/*     */         try {
/* 108 */           draggable = ((ActionBase)this.action).isDraggable().booleanValue();
/* 110 */         } catch (VariableException ex) {
/* 112 */           throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("FailedDragActionInitialiseErrorMessage"), ex);
/*     */         }  
/* 116 */       if (draggable)
/*     */         try {
/* 121 */           getMascot().setBehavior(getConfiguration().buildBehavior(this.configuration.getSchema().getString("Dragged")));
/* 123 */         } catch (BehaviorInstantiationException e) {
/* 125 */           throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("FailedDragActionInitialiseErrorMessage"), e);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void mouseReleased(MouseEvent event) throws CantBeAliveException {
/* 138 */     if (SwingUtilities.isLeftMouseButton(event)) {
/* 141 */       boolean draggable = true;
/* 142 */       if (this.action != null && this.action instanceof ActionBase)
/*     */         try {
/* 146 */           draggable = ((ActionBase)this.action).isDraggable().booleanValue();
/* 148 */         } catch (VariableException ex) {
/* 150 */           throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("FailedDropActionInitialiseErrorMessage"), ex);
/*     */         }  
/* 154 */       if (draggable)
/*     */         try {
/* 159 */           getMascot().setBehavior(getConfiguration().buildBehavior(this.configuration.getSchema().getString("Thrown")));
/* 161 */         } catch (BehaviorInstantiationException e) {
/* 163 */           throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("FailedDropActionInitialiseErrorMessage"), e);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void next() throws CantBeAliveException {
/*     */     try {
/* 173 */       if (getAction().hasNext())
/* 174 */         getAction().next(); 
/* 177 */       if (getAction().hasNext()) {
/* 179 */         if (getMascot().getBounds().getX() + getMascot().getBounds().getWidth() <= 
/* 180 */           getEnvironment().getScreen().getLeft() || 
/* 181 */           getEnvironment().getScreen().getRight() <= getMascot().getBounds().getX() || 
/* 182 */           getEnvironment().getScreen().getBottom() <= getMascot().getBounds().getY()) {
/* 184 */           log.log(Level.INFO, "Out of the screen bounds({0},{1})", new Object[] { getMascot(), this });
/* 186 */           getMascot().setAnchor(new Point(
/*     */                 
/* 188 */                 (int)(Math.random() * (getEnvironment().getScreen().getRight() - getEnvironment().getScreen().getLeft())) + 
/* 189 */                 getEnvironment().getScreen().getLeft(), getEnvironment().getScreen().getTop() - 256));
/*     */           try {
/* 192 */             getMascot().setBehavior(getConfiguration().buildBehavior(this.configuration.getSchema().getString("Fall")));
/* 193 */           } catch (BehaviorInstantiationException e) {
/* 194 */             throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("FailedFallingActionInitialiseErrorMessage"), e);
/*     */           } 
/*     */         } 
/*     */       } else {
/* 199 */         log.log(Level.INFO, "Completed Behavior ({0},{1})", new Object[] { getMascot(), this });
/*     */         try {
/* 202 */           getMascot().setBehavior(getConfiguration().buildBehavior(getName(), getMascot()));
/* 203 */         } catch (BehaviorInstantiationException e) {
/* 204 */           throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("FailedInitialiseFollowingActionsErrorMessage"), e);
/*     */         } 
/*     */       } 
/* 207 */     } catch (LostGroundException e) {
/* 208 */       log.log(Level.INFO, "Lost Ground ({0},{1})", new Object[] { getMascot(), this });
/*     */       try {
/* 211 */         getMascot().setBehavior(getConfiguration().buildBehavior(this.configuration.getSchema().getString("Fall")));
/* 212 */       } catch (BehaviorInstantiationException ex) {
/* 213 */         throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("FailedFallingActionInitialiseErrorMessage"), e);
/*     */       } 
/* 215 */     } catch (VariableException e) {
/* 216 */       throw new CantBeAliveException(Main.getInstance().getLanguageBundle().getString("VariableEvaluationErrorMessage"), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setMascot(Mascot mascot) {
/* 222 */     this.mascot = mascot;
/*     */   }
/*     */   
/*     */   private Mascot getMascot() {
/* 226 */     return this.mascot;
/*     */   }
/*     */   
/*     */   protected MascotEnvironment getEnvironment() {
/* 230 */     return getMascot().getEnvironment();
/*     */   }
/*     */   
/*     */   public boolean isHidden() {
/* 236 */     return this.hidden;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/behavior/UserBehavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */