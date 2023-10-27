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
public class Min extends Operation {
  
  public static final int PRIORITY = 7000;
  
  public Min() {
    super("min", PRIORITY, PlaceParam.RIGHT, 2, p->{
      double a = p.get(0).resolve();
      double b = p.get(1).resolve();
      return Math.min(a, b);
    });
  }

  @Override
  public String toString() {
    return String.format("min( %s, %s )", params().get(0), params().get(1));
  }
  
}