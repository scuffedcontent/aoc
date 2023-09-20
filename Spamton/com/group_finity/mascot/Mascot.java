/*     */ package com.group_finity.mascot;
/*     */ 
/*     */ import com.group_finity.mascot.behavior.Behavior;
/*     */ import com.group_finity.mascot.config.Configuration;
/*     */ import com.group_finity.mascot.environment.MascotEnvironment;
/*     */ import com.group_finity.mascot.exception.CantBeAliveException;
/*     */ import com.group_finity.mascot.image.MascotImage;
/*     */ import com.group_finity.mascot.image.TranslucentWindow;
/*     */ import com.group_finity.mascot.menu.JLongMenu;
/*     */ import com.group_finity.mascot.sound.Sounds;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.PopupMenuEvent;
/*     */ import javax.swing.event.PopupMenuListener;
/*     */ 
/*     */ public class Mascot {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*  47 */   private static final Logger log = Logger.getLogger(Mascot.class.getName());
/*     */   
/*  49 */   private static AtomicInteger lastId = new AtomicInteger();
/*     */   
/*     */   private final int id;
/*     */   
/*  53 */   private String imageSet = "";
/*     */   
/*  58 */   private final TranslucentWindow window = NativeFactory.getInstance().newTransparentWindow();
/*     */   
/*  63 */   private Manager manager = null;
/*     */   
/*  69 */   private Point anchor = new Point(0, 0);
/*     */   
/*  74 */   private MascotImage image = null;
/*     */   
/*     */   private boolean lookRight = false;
/*     */   
/*  85 */   private Behavior behavior = null;
/*     */   
/*  90 */   private int time = 0;
/*     */   
/*     */   private boolean animating = true;
/*     */   
/*  97 */   private MascotEnvironment environment = new MascotEnvironment(this);
/*     */   
/*  99 */   private String sound = null;
/*     */   
/* 101 */   protected DebugWindow debugWindow = null;
/*     */   
/* 103 */   private ArrayList<String> affordances = new ArrayList<String>(5);
/*     */   
/*     */   public Mascot(String imageSet) {
/* 106 */     this.id = lastId.incrementAndGet();
/* 107 */     this.imageSet = imageSet;
/* 109 */     log.log(Level.INFO, "Created a mascot ({0})", this);
/* 112 */     getWindow().asJWindow().setAlwaysOnTop(true);
/* 115 */     getWindow().asJWindow().addMouseListener(new MouseAdapter() {
/*     */           public void mousePressed(MouseEvent e) {
/* 118 */             Mascot.this.mousePressed(e);
/*     */           }
/*     */           
/*     */           public void mouseReleased(MouseEvent e) {
/* 123 */             Mascot.this.mouseReleased(e);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public String toString() {
/* 131 */     return "mascot" + this.id;
/*     */   }
/*     */   
/*     */   private void mousePressed(MouseEvent event) {
/* 137 */     if (getBehavior() != null)
/*     */       try {
/* 139 */         getBehavior().mousePressed(event);
/* 140 */       } catch (CantBeAliveException e) {
/* 141 */         log.log(Level.SEVERE, "Fatal Error", (Throwable)e);
/* 142 */         Main.showError(Main.getInstance().getLanguageBundle().getString("SevereShimejiErrorErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 143 */         dispose();
/*     */       }  
/*     */   }
/*     */   
/*     */   private void mouseReleased(final MouseEvent event) {
/* 151 */     if (event.isPopupTrigger()) {
/* 152 */       SwingUtilities.invokeLater(new Runnable() {
/*     */             public void run() {
/* 155 */               Mascot.this.showPopup(event.getX(), event.getY());
/*     */             }
/*     */           });
/* 159 */     } else if (getBehavior() != null) {
/*     */       try {
/* 161 */         getBehavior().mouseReleased(event);
/* 162 */       } catch (CantBeAliveException e) {
/* 163 */         log.log(Level.SEVERE, "Fatal Error", (Throwable)e);
/* 164 */         Main.showError(Main.getInstance().getLanguageBundle().getString("SevereShimejiErrorErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 165 */         dispose();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void showPopup(int x, int y) {
/* 173 */     JPopupMenu popup = new JPopupMenu();
/* 175 */     popup.addPopupMenuListener(new PopupMenuListener() {
/*     */           public void popupMenuCanceled(PopupMenuEvent e) {}
/*     */           
/*     */           public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
/* 182 */             Mascot.this.setAnimating(true);
/*     */           }
/*     */           
/*     */           public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
/* 187 */             Mascot.this.setAnimating(false);
/*     */           }
/*     */         });
/* 192 */     JMenuItem increaseMenu = new JMenuItem(Main.getInstance().getLanguageBundle().getString("CallAnother"));
/* 193 */     increaseMenu.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 195 */             Main.getInstance().createMascot(Mascot.this.imageSet);
/*     */           }
/*     */         });
/* 200 */     JMenuItem disposeMenu = new JMenuItem(Main.getInstance().getLanguageBundle().getString("Dismiss"));
/* 201 */     disposeMenu.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 204 */             Mascot.this.dispose();
/*     */           }
/*     */         });
/* 209 */     JMenuItem gatherMenu = new JMenuItem(Main.getInstance().getLanguageBundle().getString("FollowCursor"));
/* 210 */     gatherMenu.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 212 */             Mascot.this.getManager().setBehaviorAll(Main.getInstance().getConfiguration(Mascot.this.imageSet), "ChaseMouse", Mascot.this.imageSet);
/*     */           }
/*     */         });
/* 217 */     JMenuItem oneMenu = new JMenuItem(Main.getInstance().getLanguageBundle().getString("DismissOthers"));
/* 218 */     oneMenu.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 220 */             Mascot.this.getManager().remainOne(Mascot.this.imageSet);
/*     */           }
/*     */         });
/* 225 */     JMenuItem restoreMenu = new JMenuItem(Main.getInstance().getLanguageBundle().getString("RestoreWindows"));
/* 226 */     restoreMenu.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 228 */             NativeFactory.getInstance().getEnvironment().restoreIE();
/*     */           }
/*     */         });
/* 233 */     JMenuItem debugMenu = new JMenuItem(Main.getInstance().getLanguageBundle().getString("RevealStatistics"));
/* 234 */     debugMenu.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 236 */             if (Mascot.this.debugWindow == null)
/* 238 */               Mascot.this.debugWindow = new DebugWindow(); 
/* 240 */             Mascot.this.debugWindow.setVisible(true);
/*     */           }
/*     */         });
/* 245 */     JMenuItem closeMenu = new JMenuItem(Main.getInstance().getLanguageBundle().getString("DismissAll"));
/* 246 */     closeMenu.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 248 */             Main.getInstance().exit();
/*     */           }
/*     */         });
/* 253 */     JLongMenu submenu = new JLongMenu(Main.getInstance().getLanguageBundle().getString("SetBehaviour"), 30);
/* 256 */     submenu.setAutoscrolls(true);
/* 258 */     Configuration config = Main.getInstance().getConfiguration(getImageSet());
/* 259 */     Behavior behaviour = null;
/* 260 */     for (String behaviorName : config.getBehaviorNames()) {
/* 262 */       final String command = behaviorName;
/*     */       try {
/* 265 */         behaviour = Main.getInstance().getConfiguration(getImageSet()).buildBehavior(command);
/* 266 */         if (!behaviour.isHidden()) {
/* 270 */           JMenuItem item = new JMenuItem(Main.getInstance().getLanguageBundle().containsKey(behaviorName) ? Main.getInstance().getLanguageBundle().getString(behaviorName) : behaviorName.replaceAll("([a-z])(IE)?([A-Z])", "$1 $2 $3").replaceAll("  ", " "));
/* 271 */           item.addActionListener(new ActionListener() {
/*     */                 public void actionPerformed(ActionEvent e) {
/*     */                   try {
/* 274 */                     Mascot.this.setBehavior(Main.getInstance().getConfiguration(Mascot.this.getImageSet()).buildBehavior(command));
/* 275 */                   } catch (Exception err) {
/* 276 */                     Mascot.log.log(Level.SEVERE, "Error ({0})", this);
/* 277 */                     Main.showError(Main.getInstance().getLanguageBundle().getString("CouldNotSetBehaviourErrorMessage") + "\n" + err.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/*     */                   } 
/*     */                 }
/*     */               });
/* 281 */           submenu.add(item);
/*     */         } 
/* 284 */       } catch (Exception exception) {}
/*     */     } 
/* 290 */     popup.add(increaseMenu);
/* 291 */     popup.add(new JSeparator());
/* 292 */     popup.add(gatherMenu);
/* 293 */     popup.add(restoreMenu);
/* 294 */     popup.add(debugMenu);
/* 295 */     popup.add(new JSeparator());
/* 296 */     popup.add((JMenuItem)submenu);
/* 297 */     popup.add(new JSeparator());
/* 298 */     popup.add(disposeMenu);
/* 299 */     popup.add(oneMenu);
/* 300 */     popup.add(closeMenu);
/* 302 */     getWindow().asJWindow().requestFocus();
/* 306 */     popup.setLightWeightPopupEnabled(false);
/* 307 */     popup.show(getWindow().asJWindow(), x, y);
/*     */   }
/*     */   
/*     */   void tick() {
/* 311 */     if (isAnimating()) {
/* 312 */       if (getBehavior() != null) {
/*     */         try {
/* 315 */           getBehavior().next();
/* 316 */         } catch (CantBeAliveException e) {
/* 317 */           log.log(Level.SEVERE, "Fatal Error.", (Throwable)e);
/* 318 */           Main.showError(Main.getInstance().getLanguageBundle().getString("CouldNotGetNextBehaviourErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
/* 319 */           dispose();
/*     */         } 
/* 322 */         setTime(getTime() + 1);
/*     */       } 
/* 324 */       if (this.debugWindow != null) {
/* 326 */         this.debugWindow.setBehaviour(this.behavior.toString().substring(9, this.behavior.toString().length() - 1).replaceAll("([a-z])(IE)?([A-Z])", "$1 $2 $3").replaceAll("  ", " "));
/* 327 */         this.debugWindow.setShimejiX(this.anchor.x);
/* 328 */         this.debugWindow.setShimejiY(this.anchor.y);
/* 329 */         this.debugWindow.setWindowX(this.environment.getActiveIE().getLeft());
/* 330 */         this.debugWindow.setWindowY(this.environment.getActiveIE().getTop());
/* 331 */         this.debugWindow.setWindowWidth(this.environment.getActiveIE().getWidth());
/* 332 */         this.debugWindow.setWindowHeight(this.environment.getActiveIE().getHeight());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void apply() {
/* 339 */     if (isAnimating()) {
/* 342 */       if (getImage() != null) {
/* 345 */         getWindow().asJWindow().setBounds(getBounds());
/* 348 */         getWindow().setImage(getImage().getImage());
/* 351 */         if (!getWindow().asJWindow().isVisible())
/* 353 */           getWindow().asJWindow().setVisible(true); 
/* 357 */         getWindow().updateImage();
/* 361 */       } else if (getWindow().asJWindow().isVisible()) {
/* 363 */         getWindow().asJWindow().setVisible(false);
/*     */       } 
/* 368 */       if (this.sound != null && !Sounds.getSound(this.sound).isRunning() && !Sounds.isMuted()) {
/* 370 */         Sounds.getSound(this.sound).setMicrosecondPosition(0L);
/* 371 */         Sounds.getSound(this.sound).start();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 377 */     log.log(Level.INFO, "destroy mascot ({0})", this);
/* 379 */     if (this.debugWindow != null) {
/* 381 */       this.debugWindow.setVisible(false);
/* 382 */       this.debugWindow = null;
/*     */     } 
/* 385 */     this.animating = false;
/* 386 */     getWindow().asJWindow().dispose();
/* 387 */     if (getManager() != null)
/* 388 */       getManager().remove(this); 
/*     */   }
/*     */   
/*     */   public Manager getManager() {
/* 393 */     return this.manager;
/*     */   }
/*     */   
/*     */   public void setManager(Manager manager) {
/* 397 */     this.manager = manager;
/*     */   }
/*     */   
/*     */   public Point getAnchor() {
/* 401 */     return this.anchor;
/*     */   }
/*     */   
/*     */   public void setAnchor(Point anchor) {
/* 405 */     this.anchor = anchor;
/*     */   }
/*     */   
/*     */   public MascotImage getImage() {
/* 409 */     return this.image;
/*     */   }
/*     */   
/*     */   public void setImage(MascotImage image) {
/* 413 */     this.image = image;
/*     */   }
/*     */   
/*     */   public boolean isLookRight() {
/* 417 */     return this.lookRight;
/*     */   }
/*     */   
/*     */   public void setLookRight(boolean lookRight) {
/* 421 */     this.lookRight = lookRight;
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/* 425 */     if (getImage() != null) {
/* 428 */       int top = (getAnchor()).y - (getImage().getCenter()).y;
/* 429 */       int left = (getAnchor()).x - (getImage().getCenter()).x;
/* 431 */       int scaling = Integer.parseInt(Main.getInstance().getProperties().getProperty("Scaling", "1"));
/* 433 */       Rectangle result = new Rectangle(left, top, (getImage().getSize()).width * scaling, (getImage().getSize()).height * scaling);
/* 435 */       return result;
/*     */     } 
/* 440 */     return getWindow().asJWindow().getBounds();
/*     */   }
/*     */   
/*     */   public int getTime() {
/* 445 */     return this.time;
/*     */   }
/*     */   
/*     */   private void setTime(int time) {
/* 449 */     this.time = time;
/*     */   }
/*     */   
/*     */   public Behavior getBehavior() {
/* 453 */     return this.behavior;
/*     */   }
/*     */   
/*     */   public void setBehavior(Behavior behavior) throws CantBeAliveException {
/* 457 */     this.behavior = behavior;
/* 458 */     this.behavior.init(this);
/*     */   }
/*     */   
/*     */   public int getTotalCount() {
/* 462 */     return getManager().getCount();
/*     */   }
/*     */   
/*     */   private boolean isAnimating() {
/* 466 */     return this.animating;
/*     */   }
/*     */   
/*     */   private void setAnimating(boolean animating) {
/* 470 */     this.animating = animating;
/*     */   }
/*     */   
/*     */   private TranslucentWindow getWindow() {
/* 474 */     return this.window;
/*     */   }
/*     */   
/*     */   public MascotEnvironment getEnvironment() {
/* 478 */     return this.environment;
/*     */   }
/*     */   
/*     */   public ArrayList<String> getAffordances() {
/* 483 */     return this.affordances;
/*     */   }
/*     */   
/*     */   public void setImageSet(String set) {
/* 488 */     this.imageSet = set;
/*     */   }
/*     */   
/*     */   public String getImageSet() {
/* 492 */     return this.imageSet;
/*     */   }
/*     */   
/*     */   public String getSound() {
/* 497 */     return this.sound;
/*     */   }
/*     */   
/*     */   public void setSound(String name) {
/* 502 */     this.sound = name;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/Mascot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */