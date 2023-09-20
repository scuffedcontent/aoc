package com.group_finity.mascot.win.jna;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class BITMAP extends Structure {
  public int bmType;
  
  public int bmWidth;
  
  public int bmHeight;
  
  public int bmWidthBytes;
  
  public short bmPlanes;
  
  public short bmBitsPixel;
  
  public Pointer bmBits;
}


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/jna/BITMAP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */