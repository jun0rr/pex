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
    super("round", PRIORITY, PlaceParam.RIGHT, 2, e->{
      double a = e.params().get(0).resolve();
      double b = e.params().get(1).resolve();
      double c = Math.pow(10, b);
      long lv = (long) (a * c);
      double dv = a * c;
      return (dv - lv) >= 0.5 ? (lv+1) / c : lv / c;
    });
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder("round( ");
    for(int i = 0; i < params().size(); i++) {
      str.append(params().get(i)).append(", ");
    }
    if(str.toString().endsWith(", ")) {
      str.delete(str.length() -2, str.length() -1);
    }
    str.append(")");
    return str.toString();
  }
  
}
