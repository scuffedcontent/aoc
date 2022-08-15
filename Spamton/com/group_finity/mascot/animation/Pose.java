/*     */ package com.group_finity.mascot.animation;
/*     */ 
/*     */ import com.group_finity.mascot.Mascot;
/*     */ import com.group_finity.mascot.image.ImagePair;
/*     */ import com.group_finity.mascot.image.ImagePairs;
/*     */ import java.awt.Point;
/*     */ 
/*     */ public class Pose {
/*     */   private final String image;
/*     */   
/*     */   private final String rightImage;
/*     */   
/*     */   private final int dx;
/*     */   
/*     */   private final int dy;
/*     */   
/*     */   private final int duration;
/*     */   
/*     */   private final String sound;
/*     */   
/*     */   public Pose(String image) {
/*  25 */     this(image, "", 0, 0, 1);
/*     */   }
/*     */   
/*     */   public Pose(String image, int duration) {
/*  30 */     this(image, "", 0, 0, duration);
/*     */   }
/*     */   
/*     */   public Pose(String image, int dx, int dy, int duration) {
/*  35 */     this(image, "", dx, dy, duration);
/*     */   }
/*     */   
/*     */   public Pose(String image, String rightImage) {
/*  40 */     this(image, rightImage, 0, 0, 1);
/*     */   }
/*     */   
/*     */   public Pose(String image, String rightImage, int duration) {
/*  45 */     this(image, rightImage, 0, 0, duration);
/*     */   }
/*     */   
/*     */   public Pose(String image, String rightImage, int dx, int dy, int duration) {
/*  50 */     this(image, rightImage, dx, dy, duration, null);
/*     */   }
/*     */   
/*     */   public Pose(String image, String rightImage, int dx, int dy, int duration, String sound) {
/*  55 */     this.image = image;
/*  56 */     this.rightImage = rightImage;
/*  57 */     this.dx = dx;
/*  58 */     this.dy = dy;
/*  59 */     this.duration = duration;
/*  60 */     this.sound = sound;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  66 */     return "Pose (" + ((getImage() == null) ? "" : (String)getImage()) + "," + getDx() + "," + getDy() + "," + getDuration() + ", " + this.sound + ")";
/*     */   }
/*     */   
/*     */   public void next(Mascot mascot) {
/*  71 */     mascot.setAnchor(new Point((mascot.getAnchor()).x + (mascot.isLookRight() ? -getDx() : getDx()), 
/*  72 */           (mascot.getAnchor()).y + getDy()));
/*  73 */     mascot.setImage(ImagePairs.getImage(getImageName(), mascot.isLookRight()));
/*  74 */     mascot.setSound(getSoundName());
/*     */   }
/*     */   
/*     */   public int getDuration() {
/*  79 */     return this.duration;
/*     */   }
/*     */   
/*     */   public String getImageName() {
/*  84 */     return ((this.image == null) ? "" : this.image) + ((this.rightImage == null) ? "" : this.rightImage);
/*     */   }
/*     */   
/*     */   public ImagePair getImage() {
/*  89 */     return ImagePairs.getImagePair(getImageName());
/*     */   }
/*     */   
/*     */   public int getDx() {
/*  94 */     return this.dx;
/*     */   }
/*     */   
/*     */   public int getDy() {
/*  99 */     return this.dy;
/*     */   }
/*     */   
/*     */   public String getSoundName() {
/* 104 */     return this.sound;
/*     */   }
/*     */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/animation/Pose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */