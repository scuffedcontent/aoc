/*     */ package com.group_finity.mascot.config;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class Entry {
/*     */   private Element element;
/*     */   
/*     */   private Map<String, String> attributes;
/*     */   
/*     */   private List<Entry> children;
/*     */   
/*  28 */   private Map<String, List<Entry>> selected = new HashMap<String, List<Entry>>();
/*     */   
/*     */   public Entry(Element element) {
/*  31 */     this.element = element;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  35 */     return this.element.getTagName();
/*     */   }
/*     */   
/*     */   public Map<String, String> getAttributes() {
/*  39 */     if (this.attributes != null)
/*  40 */       return this.attributes; 
/*  43 */     this.attributes = new LinkedHashMap<String, String>();
/*  44 */     NamedNodeMap attrs = this.element.getAttributes();
/*  45 */     for (int i = 0; i < attrs.getLength(); i++) {
/*  46 */       Attr attr = (Attr)attrs.item(i);
/*  47 */       this.attributes.put(attr.getName(), attr.getValue());
/*     */     } 
/*  50 */     return this.attributes;
/*     */   }
/*     */   
/*     */   public String getAttribute(String attributeName) {
/*  54 */     Attr attribute = this.element.getAttributeNode(attributeName);
/*  55 */     if (attribute == null)
/*  56 */       return null; 
/*  58 */     return attribute.getValue();
/*     */   }
/*     */   
/*     */   public boolean hasChild(String tagName) {
/*  68 */     for (Entry child : getChildren()) {
/*  70 */       if (child.getName().equals(tagName))
/*  72 */         return true; 
/*     */     } 
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public List<Entry> selectChildren(String tagName) {
/*  80 */     List<Entry> children = this.selected.get(tagName);
/*  81 */     if (children != null)
/*  82 */       return children; 
/*  84 */     children = new ArrayList<Entry>();
/*  85 */     for (Entry child : getChildren()) {
/*  86 */       if (child.getName().equals(tagName))
/*  87 */         children.add(child); 
/*     */     } 
/*  91 */     this.selected.put(tagName, children);
/*  93 */     return children;
/*     */   }
/*     */   
/*     */   public List<Entry> getChildren() {
/*  98 */     if (this.children != null)
/*  99 */       return this.children; 
/* 102 */     this.children = new ArrayList<Entry>();
/* 103 */     NodeList childNodes = this.element.getChildNodes();
/* 104 */     for (int i = 0; i < childNodes.getLength(); i++) {
/* 105 */       Node childNode = childNodes.item(i);
/* 106 */       if (childNode instanceof Element)
/* 107 */         this.children.add(new Entry((Element)childNode)); 
/*     */     } 
/* 111 */     return this.children;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/config/Entry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */