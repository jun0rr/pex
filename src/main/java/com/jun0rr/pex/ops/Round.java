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
public class Round extends Operation {
  
  public static final int PRIORITY = 7000;
  
  public Round() {
    super("round", PRIORITY, PlaceParam.RIGHT, 2, p->{
      double a = p.get(0).resolve();
      double b = p.get(1).resolve();
      double c = Math.pow(10, b);
      long lv = (long) (a * c);
      double dv = a * c;
      return (dv - lv) >= 0.5 ? (lv+1) / c : lv / c;
    });
  }

  @Override
  public String toString() {
    return String.format("round( %s, %s )", params().get(0), params().get(1));
  }
  
}
