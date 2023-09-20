/*    */ package com.group_finity.mascot.script;
/*    */ 
/*    */ import com.group_finity.mascot.Main;
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ import javax.script.Compilable;
/*    */ import javax.script.CompiledScript;
/*    */ import javax.script.ScriptEngine;
/*    */ import javax.script.ScriptEngineManager;
/*    */ import javax.script.ScriptException;
/*    */ 
/*    */ public class Script extends Variable {
/* 19 */   private static final ScriptEngineManager manager = new ScriptEngineManager();
/*    */   
/* 21 */   private static final ScriptEngine engine = manager.getEngineByMimeType("text/javascript");
/*    */   
/*    */   private final String source;
/*    */   
/*    */   private final boolean clearAtInitFrame;
/*    */   
/*    */   private final CompiledScript compiled;
/*    */   
/*    */   private Object value;
/*    */   
/*    */   public Script(String source, boolean clearAtInitFrame) throws VariableException {
/* 32 */     this.source = source;
/* 33 */     this.clearAtInitFrame = clearAtInitFrame;
/*    */     try {
/* 35 */       this.compiled = ((Compilable)engine).compile(this.source);
/* 36 */     } catch (ScriptException e) {
/* 37 */       throw new VariableException(Main.getInstance().getLanguageBundle().getString("ScriptCompilationErrorMessage") + ": " + this.source, e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     return isClearAtInitFrame() ? ("#{" + getSource() + "}") : ("${" + getSource() + "}");
/*    */   }
/*    */   
/*    */   public void init() {
/* 48 */     setValue(null);
/*    */   }
/*    */   
/*    */   public void initFrame() {
/* 53 */     if (isClearAtInitFrame())
/* 54 */       setValue(null); 
/*    */   }
/*    */   
/*    */   public synchronized Object get(VariableMap variables) throws VariableException {
/* 61 */     if (getValue() != null)
/* 62 */       return getValue(); 
/*    */     try {
/* 66 */       setValue(getCompiled().eval(variables));
/* 67 */     } catch (ScriptException e) {
/* 68 */       throw new VariableException(Main.getInstance().getLanguageBundle().getString("ScriptEvaluationErrorMessage") + ": " + this.source, e);
/*    */     } 
/* 71 */     return getValue();
/*    */   }
/*    */   
/*    */   private void setValue(Object value) {
/* 75 */     this.value = value;
/*    */   }
/*    */   
/*    */   private Object getValue() {
/* 79 */     return this.value;
/*    */   }
/*    */   
/*    */   private boolean isClearAtInitFrame() {
/* 83 */     return this.clearAtInitFrame;
/*    */   }
/*    */   
/*    */   private CompiledScript getCompiled() {
/* 87 */     return this.compiled;
/*    */   }
/*    */   
/*    */   private String getSource() {
/* 91 */     return this.source;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/script/Script.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */