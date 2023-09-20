/*     */ package com.group_finity.mascot.config;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.Mascot;
/*     */ import com.group_finity.mascot.action.Action;
/*     */ import com.group_finity.mascot.behavior.Behavior;
/*     */ import com.group_finity.mascot.exception.ActionInstantiationException;
/*     */ import com.group_finity.mascot.exception.BehaviorInstantiationException;
/*     */ import com.group_finity.mascot.exception.ConfigurationException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import com.group_finity.mascot.script.VariableMap;
/*     */ import com.joconner.i18n.Utf8ResourceBundleControl;
/*     */ import java.awt.Point;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class Configuration {
/*  33 */   private static final Logger log = Logger.getLogger(Configuration.class.getName());
/*     */   
/*  34 */   private final Map<String, ActionBuilder> actionBuilders = new LinkedHashMap<String, ActionBuilder>();
/*     */   
/*  35 */   private final Map<String, BehaviorBuilder> behaviorBuilders = new LinkedHashMap<String, BehaviorBuilder>();
/*     */   
/*     */   private ResourceBundle schema;
/*     */   
/*     */   public void load(Entry configurationNode, String imageSet) throws IOException, ConfigurationException {
/*     */     Locale locale;
/*  40 */     log.log(Level.INFO, "Start Reading Configuration File...");
/*  43 */     Utf8ResourceBundleControl utf8ResourceBundleControl = new Utf8ResourceBundleControl(false);
/*  47 */     if (configurationNode.hasChild("動作リスト") || configurationNode
/*  48 */       .hasChild("行動リスト")) {
/*  49 */       locale = Locale.forLanguageTag("ja-JP");
/*     */     } else {
/*  51 */       locale = Locale.forLanguageTag("en-US");
/*     */     } 
/*  53 */     this.schema = ResourceBundle.getBundle("schema", locale, (ResourceBundle.Control)utf8ResourceBundleControl);
/*  55 */     for (Entry list : configurationNode.selectChildren(this.schema.getString("ActionList"))) {
/*  57 */       log.log(Level.INFO, "Action List...");
/*  59 */       for (Entry node : list.selectChildren(this.schema.getString("Action"))) {
/*  61 */         ActionBuilder action = new ActionBuilder(this, node, imageSet);
/*  63 */         if (getActionBuilders().containsKey(action.getName()))
/*  65 */           throw new ConfigurationException(Main.getInstance().getLanguageBundle().getString("DuplicateActionErrorMessage") + ": " + action.getName()); 
/*  68 */         getActionBuilders().put(action.getName(), action);
/*     */       } 
/*     */     } 
/*  72 */     for (Entry list : configurationNode.selectChildren(this.schema.getString("BehaviourList"))) {
/*  74 */       log.log(Level.INFO, "Behavior List...");
/*  76 */       loadBehaviors(list, new ArrayList<String>());
/*     */     } 
/*  79 */     log.log(Level.INFO, "Behavior List");
/*     */   }
/*     */   
/*     */   private void loadBehaviors(Entry list, List<String> conditions) {
/*  84 */     for (Entry node : list.getChildren()) {
/*  86 */       if (node.getName().equals(this.schema.getString("Condition"))) {
/*  88 */         List<String> newConditions = new ArrayList<String>(conditions);
/*  89 */         newConditions.add(node.getAttribute(this.schema.getString("Condition")));
/*  91 */         loadBehaviors(node, newConditions);
/*     */         continue;
/*     */       } 
/*  93 */       if (node.getName().equals(this.schema.getString("Behaviour"))) {
/*  95 */         BehaviorBuilder behavior = new BehaviorBuilder(this, node, conditions);
/*  96 */         getBehaviorBuilders().put(behavior.getName(), behavior);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Action buildAction(String name, Map<String, String> params) throws ActionInstantiationException {
/* 103 */     ActionBuilder factory = this.actionBuilders.get(name);
/* 104 */     if (factory == null)
/* 105 */       throw new ActionInstantiationException(Main.getInstance().getLanguageBundle().getString("NoCorrespondingActionFoundErrorMessage") + ": " + name); 
/* 108 */     return factory.buildAction(params);
/*     */   }
/*     */   
/*     */   public void validate() throws ConfigurationException {
/* 113 */     for (ActionBuilder builder : getActionBuilders().values())
/* 114 */       builder.validate(); 
/* 116 */     for (BehaviorBuilder builder : getBehaviorBuilders().values())
/* 117 */       builder.validate(); 
/*     */   }
/*     */   
/*     */   public Behavior buildBehavior(String previousName, Mascot mascot) throws BehaviorInstantiationException {
/* 123 */     VariableMap context = new VariableMap();
/* 124 */     context.put("mascot", mascot);
/* 126 */     List<BehaviorBuilder> candidates = new ArrayList<BehaviorBuilder>();
/* 127 */     long totalFrequency = 0L;
/* 128 */     for (BehaviorBuilder behaviorFactory : getBehaviorBuilders().values()) {
/*     */       try {
/* 130 */         if (behaviorFactory.isEffective(context)) {
/* 131 */           candidates.add(behaviorFactory);
/* 132 */           totalFrequency += behaviorFactory.getFrequency();
/*     */         } 
/* 134 */       } catch (VariableException e) {
/* 135 */         log.log(Level.WARNING, "An error occurred calculating the frequency of the action", (Throwable)e);
/*     */       } 
/*     */     } 
/* 139 */     if (previousName != null) {
/* 140 */       BehaviorBuilder previousBehaviorFactory = getBehaviorBuilders().get(previousName);
/* 141 */       if (!previousBehaviorFactory.isNextAdditive()) {
/* 142 */         totalFrequency = 0L;
/* 143 */         candidates.clear();
/*     */       } 
/* 145 */       for (BehaviorBuilder behaviorFactory : previousBehaviorFactory.getNextBehaviorBuilders()) {
/*     */         try {
/* 147 */           if (behaviorFactory.isEffective(context)) {
/* 148 */             candidates.add(behaviorFactory);
/* 149 */             totalFrequency += behaviorFactory.getFrequency();
/*     */           } 
/* 151 */         } catch (VariableException e) {
/* 152 */           log.log(Level.WARNING, "An error occurred calculating the frequency of the behavior", (Throwable)e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 157 */     if (totalFrequency == 0L) {
/* 158 */       mascot.setAnchor(new Point(
/*     */             
/* 161 */             (int)(Math.random() * (mascot.getEnvironment().getScreen().getRight() - mascot.getEnvironment().getScreen().getLeft())) + mascot
/* 162 */             .getEnvironment().getScreen().getLeft(), mascot.getEnvironment().getScreen().getTop() - 256));
/* 163 */       return buildBehavior(this.schema.getString("Fall"));
/*     */     } 
/* 166 */     double random = Math.random() * totalFrequency;
/* 168 */     for (BehaviorBuilder behaviorFactory : candidates) {
/* 169 */       random -= behaviorFactory.getFrequency();
/* 170 */       if (random < 0.0D)
/* 171 */         return behaviorFactory.buildBehavior(); 
/*     */     } 
/* 175 */     return null;
/*     */   }
/*     */   
/*     */   public Behavior buildBehavior(String name) throws BehaviorInstantiationException {
/* 179 */     return ((BehaviorBuilder)getBehaviorBuilders().get(name)).buildBehavior();
/*     */   }
/*     */   
/*     */   Map<String, ActionBuilder> getActionBuilders() {
/* 183 */     return this.actionBuilders;
/*     */   }
/*     */   
/*     */   private Map<String, BehaviorBuilder> getBehaviorBuilders() {
/* 187 */     return this.behaviorBuilders;
/*     */   }
/*     */   
/*     */   public Set<String> getBehaviorNames() {
/* 191 */     return this.behaviorBuilders.keySet();
/*     */   }
/*     */   
/*     */   public ResourceBundle getSchema() {
/* 196 */     return this.schema;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/config/Configuration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */