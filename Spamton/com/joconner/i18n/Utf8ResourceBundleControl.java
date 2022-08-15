/*    */ package com.joconner.i18n;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.Reader;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.util.Locale;
/*    */ import java.util.PropertyResourceBundle;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class Utf8ResourceBundleControl extends PackageableResourceControl {
/*    */   public Utf8ResourceBundleControl() {}
/*    */   
/*    */   public Utf8ResourceBundleControl(boolean isPackageBased) {
/* 22 */     super(isPackageBased);
/*    */   }
/*    */   
/*    */   public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
/* 28 */     String bundleName = toBundleName(baseName, locale);
/* 29 */     ResourceBundle bundle = null;
/* 30 */     if (format.equals("java.class")) {
/* 31 */       bundle = super.newBundle(baseName, locale, format, loader, reload);
/* 32 */     } else if (format.equals("java.properties")) {
/* 34 */       String resourceName = bundleName.contains("://") ? null : toResourceName(bundleName, "properties");
/* 35 */       if (resourceName == null)
/* 36 */         return bundle; 
/* 38 */       ClassLoader classLoader = loader;
/* 39 */       InputStream stream = null;
/* 40 */       if (reload) {
/* 41 */         stream = reload(resourceName, classLoader);
/*    */       } else {
/* 43 */         stream = classLoader.getResourceAsStream(resourceName);
/*    */       } 
/* 45 */       if (stream != null) {
/* 46 */         Reader reader = new InputStreamReader(stream, "UTF-8");
/*    */         try {
/* 48 */           bundle = new PropertyResourceBundle(reader);
/*    */         } finally {
/* 50 */           reader.close();
/*    */         } 
/*    */       } 
/*    */     } else {
/* 54 */       throw new IllegalArgumentException("Unknown format: " + format);
/*    */     } 
/* 56 */     return bundle;
/*    */   }
/*    */   
/*    */   InputStream reload(String resourceName, ClassLoader classLoader) throws IOException {
/* 60 */     InputStream stream = null;
/* 61 */     URL url = classLoader.getResource(resourceName);
/* 62 */     if (url != null) {
/* 63 */       URLConnection connection = url.openConnection();
/* 64 */       if (connection != null) {
/* 67 */         connection.setUseCaches(false);
/* 68 */         stream = connection.getInputStream();
/*    */       } 
/*    */     } 
/* 71 */     return stream;
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/joconner/i18n/Utf8ResourceBundleControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */