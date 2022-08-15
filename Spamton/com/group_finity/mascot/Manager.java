/*     */ package com.group_finity.mascot;
/*     */ 
/*     */ import com.group_finity.mascot.config.Configuration;
/*     */ import com.group_finity.mascot.exception.BehaviorInstantiationException;
/*     */ import com.group_finity.mascot.exception.CantBeAliveException;
/*     */ import java.awt.Point;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class Manager {
/*  25 */   private static final Logger log = Logger.getLogger(Manager.class.getName());
/*     */   
/*     */   public static final int TICK_INTERVAL = 40;
/*     */   
/*  35 */   private final List<Mascot> mascots = new ArrayList<Mascot>();
/*     */   
/*  41 */   private final Set<Mascot> added = new LinkedHashSet<Mascot>();
/*     */   
/*  47 */   private final Set<Mascot> removed = new LinkedHashSet<Mascot>();
/*     */   
/*     */   private boolean exitOnLastRemoved = true;
/*     */   
/*     */   private Thread thread;
/*     */   
/*     */   public void setExitOnLastRemoved(boolean exitOnLastRemoved) {
/*  54 */     this.exitOnLastRemoved = exitOnLastRemoved;
/*     */   }
/*     */   
/*     */   public boolean isExitOnLastRemoved() {
/*  58 */     return this.exitOnLastRemoved;
/*     */   }
/*     */   
/*     */   public Manager() {
/*  63 */     new Thread() {
/*     */         public void run() {
/*     */           while (true) {
/*     */             try {
/*     */               while (true)
/*  73 */                 Thread.sleep(2147483647L); 
/*     */               break;
/*  74 */             } catch (InterruptedException interruptedException) {}
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public void start() {
/*  82 */     if (this.thread != null && this.thread.isAlive())
/*     */       return; 
/*  86 */     this.thread = new Thread() {
/*     */         public void run() {
/*  90 */           long prev = System.nanoTime() / 1000000L;
/*     */           try {
/*     */             while (true) {
/*  94 */               long cur = System.nanoTime() / 1000000L;
/*  95 */               if (cur - prev >= 40L) {
/*  96 */                 if (cur > prev + 80L) {
/*  97 */                   prev = cur;
/*     */                 } else {
/*  99 */                   prev += 40L;
/*     */                 } 
/*     */               } else {
/* 103 */                 Thread.sleep(1L, 0);
/*     */                 continue;
/*     */               } 
/* 106 */               Manager.this.tick();
/*     */             } 
/* 108 */           } catch (InterruptedException interruptedException) {
/*     */             return;
/*     */           } 
/*     */         }
/*     */       };
/* 112 */     this.thread.setDaemon(false);
/* 114 */     this.thread.start();
/*     */   }
/*     */   
/*     */   public void stop() {
/* 118 */     if (this.thread == null || !this.thread.isAlive())
/*     */       return; 
/* 121 */     this.thread.interrupt();
/*     */     try {
/* 123 */       this.thread.join();
/* 124 */     } catch (InterruptedException interruptedException) {}
/*     */   }
/*     */   
/*     */   private void tick() {
/* 131 */     NativeFactory.getInstance().getEnvironment().tick();
/* 133 */     synchronized (getMascots()) {
/* 136 */       for (Mascot mascot : getAdded())
/* 137 */         getMascots().add(mascot); 
/* 139 */       getAdded().clear();
/* 142 */       for (Mascot mascot : getRemoved())
/* 143 */         getMascots().remove(mascot); 
/* 145 */       getRemoved().clear();
/* 148 */       for (Mascot mascot : getMascots())
/* 149 */         mascot.tick(); 
/* 153 */       for (Mascot mascot : getMascots())
/* 154 */         mascot.apply(); 
/*     */     } 
/* 158 */     if (isExitOnLastRemoved() && 
/* 159 */       getMascots().size() == 0)
/* 160 */       Main.getInstance().exit(); 
/*     */   }
/*     */   
/*     */   public void add(Mascot mascot) {
/* 166 */     synchronized (getAdded()) {
/* 167 */       getAdded().add(mascot);
/* 168 */       getRemoved().remove(mascot);
/*     */     } 
/* 170 */     mascot.setManager(this);
/*     */   }
/*     */   
/*     */   public void remove(Mascot mascot) {
/* 174 */     synchronized (getAdded()) {
/* 175 */       getAdded().remove(mascot);
/* 176 */       getRemoved().add(mascot);
/*     */     } 
/* 178 */     mascot.setManager(null);
/*     */   }
/*     */   
/*     */   public void setBehaviorAll(String name) {
/* 182 */     synchronized (getMascots()) {
/* 183 */       for (Mascot mascot : getMascots()) {
/*     */         try {
/* 185 */           Configuration configuration = Main.getInstance().getConfiguration(mascot.getImageSet());
/* 186 */           mascot.setBehavior(configuration.buildBehavior(configuration.getSchema().getString(name)));
/* 187 */         } catch (BehaviorInstantiationException e) {
/* 188 */           log.log(Level.SEVERE, "Failed to initialize the following actions", (Throwable)e);
/* 189 */           Main.showError(Main.getInstance().getLanguageBundle().getString("FailedSetBehaviourErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 190 */           mascot.dispose();
/* 191 */         } catch (CantBeAliveException e) {
/* 192 */           log.log(Level.SEVERE, "Fatal Error", (Throwable)e);
/* 193 */           Main.showError(Main.getInstance().getLanguageBundle().getString("FailedSetBehaviourErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 194 */           mascot.dispose();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBehaviorAll(Configuration configuration, String name, String imageSet) {
/* 201 */     synchronized (getMascots()) {
/* 202 */       for (Mascot mascot : getMascots()) {
/*     */         try {
/* 204 */           if (mascot.getImageSet().equals(imageSet))
/* 205 */             mascot.setBehavior(configuration.buildBehavior(configuration.getSchema().getString(name))); 
/* 207 */         } catch (BehaviorInstantiationException e) {
/* 208 */           log.log(Level.SEVERE, "Failed to initialize the following actions", (Throwable)e);
/* 209 */           Main.showError(Main.getInstance().getLanguageBundle().getString("FailedSetBehaviourErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 210 */           mascot.dispose();
/* 211 */         } catch (CantBeAliveException e) {
/* 212 */           log.log(Level.SEVERE, "Fatal Error", (Throwable)e);
/* 213 */           Main.showError(Main.getInstance().getLanguageBundle().getString("FailedSetBehaviourErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 214 */           mascot.dispose();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remainOne() {
/* 221 */     synchronized (getMascots()) {
/* 222 */       int totalMascots = getMascots().size();
/* 223 */       for (int i = totalMascots - 1; i > 0; i--)
/* 224 */         ((Mascot)getMascots().get(i)).dispose(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remainOne(String imageSet) {
/* 230 */     synchronized (getMascots()) {
/* 231 */       int totalMascots = getMascots().size();
/* 232 */       boolean isFirst = true;
/* 233 */       for (int i = totalMascots - 1; i >= 0; i--) {
/* 234 */         Mascot m = getMascots().get(i);
/* 235 */         if (m.getImageSet().equals(imageSet) && isFirst) {
/* 236 */           isFirst = false;
/* 237 */         } else if (m.getImageSet().equals(imageSet) && !isFirst) {
/* 238 */           m.dispose();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 245 */     synchronized (getMascots()) {
/* 246 */       return getMascots().size();
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<Mascot> getMascots() {
/* 251 */     return this.mascots;
/*     */   }
/*     */   
/*     */   private Set<Mascot> getAdded() {
/* 255 */     return this.added;
/*     */   }
/*     */   
/*     */   private Set<Mascot> getRemoved() {
/* 259 */     return this.removed;
/*     */   }
/*     */   
/*     */   public WeakReference<Mascot> getMascotWithAffordance(String affordance) {
/* 269 */     synchronized (getMascots()) {
/* 271 */       for (Mascot mascot : getMascots()) {
/* 273 */         if (mascot.getAffordances().contains(affordance))
/* 274 */           return new WeakReference<Mascot>(mascot); 
/*     */       } 
/*     */     } 
/* 278 */     return null;
/*     */   }
/*     */   
/*     */   public boolean hasOverlappingMascotsAtPoint(Point anchor) {
/* 283 */     int count = 0;
/* 285 */     synchronized (getMascots()) {
/* 287 */       for (Mascot mascot : getMascots()) {
/* 289 */         if (mascot.getAnchor().equals(anchor))
/* 290 */           count++; 
/* 291 */         if (count > 1)
/* 292 */           return true; 
/*     */       } 
/*     */     } 
/* 296 */     return false;
/*     */   }
/*     */   
/*     */   public void disposeAll() {
/* 300 */     synchronized (getMascots()) {
/* 301 */       for (int i = getMascots().size() - 1; i >= 0; i--)
/* 302 */         ((Mascot)getMascots().get(i)).dispose(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/Manager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */