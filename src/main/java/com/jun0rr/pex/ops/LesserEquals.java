/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.ops;

import com.jun0rr.pex.Operation;

/**
 *
 * @author F6036477
 */
public class LesserEquals extends Operation {
  
  public static final int PRIORITY = 7000;
  
  public LesserEquals() {
    super("<=", PRIORITY, PlaceParam.BOTH, 2, e->{
      double a = e.params().get(0).resolve();
      double b = e.params().get(1).resolve();
      return a <= b ? 1.0 : 0.0;
    });
  }

  @Override
  public String toString() {
    String fmt = "( %s <= %s )";
    String a = params().size() > 0 ? params().get(0).toString() : "?";
    String b = params().size() > 1 ? params().get(1).toString() : "?";
    return String.format(fmt, a, b);
  }
  
}
