/*    */ package com.joconner.i18n;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class PackageableResourceControl extends ResourceBundle.Control {
/*    */   boolean isPackageBased;
/*    */   
/*    */   public PackageableResourceControl() {
/* 18 */     this(true);
/*    */   }
/*    */   
/*    */   public PackageableResourceControl(boolean isPackageBased) {
/* 22 */     this.isPackageBased = isPackageBased;
/*    */   }
/*    */   
/*    */   public String toBundleName(String baseName, Locale locale) {
/* 38 */     String bundleName = null;
/* 39 */     if (this.isPackageBased) {
/* 40 */       int nBasePackage = baseName.lastIndexOf(".");
/* 41 */       String basePackageName = (nBasePackage > 0) ? baseName.substring(0, nBasePackage) : "";
/* 42 */       String resName = (nBasePackage > 0) ? baseName.substring(nBasePackage + 1) : baseName;
/* 43 */       String langSubPackage = locale.equals(Locale.ROOT) ? "" : locale.toLanguageTag().toLowerCase();
/* 44 */       StringBuilder strBuilder = new StringBuilder();
/* 45 */       if (nBasePackage > 0)
/* 46 */         strBuilder.append(basePackageName).append("."); 
/* 48 */       if (langSubPackage.length() > 0)
/* 49 */         strBuilder.append(langSubPackage).append("."); 
/* 51 */       strBuilder.append(resName);
/* 52 */       bundleName = strBuilder.toString();
/*    */     } else {
/* 54 */       bundleName = super.toBundleName(baseName, locale);
/*    */     } 
/* 56 */     return bundleName;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/joconner/i18n/PackageableResourceControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */