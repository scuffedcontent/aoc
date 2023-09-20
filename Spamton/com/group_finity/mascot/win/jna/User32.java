/*    */ package com.group_finity.mascot.win.jna;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.win32.StdCallLibrary;
/*    */ 
/*    */ public interface User32 extends StdCallLibrary {
/* 14 */   public static final User32 INSTANCE = (User32)Native.loadLibrary("User32", User32.class);
/*    */   
/*    */   public static final int SM_CXSCREEN = 0;
/*    */   
/*    */   public static final int SM_CYSCREEN = 1;
/*    */   
/*    */   public static final int SPI_GETWORKAREA = 48;
/*    */   
/*    */   public static final int GW_HWNDFIRST = 0;
/*    */   
/*    */   public static final int GW_HWNDNEXT = 2;
/*    */   
/*    */   public static final int GWL_STYLE = -16;
/*    */   
/*    */   public static final int GWL_EXSTYLE = -20;
/*    */   
/*    */   public static final int WS_MAXIMIZE = 16777216;
/*    */   
/*    */   public static final int WS_EX_LAYERED = 524288;
/*    */   
/*    */   public static final int ERROR = 0;
/*    */   
/*    */   public static final int ULW_ALPHA = 2;
/*    */   
/*    */   int GetSystemMetrics(int paramInt);
/*    */   
/*    */   int SystemParametersInfoW(int paramInt1, int paramInt2, RECT paramRECT, int paramInt3);
/*    */   
/*    */   Pointer GetForegroundWindow();
/*    */   
/*    */   Pointer GetWindow(Pointer paramPointer, int paramInt);
/*    */   
/*    */   int IsWindow(Pointer paramPointer);
/*    */   
/*    */   int IsWindowVisible(Pointer paramPointer);
/*    */   
/*    */   int GetWindowLongW(Pointer paramPointer, int paramInt);
/*    */   
/*    */   int SetWindowLongW(Pointer paramPointer, int paramInt1, int paramInt2);
/*    */   
/*    */   int IsIconic(Pointer paramPointer);
/*    */   
/*    */   int GetWindowTextW(Pointer paramPointer, char[] paramArrayOfchar, int paramInt);
/*    */   
/*    */   int GetClassNameW(Pointer paramPointer, char[] paramArrayOfchar, int paramInt);
/*    */   
/*    */   int GetWindowRect(Pointer paramPointer, RECT paramRECT);
/*    */   
/*    */   int GetWindowRgn(Pointer paramPointer1, Pointer paramPointer2);
/*    */   
/*    */   int MoveWindow(Pointer paramPointer, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
/*    */   
/*    */   int BringWindowToTop(Pointer paramPointer);
/*    */   
/*    */   Pointer GetDC(Pointer paramPointer);
/*    */   
/*    */   int ReleaseDC(Pointer paramPointer1, Pointer paramPointer2);
/*    */   
/*    */   int UpdateLayeredWindow(Pointer paramPointer1, Pointer paramPointer2, POINT paramPOINT1, SIZE paramSIZE, Pointer paramPointer3, POINT paramPOINT2, int paramInt1, BLENDFUNCTION paramBLENDFUNCTION, int paramInt2);
/*    */   
/*    */   boolean EnumWindows(WNDENUMPROC paramWNDENUMPROC, Pointer paramPointer);
/*    */   
/*    */   public static interface WNDENUMPROC extends StdCallLibrary.StdCallCallback {
/*    */     boolean callback(Pointer param1Pointer1, Pointer param1Pointer2);
/*    */   }
/*    */ }


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/win/jna/User32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */