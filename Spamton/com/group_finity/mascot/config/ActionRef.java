/*    */ package com.group_finity.mascot.config;
/*    */ 
/*    */ import com.group_finity.mascot.Main;
/*    */ import com.group_finity.mascot.action.Action;
/*    */ import com.group_finity.mascot.exception.ActionInstantiationException;
/*    */ import com.group_finity.mascot.exception.ConfigurationException;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class ActionRef implements IActionBuilder {
/* 20 */   private static final Logger log = Logger.getLogger(ActionRef.class.getName());
/*    */   
/*    */   private final Configuration configuration;
/*    */   
/*    */   private final String name;
/*    */   
/* 26 */   private final Map<String, String> params = new LinkedHashMap<String, String>();
/*    */   
/*    */   public ActionRef(Configuration configuration, Entry refNode) {
/* 29 */     this.configuration = configuration;
/* 31 */     this.name = refNode.getAttribute(configuration.getSchema().getString("Name"));
/* 32 */     getParams().putAll(refNode.getAttributes());
/* 34 */     log.log(Level.INFO, "Read Action Reference({0})", this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 39 */     return "Action(" + getName() + ")";
/*    */   }
/*    */   
/*    */   private String getName() {
/* 43 */     return this.name;
/*    */   }
/*    */   
/*    */   private Map<String, String> getParams() {
/* 47 */     return this.params;
/*    */   }
/*    */   
/*    */   private Configuration getConfiguration() {
/* 51 */     return this.configuration;
/*    */   }
/*    */   
/*    */   public void validate() throws ConfigurationException {
/* 56 */     if (!getConfiguration().getActionBuilders().containsKey(getName())) {
/* 57 */       log.log(Level.SEVERE, "There is no corresponding behavior(" + this + ")");
/* 58 */       throw new ConfigurationException(Main.getInstance().getLanguageBundle().getString("NoBehaviourFoundErrorMessage") + "(" + this + ")");
/*    */     } 
/*    */   }
/*    */   
/*    */   public Action buildAction(Map<String, String> params) throws ActionInstantiationException {
/* 63 */     Map<String, String> newParams = new LinkedHashMap<String, String>(params);
/* 64 */     newParams.putAll(getParams());
/* 65 */     return getConfiguration().buildAction(getName(), newParams);
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/config/ActionRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */