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
public class Max extends Operation {
  
  public static final int PRIORITY = 7000;
  
  public Max() {
    super("max", PRIORITY, PlaceParam.RIGHT, 2, e->{
      double a = e.params().get(0).resolve();
      double b = e.params().get(1).resolve();
      //System.out.printf("[DEBUG] Max: a=%s, b=%s%n", a, b);
      return Math.max(a, b);
    });
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder("max( ");
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
