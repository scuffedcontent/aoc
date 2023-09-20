/*     */ package com.group_finity.mascot.config;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.action.Action;
/*     */ import com.group_finity.mascot.action.Animate;
/*     */ import com.group_finity.mascot.action.Move;
/*     */ import com.group_finity.mascot.action.Select;
/*     */ import com.group_finity.mascot.action.Sequence;
/*     */ import com.group_finity.mascot.action.Stay;
/*     */ import com.group_finity.mascot.animation.Animation;
/*     */ import com.group_finity.mascot.exception.ActionInstantiationException;
/*     */ import com.group_finity.mascot.exception.AnimationInstantiationException;
/*     */ import com.group_finity.mascot.exception.ConfigurationException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import com.group_finity.mascot.script.Variable;
/*     */ import com.group_finity.mascot.script.VariableMap;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class ActionBuilder implements IActionBuilder {
/*  35 */   private static final Logger log = Logger.getLogger(ActionBuilder.class.getName());
/*     */   
/*     */   private final String type;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final String className;
/*     */   
/*  39 */   private final Map<String, String> params = new LinkedHashMap<String, String>();
/*     */   
/*  40 */   private final List<AnimationBuilder> animationBuilders = new ArrayList<AnimationBuilder>();
/*     */   
/*  41 */   private final List<IActionBuilder> actionRefs = new ArrayList<IActionBuilder>();
/*     */   
/*     */   private final ResourceBundle schema;
/*     */   
/*     */   public ActionBuilder(Configuration configuration, Entry actionNode, String imageSet) throws IOException {
/*  46 */     this.schema = configuration.getSchema();
/*  47 */     this.name = actionNode.getAttribute(this.schema.getString("Name"));
/*  48 */     this.type = actionNode.getAttribute(this.schema.getString("Type"));
/*  49 */     this.className = actionNode.getAttribute(this.schema.getString("Class"));
/*  51 */     log.log(Level.INFO, "Read Start Operation({0})", this);
/*  53 */     getParams().putAll(actionNode.getAttributes());
/*  54 */     for (Entry node : actionNode.selectChildren(this.schema.getString("Animation")))
/*  56 */       getAnimationBuilders().add(new AnimationBuilder(this.schema, node, imageSet)); 
/*  59 */     for (Entry node : actionNode.getChildren()) {
/*  61 */       if (node.getName().equals(this.schema.getString("ActionReference"))) {
/*  63 */         getActionRefs().add(new ActionRef(configuration, node));
/*     */         continue;
/*     */       } 
/*  65 */       if (node.getName().equals(this.schema.getString("Action")))
/*  67 */         getActionRefs().add(new ActionBuilder(configuration, node, imageSet)); 
/*     */     } 
/*  71 */     log.log(Level.INFO, "Actions Finished Loading");
/*     */   }
/*     */   
/*     */   public String toString() {
/*  77 */     return "Action(" + getName() + "," + getType() + "," + getClassName() + ")";
/*     */   }
/*     */   
/*     */   public Action buildAction(Map<String, String> params) throws ActionInstantiationException {
/*     */     try {
/*  85 */       VariableMap variables = createVariables(params);
/*  88 */       List<Animation> animations = createAnimations();
/*  91 */       List<Action> actions = createActions();
/*  93 */       if (this.type.equals(this.schema.getString("Embedded")))
/*     */         try {
/*  96 */           Class<? extends Action> cls = (Class)Class.forName(getClassName());
/*     */           try {
/* 100 */             return cls.getConstructor(new Class[] { ResourceBundle.class, List.class, VariableMap.class }).newInstance(new Object[] { this.schema, animations, variables });
/* 101 */           } catch (Exception exception) {
/* 105 */             return cls.getConstructor(new Class[] { ResourceBundle.class, VariableMap.class }).newInstance(new Object[] { this.schema, variables });
/* 106 */           } catch (Exception exception) {
/* 110 */             return cls.newInstance();
/*     */           } 
/* 111 */         } catch (InstantiationException e) {
/* 112 */           throw new ActionInstantiationException(Main.getInstance().getLanguageBundle().getString("FailedClassActionInitialiseErrorMessage") + "(" + this + ")", e);
/* 113 */         } catch (IllegalAccessException e) {
/* 114 */           throw new ActionInstantiationException(Main.getInstance().getLanguageBundle().getString("CannotAccessClassActionErrorMessage") + "(" + this + ")", e);
/* 115 */         } catch (ClassNotFoundException e) {
/* 116 */           throw new ActionInstantiationException(Main.getInstance().getLanguageBundle().getString("ClassNotFoundErrorMessage") + "(" + this + ")", e);
/*     */         }  
/* 119 */       if (this.type.equals(this.schema.getString("Move")))
/* 120 */         return (Action)new Move(this.schema, animations, variables); 
/* 121 */       if (this.type.equals(this.schema.getString("Stay")))
/* 122 */         return (Action)new Stay(this.schema, animations, variables); 
/* 123 */       if (this.type.equals(this.schema.getString("Animate")))
/* 124 */         return (Action)new Animate(this.schema, animations, variables); 
/* 125 */       if (this.type.equals(this.schema.getString("Sequence")))
/* 126 */         return (Action)new Sequence(this.schema, variables, actions.<Action>toArray(new Action[0])); 
/* 127 */       if (this.type.equals(this.schema.getString("Select")))
/* 128 */         return (Action)new Select(this.schema, variables, actions.<Action>toArray(new Action[0])); 
/* 130 */       throw new ActionInstantiationException(Main.getInstance().getLanguageBundle().getString("UnknownActionTypeErrorMessage") + "(" + this + ")");
/* 133 */     } catch (AnimationInstantiationException e) {
/* 134 */       throw new ActionInstantiationException(Main.getInstance().getLanguageBundle().getString("FailedCreateAnimationErrorMessage") + "(" + this + ")", e);
/* 135 */     } catch (VariableException e) {
/* 136 */       throw new ActionInstantiationException(Main.getInstance().getLanguageBundle().getString("FailedParameterEvaluationErrorMessage") + "(" + this + ")", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void validate() throws ConfigurationException {
/* 142 */     for (IActionBuilder ref : getActionRefs())
/* 143 */       ref.validate(); 
/*     */   }
/*     */   
/*     */   private List<Action> createActions() throws ActionInstantiationException {
/* 148 */     List<Action> actions = new ArrayList<Action>();
/* 149 */     for (IActionBuilder ref : getActionRefs())
/* 150 */       actions.add(ref.buildAction(new HashMap<String, String>())); 
/* 152 */     return actions;
/*     */   }
/*     */   
/*     */   private List<Animation> createAnimations() throws AnimationInstantiationException {
/* 156 */     List<Animation> animations = new ArrayList<Animation>();
/* 157 */     for (AnimationBuilder animationFactory : getAnimationBuilders())
/* 158 */       animations.add(animationFactory.buildAnimation()); 
/* 160 */     return animations;
/*     */   }
/*     */   
/*     */   private VariableMap createVariables(Map<String, String> params) throws VariableException {
/* 164 */     VariableMap variables = new VariableMap();
/* 165 */     for (Map.Entry<String, String> param : getParams().entrySet())
/* 166 */       variables.put(param.getKey(), Variable.parse(param.getValue())); 
/* 168 */     for (Map.Entry<String, String> param : params.entrySet())
/* 169 */       variables.put(param.getKey(), Variable.parse(param.getValue())); 
/* 171 */     return variables;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 175 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getType() {
/* 179 */     return this.type;
/*     */   }
/*     */   
/*     */   private String getClassName() {
/* 183 */     return this.className;
/*     */   }
/*     */   
/*     */   private Map<String, String> getParams() {
/* 187 */     return this.params;
/*     */   }
/*     */   
/*     */   private List<AnimationBuilder> getAnimationBuilders() {
/* 191 */     return this.animationBuilders;
/*     */   }
/*     */   
/*     */   private List<IActionBuilder> getActionRefs() {
/* 195 */     return this.actionRefs;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/config/ActionBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */