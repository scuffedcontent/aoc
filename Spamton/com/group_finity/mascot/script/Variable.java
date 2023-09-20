/*    */ package com.group_finity.mascot.script;
/*    */ 
/*    */ import com.group_finity.mascot.exception.VariableException;
/*    */ 
/*    */ public abstract class Variable {
/*    */   public static Variable parse(String source) throws VariableException {
/* 14 */     Variable result = null;
/* 16 */     if (source != null)
/* 17 */       if (source.startsWith("${") && source.endsWith("}")) {
/* 18 */         result = new Script(source.substring(2, source.length() - 1), false);
/* 19 */       } else if (source.startsWith("#{") && source.endsWith("}")) {
/* 20 */         result = new Script(source.substring(2, source.length() - 1), true);
/*    */       } else {
/* 22 */         result = new Constant(parseConstant(source));
/*    */       }  
/* 26 */     return result;
/*    */   }
/*    */   
/*    */   private static Object parseConstant(String source) {
/* 30 */     Object result = null;
/* 32 */     if (source != null) {
/* 33 */       if (source.equals("null"))
/* 34 */         result = null; 
/* 36 */       if (source.equals("true")) {
/* 37 */         result = Boolean.TRUE;
/* 38 */       } else if (source.equals("false")) {
/* 39 */         result = Boolean.FALSE;
/*    */       } else {
/*    */         try {
/* 42 */           result = Double.valueOf(Double.parseDouble(source));
/* 43 */         } catch (NumberFormatException e) {
/* 44 */           result = source;
/*    */         } 
/*    */       } 
/*    */     } 
/* 49 */     return result;
/*    */   }
/*    */   
/*    */   public abstract void init();
/*    */   
/*    */   public abstract void initFrame();
/*    */   
/*    */   public abstract Object get(VariableMap paramVariableMap) throws VariableException;
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/script/Variable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */