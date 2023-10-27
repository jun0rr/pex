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
public class Multiply extends Operation {
  
  public static final int PRIORITY = 3000;
  
  public Multiply() {
    super("*", PRIORITY, PlaceParam.BOTH, 2, p->{
      double a = p.get(0).resolve();
      double b = p.get(1).resolve();
      return a * b;
    });
  }

  @Override
  public String toString() {
    return String.format("( %s * %s )", params().get(0), params().get(1));
  }
  
}
