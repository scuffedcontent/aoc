package com.group_finity.mascot.action;

import com.group_finity.mascot.Mascot;
import com.group_finity.mascot.exception.LostGroundException;
import com.group_finity.mascot.exception.VariableException;

public interface Action {
  void init(Mascot paramMascot) throws VariableException;
  
  boolean hasNext() throws VariableException;
  
  void next() throws LostGroundException, VariableException;
}


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/action/Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */