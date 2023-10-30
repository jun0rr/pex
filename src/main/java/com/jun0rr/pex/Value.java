/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 *
 * @author F6036477
 */
public class Value implements Expression {
  
  public static final Predicate<String> PATTERN = Pattern.compile("[0-9]+(\\.[0-9]+)?").asMatchPredicate();
  
  public static final int PRIORITY = 10;
  
  private final double value;
  
  private int priority;
  
  public Value(double value) {
    this.value = value;
    this.priority = PRIORITY;
  }
  
  public static Value of(double d) {
    return new Value(d);
  }
  
  public static Value of(String d) {
    return new Value(Double.parseDouble(d));
  }
  
  @Override
  public int priority() {
    return priority;
  }

  @Override
  public List<Expression> params() {
    return Collections.EMPTY_LIST;
  }

  @Override
  public double resolve() {
    return value;
  }

  @Override
  public String toString() {
    return String.format("%s", value);
  }

  @Override
  public Expression priority(int p) {
    this.priority = p;
    return this;
  }

  @Override
  public int arity() {
    return 0;
  }

  @Override
  public PlaceParam placeParam() {
    return PlaceParam.NONE;
  }

  @Override
  public Expression params(Expression... e) {
    throw new UnsupportedOperationException("Value does not accept parameters");
  }

  @Override
  public Expression addParam(Expression e) {
    throw new UnsupportedOperationException("Value does not accept parameters");
  }

  @Override
  public boolean isPartialToken(String s) {
    return PATTERN.test(s);
  }

  @Override
  public String token() {
    throw new UnsupportedOperationException("Value does does not have token");
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 53 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
    hash = 53 * hash + this.priority;
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
    final Value other = (Value) obj;
    if (Double.doubleToLongBits(this.value) != Double.doubleToLongBits(other.value)) {
      return false;
    }
    return this.priority == other.priority;
  }

}
