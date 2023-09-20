/*    */ package com.group_finity.mascot.imagesetchooser;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.SystemColor;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.ListCellRenderer;
/*    */ 
/*    */ public class ShimejiList extends JList {
/*    */   public ShimejiList() {
/* 13 */     setCellRenderer(new CustomCellRenderer());
/*    */   }
/*    */   
/*    */   class CustomCellRenderer implements ListCellRenderer {
/*    */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 19 */       if (value instanceof ImageSetChooserPanel) {
/* 21 */         ImageSetChooserPanel component = (ImageSetChooserPanel)value;
/* 22 */         component.setForeground(Color.white);
/* 23 */         component.setBackground(isSelected ? SystemColor.controlHighlight : Color.white);
/* 24 */         component.setCheckbox(isSelected);
/* 25 */         return component;
/*    */       } 
/* 29 */       return new JLabel("???");
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/imagesetchooser/ShimejiList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */