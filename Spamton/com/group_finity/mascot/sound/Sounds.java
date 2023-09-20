/*    */ package com.group_finity.mascot.sound;
/*    */ 
/*    */ import com.group_finity.mascot.Main;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Hashtable;
/*    */ import javax.sound.sampled.Clip;
/*    */ 
/*    */ public class Sounds {
/* 16 */   private static final Hashtable<String, Clip> SOUNDS = new Hashtable<String, Clip>();
/*    */   
/*    */   public static void load(String filename, Clip clip) {
/* 20 */     if (!SOUNDS.containsKey(filename))
/* 21 */       SOUNDS.put(filename, clip); 
/*    */   }
/*    */   
/*    */   public static boolean contains(String filename) {
/* 26 */     return SOUNDS.containsKey(filename);
/*    */   }
/*    */   
/*    */   public static Clip getSound(String filename) {
/* 31 */     if (!SOUNDS.containsKey(filename))
/* 32 */       return null; 
/* 33 */     return SOUNDS.get(filename);
/*    */   }
/*    */   
/*    */   public static boolean isMuted() {
/* 38 */     return !Boolean.parseBoolean(Main.getInstance().getProperties().getProperty("Sounds", "true"));
/*    */   }
/*    */   
/*    */   public static void setMuted(boolean mutedFlag) {
/* 43 */     if (mutedFlag) {
/* 46 */       Enumeration<String> keys = SOUNDS.keys();
/* 47 */       while (keys.hasMoreElements())
/* 49 */         ((Clip)SOUNDS.get(keys.nextElement())).stop(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/sound/Sounds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */