/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.main;

import java.util.Objects;

/**
 *
 * @author F6036477
 */
public class StringColumn {
  
  public static enum Align {
    LEFT, CENTER, RIGHT;
  }
  
  private final int lenght;
  
  private final String value;
  
  private final Align align;
  
  private final char colchar;
  
  public StringColumn(String value, int lenght, Align align, char colsep) {
    this.value = Objects.requireNonNull(value);
    this.lenght = lenght;
    this.align = Objects.requireNonNull(align);
    this.colchar = colsep;
  }
  
  public String value() {
    return value;
  }
  
  public int lenght() {
    
  }
  
}
