/*    */ package com.group_finity.mascot;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ import java.text.FieldPosition;
/*    */ import java.text.MessageFormat;
/*    */ import java.util.Date;
/*    */ import java.util.logging.LogRecord;
/*    */ import java.util.logging.SimpleFormatter;
/*    */ 
/*    */ public class LogFormatter extends SimpleFormatter {
/* 12 */   private final Date dat = new Date();
/*    */   
/*    */   private static final String format = "{0,date} {0,time}";
/*    */   
/*    */   private MessageFormat formatter;
/*    */   
/* 16 */   private Object[] args = new Object[1];
/*    */   
/* 18 */   private String lineSeparator = System.getProperty("line.separator");
/*    */   
/*    */   public synchronized String format(LogRecord record) {
/* 27 */     StringBuffer sb = new StringBuffer();
/* 30 */     this.dat.setTime(record.getMillis());
/* 31 */     this.args[0] = this.dat;
/* 32 */     StringBuffer text = new StringBuffer();
/* 33 */     if (this.formatter == null)
/* 34 */       this.formatter = new MessageFormat("{0,date} {0,time}"); 
/* 36 */     this.formatter.format(this.args, text, (FieldPosition)null);
/* 37 */     sb.append(text);
/* 38 */     sb.append(" ");
/* 40 */     sb.append(record.getLevel().getLocalizedName());
/* 41 */     sb.append(": ");
/* 44 */     if (record.getSourceClassName() != null) {
/* 45 */       sb.append(record.getSourceClassName());
/*    */     } else {
/* 47 */       sb.append(record.getLoggerName());
/*    */     } 
/* 49 */     if (record.getSourceMethodName() != null) {
/* 50 */       sb.append(" ");
/* 51 */       sb.append(record.getSourceMethodName());
/*    */     } 
/* 53 */     sb.append(" ");
/* 55 */     String message = formatMessage(record);
/* 56 */     sb.append(message);
/* 57 */     sb.append(this.lineSeparator);
/* 58 */     if (record.getThrown() != null)
/*    */       try {
/* 60 */         StringWriter sw = new StringWriter();
/* 61 */         PrintWriter pw = new PrintWriter(sw);
/* 62 */         record.getThrown().printStackTrace(pw);
/* 63 */         pw.close();
/* 64 */         sb.append(sw.toString());
/* 65 */       } catch (Exception exception) {} 
/* 68 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/LogFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */