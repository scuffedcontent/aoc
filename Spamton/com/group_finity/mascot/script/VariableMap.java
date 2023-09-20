/*     */ package com.group_finity.mascot.script;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.script.Bindings;
/*     */ 
/*     */ public class VariableMap extends AbstractMap<String, Object> implements Bindings {
/*  22 */   private final Map<String, Variable> rawMap = new LinkedHashMap<String, Variable>();
/*     */   
/*     */   public Map<String, Variable> getRawMap() {
/*  25 */     return this.rawMap;
/*     */   }
/*     */   
/*     */   public void init() {
/*  29 */     for (Variable o : getRawMap().values())
/*  30 */       o.init(); 
/*     */   }
/*     */   
/*     */   public void initFrame() {
/*  35 */     for (Variable o : getRawMap().values())
/*  36 */       o.initFrame(); 
/*     */   }
/*     */   
/*  40 */   private final Set<Map.Entry<String, Object>> entrySet = new AbstractSet<Map.Entry<String, Object>>() {
/*     */       public Iterator<Map.Entry<String, Object>> iterator() {
/*  45 */         return new Iterator<Map.Entry<String, Object>>() {
/*  47 */             private Iterator<Map.Entry<String, Variable>> rawIterator = VariableMap.this.getRawMap().entrySet()
/*  48 */               .iterator();
/*     */             
/*     */             public boolean hasNext() {
/*  52 */               return this.rawIterator.hasNext();
/*     */             }
/*     */             
/*     */             public Map.Entry<String, Object> next() {
/*  57 */               final Map.Entry<String, Variable> rawKeyValue = this.rawIterator.next();
/*  58 */               final Object value = rawKeyValue.getValue();
/*  60 */               return new Map.Entry<String, Object>() {
/*     */                   public String getKey() {
/*  64 */                     return (String)rawKeyValue.getKey();
/*     */                   }
/*     */                   
/*     */                   public Object getValue() {
/*     */                     try {
/*  70 */                       return ((Variable)value).get(VariableMap.this);
/*  71 */                     } catch (VariableException e) {
/*  72 */                       throw new RuntimeException(e);
/*     */                     } 
/*     */                   }
/*     */                   
/*     */                   public Object setValue(Object value) {
/*  78 */                     throw new UnsupportedOperationException(Main.getInstance().getLanguageBundle().getString("SetValueNotSupportedErrorMessage"));
/*     */                   }
/*     */                 };
/*     */             }
/*     */             
/*     */             public void remove() {
/*  86 */               this.rawIterator.remove();
/*     */             }
/*     */           };
/*     */       }
/*     */       
/*     */       public int size() {
/*  94 */         return VariableMap.this.getRawMap().size();
/*     */       }
/*     */     };
/*     */   
/*     */   public Set<Map.Entry<String, Object>> entrySet() {
/* 101 */     return this.entrySet;
/*     */   }
/*     */   
/*     */   public Object put(String key, Object value) {
/*     */     Object result;
/* 108 */     if (value instanceof Variable) {
/* 109 */       result = getRawMap().put(key, (Variable)value);
/*     */     } else {
/* 111 */       result = getRawMap().put(key, new Constant(value));
/*     */     } 
/* 114 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/script/VariableMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */