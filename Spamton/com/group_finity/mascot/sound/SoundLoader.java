/*    */ package com.group_finity.mascot.sound;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.sound.sampled.AudioInputStream;
/*    */ import javax.sound.sampled.AudioSystem;
/*    */ import javax.sound.sampled.Clip;
/*    */ import javax.sound.sampled.FloatControl;
/*    */ import javax.sound.sampled.LineEvent;
/*    */ import javax.sound.sampled.LineListener;
/*    */ import javax.sound.sampled.LineUnavailableException;
/*    */ import javax.sound.sampled.UnsupportedAudioFileException;
/*    */ 
/*    */ public class SoundLoader {
/*    */   public static void load(String name, float volume) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
/* 17 */     if (Sounds.contains(name + volume))
/*    */       return; 
/* 20 */     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(SoundLoader.class.getResource(name));
/* 21 */     Clip clip = AudioSystem.getClip();
/* 22 */     clip.open(audioInputStream);
/* 23 */     ((FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
/* 24 */     clip.addLineListener(new LineListener() {
/*    */           public void update(LineEvent event) {
/* 29 */             if (event.getType() == LineEvent.Type.STOP)
/* 31 */               ((Clip)event.getLine()).stop(); 
/*    */           }
/*    */         });
/* 36 */     Sounds.load(name + volume, clip);
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/sound/SoundLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */