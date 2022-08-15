package com.group_finity.mascot.win.jna;

import com.sun.jna.Structure;

public class BITMAPINFOHEADER extends Structure {
  public int biSize;
  
  public int biWidth;
  
  public int biHeight;
  
  public short biPlanes;
  
  public short biBitCount;
  
  public int biCompression;
  
  public int biSizeImage;
  
  public int biXPelsPerMeter;
  
  public int biYPelsPerMeter;
  
  public int biClrUsed;
  
  public int biClrImportant;
}


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/jna/BITMAPINFOHEADER.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */