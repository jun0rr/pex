/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jun0rr.pex;

/**
 *
 * @author Juno
 */
public interface StateObserver {
  
  public void bracketOpen(StateEngine e);
  
  public void bracketClose(StateEngine e);
  
  public void operation(StateEngine e);
  
  public void value(StateEngine e);
  
  public void variable(StateEngine e);
  
}
