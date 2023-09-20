/*    */ package com.group_finity.mascot.win.jna;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.win32.StdCallLibrary;
/*    */ 
/*    */ public interface Gdi32 extends StdCallLibrary {
/* 14 */   public static final Gdi32 INSTANCE = (Gdi32)Native.loadLibrary("Gdi32", Gdi32.class);
/*    */   
/*    */   public static final int DIB_RGB_COLORS = 0;
/*    */   
/*    */   Pointer CreateCompatibleDC(Pointer paramPointer);
/*    */   
/*    */   Pointer SelectObject(Pointer paramPointer1, Pointer paramPointer2);
/*    */   
/*    */   int DeleteDC(Pointer paramPointer);
/*    */   
/*    */   Pointer CreateDIBSection(Pointer paramPointer1, BITMAPINFOHEADER paramBITMAPINFOHEADER, int paramInt1, Pointer paramPointer2, Pointer paramPointer3, int paramInt2);
/*    */   
/*    */   int GetObjectW(Pointer paramPointer, int paramInt, BITMAP paramBITMAP);
/*    */   
/*    */   int DeleteObject(Pointer paramPointer);
/*    */   
/*    */   Pointer CreateRectRgn(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*    */   
/*    */   int GetRgnBox(Pointer paramPointer, RECT paramRECT);
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/jna/Gdi32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */