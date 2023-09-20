/*     */ package com.group_finity.mascot.imagesetchooser;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FilenameFilter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.DefaultListSelectionModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.LayoutStyle;
/*     */ 
/*     */ public class ImageSetChooser extends JDialog {
/*  17 */   private final String configFile = "./conf/settings.properties";
/*     */   
/*  18 */   private final String topDir = "./img";
/*     */   
/*  19 */   private ArrayList<String> imageSets = new ArrayList<String>();
/*     */   
/*     */   private boolean closeProgram = true;
/*     */   
/*     */   private boolean selectAllSets = false;
/*     */   
/*     */   private JButton cancelButton;
/*     */   
/*     */   private JLabel clearAllLabel;
/*     */   
/*     */   private JLabel jLabel1;
/*     */   
/*     */   private JList jList1;
/*     */   
/*     */   private JList jList2;
/*     */   
/*     */   private JPanel jPanel1;
/*     */   
/*     */   private JPanel jPanel2;
/*     */   
/*     */   private JPanel jPanel4;
/*     */   
/*     */   private JScrollPane jScrollPane1;
/*     */   
/*     */   private JLabel selectAllLabel;
/*     */   
/*     */   private JLabel slashLabel;
/*     */   
/*     */   private JButton useAllButton;
/*     */   
/*     */   private JButton useSelectedButton;
/*     */   
/*     */   public ImageSetChooser(Frame parent, boolean modal) {
/*  25 */     super(parent, modal);
/*  26 */     initComponents();
/*  27 */     setLocationRelativeTo(null);
/*  29 */     ArrayList<String> activeImageSets = readConfigFile();
/*  31 */     ArrayList<ImageSetChooserPanel> data1 = new ArrayList<ImageSetChooserPanel>();
/*  32 */     ArrayList<ImageSetChooserPanel> data2 = new ArrayList<ImageSetChooserPanel>();
/*  33 */     ArrayList<Integer> si1 = new ArrayList<Integer>();
/*  34 */     ArrayList<Integer> si2 = new ArrayList<Integer>();
/*  37 */     FilenameFilter fileFilter = new FilenameFilter() {
/*     */         public boolean accept(File dir, String name) {
/*  41 */           if (name.equals("unused") || name.equals(".svn"))
/*  43 */             return false; 
/*  45 */           return (new File(dir + "/" + name)).isDirectory();
/*     */         }
/*     */       };
/*  48 */     File dir = new File("./img");
/*  49 */     String[] children = dir.list(fileFilter);
/*  52 */     boolean onList1 = true;
/*  53 */     int row = 0;
/*  54 */     for (String imageSet : children) {
/*  56 */       String imageFile = "./img/" + imageSet + "/shime1.png";
/*  59 */       String actionsFile = "conf/actions.xml";
/*  60 */       if ((new File("./conf/動作.xml")).exists())
/*  62 */         actionsFile = "conf/動作.xml"; 
/*  64 */       if ((new File("./conf/" + imageSet + "/actions.xml")).exists())
/*  66 */         actionsFile = "conf/" + imageSet + "/actions.xml"; 
/*  68 */       if ((new File("./conf/" + imageSet + "/動作.xml")).exists())
/*  70 */         actionsFile = "conf/" + imageSet + "/動作.xml"; 
/*  72 */       if ((new File("./conf/" + imageSet + "/Õïòõ¢£.xml")).exists())
/*  74 */         actionsFile = "conf/" + imageSet + "/Õïòõ¢£.xml"; 
/*  76 */       if ((new File("./conf/" + imageSet + "/¦-º@.xml")).exists())
/*  78 */         actionsFile = "conf/" + imageSet + "/¦-º@.xml"; 
/*  80 */       if ((new File("./conf/" + imageSet + "/ô«ìý.xml")).exists())
/*  82 */         actionsFile = "conf/" + imageSet + "/ô«ìý.xml"; 
/*  84 */       if ((new File("./conf/" + imageSet + "/one.xml")).exists())
/*  86 */         actionsFile = "conf/" + imageSet + "/one.xml"; 
/*  88 */       if ((new File("./conf/" + imageSet + "/1.xml")).exists())
/*  90 */         actionsFile = "conf/" + imageSet + "/1.xml"; 
/*  92 */       if ((new File("./img/" + imageSet + "/conf/actions.xml")).exists())
/*  94 */         actionsFile = "img/" + imageSet + "/conf/actions.xml"; 
/*  96 */       if ((new File("./img/" + imageSet + "/conf/動作.xml")).exists())
/*  98 */         actionsFile = "img/" + imageSet + "/conf/動作.xml"; 
/* 100 */       if ((new File("./img/" + imageSet + "/conf/Õïòõ¢£.xml")).exists())
/* 102 */         actionsFile = "img/" + imageSet + "/conf/Õïòõ¢£.xml"; 
/* 104 */       if ((new File("./img/" + imageSet + "/conf/¦-º@.xml")).exists())
/* 106 */         actionsFile = "img/" + imageSet + "/conf/¦-º@.xml"; 
/* 108 */       if ((new File("./img/" + imageSet + "/conf/ô«ìý.xml")).exists())
/* 110 */         actionsFile = "img/" + imageSet + "/conf/ô«ìý.xml"; 
/* 112 */       if ((new File("./img/" + imageSet + "/conf/one.xml")).exists())
/* 114 */         actionsFile = "img/" + imageSet + "/conf/one.xml"; 
/* 116 */       if ((new File("./img/" + imageSet + "/conf/1.xml")).exists())
/* 118 */         actionsFile = "img/" + imageSet + "/conf/1.xml"; 
/* 122 */       String behaviorsFile = "./conf/behaviors.xml";
/* 123 */       if ((new File("./conf/行動.xml")).exists())
/* 125 */         behaviorsFile = "conf/" + imageSet + "/行動.xml"; 
/* 127 */       if ((new File("./conf/" + imageSet + "/behaviors.xml")).exists())
/* 129 */         behaviorsFile = "conf/" + imageSet + "/behaviors.xml"; 
/* 131 */       if ((new File("./conf/" + imageSet + "/behavior.xml")).exists())
/* 133 */         behaviorsFile = "conf/" + imageSet + "/behavior.xml"; 
/* 135 */       if ((new File("./conf/" + imageSet + "/行動.xml")).exists())
/* 137 */         behaviorsFile = "conf/" + imageSet + "/行動.xml"; 
/* 139 */       if ((new File("./conf/" + imageSet + "/ÞíîÕïò.xml")).exists())
/* 141 */         behaviorsFile = "conf/" + imageSet + "/ÞíîÕïò.xml"; 
/* 143 */       if ((new File("./conf/" + imageSet + "/ªµ¦-.xml")).exists())
/* 145 */         behaviorsFile = "conf/" + imageSet + "/ªµ¦-.xml"; 
/* 147 */       if ((new File("./conf/" + imageSet + "/ìsô«.xml")).exists())
/* 149 */         behaviorsFile = "conf/" + imageSet + "/ìsô«.xml"; 
/* 151 */       if ((new File("./conf/" + imageSet + "/two.xml")).exists())
/* 153 */         behaviorsFile = "conf/" + imageSet + "/two.xml"; 
/* 155 */       if ((new File("./conf/" + imageSet + "/2.xml")).exists())
/* 157 */         behaviorsFile = "conf/" + imageSet + "/2.xml"; 
/* 159 */       if ((new File("./img/" + imageSet + "/conf/behaviors.xml")).exists())
/* 161 */         behaviorsFile = "img/" + imageSet + "/conf/behaviors.xml"; 
/* 163 */       if ((new File("./img/" + imageSet + "/conf/behavior.xml")).exists())
/* 165 */         behaviorsFile = "img/" + imageSet + "/conf/behavior.xml"; 
/* 167 */       if ((new File("./img/" + imageSet + "/conf/行動.xml")).exists())
/* 169 */         behaviorsFile = "img/" + imageSet + "/conf/行動.xml"; 
/* 171 */       if ((new File("./img/" + imageSet + "/conf/ÞíîÕïò.xml")).exists())
/* 173 */         behaviorsFile = "img/" + imageSet + "/conf/ÞíîÕïò.xml"; 
/* 175 */       if ((new File("./img/" + imageSet + "/conf/ªµ¦-.xml")).exists())
/* 177 */         behaviorsFile = "img/" + imageSet + "/conf/ªµ¦-.xml"; 
/* 179 */       if ((new File("./img/" + imageSet + "/conf/ìsô«.xml")).exists())
/* 181 */         behaviorsFile = "img/" + imageSet + "/conf/ìsô«.xml"; 
/* 183 */       if ((new File("./img/" + imageSet + "/conf/two.xml")).exists())
/* 185 */         behaviorsFile = "img/" + imageSet + "/conf/two.xml"; 
/* 187 */       if ((new File("./img/" + imageSet + "/conf/2.xml")).exists())
/* 189 */         behaviorsFile = "img/" + imageSet + "/conf/2.xml"; 
/* 192 */       if (onList1) {
/* 194 */         onList1 = false;
/* 195 */         data1.add(new ImageSetChooserPanel(imageSet, actionsFile, behaviorsFile, imageFile));
/* 198 */         if (activeImageSets.contains(imageSet) || this.selectAllSets)
/* 200 */           si1.add(Integer.valueOf(row)); 
/*     */       } else {
/* 205 */         onList1 = true;
/* 206 */         data2.add(new ImageSetChooserPanel(imageSet, actionsFile, behaviorsFile, imageFile));
/* 209 */         if (activeImageSets.contains(imageSet) || this.selectAllSets)
/* 211 */           si2.add(Integer.valueOf(row)); 
/* 213 */         row++;
/*     */       } 
/* 215 */       this.imageSets.add(imageSet);
/*     */     } 
/* 218 */     setUpList1();
/* 219 */     this.jList1.setListData(data1.toArray());
/* 220 */     this.jList1.setSelectedIndices(convertIntegers(si1));
/* 222 */     setUpList2();
/* 223 */     this.jList2.setListData(data2.toArray());
/* 224 */     this.jList2.setSelectedIndices(convertIntegers(si2));
/*     */   }
/*     */   
/*     */   public ArrayList<String> display() {
/* 229 */     setTitle(Main.getInstance().getLanguageBundle().getString("ShimejiImageSetChooser"));
/* 230 */     this.jLabel1.setText(Main.getInstance().getLanguageBundle().getString("SelectImageSetsToUse"));
/* 231 */     this.useSelectedButton.setText(Main.getInstance().getLanguageBundle().getString("UseSelected"));
/* 232 */     this.useAllButton.setText(Main.getInstance().getLanguageBundle().getString("UseAll"));
/* 233 */     this.cancelButton.setText(Main.getInstance().getLanguageBundle().getString("Cancel"));
/* 234 */     this.clearAllLabel.setText(Main.getInstance().getLanguageBundle().getString("ClearAll"));
/* 235 */     this.selectAllLabel.setText(Main.getInstance().getLanguageBundle().getString("SelectAll"));
/* 236 */     setVisible(true);
/* 237 */     if (this.closeProgram)
/* 239 */       return null; 
/* 241 */     return this.imageSets;
/*     */   }
/*     */   
/*     */   private ArrayList<String> readConfigFile() {
/* 247 */     ArrayList<String> activeImageSets = new ArrayList<String>();
/* 248 */     activeImageSets.addAll(Arrays.asList(Main.getInstance().getProperties().getProperty("ActiveShimeji", "").split("/")));
/* 249 */     this.selectAllSets = ((String)activeImageSets.get(0)).trim().isEmpty();
/* 250 */     return activeImageSets;
/*     */   }
/*     */   
/*     */   private void updateConfigFile() {
/*     */     try {
/* 257 */       FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*     */       try {
/* 260 */         Main.getInstance().getProperties().setProperty("ActiveShimeji", this.imageSets.toString().replace("[", "").replace("]", "").replace(", ", "/"));
/* 261 */         Main.getInstance().getProperties().store(output, "Shimeji-ee Configuration Options");
/*     */       } finally {
/* 265 */         output.close();
/*     */       } 
/* 268 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   private void initComponents() {
/* 283 */     this.jScrollPane1 = new JScrollPane();
/* 284 */     this.jPanel2 = new JPanel();
/* 285 */     this.jList1 = new ShimejiList();
/* 286 */     this.jList2 = new ShimejiList();
/* 287 */     this.jLabel1 = new JLabel();
/* 288 */     this.jPanel1 = new JPanel();
/* 289 */     this.useSelectedButton = new JButton();
/* 290 */     this.useAllButton = new JButton();
/* 291 */     this.cancelButton = new JButton();
/* 292 */     this.jPanel4 = new JPanel();
/* 293 */     this.clearAllLabel = new JLabel();
/* 294 */     this.slashLabel = new JLabel();
/* 295 */     this.selectAllLabel = new JLabel();
/* 297 */     setDefaultCloseOperation(2);
/* 298 */     setTitle("Shimeji-ee Image Set Chooser");
/* 299 */     setMinimumSize(new Dimension(670, 495));
/* 301 */     this.jScrollPane1.setPreferredSize(new Dimension(518, 100));
/* 303 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 304 */     this.jPanel2.setLayout(jPanel2Layout);
/* 305 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout
/* 306 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 307 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 308 */           .addComponent(this.jList1, -1, 298, 32767)
/* 309 */           .addGap(0, 0, 0)
/* 310 */           .addComponent(this.jList2, -1, 300, 32767)));
/* 311 */     jPanel2Layout.setVerticalGroup(jPanel2Layout
/* 312 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 313 */         .addComponent(this.jList2, -1, 376, 32767)
/* 314 */         .addComponent(this.jList1, -1, 376, 32767));
/* 316 */     this.jScrollPane1.setViewportView(this.jPanel2);
/* 318 */     this.jLabel1.setText("Select Image Sets to Use:");
/* 320 */     this.jPanel1.setLayout(new FlowLayout(1, 10, 5));
/* 322 */     this.useSelectedButton.setText("Use Selected");
/* 323 */     this.useSelectedButton.setMaximumSize(new Dimension(130, 26));
/* 324 */     this.useSelectedButton.setPreferredSize(new Dimension(130, 26));
/* 325 */     this.useSelectedButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 329 */             ImageSetChooser.this.useSelectedButtonActionPerformed(evt);
/*     */           }
/*     */         });
/* 332 */     this.jPanel1.add(this.useSelectedButton);
/* 334 */     this.useAllButton.setText("Use All");
/* 335 */     this.useAllButton.setMaximumSize(new Dimension(95, 23));
/* 336 */     this.useAllButton.setMinimumSize(new Dimension(95, 23));
/* 337 */     this.useAllButton.setPreferredSize(new Dimension(130, 26));
/* 338 */     this.useAllButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 342 */             ImageSetChooser.this.useAllButtonActionPerformed(evt);
/*     */           }
/*     */         });
/* 345 */     this.jPanel1.add(this.useAllButton);
/* 347 */     this.cancelButton.setText("Cancel");
/* 348 */     this.cancelButton.setMaximumSize(new Dimension(95, 23));
/* 349 */     this.cancelButton.setMinimumSize(new Dimension(95, 23));
/* 350 */     this.cancelButton.setPreferredSize(new Dimension(130, 26));
/* 351 */     this.cancelButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 355 */             ImageSetChooser.this.cancelButtonActionPerformed(evt);
/*     */           }
/*     */         });
/* 358 */     this.jPanel1.add(this.cancelButton);
/* 360 */     this.jPanel4.setLayout(new BoxLayout(this.jPanel4, 2));
/* 362 */     this.clearAllLabel.setForeground(new Color(0, 0, 204));
/* 363 */     this.clearAllLabel.setText("Clear All");
/* 364 */     this.clearAllLabel.setCursor(new Cursor(12));
/* 365 */     this.clearAllLabel.addMouseListener(new MouseAdapter() {
/*     */           public void mouseClicked(MouseEvent evt) {
/* 369 */             ImageSetChooser.this.clearAllLabelMouseClicked(evt);
/*     */           }
/*     */         });
/* 372 */     this.jPanel4.add(this.clearAllLabel);
/* 374 */     this.slashLabel.setText(" / ");
/* 375 */     this.jPanel4.add(this.slashLabel);
/* 377 */     this.selectAllLabel.setForeground(new Color(0, 0, 204));
/* 378 */     this.selectAllLabel.setText("Select All");
/* 379 */     this.selectAllLabel.setCursor(new Cursor(12));
/* 380 */     this.selectAllLabel.addMouseListener(new MouseAdapter() {
/*     */           public void mouseClicked(MouseEvent evt) {
/* 384 */             ImageSetChooser.this.selectAllLabelMouseClicked(evt);
/*     */           }
/*     */         });
/* 387 */     this.jPanel4.add(this.selectAllLabel);
/* 389 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 390 */     getContentPane().setLayout(layout);
/* 391 */     layout.setHorizontalGroup(layout
/* 392 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 393 */         .addGroup(layout.createSequentialGroup()
/* 394 */           .addContainerGap()
/* 395 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 396 */             .addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING, -1, 600, 32767)
/* 397 */             .addGroup(layout.createSequentialGroup()
/* 398 */               .addComponent(this.jLabel1)
/* 399 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 384, 32767)
/* 400 */               .addComponent(this.jPanel4, -2, -1, -2))
/* 401 */             .addComponent(this.jPanel1, -1, 600, 32767))
/* 402 */           .addContainerGap()));
/* 403 */     layout.setVerticalGroup(layout
/* 404 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 405 */         .addGroup(layout.createSequentialGroup()
/* 406 */           .addContainerGap()
/* 407 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 408 */             .addComponent(this.jLabel1)
/* 409 */             .addComponent(this.jPanel4, -2, -1, -2))
/* 410 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 411 */           .addComponent(this.jScrollPane1, -1, 378, 32767)
/* 412 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 413 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 414 */           .addGap(11, 11, 11)));
/* 416 */     pack();
/*     */   }
/*     */   
/*     */   private void clearAllLabelMouseClicked(MouseEvent evt) {
/* 421 */     this.jList1.clearSelection();
/* 422 */     this.jList2.clearSelection();
/*     */   }
/*     */   
/*     */   private void selectAllLabelMouseClicked(MouseEvent evt) {
/* 427 */     this.jList1.setSelectionInterval(0, this.jList1.getModel().getSize() - 1);
/* 428 */     this.jList2.setSelectionInterval(0, this.jList2.getModel().getSize() - 1);
/*     */   }
/*     */   
/*     */   private void useSelectedButtonActionPerformed(ActionEvent evt) {
/* 433 */     this.imageSets.clear();
/* 435 */     for (Object obj : this.jList1.getSelectedValues()) {
/* 437 */       if (obj instanceof ImageSetChooserPanel)
/* 439 */         this.imageSets.add(((ImageSetChooserPanel)obj).getImageSetName()); 
/*     */     } 
/* 443 */     for (Object obj : this.jList2.getSelectedValues()) {
/* 445 */       if (obj instanceof ImageSetChooserPanel)
/* 447 */         this.imageSets.add(((ImageSetChooserPanel)obj).getImageSetName()); 
/*     */     } 
/* 451 */     updateConfigFile();
/* 452 */     this.closeProgram = false;
/* 453 */     dispose();
/*     */   }
/*     */   
/*     */   private void useAllButtonActionPerformed(ActionEvent evt) {
/* 458 */     this.closeProgram = false;
/* 459 */     dispose();
/*     */   }
/*     */   
/*     */   private void cancelButtonActionPerformed(ActionEvent evt) {
/* 464 */     dispose();
/*     */   }
/*     */   
/*     */   private int[] convertIntegers(List<Integer> integers) {
/* 469 */     int[] ret = new int[integers.size()];
/* 470 */     for (int i = 0; i < ret.length; i++)
/* 472 */       ret[i] = ((Integer)integers.get(i)).intValue(); 
/* 474 */     return ret;
/*     */   }
/*     */   
/*     */   private void setUpList1() {
/* 479 */     this.jList1.setSelectionModel(new DefaultListSelectionModel() {
/*     */           public void setSelectionInterval(int index0, int index1) {
/* 484 */             if (isSelectedIndex(index0)) {
/* 486 */               removeSelectionInterval(index0, index1);
/*     */             } else {
/* 490 */               addSelectionInterval(index0, index1);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void setUpList2() {
/* 498 */     this.jList2.setSelectionModel(new DefaultListSelectionModel() {
/*     */           public void setSelectionInterval(int index0, int index1) {
/* 503 */             if (isSelectedIndex(index0)) {
/* 505 */               removeSelectionInterval(index0, index1);
/*     */             } else {
/* 509 */               addSelectionInterval(index0, index1);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 520 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 524 */             (new ImageSetChooser(new JFrame(), true)).display();
/* 525 */             System.exit(0);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/imagesetchooser/ImageSetChooser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */