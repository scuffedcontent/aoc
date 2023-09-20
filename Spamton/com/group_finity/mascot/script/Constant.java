/*    */ package com.group_finity.mascot.script;
/*    */ 
/*    */ public class Constant extends Variable {
/*    */   private final Object value;
/*    */   
/*    */   public Constant(Object value) {
/* 13 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 18 */     return (this.value == null) ? "null" : this.value.toString();
/*    */   }
/*    */   
/*    */   private Object getValue() {
/* 22 */     return this.value;
/*    */   }
/*    */   
/*    */   public void init() {}
/*    */   
/*    */   public void initFrame() {}
/*    */   
/*    */   public Object get(VariableMap variables) {
/* 35 */     return getValue();
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/script/Constant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */