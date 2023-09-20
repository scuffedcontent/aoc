/*     */ package com.group_finity.mascot.config;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.behavior.Behavior;
/*     */ import com.group_finity.mascot.behavior.UserBehavior;
/*     */ import com.group_finity.mascot.exception.ActionInstantiationException;
/*     */ import com.group_finity.mascot.exception.BehaviorInstantiationException;
/*     */ import com.group_finity.mascot.exception.ConfigurationException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import com.group_finity.mascot.script.Variable;
/*     */ import com.group_finity.mascot.script.VariableMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class BehaviorBuilder {
/*  27 */   private static final Logger log = Logger.getLogger(BehaviorBuilder.class.getName());
/*     */   
/*     */   private final Configuration configuration;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final String actionName;
/*     */   
/*     */   private final int frequency;
/*     */   
/*     */   private final List<String> conditions;
/*     */   
/*     */   private final boolean hidden;
/*     */   
/*     */   private final boolean nextAdditive;
/*     */   
/*  43 */   private final List<BehaviorBuilder> nextBehaviorBuilders = new ArrayList<BehaviorBuilder>();
/*     */   
/*  45 */   private final Map<String, String> params = new LinkedHashMap<String, String>();
/*     */   
/*     */   public BehaviorBuilder(Configuration configuration, Entry behaviorNode, List<String> conditions) {
/*  48 */     this.configuration = configuration;
/*  49 */     this.name = behaviorNode.getAttribute(configuration.getSchema().getString("Name"));
/*  50 */     this.actionName = (behaviorNode.getAttribute(configuration.getSchema().getString("Action")) == null) ? getName() : behaviorNode.getAttribute(configuration.getSchema().getString("Action"));
/*  51 */     this.frequency = Integer.parseInt(behaviorNode.getAttribute(configuration.getSchema().getString("Frequency")));
/*  52 */     this.hidden = Boolean.parseBoolean(behaviorNode.getAttribute(configuration.getSchema().getString("Hidden")));
/*  53 */     this.conditions = new ArrayList<String>(conditions);
/*  54 */     getConditions().add(behaviorNode.getAttribute(configuration.getSchema().getString("Condition")));
/*  56 */     log.log(Level.INFO, "Start Reading({0})", this);
/*  58 */     getParams().putAll(behaviorNode.getAttributes());
/*  59 */     getParams().remove(configuration.getSchema().getString("Name"));
/*  60 */     getParams().remove(configuration.getSchema().getString("Action"));
/*  61 */     getParams().remove(configuration.getSchema().getString("Frequency"));
/*  62 */     getParams().remove(configuration.getSchema().getString("Hidden"));
/*  63 */     getParams().remove(configuration.getSchema().getString("Condition"));
/*  65 */     boolean nextAdditive = true;
/*  67 */     for (Entry nextList : behaviorNode.selectChildren(configuration.getSchema().getString("NextBehaviourList"))) {
/*  69 */       log.log(Level.INFO, "Lists the Following Behaviors...");
/*  71 */       nextAdditive = Boolean.parseBoolean(nextList.getAttribute(configuration.getSchema().getString("Add")));
/*  73 */       loadBehaviors(nextList, new ArrayList<String>());
/*     */     } 
/*  76 */     this.nextAdditive = nextAdditive;
/*  78 */     log.log(Level.INFO, "Behaviors have finished loading({0})", this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  84 */     return "Behavior(" + getName() + "," + getFrequency() + "," + getActionName() + ")";
/*     */   }
/*     */   
/*     */   private void loadBehaviors(Entry list, List<String> conditions) {
/*  89 */     for (Entry node : list.getChildren()) {
/*  91 */       if (node.getName().equals(this.configuration.getSchema().getString("Condition"))) {
/*  94 */         List<String> newConditions = new ArrayList<String>(conditions);
/*  95 */         newConditions.add(node.getAttribute(this.configuration.getSchema().getString("Condition")));
/*  97 */         loadBehaviors(node, newConditions);
/*     */         continue;
/*     */       } 
/* 100 */       if (node.getName().equals(this.configuration.getSchema().getString("BehaviourReference"))) {
/* 102 */         BehaviorBuilder behavior = new BehaviorBuilder(getConfiguration(), node, conditions);
/* 103 */         getNextBehaviorBuilders().add(behavior);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void validate() throws ConfigurationException {
/* 110 */     if (!getConfiguration().getActionBuilders().containsKey(getActionName())) {
/* 111 */       log.log(Level.SEVERE, "There is no corresponding action(" + this + ")");
/* 112 */       throw new ConfigurationException(Main.getInstance().getLanguageBundle().getString("NoActionFoundErrorMessage") + "(" + this + ")");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Behavior buildBehavior() throws BehaviorInstantiationException {
/*     */     try {
/* 119 */       return (Behavior)new UserBehavior(getName(), 
/* 120 */           getConfiguration().buildAction(getActionName(), 
/* 121 */             getParams()), getConfiguration(), isHidden());
/* 122 */     } catch (ActionInstantiationException e) {
/* 123 */       log.log(Level.SEVERE, "Failed to initialize the corresponding action(" + this + ")");
/* 124 */       throw new BehaviorInstantiationException(Main.getInstance().getLanguageBundle().getString("FailedInitialiseCorrespondingActionErrorMessage") + "(" + this + ")", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEffective(VariableMap context) throws VariableException {
/* 131 */     for (String condition : getConditions()) {
/* 132 */       if (condition != null && 
/* 133 */         !((Boolean)Variable.parse(condition).get(context)).booleanValue())
/* 134 */         return false; 
/*     */     } 
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 143 */     return this.name;
/*     */   }
/*     */   
/*     */   public int getFrequency() {
/* 147 */     return this.frequency;
/*     */   }
/*     */   
/*     */   public boolean isHidden() {
/* 151 */     return this.hidden;
/*     */   }
/*     */   
/*     */   private String getActionName() {
/* 155 */     return this.actionName;
/*     */   }
/*     */   
/*     */   private Map<String, String> getParams() {
/* 159 */     return this.params;
/*     */   }
/*     */   
/*     */   private List<String> getConditions() {
/* 163 */     return this.conditions;
/*     */   }
/*     */   
/*     */   private Configuration getConfiguration() {
/* 167 */     return this.configuration;
/*     */   }
/*     */   
/*     */   public boolean isNextAdditive() {
/* 171 */     return this.nextAdditive;
/*     */   }
/*     */   
/*     */   public List<BehaviorBuilder> getNextBehaviorBuilders() {
/* 175 */     return this.nextBehaviorBuilders;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/config/BehaviorBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */