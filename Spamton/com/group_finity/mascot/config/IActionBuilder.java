package com.group_finity.mascot.config;

import com.group_finity.mascot.action.Action;
import com.group_finity.mascot.exception.ActionInstantiationException;
import com.group_finity.mascot.exception.ConfigurationException;
import java.util.Map;

public interface IActionBuilder {
  void validate() throws ConfigurationException;
  
  Action buildAction(Map<String, String> paramMap) throws ActionInstantiationException;
}


/* Location:              /home/hakku/code/spamton/Shimeji-ee.jar!/com/group_finity/mascot/config/IActionBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */