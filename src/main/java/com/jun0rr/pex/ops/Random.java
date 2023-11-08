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
public class Random extends Operation {
  
  public static final int PRIORITY = 7000;
  
  public Random() {
    super("rdm", PRIORITY, PlaceParam.NONE, 0, e->{
      return Math.random();
    });
  }

}
