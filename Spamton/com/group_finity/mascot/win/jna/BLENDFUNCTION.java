package com.group_finity.mascot.win.jna;

import com.sun.jna.Structure;

public class BLENDFUNCTION extends Structure {
  public static final byte AC_SRC_OVER = 0;
  
  public static final byte AC_SRC_ALPHA = 1;
  
  public byte BlendOp;
  
  public byte BlendFlags;
  
  public byte SourceConstantAlpha;
  
  public byte AlphaFormat;
}


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/jna/BLENDFUNCTION.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */