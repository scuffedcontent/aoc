/*     */ package com.group_finity.mascot.win;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import javax.swing.AbstractListModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ 
/*     */ public class WindowsInteractiveWindowForm extends JDialog {
/*  15 */   private final String configFile = "./conf/settings.properties";
/*     */   
/*  16 */   ArrayList<String> listData = new ArrayList<String>();
/*     */   
/*     */   private JButton jButton1;
/*     */   
/*     */   private JButton jButton2;
/*     */   
/*     */   private JButton jButton3;
/*     */   
/*     */   private JList jList1;
/*     */   
/*     */   private JPanel jPanel1;
/*     */   
/*     */   private JPanel jPanel2;
/*     */   
/*     */   private JScrollPane jScrollPane1;
/*     */   
/*     */   public WindowsInteractiveWindowForm(Frame parent, boolean modal) {
/*  20 */     super(parent, modal);
/*  21 */     initComponents();
/*  22 */     setLocationRelativeTo(null);
/*  24 */     this.listData.addAll(Arrays.asList(Main.getInstance().getProperties().getProperty("InteractiveWindows", "").split("/")));
/*  25 */     this.jList1.setListData(this.listData.toArray());
/*     */   }
/*     */   
/*     */   public boolean display() {
/*  30 */     setTitle(Main.getInstance().getLanguageBundle().getString("InteractiveWindows"));
/*  31 */     this.jButton1.setText(Main.getInstance().getLanguageBundle().getString("Add"));
/*  32 */     this.jButton2.setText(Main.getInstance().getLanguageBundle().getString("Done"));
/*  33 */     this.jButton3.setText(Main.getInstance().getLanguageBundle().getString("Remove"));
/*  34 */     setVisible(true);
/*  35 */     return true;
/*     */   }
/*     */   
/*     */   private void initComponents() {
/*  48 */     this.jPanel1 = new JPanel();
/*  49 */     this.jScrollPane1 = new JScrollPane();
/*  50 */     this.jList1 = new JList();
/*  51 */     this.jPanel2 = new JPanel();
/*  52 */     this.jButton1 = new JButton();
/*  53 */     this.jButton3 = new JButton();
/*  54 */     this.jButton2 = new JButton();
/*  56 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  57 */     this.jPanel1.setLayout(jPanel1Layout);
/*  58 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/*  59 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  60 */         .addGap(0, 100, 32767));
/*  62 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/*  63 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  64 */         .addGap(0, 100, 32767));
/*  67 */     setDefaultCloseOperation(2);
/*  68 */     setTitle("Interactive Windows");
/*  70 */     this.jList1.setModel(new AbstractListModel() {
/*  72 */           String[] strings = new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
/*     */           
/*     */           public int getSize() {
/*  73 */             return this.strings.length;
/*     */           }
/*     */           
/*     */           public Object getElementAt(int i) {
/*  74 */             return this.strings[i];
/*     */           }
/*     */         });
/*  76 */     this.jScrollPane1.setViewportView(this.jList1);
/*  78 */     getContentPane().add(this.jScrollPane1, "Center");
/*  80 */     this.jPanel2.setLayout(new GridLayout(1, 0));
/*  82 */     this.jButton1.setLabel("Add");
/*  83 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  87 */             WindowsInteractiveWindowForm.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*  90 */     this.jPanel2.add(this.jButton1);
/*  92 */     this.jButton3.setLabel("Remove");
/*  93 */     this.jButton3.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  97 */             WindowsInteractiveWindowForm.this.jButton3ActionPerformed(evt);
/*     */           }
/*     */         });
/* 100 */     this.jPanel2.add(this.jButton3);
/* 102 */     this.jButton2.setText("Done");
/* 103 */     this.jButton2.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 107 */             WindowsInteractiveWindowForm.this.jButton2ActionPerformed(evt);
/*     */           }
/*     */         });
/* 110 */     this.jPanel2.add(this.jButton2);
/* 112 */     getContentPane().add(this.jPanel2, "Last");
/* 114 */     pack();
/*     */   }
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 120 */     String inputValue = JOptionPane.showInputDialog(this.rootPane, Main.getInstance().getLanguageBundle().getString("InteractiveWindowHintMessage"), Main.getInstance().getLanguageBundle().getString("AddInteractiveWindow"), 3).trim();
/* 121 */     if (!inputValue.isEmpty() && !inputValue.contains("/")) {
/* 123 */       this.listData.add(inputValue);
/* 124 */       this.jList1.setListData(this.listData.toArray());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jButton3ActionPerformed(ActionEvent evt) {
/* 131 */     if (this.jList1.getSelectedIndex() != -1) {
/* 133 */       this.listData.remove(this.jList1.getSelectedIndex());
/* 134 */       this.jList1.setListData(this.listData.toArray());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/*     */     try {
/* 143 */       FileOutputStream output = new FileOutputStream("./conf/settings.properties");
/*     */       try {
/* 146 */         Main.getInstance().getProperties().setProperty("InteractiveWindows", this.listData.toString().replace("[", "").replace("]", "").replace(", ", "/"));
/* 147 */         Main.getInstance().getProperties().store(output, "Shimeji-ee Configuration Options");
/*     */       } finally {
/* 151 */         output.close();
/*     */       } 
/* 154 */     } catch (Exception exception) {}
/* 158 */     dispose();
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 166 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 170 */             (new WindowsInteractiveWindowForm(new JFrame(), true)).display();
/* 171 */             System.exit(0);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/WindowsInteractiveWindowForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */