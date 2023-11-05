/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jun0rr.pex;

import java.util.List;

/**
 *
 * @author F6036477
 */
public interface Expression extends Comparable<Expression> {
  
  public int priority();
  
  public Expression priority(int p);
  
  public Expression addPriority(int amount);
  
  public Expression subPriority(int amount);
  
  @Override
  public default int compareTo(Expression e) {
    return Integer.compare(priority(), e.priority());
  }
  
  public boolean isValue();
  
  public boolean isOperation();
  
  public boolean isVariable();
  
  public int arity();
  
  public PlaceParam placeParam();
  
  public List<Expression> params();
  
  public Expression params(Expression... e);
  
  public Expression addParam(Expression e);
  
  public boolean isPartialToken(String s);
  
  public String token();
  
  public double resolve();
  
  public static enum PlaceParam {
    LEFT, RIGHT, BOTH, NONE;
  }
  
}
