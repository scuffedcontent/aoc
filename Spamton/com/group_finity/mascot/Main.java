/*      */ package com.group_finity.mascot;
/*      */ 
/*      */ import com.group_finity.mascot.config.Configuration;
/*      */ import com.group_finity.mascot.config.Entry;
/*      */ import com.group_finity.mascot.exception.BehaviorInstantiationException;
/*      */ import com.group_finity.mascot.exception.CantBeAliveException;
/*      */ import com.group_finity.mascot.exception.ConfigurationException;
/*      */ import com.group_finity.mascot.image.ImagePairs;
/*      */ import com.group_finity.mascot.imagesetchooser.ImageSetChooser;
/*      */ import com.group_finity.mascot.sound.Sounds;
/*      */ import com.group_finity.mascot.win.WindowsInteractiveWindowForm;
/*      */ import com.joconner.i18n.Utf8ResourceBundleControl;
/*      */ import com.nilo.plaf.nimrod.NimRODLookAndFeel;
/*      */ import com.nilo.plaf.nimrod.NimRODTheme;
/*      */ import java.awt.AWTException;
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.GridBagConstraints;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.SystemTray;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.TrayIcon;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Locale;
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogManager;
/*      */ import java.util.logging.Logger;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBoxMenuItem;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.JSeparator;
/*      */ import javax.swing.LookAndFeel;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ import javax.swing.event.PopupMenuEvent;
/*      */ import javax.swing.event.PopupMenuListener;
/*      */ import javax.swing.plaf.metal.MetalTheme;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import org.w3c.dom.Document;
/*      */ import org.xml.sax.SAXException;
/*      */ 
/*      */ public class Main {
/*   78 */   private static final Logger log = Logger.getLogger(Main.class.getName());
/*      */   
/*      */   static final String BEHAVIOR_GATHER = "ChaseMouse";
/*      */   
/*      */   static {
/*      */     try {
/*   86 */       LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
/*   88 */     } catch (SecurityException e) {
/*   90 */       e.printStackTrace();
/*   92 */     } catch (IOException e) {
/*   94 */       e.printStackTrace();
/*   96 */     } catch (OutOfMemoryError err) {
/*   98 */       log.log(Level.SEVERE, "Out of Memory Exception.  There are probably have too many Shimeji mascots in the image folder for your computer to handle.  Select fewer image sets or move some to the img/unused folder and try again.", err);
/*  101 */       showError("Out of Memory.  There are probably have too many \nShimeji mascots for your computer to handle.\nSelect fewer image sets or move some to the \nimg/unused folder and try again.");
/*  105 */       System.exit(0);
/*      */     } 
/*      */   }
/*      */   
/*  108 */   private final Manager manager = new Manager();
/*      */   
/*  109 */   private ArrayList<String> imageSets = new ArrayList<String>();
/*      */   
/*  110 */   private Hashtable<String, Configuration> configurations = new Hashtable<String, Configuration>();
/*      */   
/*  111 */   private static Main instance = new Main();
/*      */   
/*  112 */   private Properties properties = new Properties();
/*      */   
/*      */   private Platform platform;
/*      */   
/*      */   private ResourceBundle languageBundle;
/*      */   
/*      */   private JDialog form;
/*      */   
/*      */   public static Main getInstance() {
/*  120 */     return instance;
/*      */   }
/*      */   
/*  122 */   private static JFrame frame = new JFrame();
/*      */   
/*      */   public static void showError(String message) {
/*  126 */     JOptionPane.showMessageDialog(frame, message, "Error", 0);
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/*      */     try {
/*  133 */       getInstance().run();
/*  135 */     } catch (OutOfMemoryError err) {
/*  137 */       log.log(Level.SEVERE, "Out of Memory Exception.  There are probably have too many Shimeji mascots in the image folder for your computer to handle.  Select fewer image sets or move some to the img/unused folder and try again.", err);
/*  140 */       showError("Out of Memory.  There are probably have too many \nShimeji mascots for your computer to handle.\nSelect fewer image sets or move some to the \nimg/unused folder and try again.");
/*  144 */       System.exit(0);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void run() {
/*  151 */     if (!System.getProperty("sun.arch.data.model").equals("64")) {
/*  152 */       this.platform = Platform.x86;
/*      */     } else {
/*  154 */       this.platform = Platform.x86_64;
/*      */     } 
/*  157 */     this.properties = new Properties();
/*      */     try {
/*  161 */       FileInputStream input = new FileInputStream("./conf/settings.properties");
/*  162 */       this.properties.load(input);
/*  164 */     } catch (FileNotFoundException fileNotFoundException) {
/*      */     
/*  167 */     } catch (IOException iOException) {}
/*      */     try {
/*  174 */       Utf8ResourceBundleControl utf8ResourceBundleControl = new Utf8ResourceBundleControl(false);
/*  175 */       this.languageBundle = ResourceBundle.getBundle("language", Locale.forLanguageTag(this.properties.getProperty("Language", "en-GB")), (ResourceBundle.Control)utf8ResourceBundleControl);
/*  177 */     } catch (Exception ex) {
/*  179 */       showError("The default language file could not be loaded. Ensure that you have the latest shimeji language.properties in your conf directory.");
/*  180 */       exit();
/*      */     } 
/*      */     try {
/*  187 */       NimRODLookAndFeel lookAndFeel = new NimRODLookAndFeel();
/*  190 */       NimRODTheme theme = null;
/*      */       try {
/*  193 */         if ((new File("./conf/theme.properties")).isFile())
/*  195 */           theme = new NimRODTheme("./conf/theme.properties"); 
/*  198 */       } catch (Exception exc) {
/*  200 */         theme = null;
/*      */       } 
/*  203 */       if (theme == null) {
/*  206 */         theme = new NimRODTheme();
/*  207 */         theme.setPrimary1(Color.decode("#1EA6EB"));
/*  208 */         theme.setPrimary2(Color.decode("#28B0F5"));
/*  209 */         theme.setPrimary3(Color.decode("#32BAFF"));
/*  210 */         theme.setSecondary1(Color.decode("#BCBCBE"));
/*  211 */         theme.setSecondary2(Color.decode("#C6C6C8"));
/*  212 */         theme.setSecondary3(Color.decode("#D0D0D2"));
/*  213 */         theme.setMenuOpacity(255);
/*  214 */         theme.setFrameOpacity(255);
/*      */       } 
/*  218 */       if (!this.properties.containsKey("MenuDPI")) {
/*  220 */         int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
/*  221 */         if (dpi < 96)
/*  222 */           dpi = 96; 
/*  223 */         this.properties.setProperty("MenuDPI", dpi + "");
/*      */         try {
/*  226 */           FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */           try {
/*  229 */             this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */           } finally {
/*  233 */             output.close();
/*      */           } 
/*  236 */         } catch (Exception exception) {}
/*      */       } 
/*  240 */       float menuScaling = Float.parseFloat(this.properties.getProperty("MenuDPI", "96")) / 96.0F;
/*  241 */       Font font = theme.getUserTextFont().deriveFont(theme.getUserTextFont().getSize() * menuScaling);
/*  242 */       theme.setFont(font);
/*  244 */       NimRODLookAndFeel.setCurrentTheme((MetalTheme)theme);
/*  245 */       JFrame.setDefaultLookAndFeelDecorated(true);
/*  246 */       JDialog.setDefaultLookAndFeelDecorated(true);
/*  248 */       lookAndFeel.initialize();
/*  249 */       UIManager.setLookAndFeel((LookAndFeel)lookAndFeel);
/*  251 */     } catch (Exception ex) {
/*      */       try {
/*  255 */         UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
/*  257 */       } catch (Exception ex1) {
/*  259 */         log.log(Level.SEVERE, "Look & Feel unsupported.", ex1);
/*  260 */         exit();
/*      */       } 
/*      */     } 
/*  265 */     this.imageSets.addAll(Arrays.asList(this.properties.getProperty("ActiveShimeji", "").split("/")));
/*  266 */     if (((String)this.imageSets.get(0)).trim().isEmpty()) {
/*  268 */       this.imageSets = (new ImageSetChooser(frame, true)).display();
/*  269 */       if (this.imageSets == null)
/*  271 */         exit(); 
/*      */     } 
/*  276 */     for (int index = 0; index < this.imageSets.size(); index++) {
/*  278 */       if (!loadConfiguration(this.imageSets.get(index))) {
/*  281 */         this.configurations.remove(this.imageSets.get(index));
/*  282 */         this.imageSets.remove(this.imageSets.get(index));
/*  283 */         index--;
/*      */       } 
/*      */     } 
/*  286 */     if (this.imageSets.isEmpty())
/*  288 */       exit(); 
/*  292 */     createTrayIcon();
/*  295 */     for (String imageSet : this.imageSets)
/*  297 */       createMascot(imageSet); 
/*  300 */     getManager().start();
/*      */   }
/*      */   
/*      */   private boolean loadConfiguration(String imageSet) {
/*      */     try {
/*  307 */       String actionsFile = "./conf/actions.xml";
/*  308 */       if ((new File("./conf/動作.xml")).exists())
/*  310 */         actionsFile = "./conf/動作.xml"; 
/*  312 */       if ((new File("./conf/" + imageSet + "/actions.xml")).exists())
/*  314 */         actionsFile = "./conf/" + imageSet + "/actions.xml"; 
/*  316 */       if ((new File("./conf/" + imageSet + "/動作.xml")).exists())
/*  318 */         actionsFile = "./conf/" + imageSet + "/動作.xml"; 
/*  320 */       if ((new File("./conf/" + imageSet + "/Õïòõ¢£.xml")).exists())
/*  322 */         actionsFile = "./conf/" + imageSet + "/Õïòõ¢£.xml"; 
/*  324 */       if ((new File("./conf/" + imageSet + "/¦-º@.xml")).exists())
/*  326 */         actionsFile = "./conf/" + imageSet + "/¦-º@.xml"; 
/*  328 */       if ((new File("./conf/" + imageSet + "/ô«ìý.xml")).exists())
/*  330 */         actionsFile = "./conf/" + imageSet + "/ô«ìý.xml"; 
/*  332 */       if ((new File("./conf/" + imageSet + "/one.xml")).exists())
/*  334 */         actionsFile = "./conf/" + imageSet + "/one.xml"; 
/*  336 */       if ((new File("./conf/" + imageSet + "/1.xml")).exists())
/*  338 */         actionsFile = "./conf/" + imageSet + "/1.xml"; 
/*  340 */       if ((new File("./img/" + imageSet + "/conf/actions.xml")).exists())
/*  342 */         actionsFile = "./img/" + imageSet + "/conf/actions.xml"; 
/*  344 */       if ((new File("./img/" + imageSet + "/conf/動作.xml")).exists())
/*  346 */         actionsFile = "./img/" + imageSet + "/conf/動作.xml"; 
/*  348 */       if ((new File("./img/" + imageSet + "/conf/Õïòõ¢£.xml")).exists())
/*  350 */         actionsFile = "./img/" + imageSet + "/conf/Õïòõ¢£.xml"; 
/*  352 */       if ((new File("./img/" + imageSet + "/conf/¦-º@.xml")).exists())
/*  354 */         actionsFile = "./img/" + imageSet + "/conf/¦-º@.xml"; 
/*  356 */       if ((new File("./img/" + imageSet + "/conf/ô«ìý.xml")).exists())
/*  358 */         actionsFile = "./img/" + imageSet + "/conf/ô«ìý.xml"; 
/*  360 */       if ((new File("./img/" + imageSet + "/conf/one.xml")).exists())
/*  362 */         actionsFile = "./img/" + imageSet + "/conf/one.xml"; 
/*  364 */       if ((new File("./img/" + imageSet + "/conf/1.xml")).exists())
/*  366 */         actionsFile = "./img/" + imageSet + "/conf/1.xml"; 
/*  369 */       log.log(Level.INFO, imageSet + " Read Action File ({0})", actionsFile);
/*  371 */       Document actions = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File(actionsFile)));
/*  374 */       Configuration configuration = new Configuration();
/*  376 */       configuration.load(new Entry(actions.getDocumentElement()), imageSet);
/*  378 */       String behaviorsFile = "./conf/behaviors.xml";
/*  379 */       if ((new File("./conf/行動.xml")).exists())
/*  381 */         behaviorsFile = "./conf/" + imageSet + "/行動.xml"; 
/*  383 */       if ((new File("./conf/" + imageSet + "/behaviors.xml")).exists())
/*  385 */         behaviorsFile = "./conf/" + imageSet + "/behaviors.xml"; 
/*  387 */       if ((new File("./conf/" + imageSet + "/behavior.xml")).exists())
/*  389 */         behaviorsFile = "./conf/" + imageSet + "/behavior.xml"; 
/*  391 */       if ((new File("./conf/" + imageSet + "/行動.xml")).exists())
/*  393 */         behaviorsFile = "./conf/" + imageSet + "/行動.xml"; 
/*  395 */       if ((new File("./conf/" + imageSet + "/ÞíîÕïò.xml")).exists())
/*  397 */         behaviorsFile = "./conf/" + imageSet + "/ÞíîÕïò.xml"; 
/*  399 */       if ((new File("./conf/" + imageSet + "/ªµ¦-.xml")).exists())
/*  401 */         behaviorsFile = "./conf/" + imageSet + "/ªµ¦-.xml"; 
/*  403 */       if ((new File("./conf/" + imageSet + "/ìsô«.xml")).exists())
/*  405 */         behaviorsFile = "./conf/" + imageSet + "/ìsô«.xml"; 
/*  407 */       if ((new File("./conf/" + imageSet + "/two.xml")).exists())
/*  409 */         behaviorsFile = "./conf/" + imageSet + "/two.xml"; 
/*  411 */       if ((new File("./conf/" + imageSet + "/2.xml")).exists())
/*  413 */         behaviorsFile = "./conf/" + imageSet + "/2.xml"; 
/*  415 */       if ((new File("./img/" + imageSet + "/conf/behaviors.xml")).exists())
/*  417 */         behaviorsFile = "./img/" + imageSet + "/conf/behaviors.xml"; 
/*  419 */       if ((new File("./img/" + imageSet + "/conf/behavior.xml")).exists())
/*  421 */         behaviorsFile = "./img/" + imageSet + "/conf/behavior.xml"; 
/*  423 */       if ((new File("./img/" + imageSet + "/conf/行動.xml")).exists())
/*  425 */         behaviorsFile = "./img/" + imageSet + "/conf/行動.xml"; 
/*  427 */       if ((new File("./img/" + imageSet + "/conf/ÞíîÕïò.xml")).exists())
/*  429 */         behaviorsFile = "./img/" + imageSet + "/conf/ÞíîÕïò.xml"; 
/*  431 */       if ((new File("./img/" + imageSet + "/conf/ªµ¦-.xml")).exists())
/*  433 */         behaviorsFile = "./img/" + imageSet + "/conf/ªµ¦-.xml"; 
/*  435 */       if ((new File("./img/" + imageSet + "/conf/ìsô«.xml")).exists())
/*  437 */         behaviorsFile = "./img/" + imageSet + "/conf/ìsô«.xml"; 
/*  439 */       if ((new File("./img/" + imageSet + "/conf/two.xml")).exists())
/*  441 */         behaviorsFile = "./img/" + imageSet + "/conf/two.xml"; 
/*  443 */       if ((new File("./img/" + imageSet + "/conf/2.xml")).exists())
/*  445 */         behaviorsFile = "./img/" + imageSet + "/conf/2.xml"; 
/*  448 */       log.log(Level.INFO, imageSet + " Read Behavior File ({0})", behaviorsFile);
/*  450 */       Document behaviors = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File(behaviorsFile)));
/*  453 */       configuration.load(new Entry(behaviors.getDocumentElement()), imageSet);
/*  455 */       configuration.validate();
/*  457 */       this.configurations.put(imageSet, configuration);
/*  460 */       for (Entry list : (new Entry(actions.getDocumentElement())).selectChildren("ActionList")) {
/*  462 */         for (Entry node : list.selectChildren("Action")) {
/*  464 */           if (node.getAttributes().containsKey("BornMascot"))
/*  466 */             if (!this.configurations.containsKey(node.getAttribute("BornMascot")))
/*  468 */               loadConfiguration(node.getAttribute("BornMascot"));  
/*  471 */           if (node.getAttributes().containsKey("TransformMascot"))
/*  473 */             if (!this.configurations.containsKey(node.getAttribute("TransformMascot")))
/*  475 */               loadConfiguration(node.getAttribute("TransformMascot"));  
/*      */         } 
/*      */       } 
/*  481 */       return true;
/*  483 */     } catch (IOException e) {
/*  485 */       log.log(Level.SEVERE, "Failed to load configuration files", e);
/*  486 */       showError(this.languageBundle.getString("FailedLoadConfigErrorMessage") + "\n" + e.getMessage() + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/*  488 */     } catch (SAXException e) {
/*  490 */       log.log(Level.SEVERE, "Failed to load configuration files", e);
/*  491 */       showError(this.languageBundle.getString("FailedLoadConfigErrorMessage") + "\n" + e.getMessage() + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/*  493 */     } catch (ParserConfigurationException e) {
/*  495 */       log.log(Level.SEVERE, "Failed to load configuration files", e);
/*  496 */       showError(this.languageBundle.getString("FailedLoadConfigErrorMessage") + "\n" + e.getMessage() + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/*  498 */     } catch (ConfigurationException e) {
/*  500 */       log.log(Level.SEVERE, "Failed to load configuration files", (Throwable)e);
/*  501 */       showError(this.languageBundle.getString("FailedLoadConfigErrorMessage") + "\n" + e.getMessage() + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/*  503 */     } catch (Exception e) {
/*  505 */       log.log(Level.SEVERE, "Failed to load configuration files", e);
/*  506 */       showError(this.languageBundle.getString("FailedLoadConfigErrorMessage") + "\n" + e.getMessage() + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/*      */     } 
/*  509 */     return false;
/*      */   }
/*      */   
/*      */   private void createTrayIcon() {
/*  520 */     log.log(Level.INFO, "create a tray icon");
/*      */     try {
/*  525 */       TrayIcon icon = new TrayIcon(ImageIO.read(Main.class.getResource("/icon.png")), this.languageBundle.getString("ShimejiEE"));
/*  528 */       icon.addMouseListener(new MouseListener() {
/*      */             public void mouseClicked(MouseEvent event) {}
/*      */             
/*      */             public void mousePressed(MouseEvent e) {}
/*      */             
/*      */             public void mouseReleased(MouseEvent event) {
/*  543 */               if (event.isPopupTrigger()) {
/*  546 */                 if (Main.this.form != null)
/*  547 */                   Main.this.form.dispose(); 
/*  550 */                 Main.this.form = new JDialog(Main.frame, false);
/*  551 */                 final JPanel panel = new JPanel();
/*  552 */                 panel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  553 */                 Main.this.form.add(panel);
/*  556 */                 JButton btnCallShimeji = new JButton(Main.this.languageBundle.getString("CallShimeji"));
/*  557 */                 btnCallShimeji.addActionListener(new ActionListener() {
/*      */                       public void actionPerformed(ActionEvent event) {
/*  561 */                         Main.this.createMascot();
/*  562 */                         Main.this.form.dispose();
/*      */                       }
/*      */                     });
/*  566 */                 JButton btnFollowCursor = new JButton(Main.this.languageBundle.getString("FollowCursor"));
/*  567 */                 btnFollowCursor.addActionListener(new ActionListener() {
/*      */                       public void actionPerformed(ActionEvent event) {
/*  571 */                         Main.this.getManager().setBehaviorAll("ChaseMouse");
/*  572 */                         Main.this.form.dispose();
/*      */                       }
/*      */                     });
/*  576 */                 JButton btnReduceToOne = new JButton(Main.this.languageBundle.getString("ReduceToOne"));
/*  577 */                 btnReduceToOne.addActionListener(new ActionListener() {
/*      */                       public void actionPerformed(ActionEvent event) {
/*  581 */                         Main.this.getManager().remainOne();
/*  582 */                         Main.this.form.dispose();
/*      */                       }
/*      */                     });
/*  586 */                 JButton btnRestoreWindows = new JButton(Main.this.languageBundle.getString("RestoreWindows"));
/*  587 */                 btnRestoreWindows.addActionListener(new ActionListener() {
/*      */                       public void actionPerformed(ActionEvent event) {
/*  591 */                         NativeFactory.getInstance().getEnvironment().restoreIE();
/*  592 */                         Main.this.form.dispose();
/*      */                       }
/*      */                     });
/*  596 */                 final JButton btnAllowedBehaviours = new JButton(Main.this.languageBundle.getString("AllowedBehaviours"));
/*  597 */                 btnAllowedBehaviours.addMouseListener(new MouseListener() {
/*      */                       public void mouseClicked(MouseEvent e) {}
/*      */                       
/*      */                       public void mousePressed(MouseEvent e) {}
/*      */                       
/*      */                       public void mouseReleased(MouseEvent e) {
/*  612 */                         btnAllowedBehaviours.setEnabled(true);
/*      */                       }
/*      */                       
/*      */                       public void mouseEntered(MouseEvent e) {}
/*      */                       
/*      */                       public void mouseExited(MouseEvent e) {}
/*      */                     });
/*  625 */                 btnAllowedBehaviours.addActionListener(new ActionListener() {
/*      */                       public void actionPerformed(ActionEvent event) {
/*  631 */                         final JCheckBoxMenuItem breedingMenu = new JCheckBoxMenuItem(Main.this.languageBundle.getString("BreedingCloning"), Boolean.parseBoolean(Main.this.properties.getProperty("Breeding", "true")));
/*  632 */                         breedingMenu.addItemListener(new ItemListener() {
/*      */                               public void itemStateChanged(ItemEvent e) {
/*  636 */                                 if (Boolean.parseBoolean(Main.this.properties.getProperty("Breeding", "true"))) {
/*  638 */                                   breedingMenu.setState(false);
/*  639 */                                   Main.this.properties.setProperty("Breeding", "false");
/*      */                                 } else {
/*  643 */                                   breedingMenu.setState(true);
/*  644 */                                   Main.this.properties.setProperty("Breeding", "true");
/*      */                                 } 
/*  646 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/*  649 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/*  652 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/*  656 */                                     output.close();
/*      */                                   } 
/*  659 */                                 } catch (Exception exception) {}
/*  662 */                                 btnAllowedBehaviours.setEnabled(true);
/*      */                               }
/*      */                             });
/*  667 */                         final JCheckBoxMenuItem throwingMenu = new JCheckBoxMenuItem(Main.this.languageBundle.getString("ThrowingWindows"), Boolean.parseBoolean(Main.this.properties.getProperty("Throwing", "true")));
/*  668 */                         throwingMenu.addItemListener(new ItemListener() {
/*      */                               public void itemStateChanged(ItemEvent e) {
/*  672 */                                 if (Boolean.parseBoolean(Main.this.properties.getProperty("Throwing", "true"))) {
/*  674 */                                   throwingMenu.setState(false);
/*  675 */                                   Main.this.properties.setProperty("Throwing", "false");
/*      */                                 } else {
/*  679 */                                   throwingMenu.setState(true);
/*  680 */                                   Main.this.properties.setProperty("Throwing", "true");
/*      */                                 } 
/*  682 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/*  685 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/*  688 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/*  692 */                                     output.close();
/*      */                                   } 
/*  695 */                                 } catch (Exception exception) {}
/*  698 */                                 btnAllowedBehaviours.setEnabled(true);
/*      */                               }
/*      */                             });
/*  703 */                         final JCheckBoxMenuItem soundsMenu = new JCheckBoxMenuItem(Main.this.languageBundle.getString("SoundEffects"), Boolean.parseBoolean(Main.this.properties.getProperty("Sounds", "true")));
/*  704 */                         soundsMenu.addItemListener(new ItemListener() {
/*      */                               public void itemStateChanged(ItemEvent e) {
/*  708 */                                 if (Boolean.parseBoolean(Main.this.properties.getProperty("Sounds", "true"))) {
/*  710 */                                   soundsMenu.setState(false);
/*  711 */                                   Main.this.properties.setProperty("Sounds", "false");
/*  712 */                                   Sounds.setMuted(true);
/*      */                                 } else {
/*  716 */                                   soundsMenu.setState(true);
/*  717 */                                   Main.this.properties.setProperty("Sounds", "true");
/*  718 */                                   Sounds.setMuted(false);
/*      */                                 } 
/*  720 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/*  723 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/*  726 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/*  730 */                                     output.close();
/*      */                                   } 
/*  733 */                                 } catch (Exception exception) {}
/*  736 */                                 btnAllowedBehaviours.setEnabled(true);
/*      */                               }
/*      */                             });
/*  740 */                         JPopupMenu behaviourPopup = new JPopupMenu();
/*  741 */                         behaviourPopup.add(breedingMenu);
/*  742 */                         behaviourPopup.add(throwingMenu);
/*  743 */                         behaviourPopup.add(soundsMenu);
/*  744 */                         behaviourPopup.addPopupMenuListener(new PopupMenuListener() {
/*      */                               public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
/*      */                               
/*      */                               public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
/*  754 */                                 if (panel.getMousePosition() != null) {
/*  756 */                                   btnAllowedBehaviours.setEnabled(((panel.getMousePosition()).x <= btnAllowedBehaviours.getX() || 
/*  757 */                                       (panel.getMousePosition()).x >= btnAllowedBehaviours.getX() + btnAllowedBehaviours.getWidth() || 
/*  758 */                                       (panel.getMousePosition()).y <= btnAllowedBehaviours.getY() || 
/*  759 */                                       (panel.getMousePosition()).y >= btnAllowedBehaviours.getY() + btnAllowedBehaviours.getHeight()));
/*      */                                 } else {
/*  763 */                                   btnAllowedBehaviours.setEnabled(true);
/*      */                                 } 
/*      */                               }
/*      */                               
/*      */                               public void popupMenuCanceled(PopupMenuEvent e) {}
/*      */                             });
/*  772 */                         behaviourPopup.show(btnAllowedBehaviours, 0, btnAllowedBehaviours.getHeight());
/*  773 */                         btnAllowedBehaviours.requestFocusInWindow();
/*      */                       }
/*      */                     });
/*  777 */                 final JButton btnSettings = new JButton(Main.this.languageBundle.getString("Settings"));
/*  778 */                 btnSettings.addMouseListener(new MouseListener() {
/*      */                       public void mouseClicked(MouseEvent e) {}
/*      */                       
/*      */                       public void mousePressed(MouseEvent e) {}
/*      */                       
/*      */                       public void mouseReleased(MouseEvent e) {
/*  793 */                         btnSettings.setEnabled(true);
/*      */                       }
/*      */                       
/*      */                       public void mouseEntered(MouseEvent e) {}
/*      */                       
/*      */                       public void mouseExited(MouseEvent e) {}
/*      */                     });
/*  806 */                 btnSettings.addActionListener(new ActionListener() {
/*      */                       public void actionPerformed(ActionEvent e) {
/*  810 */                         JMenuItem chooseShimejiMenu = new JMenuItem(Main.this.languageBundle.getString("ChooseShimeji"));
/*  811 */                         chooseShimejiMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/*  815 */                                 Main.this.form.dispose();
/*  816 */                                 boolean isExit = Main.this.getManager().isExitOnLastRemoved();
/*  817 */                                 Main.this.getManager().setExitOnLastRemoved(false);
/*  818 */                                 Main.this.getManager().disposeAll();
/*  821 */                                 ArrayList<String> temporaryImageSet = new ArrayList<String>();
/*  822 */                                 temporaryImageSet = (new ImageSetChooser(Main.frame, true)).display();
/*  823 */                                 if (temporaryImageSet != null)
/*  825 */                                   Main.this.imageSets = temporaryImageSet; 
/*  829 */                                 for (String imageSet : Main.this.imageSets)
/*  831 */                                   Main.this.loadConfiguration(imageSet); 
/*  835 */                                 for (String imageSet : Main.this.imageSets)
/*  837 */                                   Main.this.createMascot(imageSet); 
/*  840 */                                 Main.this.getManager().setExitOnLastRemoved(isExit);
/*      */                               }
/*      */                             });
/*  845 */                         JMenuItem interactiveMenu = new JMenuItem(Main.this.languageBundle.getString("ChooseInteractiveWindows"));
/*  846 */                         interactiveMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/*  850 */                                 Main.this.form.dispose();
/*  851 */                                 (new WindowsInteractiveWindowForm(Main.frame, true)).display();
/*  852 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                               }
/*      */                             });
/*  857 */                         JMenu scalingMenu = new JMenu(Main.this.languageBundle.getString("Scaling"));
/*  858 */                         JCheckBoxMenuItem scaling1x = new JCheckBoxMenuItem("1x", (Integer.parseInt(Main.this.properties.getProperty("Scaling", "1")) == 1));
/*  859 */                         scaling1x.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/*  863 */                                 Main.this.form.dispose();
/*  864 */                                 Main.this.properties.setProperty("Scaling", "1");
/*      */                                 try {
/*  867 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/*  870 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/*  874 */                                     output.close();
/*      */                                   } 
/*  877 */                                 } catch (Exception exception) {}
/*  882 */                                 boolean isExit = Main.this.getManager().isExitOnLastRemoved();
/*  883 */                                 Main.this.getManager().setExitOnLastRemoved(false);
/*  884 */                                 Main.this.getManager().disposeAll();
/*  887 */                                 ImagePairs.imagepairs.clear();
/*  888 */                                 Main.this.configurations.clear();
/*  891 */                                 for (String imageSet : Main.this.imageSets)
/*  893 */                                   Main.this.loadConfiguration(imageSet); 
/*  897 */                                 for (String imageSet : Main.this.imageSets)
/*  899 */                                   Main.this.createMascot(imageSet); 
/*  902 */                                 Main.this.getManager().setExitOnLastRemoved(isExit);
/*      */                               }
/*      */                             });
/*  905 */                         JCheckBoxMenuItem scaling2x = new JCheckBoxMenuItem("2x", (Integer.parseInt(Main.this.properties.getProperty("Scaling", "1")) == 2));
/*  906 */                         scaling2x.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/*  910 */                                 Main.this.form.dispose();
/*  911 */                                 Main.this.properties.setProperty("Scaling", "2");
/*      */                                 try {
/*  914 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/*  917 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/*  921 */                                     output.close();
/*      */                                   } 
/*  924 */                                 } catch (Exception exception) {}
/*  929 */                                 boolean isExit = Main.this.getManager().isExitOnLastRemoved();
/*  930 */                                 Main.this.getManager().setExitOnLastRemoved(false);
/*  931 */                                 Main.this.getManager().disposeAll();
/*  934 */                                 ImagePairs.imagepairs.clear();
/*  935 */                                 Main.this.configurations.clear();
/*  938 */                                 for (String imageSet : Main.this.imageSets)
/*  940 */                                   Main.this.loadConfiguration(imageSet); 
/*  944 */                                 for (String imageSet : Main.this.imageSets)
/*  946 */                                   Main.this.createMascot(imageSet); 
/*  949 */                                 Main.this.getManager().setExitOnLastRemoved(isExit);
/*      */                               }
/*      */                             });
/*  952 */                         JCheckBoxMenuItem scaling3x = new JCheckBoxMenuItem("3x", (Integer.parseInt(Main.this.properties.getProperty("Scaling", "1")) == 3));
/*  953 */                         scaling3x.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/*  957 */                                 Main.this.form.dispose();
/*  958 */                                 Main.this.properties.setProperty("Scaling", "3");
/*      */                                 try {
/*  961 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/*  964 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/*  968 */                                     output.close();
/*      */                                   } 
/*  971 */                                 } catch (Exception exception) {}
/*  976 */                                 boolean isExit = Main.this.getManager().isExitOnLastRemoved();
/*  977 */                                 Main.this.getManager().setExitOnLastRemoved(false);
/*  978 */                                 Main.this.getManager().disposeAll();
/*  981 */                                 ImagePairs.imagepairs.clear();
/*  982 */                                 Main.this.configurations.clear();
/*  985 */                                 for (String imageSet : Main.this.imageSets)
/*  987 */                                   Main.this.loadConfiguration(imageSet); 
/*  991 */                                 for (String imageSet : Main.this.imageSets)
/*  993 */                                   Main.this.createMascot(imageSet); 
/*  996 */                                 Main.this.getManager().setExitOnLastRemoved(isExit);
/*      */                               }
/*      */                             });
/*  999 */                         JCheckBoxMenuItem scaling4x = new JCheckBoxMenuItem("4x", (Integer.parseInt(Main.this.properties.getProperty("Scaling", "1")) == 4));
/* 1000 */                         scaling4x.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1004 */                                 Main.this.form.dispose();
/* 1005 */                                 Main.this.properties.setProperty("Scaling", "4");
/*      */                                 try {
/* 1008 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1011 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1015 */                                     output.close();
/*      */                                   } 
/* 1018 */                                 } catch (Exception exception) {}
/* 1023 */                                 boolean isExit = Main.this.getManager().isExitOnLastRemoved();
/* 1024 */                                 Main.this.getManager().setExitOnLastRemoved(false);
/* 1025 */                                 Main.this.getManager().disposeAll();
/* 1028 */                                 ImagePairs.imagepairs.clear();
/* 1029 */                                 Main.this.configurations.clear();
/* 1032 */                                 for (String imageSet : Main.this.imageSets)
/* 1034 */                                   Main.this.loadConfiguration(imageSet); 
/* 1038 */                                 for (String imageSet : Main.this.imageSets)
/* 1040 */                                   Main.this.createMascot(imageSet); 
/* 1043 */                                 Main.this.getManager().setExitOnLastRemoved(isExit);
/*      */                               }
/*      */                             });
/* 1046 */                         JCheckBoxMenuItem scaling6x = new JCheckBoxMenuItem("6x", (Integer.parseInt(Main.this.properties.getProperty("Scaling", "1")) == 6));
/* 1047 */                         scaling6x.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1051 */                                 Main.this.form.dispose();
/* 1052 */                                 Main.this.properties.setProperty("Scaling", "6");
/*      */                                 try {
/* 1055 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1058 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1062 */                                     output.close();
/*      */                                   } 
/* 1065 */                                 } catch (Exception exception) {}
/* 1070 */                                 boolean isExit = Main.this.getManager().isExitOnLastRemoved();
/* 1071 */                                 Main.this.getManager().setExitOnLastRemoved(false);
/* 1072 */                                 Main.this.getManager().disposeAll();
/* 1075 */                                 ImagePairs.imagepairs.clear();
/* 1076 */                                 Main.this.configurations.clear();
/* 1079 */                                 for (String imageSet : Main.this.imageSets)
/* 1081 */                                   Main.this.loadConfiguration(imageSet); 
/* 1085 */                                 for (String imageSet : Main.this.imageSets)
/* 1087 */                                   Main.this.createMascot(imageSet); 
/* 1090 */                                 Main.this.getManager().setExitOnLastRemoved(isExit);
/*      */                               }
/*      */                             });
/* 1093 */                         JCheckBoxMenuItem scaling8x = new JCheckBoxMenuItem("8x", (Integer.parseInt(Main.this.properties.getProperty("Scaling", "1")) == 8));
/* 1094 */                         scaling8x.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1098 */                                 Main.this.form.dispose();
/* 1099 */                                 Main.this.properties.setProperty("Scaling", "8");
/*      */                                 try {
/* 1102 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1105 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1109 */                                     output.close();
/*      */                                   } 
/* 1112 */                                 } catch (Exception exception) {}
/* 1117 */                                 boolean isExit = Main.this.getManager().isExitOnLastRemoved();
/* 1118 */                                 Main.this.getManager().setExitOnLastRemoved(false);
/* 1119 */                                 Main.this.getManager().disposeAll();
/* 1122 */                                 ImagePairs.imagepairs.clear();
/* 1123 */                                 Main.this.configurations.clear();
/* 1126 */                                 for (String imageSet : Main.this.imageSets)
/* 1128 */                                   Main.this.loadConfiguration(imageSet); 
/* 1132 */                                 for (String imageSet : Main.this.imageSets)
/* 1134 */                                   Main.this.createMascot(imageSet); 
/* 1137 */                                 Main.this.getManager().setExitOnLastRemoved(isExit);
/*      */                               }
/*      */                             });
/* 1140 */                         scalingMenu.add(scaling1x);
/* 1141 */                         scalingMenu.add(scaling2x);
/* 1142 */                         scalingMenu.add(scaling3x);
/* 1143 */                         scalingMenu.add(scaling4x);
/* 1144 */                         scalingMenu.add(scaling6x);
/* 1145 */                         scalingMenu.add(scaling8x);
/* 1147 */                         JPopupMenu settingsPopup = new JPopupMenu();
/* 1148 */                         settingsPopup.add(chooseShimejiMenu);
/* 1149 */                         settingsPopup.add(interactiveMenu);
/* 1150 */                         settingsPopup.add(new JSeparator());
/* 1151 */                         settingsPopup.add(scalingMenu);
/* 1152 */                         settingsPopup.addPopupMenuListener(new PopupMenuListener() {
/*      */                               public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
/*      */                               
/*      */                               public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
/* 1162 */                                 if (panel.getMousePosition() != null) {
/* 1164 */                                   btnSettings.setEnabled(((panel.getMousePosition()).x <= btnSettings.getX() || 
/* 1165 */                                       (panel.getMousePosition()).x >= btnSettings.getX() + btnSettings.getWidth() || 
/* 1166 */                                       (panel.getMousePosition()).y <= btnSettings.getY() || 
/* 1167 */                                       (panel.getMousePosition()).y >= btnSettings.getY() + btnSettings.getHeight()));
/*      */                                 } else {
/* 1171 */                                   btnSettings.setEnabled(true);
/*      */                                 } 
/*      */                               }
/*      */                               
/*      */                               public void popupMenuCanceled(PopupMenuEvent e) {}
/*      */                             });
/* 1180 */                         settingsPopup.show(btnSettings, 0, btnSettings.getHeight());
/* 1181 */                         btnSettings.requestFocusInWindow();
/*      */                       }
/*      */                     });
/* 1186 */                 final JButton btnLanguage = new JButton(Main.this.languageBundle.getString("Language"));
/* 1187 */                 btnLanguage.addMouseListener(new MouseListener() {
/*      */                       public void mouseClicked(MouseEvent e) {}
/*      */                       
/*      */                       public void mousePressed(MouseEvent e) {}
/*      */                       
/*      */                       public void mouseReleased(MouseEvent e) {
/* 1202 */                         btnLanguage.setEnabled(true);
/*      */                       }
/*      */                       
/*      */                       public void mouseEntered(MouseEvent e) {}
/*      */                       
/*      */                       public void mouseExited(MouseEvent e) {}
/*      */                     });
/* 1215 */                 btnLanguage.addActionListener(new ActionListener() {
/*      */                       public void actionPerformed(ActionEvent e) {
/* 1220 */                         JMenuItem englishMenu = new JMenuItem("English");
/* 1221 */                         englishMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1225 */                                 Main.this.form.dispose();
/* 1226 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("en-GB")) {
/* 1228 */                                   Main.this.properties.setProperty("Language", "en-GB");
/* 1229 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1231 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1234 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1237 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1241 */                                     output.close();
/*      */                                   } 
/* 1244 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1251 */                         JMenuItem catalanMenu = new JMenuItem("Català");
/* 1252 */                         catalanMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1256 */                                 Main.this.form.dispose();
/* 1257 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("ca-ES")) {
/* 1259 */                                   Main.this.properties.setProperty("Language", "ca-ES");
/* 1260 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1262 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1265 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1268 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1272 */                                     output.close();
/*      */                                   } 
/* 1275 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1282 */                         JMenuItem germanMenu = new JMenuItem("Deutsch");
/* 1283 */                         germanMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1287 */                                 Main.this.form.dispose();
/* 1288 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("de-DE")) {
/* 1290 */                                   Main.this.properties.setProperty("Language", "de-DE");
/* 1291 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1293 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1296 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1299 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1303 */                                     output.close();
/*      */                                   } 
/* 1306 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1313 */                         JMenuItem spanishMenu = new JMenuItem("Español");
/* 1314 */                         spanishMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1318 */                                 Main.this.form.dispose();
/* 1319 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("es-ES")) {
/* 1321 */                                   Main.this.properties.setProperty("Language", "es-ES");
/* 1322 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1324 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1327 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1330 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1334 */                                     output.close();
/*      */                                   } 
/* 1337 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1344 */                         JMenuItem frenchMenu = new JMenuItem("Français");
/* 1345 */                         frenchMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1349 */                                 Main.this.form.dispose();
/* 1350 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("fr-FR")) {
/* 1352 */                                   Main.this.properties.setProperty("Language", "fr-FR");
/* 1353 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1355 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1358 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1361 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1365 */                                     output.close();
/*      */                                   } 
/* 1368 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1375 */                         JMenuItem croatianMenu = new JMenuItem("Hrvatski");
/* 1376 */                         croatianMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1380 */                                 Main.this.form.dispose();
/* 1381 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("hr-HR")) {
/* 1383 */                                   Main.this.properties.setProperty("Language", "hr-HR");
/* 1384 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1386 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1389 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1392 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1396 */                                     output.close();
/*      */                                   } 
/* 1399 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1406 */                         JMenuItem italianMenu = new JMenuItem("Italiano");
/* 1407 */                         italianMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1411 */                                 Main.this.form.dispose();
/* 1412 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("it-IT")) {
/* 1414 */                                   Main.this.properties.setProperty("Language", "it-IT");
/* 1415 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1417 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1420 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1423 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1427 */                                     output.close();
/*      */                                   } 
/* 1430 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1437 */                         JMenuItem dutchMenu = new JMenuItem("Nederlands");
/* 1438 */                         dutchMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1442 */                                 Main.this.form.dispose();
/* 1443 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("nl-NL")) {
/* 1445 */                                   Main.this.properties.setProperty("Language", "nl-NL");
/* 1446 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1448 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1451 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1454 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1458 */                                     output.close();
/*      */                                   } 
/* 1461 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1468 */                         JMenuItem polishMenu = new JMenuItem("Polski");
/* 1469 */                         polishMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1473 */                                 Main.this.form.dispose();
/* 1474 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("pl-PL")) {
/* 1476 */                                   Main.this.properties.setProperty("Language", "pl-PL");
/* 1477 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1479 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1482 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1485 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1489 */                                     output.close();
/*      */                                   } 
/* 1492 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1499 */                         JMenuItem brazilianPortugueseMenu = new JMenuItem("Português Brasileiro");
/* 1500 */                         brazilianPortugueseMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1504 */                                 Main.this.form.dispose();
/* 1505 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("pt-BR")) {
/* 1507 */                                   Main.this.properties.setProperty("Language", "pt-BR");
/* 1508 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1510 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1513 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1516 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1520 */                                     output.close();
/*      */                                   } 
/* 1523 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1530 */                         JMenuItem portugueseMenu = new JMenuItem("Português");
/* 1531 */                         portugueseMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1535 */                                 Main.this.form.dispose();
/* 1536 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("pt-PT")) {
/* 1538 */                                   Main.this.properties.setProperty("Language", "pt-PT");
/* 1539 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1541 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1544 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1547 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1551 */                                     output.close();
/*      */                                   } 
/* 1554 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1561 */                         JMenuItem russianMenu = new JMenuItem("ру́сский язы́к");
/* 1562 */                         russianMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1566 */                                 Main.this.form.dispose();
/* 1567 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("ru-RU")) {
/* 1569 */                                   Main.this.properties.setProperty("Language", "ru-RU");
/* 1570 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1572 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1575 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1578 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1582 */                                     output.close();
/*      */                                   } 
/* 1585 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1592 */                         JMenuItem romanianMenu = new JMenuItem("Română");
/* 1593 */                         romanianMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1597 */                                 Main.this.form.dispose();
/* 1598 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("ro-RO")) {
/* 1600 */                                   Main.this.properties.setProperty("Language", "ro-RO");
/* 1601 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1603 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1606 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1609 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1613 */                                     output.close();
/*      */                                   } 
/* 1616 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1623 */                         JMenuItem serbianMenu = new JMenuItem("Srpski");
/* 1624 */                         serbianMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1628 */                                 Main.this.form.dispose();
/* 1629 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("sr-RS")) {
/* 1631 */                                   Main.this.properties.setProperty("Language", "sr-RS");
/* 1632 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1634 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1637 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1640 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1644 */                                     output.close();
/*      */                                   } 
/* 1647 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1654 */                         JMenuItem finnishMenu = new JMenuItem("Suomi");
/* 1655 */                         finnishMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1659 */                                 Main.this.form.dispose();
/* 1660 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("fi-FI")) {
/* 1662 */                                   Main.this.properties.setProperty("Language", "fi-FI");
/* 1663 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1665 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1668 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1671 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1675 */                                     output.close();
/*      */                                   } 
/* 1678 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1685 */                         JMenuItem vietnameseMenu = new JMenuItem("tiếng Việt");
/* 1686 */                         vietnameseMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1690 */                                 Main.this.form.dispose();
/* 1691 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("vi-VN")) {
/* 1693 */                                   Main.this.properties.setProperty("Language", "vi-VN");
/* 1694 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1696 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1699 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1702 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1706 */                                     output.close();
/*      */                                   } 
/* 1709 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1716 */                         JMenuItem chineseMenu = new JMenuItem("简体中文");
/* 1717 */                         chineseMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1721 */                                 Main.this.form.dispose();
/* 1722 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("zh-CN")) {
/* 1724 */                                   Main.this.properties.setProperty("Language", "zh-CN");
/* 1725 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1727 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1730 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1733 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1737 */                                     output.close();
/*      */                                   } 
/* 1740 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1747 */                         JMenuItem koreanMenu = new JMenuItem("한국어");
/* 1748 */                         koreanMenu.addActionListener(new ActionListener() {
/*      */                               public void actionPerformed(ActionEvent e) {
/* 1752 */                                 Main.this.form.dispose();
/* 1753 */                                 if (!Main.this.properties.getProperty("Language", "en-GB").equals("ko-KR")) {
/* 1755 */                                   Main.this.properties.setProperty("Language", "ko-KR");
/* 1756 */                                   Main.this.refreshLanguage();
/*      */                                 } 
/* 1758 */                                 NativeFactory.getInstance().getEnvironment().refreshCache();
/*      */                                 try {
/* 1761 */                                   FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*      */                                   try {
/* 1764 */                                     Main.this.properties.store(output, "Shimeji-ee Configuration Options");
/*      */                                   } finally {
/* 1768 */                                     output.close();
/*      */                                   } 
/* 1771 */                                 } catch (Exception exception) {}
/*      */                               }
/*      */                             });
/* 1777 */                         JPopupMenu languagePopup = new JPopupMenu();
/* 1778 */                         languagePopup.add(englishMenu);
/* 1779 */                         languagePopup.addSeparator();
/* 1780 */                         languagePopup.add(catalanMenu);
/* 1781 */                         languagePopup.add(germanMenu);
/* 1782 */                         languagePopup.add(spanishMenu);
/* 1783 */                         languagePopup.add(frenchMenu);
/* 1784 */                         languagePopup.add(croatianMenu);
/* 1785 */                         languagePopup.add(italianMenu);
/* 1786 */                         languagePopup.add(dutchMenu);
/* 1787 */                         languagePopup.add(polishMenu);
/* 1788 */                         languagePopup.add(portugueseMenu);
/* 1789 */                         languagePopup.add(brazilianPortugueseMenu);
/* 1790 */                         languagePopup.add(russianMenu);
/* 1791 */                         languagePopup.add(romanianMenu);
/* 1792 */                         languagePopup.add(serbianMenu);
/* 1793 */                         languagePopup.add(finnishMenu);
/* 1794 */                         languagePopup.add(vietnameseMenu);
/* 1795 */                         languagePopup.add(chineseMenu);
/* 1796 */                         languagePopup.add(koreanMenu);
/* 1797 */                         languagePopup.addPopupMenuListener(new PopupMenuListener() {
/*      */                               public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
/*      */                               
/*      */                               public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
/* 1807 */                                 if (panel.getMousePosition() != null) {
/* 1809 */                                   btnLanguage.setEnabled(((panel.getMousePosition()).x <= btnLanguage.getX() || 
/* 1810 */                                       (panel.getMousePosition()).x >= btnLanguage.getX() + btnLanguage.getWidth() || 
/* 1811 */                                       (panel.getMousePosition()).y <= btnLanguage.getY() || 
/* 1812 */                                       (panel.getMousePosition()).y >= btnLanguage.getY() + btnLanguage.getHeight()));
/*      */                                 } else {
/* 1816 */                                   btnLanguage.setEnabled(true);
/*      */                                 } 
/*      */                               }
/*      */                               
/*      */                               public void popupMenuCanceled(PopupMenuEvent e) {}
/*      */                             });
/* 1825 */                         languagePopup.show(btnLanguage, 0, btnLanguage.getHeight());
/* 1826 */                         btnLanguage.requestFocusInWindow();
/*      */                       }
/*      */                     });
/* 1830 */                 JButton btnDismissAll = new JButton(Main.this.languageBundle.getString("DismissAll"));
/* 1831 */                 btnDismissAll.addActionListener(new ActionListener() {
/*      */                       public void actionPerformed(ActionEvent e) {
/* 1835 */                         Main.this.exit();
/*      */                       }
/*      */                     });
/* 1840 */                 float scaling = Float.parseFloat(Main.this.properties.getProperty("MenuDPI", "96")) / 96.0F;
/* 1841 */                 panel.setLayout(new GridBagLayout());
/* 1842 */                 GridBagConstraints gridBag = new GridBagConstraints();
/* 1843 */                 gridBag.fill = 2;
/* 1844 */                 gridBag.gridx = 0;
/* 1845 */                 gridBag.gridy = 0;
/* 1846 */                 panel.add(btnCallShimeji, gridBag);
/* 1847 */                 gridBag.insets = new Insets((int)(5.0F * scaling), 0, 0, 0);
/* 1848 */                 gridBag.gridy++;
/* 1849 */                 panel.add(btnFollowCursor, gridBag);
/* 1850 */                 gridBag.gridy++;
/* 1851 */                 panel.add(btnReduceToOne, gridBag);
/* 1852 */                 gridBag.gridy++;
/* 1853 */                 panel.add(btnRestoreWindows, gridBag);
/* 1854 */                 gridBag.gridy++;
/* 1855 */                 panel.add(new JSeparator(), gridBag);
/* 1856 */                 gridBag.gridy++;
/* 1857 */                 panel.add(btnAllowedBehaviours, gridBag);
/* 1858 */                 gridBag.gridy++;
/* 1859 */                 panel.add(btnSettings, gridBag);
/* 1860 */                 gridBag.gridy++;
/* 1861 */                 panel.add(btnLanguage, gridBag);
/* 1862 */                 gridBag.gridy++;
/* 1863 */                 panel.add(new JSeparator(), gridBag);
/* 1864 */                 gridBag.gridy++;
/* 1865 */                 panel.add(btnDismissAll, gridBag);
/*      */                 try {
/* 1869 */                   Main.this.form.setIconImage(ImageIO.read(Main.class.getResource("/icon.png")));
/* 1871 */                 } catch (IOException iOException) {}
/* 1875 */                 Main.this.form.setTitle(Main.this.languageBundle.getString("ShimejiEE"));
/* 1876 */                 Main.this.form.setDefaultCloseOperation(2);
/* 1877 */                 Main.this.form.setAlwaysOnTop(true);
/* 1880 */                 FontMetrics metrics = btnCallShimeji.getFontMetrics(btnCallShimeji.getFont());
/* 1881 */                 int width = metrics.stringWidth(btnCallShimeji.getText());
/* 1882 */                 width = Math.max(metrics.stringWidth(btnFollowCursor.getText()), width);
/* 1883 */                 width = Math.max(metrics.stringWidth(btnReduceToOne.getText()), width);
/* 1884 */                 width = Math.max(metrics.stringWidth(btnRestoreWindows.getText()), width);
/* 1885 */                 width = Math.max(metrics.stringWidth(btnAllowedBehaviours.getText()), width);
/* 1886 */                 width = Math.max(metrics.stringWidth(btnSettings.getText()), width);
/* 1887 */                 width = Math.max(metrics.stringWidth(btnLanguage.getText()), width);
/* 1888 */                 width = Math.max(metrics.stringWidth(btnDismissAll.getText()), width);
/* 1889 */                 panel.setPreferredSize(new Dimension(width + 64, (int)(24.0F * scaling) + (int)(45.0F * scaling) + 8 * metrics
/*      */                       
/* 1892 */                       .getHeight() + 84));
/* 1894 */                 Main.this.form.pack();
/* 1897 */                 Main.this.form.setLocation((event.getPoint()).x - Main.this.form.getWidth(), (event.getPoint()).y - Main.this.form.getHeight());
/* 1900 */                 Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
/* 1901 */                 if (Main.this.form.getX() < screen.getX())
/* 1903 */                   Main.this.form.setLocation((event.getPoint()).x, Main.this.form.getY()); 
/* 1905 */                 if (Main.this.form.getY() < screen.getY())
/* 1907 */                   Main.this.form.setLocation(Main.this.form.getX(), (event.getPoint()).y); 
/* 1909 */                 Main.this.form.setVisible(true);
/* 1910 */                 Main.this.form.setMinimumSize(Main.this.form.getSize());
/* 1912 */               } else if (event.getButton() == 1) {
/* 1914 */                 Main.this.createMascot();
/*      */               } 
/*      */             }
/*      */             
/*      */             public void mouseEntered(MouseEvent e) {}
/*      */             
/*      */             public void mouseExited(MouseEvent e) {}
/*      */           });
/* 1930 */       SystemTray.getSystemTray().add(icon);
/* 1932 */     } catch (IOException e) {
/* 1934 */       log.log(Level.SEVERE, "Failed to create tray icon", e);
/* 1935 */       showError(this.languageBundle.getString("FailedDisplaySystemTrayErrorMessage") + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/* 1936 */       exit();
/* 1938 */     } catch (AWTException e) {
/* 1940 */       log.log(Level.SEVERE, "Failed to create tray icon", e);
/* 1941 */       showError(this.languageBundle.getString("FailedDisplaySystemTrayErrorMessage") + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/* 1942 */       exit();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void createMascot() {
/* 1949 */     int length = this.imageSets.size();
/* 1950 */     int random = (int)(length * Math.random());
/* 1951 */     createMascot(this.imageSets.get(random));
/*      */   }
/*      */   
/*      */   public void createMascot(String imageSet) {
/* 1959 */     log.log(Level.INFO, "create a mascot");
/* 1962 */     Mascot mascot = new Mascot(imageSet);
/* 1965 */     mascot.setAnchor(new Point(-4000, -4000));
/* 1968 */     mascot.setLookRight((Math.random() < 0.5D));
/*      */     try {
/* 1972 */       mascot.setBehavior(getConfiguration(imageSet).buildBehavior(null, mascot));
/* 1973 */       getManager().add(mascot);
/* 1975 */     } catch (BehaviorInstantiationException e) {
/* 1977 */       log.log(Level.SEVERE, "Failed to initialize the first action", (Throwable)e);
/* 1978 */       showError(this.languageBundle.getString("FailedInitialiseFirstActionErrorMessage") + "\n" + e.getMessage() + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/* 1979 */       mascot.dispose();
/* 1981 */     } catch (CantBeAliveException e) {
/* 1983 */       log.log(Level.SEVERE, "Fatal Error", (Throwable)e);
/* 1984 */       showError(this.languageBundle.getString("FailedInitialiseFirstActionErrorMessage") + "\n" + e.getMessage() + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/* 1985 */       mascot.dispose();
/* 1987 */     } catch (Exception e) {
/* 1989 */       log.log(Level.SEVERE, imageSet + " fatal error, can not be started.", e);
/* 1990 */       showError(this.languageBundle.getString("CouldNotCreateShimejiErrorMessage") + imageSet + ".\n" + e.getMessage() + "\n" + this.languageBundle.getString("SeeLogForDetails"));
/* 1991 */       mascot.dispose();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void refreshLanguage() {
/* 1997 */     Utf8ResourceBundleControl utf8ResourceBundleControl = new Utf8ResourceBundleControl(false);
/* 1998 */     this.languageBundle = ResourceBundle.getBundle("language", Locale.forLanguageTag(this.properties.getProperty("Language", "en-GB")), (ResourceBundle.Control)utf8ResourceBundleControl);
/* 2000 */     boolean isExit = getManager().isExitOnLastRemoved();
/* 2001 */     getManager().setExitOnLastRemoved(false);
/* 2002 */     getManager().disposeAll();
/* 2005 */     for (String imageSet : this.imageSets)
/* 2007 */       loadConfiguration(imageSet); 
/* 2011 */     for (String imageSet : this.imageSets)
/* 2013 */       createMascot(imageSet); 
/* 2016 */     getManager().setExitOnLastRemoved(isExit);
/*      */   }
/*      */   
/*      */   public Configuration getConfiguration(String imageSet) {
/* 2021 */     return this.configurations.get(imageSet);
/*      */   }
/*      */   
/*      */   private Manager getManager() {
/* 2026 */     return this.manager;
/*      */   }
/*      */   
/*      */   public Platform getPlatform() {
/* 2031 */     return this.platform;
/*      */   }
/*      */   
/*      */   public Properties getProperties() {
/* 2036 */     return this.properties;
/*      */   }
/*      */   
/*      */   public ResourceBundle getLanguageBundle() {
/* 2041 */     return this.languageBundle;
/*      */   }
/*      */   
/*      */   public void exit() {
/* 2046 */     getManager().disposeAll();
/* 2047 */     getManager().stop();
/* 2050 */     System.exit(0);
/*      */   }
/*      */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */