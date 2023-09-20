/*     */ package com.group_finity.mascot.config;
/*     */ 
/*     */ import com.group_finity.mascot.Main;
/*     */ import com.group_finity.mascot.animation.Animation;
/*     */ import com.group_finity.mascot.animation.Pose;
/*     */ import com.group_finity.mascot.exception.AnimationInstantiationException;
/*     */ import com.group_finity.mascot.exception.VariableException;
/*     */ import com.group_finity.mascot.image.ImagePairLoader;
/*     */ import com.group_finity.mascot.script.Variable;
/*     */ import com.group_finity.mascot.sound.SoundLoader;
/*     */ import java.awt.Point;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class AnimationBuilder {
/*  26 */   private static final Logger log = Logger.getLogger(AnimationBuilder.class.getName());
/*     */   
/*     */   private final String condition;
/*     */   
/*  28 */   private String imageSet = "";
/*     */   
/*  29 */   private final List<Pose> poses = new ArrayList<Pose>();
/*     */   
/*     */   private final ResourceBundle schema;
/*     */   
/*     */   public AnimationBuilder(ResourceBundle schema, Entry animationNode, String imageSet) throws IOException {
/*  33 */     if (!imageSet.equals(""))
/*  34 */       this.imageSet = "/" + imageSet; 
/*  36 */     this.schema = schema;
/*  37 */     this.condition = (animationNode.getAttribute(schema.getString("Condition")) == null) ? "true" : animationNode.getAttribute(schema.getString("Condition"));
/*  39 */     log.log(Level.INFO, "Start Reading Animations");
/*  41 */     for (Entry frameNode : animationNode.getChildren())
/*  42 */       getPoses().add(loadPose(frameNode)); 
/*  45 */     log.log(Level.INFO, "Animations Finished Loading");
/*     */   }
/*     */   
/*     */   private Pose loadPose(Entry frameNode) throws IOException {
/*  50 */     String imageText = (frameNode.getAttribute(this.schema.getString("Image")) != null) ? (this.imageSet + frameNode.getAttribute(this.schema.getString("Image"))) : null;
/*  51 */     String imageRightText = (frameNode.getAttribute(this.schema.getString("ImageRight")) != null) ? (this.imageSet + frameNode.getAttribute(this.schema.getString("ImageRight"))) : null;
/*  52 */     String anchorText = (frameNode.getAttribute(this.schema.getString("ImageAnchor")) != null) ? frameNode.getAttribute(this.schema.getString("ImageAnchor")) : null;
/*  53 */     String moveText = frameNode.getAttribute(this.schema.getString("Velocity"));
/*  54 */     String durationText = frameNode.getAttribute(this.schema.getString("Duration"));
/*  55 */     String soundText = (frameNode.getAttribute(this.schema.getString("Sound")) != null) ? (this.imageSet + "/sound" + frameNode.getAttribute(this.schema.getString("Sound"))) : null;
/*  56 */     String volumeText = (frameNode.getAttribute(this.schema.getString("Volume")) != null) ? frameNode.getAttribute(this.schema.getString("Volume")) : "0";
/*  58 */     int scaling = Integer.parseInt(Main.getInstance().getProperties().getProperty("Scaling", "1"));
/*  60 */     if (imageText != null) {
/*  62 */       String[] anchorCoordinates = anchorText.split(",");
/*  63 */       Point anchor = new Point(Integer.parseInt(anchorCoordinates[0]), Integer.parseInt(anchorCoordinates[1]));
/*     */       try {
/*  66 */         ImagePairLoader.load(imageText, imageRightText, anchor, scaling);
/*  67 */       } catch (Exception e) {
/*  68 */         String error = imageText;
/*  69 */         if (imageRightText != null)
/*  70 */           error = error + ", " + imageRightText; 
/*  71 */         log.log(Level.SEVERE, "Failed to load image: " + error);
/*  72 */         throw new IOException(Main.getInstance().getLanguageBundle().getString("FailedLoadImageErrorMessage") + " " + error);
/*     */       } 
/*     */     } 
/*  76 */     String[] moveCoordinates = moveText.split(",");
/*  77 */     Point move = new Point(Integer.parseInt(moveCoordinates[0]) * scaling, Integer.parseInt(moveCoordinates[1]) * scaling);
/*  79 */     int duration = Integer.parseInt(durationText);
/*  81 */     if (soundText != null)
/*     */       try {
/*  85 */         SoundLoader.load(soundText, Float.parseFloat(volumeText));
/*  87 */       } catch (Exception e) {
/*  89 */         log.log(Level.SEVERE, "Failed to load sound: " + soundText);
/*  90 */         throw new IOException(Main.getInstance().getLanguageBundle().getString("FailedLoadSoundErrorMessage") + soundText);
/*     */       }  
/*  94 */     Pose pose = new Pose(imageText, imageRightText, move.x, move.y, duration, (soundText != null) ? (soundText + Float.parseFloat(volumeText)) : null);
/*  96 */     log.log(Level.INFO, "ReadPosition({0})", pose);
/*  98 */     return pose;
/*     */   }
/*     */   
/*     */   public Animation buildAnimation() throws AnimationInstantiationException {
/*     */     try {
/* 103 */       return new Animation(Variable.parse(getCondition()), getPoses().<Pose>toArray(new Pose[0]));
/* 104 */     } catch (VariableException e) {
/* 105 */       throw new AnimationInstantiationException(Main.getInstance().getLanguageBundle().getString("FailedConditionEvaluationErrorMessage"), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<Pose> getPoses() {
/* 110 */     return this.poses;
/*     */   }
/*     */   
/*     */   private String getCondition() {
/* 114 */     return this.condition;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/config/AnimationBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */