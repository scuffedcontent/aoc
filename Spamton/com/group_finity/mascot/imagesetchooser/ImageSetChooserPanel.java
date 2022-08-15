/*    */ package com.group_finity.mascot.imagesetchooser;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JCheckBox;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import org.netbeans.lib.awtextra.AbsoluteConstraints;
/*    */ import org.netbeans.lib.awtextra.AbsoluteLayout;
/*    */ 
/*    */ public class ImageSetChooserPanel extends JPanel {
/*    */   private JLabel actionsFile;
/*    */   
/*    */   private JLabel behaviorsFile;
/*    */   
/*    */   private JCheckBox checkbox;
/*    */   
/*    */   private JLabel image;
/*    */   
/*    */   private JLabel name;
/*    */   
/*    */   public ImageSetChooserPanel() {
/* 15 */     initComponents();
/*    */   }
/*    */   
/*    */   public ImageSetChooserPanel(String name, String actions, String behaviors, String imageLocation) {
/* 20 */     initComponents();
/* 22 */     this.name.setText(name);
/* 23 */     this.actionsFile.setText(actions);
/* 24 */     this.behaviorsFile.setText(behaviors);
/*    */     try {
/* 26 */       BufferedImage img = ImageIO.read(new File(imageLocation));
/* 27 */       this.image.setIcon(new ImageIcon(img.getScaledInstance(60, 60, 1)));
/* 28 */     } catch (Exception exception) {}
/*    */   }
/*    */   
/*    */   public void setCheckbox(boolean value) {
/* 34 */     this.checkbox.setSelected(value);
/*    */   }
/*    */   
/*    */   public String getImageSetName() {
/* 38 */     return this.name.getText();
/*    */   }
/*    */   
/*    */   private void initComponents() {
/* 49 */     this.checkbox = new JCheckBox();
/* 50 */     this.name = new JLabel();
/* 51 */     this.actionsFile = new JLabel();
/* 52 */     this.behaviorsFile = new JLabel();
/* 53 */     this.image = new JLabel();
/* 55 */     setBorder(BorderFactory.createEtchedBorder());
/* 56 */     setMinimumSize(new Dimension(248, 80));
/* 57 */     setPreferredSize(new Dimension(248, 80));
/* 58 */     setLayout((LayoutManager)new AbsoluteLayout());
/* 60 */     this.checkbox.setOpaque(false);
/* 61 */     add(this.checkbox, new AbsoluteConstraints(10, 30, -1, -1));
/* 63 */     this.name.setText("Builder");
/* 64 */     add(this.name, new AbsoluteConstraints(110, 10, -1, -1));
/* 66 */     this.actionsFile.setText("img/Builder/conf/actionsxml");
/* 67 */     add(this.actionsFile, new AbsoluteConstraints(110, 30, -1, -1));
/* 69 */     this.behaviorsFile.setText("img/Builder/conf/behaviors.xml");
/* 70 */     add(this.behaviorsFile, new AbsoluteConstraints(110, 50, -1, -1));
/* 72 */     this.image.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
/* 73 */     add(this.image, new AbsoluteConstraints(40, 10, 60, 60));
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/imagesetchooser/ImageSetChooserPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */