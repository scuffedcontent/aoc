/*     */ package com.group_finity.mascot;
/*     */ 
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.LayoutStyle;
/*     */ 
/*     */ public class DebugWindow extends JFrame {
/*     */   private JLabel lblBehaviour;
/*     */   
/*     */   private JLabel lblBehaviourValue;
/*     */   
/*     */   private JLabel lblShimejiX;
/*     */   
/*     */   private JLabel lblShimejiXValue;
/*     */   
/*     */   private JLabel lblShimejiY;
/*     */   
/*     */   private JLabel lblShimejiYValue;
/*     */   
/*     */   private JLabel lblWindowHeight;
/*     */   
/*     */   private JLabel lblWindowHeightValue;
/*     */   
/*     */   private JLabel lblWindowWidth;
/*     */   
/*     */   private JLabel lblWindowWidthValue;
/*     */   
/*     */   private JLabel lblWindowX;
/*     */   
/*     */   private JLabel lblWindowXValue;
/*     */   
/*     */   private JLabel lblWindowY;
/*     */   
/*     */   private JLabel lblWindowYValue;
/*     */   
/*     */   public DebugWindow() {
/*  11 */     initComponents();
/*     */   }
/*     */   
/*     */   private void initComponents() {
/*  23 */     this.lblShimejiX = new JLabel();
/*  24 */     this.lblShimejiXValue = new JLabel();
/*  25 */     this.lblShimejiYValue = new JLabel();
/*  26 */     this.lblShimejiY = new JLabel();
/*  27 */     this.lblWindowX = new JLabel();
/*  28 */     this.lblWindowY = new JLabel();
/*  29 */     this.lblWindowWidth = new JLabel();
/*  30 */     this.lblWindowHeight = new JLabel();
/*  31 */     this.lblWindowXValue = new JLabel();
/*  32 */     this.lblWindowYValue = new JLabel();
/*  33 */     this.lblWindowWidthValue = new JLabel();
/*  34 */     this.lblWindowHeightValue = new JLabel();
/*  35 */     this.lblBehaviour = new JLabel();
/*  36 */     this.lblBehaviourValue = new JLabel();
/*  38 */     setDefaultCloseOperation(2);
/*  40 */     this.lblShimejiX.setText("Shimeji X");
/*  42 */     this.lblShimejiXValue.setHorizontalAlignment(2);
/*  43 */     this.lblShimejiXValue.setText("jLabel2");
/*  45 */     this.lblShimejiYValue.setHorizontalAlignment(2);
/*  46 */     this.lblShimejiYValue.setText("jLabel3");
/*  48 */     this.lblShimejiY.setText("Shimeji Y");
/*  50 */     this.lblWindowX.setText("Window X");
/*  52 */     this.lblWindowY.setText("Window Y");
/*  54 */     this.lblWindowWidth.setText("Window W");
/*  56 */     this.lblWindowHeight.setText("Window H");
/*  58 */     this.lblWindowXValue.setHorizontalAlignment(2);
/*  59 */     this.lblWindowXValue.setText("jLabel9");
/*  61 */     this.lblWindowYValue.setHorizontalAlignment(2);
/*  62 */     this.lblWindowYValue.setText("jLabel10");
/*  64 */     this.lblWindowWidthValue.setHorizontalAlignment(2);
/*  65 */     this.lblWindowWidthValue.setText("jLabel11");
/*  67 */     this.lblWindowHeightValue.setHorizontalAlignment(2);
/*  68 */     this.lblWindowHeightValue.setText("jLabel12");
/*  70 */     this.lblBehaviour.setText("Behaviour");
/*  72 */     this.lblBehaviourValue.setHorizontalAlignment(2);
/*  73 */     this.lblBehaviourValue.setText("jLabel14");
/*  75 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  76 */     getContentPane().setLayout(layout);
/*  77 */     layout.setHorizontalGroup(layout
/*  78 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  79 */         .addGroup(layout.createSequentialGroup()
/*  80 */           .addContainerGap()
/*  81 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  82 */             .addGroup(layout.createSequentialGroup()
/*  83 */               .addComponent(this.lblBehaviour)
/*  84 */               .addGap(45, 45, 45)
/*  85 */               .addComponent(this.lblBehaviourValue, -1, 130, 32767))
/*  86 */             .addGroup(layout.createSequentialGroup()
/*  87 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  88 */                 .addComponent(this.lblShimejiX)
/*  89 */                 .addComponent(this.lblShimejiY)
/*  90 */                 .addComponent(this.lblWindowX)
/*  91 */                 .addComponent(this.lblWindowY)
/*  92 */                 .addComponent(this.lblWindowWidth)
/*  93 */                 .addComponent(this.lblWindowHeight))
/*  94 */               .addGap(42, 42, 42)
/*  95 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  96 */                 .addComponent(this.lblShimejiYValue)
/*  97 */                 .addComponent(this.lblShimejiXValue)
/*  98 */                 .addComponent(this.lblWindowXValue)
/*  99 */                 .addComponent(this.lblWindowYValue)
/* 100 */                 .addComponent(this.lblWindowWidthValue)
/* 101 */                 .addComponent(this.lblWindowHeightValue))
/* 102 */               .addGap(0, 0, 32767)))
/* 103 */           .addContainerGap()));
/* 105 */     layout.setVerticalGroup(layout
/* 106 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 107 */         .addGroup(layout.createSequentialGroup()
/* 108 */           .addContainerGap()
/* 109 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 110 */             .addComponent(this.lblBehaviour)
/* 111 */             .addComponent(this.lblBehaviourValue))
/* 112 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 113 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 114 */             .addComponent(this.lblShimejiX)
/* 115 */             .addComponent(this.lblShimejiXValue))
/* 116 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 117 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 118 */             .addComponent(this.lblShimejiYValue)
/* 119 */             .addComponent(this.lblShimejiY))
/* 120 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 121 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 122 */             .addComponent(this.lblWindowX)
/* 123 */             .addComponent(this.lblWindowXValue))
/* 124 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 125 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 126 */             .addComponent(this.lblWindowY)
/* 127 */             .addComponent(this.lblWindowYValue))
/* 128 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 129 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 130 */             .addComponent(this.lblWindowWidth)
/* 131 */             .addComponent(this.lblWindowWidthValue))
/* 132 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 133 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 134 */             .addComponent(this.lblWindowHeight)
/* 135 */             .addComponent(this.lblWindowHeightValue))
/* 136 */           .addContainerGap(-1, 32767)));
/* 139 */     pack();
/*     */   }
/*     */   
/*     */   void setBehaviour(String text) {
/* 144 */     this.lblBehaviourValue.setText(text);
/*     */   }
/*     */   
/*     */   void setShimejiX(int x) {
/* 149 */     this.lblShimejiXValue.setText(String.format("%d", new Object[] { Integer.valueOf(x) }));
/*     */   }
/*     */   
/*     */   void setShimejiY(int y) {
/* 154 */     this.lblShimejiYValue.setText(String.format("%d", new Object[] { Integer.valueOf(y) }));
/*     */   }
/*     */   
/*     */   void setWindowX(int x) {
/* 159 */     this.lblWindowXValue.setText(String.format("%d", new Object[] { Integer.valueOf(x) }));
/*     */   }
/*     */   
/*     */   void setWindowY(int y) {
/* 164 */     this.lblWindowYValue.setText(String.format("%d", new Object[] { Integer.valueOf(y) }));
/*     */   }
/*     */   
/*     */   void setWindowWidth(int width) {
/* 169 */     this.lblWindowWidthValue.setText(String.format("%d", new Object[] { Integer.valueOf(width) }));
/*     */   }
/*     */   
/*     */   void setWindowHeight(int height) {
/* 174 */     this.lblWindowHeightValue.setText(String.format("%d", new Object[] { Integer.valueOf(height) }));
/*     */   }
/*     */   
/*     */   public void setVisible(boolean b) {
/* 180 */     if (b) {
/* 182 */       this.lblBehaviour.setText(Main.getInstance().getLanguageBundle().getString("Behaviour"));
/* 183 */       this.lblShimejiX.setText(Main.getInstance().getLanguageBundle().getString("ShimejiX"));
/* 184 */       this.lblShimejiY.setText(Main.getInstance().getLanguageBundle().getString("ShimejiY"));
/* 185 */       this.lblWindowX.setText(Main.getInstance().getLanguageBundle().getString("WindowX"));
/* 186 */       this.lblWindowY.setText(Main.getInstance().getLanguageBundle().getString("WindowY"));
/* 187 */       this.lblWindowWidth.setText(Main.getInstance().getLanguageBundle().getString("WindowWidth"));
/* 188 */       this.lblWindowHeight.setText(Main.getInstance().getLanguageBundle().getString("WindowHeight"));
/*     */     } 
/* 190 */     super.setVisible(b);
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/DebugWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */