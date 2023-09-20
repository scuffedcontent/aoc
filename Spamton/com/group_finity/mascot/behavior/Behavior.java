package com.group_finity.mascot.behavior;

import com.group_finity.mascot.Mascot;
import com.group_finity.mascot.exception.CantBeAliveException;
import java.awt.event.MouseEvent;

public interface Behavior {
  void init(Mascot paramMascot) throws CantBeAliveException;
  
  void next() throws CantBeAliveException;
  
  void mousePressed(MouseEvent paramMouseEvent) throws CantBeAliveException;
  
  void mouseReleased(MouseEvent paramMouseEvent) throws CantBeAliveException;
  
  boolean isHidden();
}


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/behavior/Behavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */