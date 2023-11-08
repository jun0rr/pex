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
  
  private final char colsep;
  
  private final String formatted;
  
  public StringColumn(String value, int lenght, Align align, char colsep) {
    this.value = Objects.requireNonNull(value);
    this.lenght = lenght;
    this.align = Objects.requireNonNull(align);
    this.colsep = colsep;
    StringPad pad = StringPad.of(String.format(" %s ", 
        value.length() > lenght 
            ? value.substring(0, lenght -2) : value)
    );
    this.formatted = switch(align) {
      case LEFT -> pad.rpad(" ", lenght).concat(String.valueOf(colsep));
      case CENTER -> pad.cpad(" ", lenght).concat(String.valueOf(colsep));
      case RIGHT -> pad.lpad(" ", lenght).concat(String.valueOf(colsep));
      default -> throw new IllegalStateException("Bad column alignment: " + align);
    };
  }
  
  public String value() {
    return value;
  }
  
  public int lenght() {
    return lenght;
  }
  
  public Align align() {
    return align;
  }
  
  public char colSeparator() {
    return colsep;
  }
  
  public String format() {
    return formatted;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 83 * hash + this.lenght;
    hash = 83 * hash + Objects.hashCode(this.value);
    hash = 83 * hash + Objects.hashCode(this.align);
    hash = 83 * hash + this.colsep;
    hash = 83 * hash + Objects.hashCode(this.formatted);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StringColumn other = (StringColumn) obj;
    if (this.lenght != other.lenght) {
      return false;
    }
    if (this.colsep != other.colsep) {
      return false;
    }
    if (!Objects.equals(this.value, other.value)) {
      return false;
    }
    if (!Objects.equals(this.formatted, other.formatted)) {
      return false;
    }
    return this.align == other.align;
  }
  
  @Override
  public String toString() {
    return formatted;
  }
  
}
