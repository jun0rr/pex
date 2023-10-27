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
public class Floor extends Operation {
  
  public static final int PRIORITY = 7000;
  
  public Floor() {
    super("floor", PRIORITY, PlaceParam.RIGHT, 1, p->{
      double a = p.get(0).resolve();
      return Math.floor(a);
    });
  }

  @Override
  public String toString() {
    return String.format("floor( %s )", params().get(0));
  }
  
}
