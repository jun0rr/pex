/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jun0rr.pex;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author F6036477
 */
public class Operation implements Expression, Cloneable {
  
  private final List<Expression> params;
  
  private final String token;
  
  private final int arity;
  
  private PlaceParam placeparam;
  
  private final Function<Expression,Double> function;
  
  private final int initPriority;
  
  private int priority;
  
  public Operation(String token, int priority, PlaceParam pp, int arity, Function<Expression,Double> fn) {
    this.token = Objects.requireNonNull(token);
    this.function = Objects.requireNonNull(fn);
    this.placeparam = Objects.requireNonNull(pp);
    this.params = new LinkedList<>();
    this.initPriority = priority;
    this.priority = priority;
    this.arity = arity;
  }
  
  @Override
  public Operation clone() {
    return new Operation(token, initPriority, placeparam, arity, function);
  }
  
  @Override
  public boolean isValue() {
    return false;
  }

  @Override
  public boolean isOperation() {
    return true;
  }

  @Override
  public boolean isVariable() {
    return false;
  }
  
  @Override
  public int priority() {
    return priority;
  }
  
  @Override
  public Operation priority(int p) {
    this.priority = p;
    return this;
  }
  
  @Override
  public Expression addPriority(int amount) {
    priority += amount;
    return this;
  }
  
  @Override
  public Expression subPriority(int amount) {
    priority -= amount;
    return this;
  }
  
  @Override
  public boolean isPartialToken(String s) {
    return token.startsWith(s);
  }
  
  @Override
  public String token() {
    return token;
  }
  
  @Override
  public double resolve() {
    return function.apply(this);
  }

  @Override
  public int arity() {
    return arity;
  }

  @Override
  public PlaceParam placeParam() {
    return placeparam;
  }

  @Override
  public List<Expression> params() {
    return params;
  }

  @Override
  public Expression params(Expression... e) {
    params.clear();
    params.addAll(List.of(e));
    return this;
  }

  @Override
  public Expression addParam(Expression e) {
    params.add(Objects.requireNonNull(e));
    return this;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 53 * hash + Objects.hashCode(this.params);
    hash = 53 * hash + Objects.hashCode(this.token);
    hash = 53 * hash + this.arity;
    hash = 53 * hash + Objects.hashCode(this.placeparam);
    hash = 53 * hash + this.initPriority;
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
    final Operation other = (Operation) obj;
    if (this.arity != other.arity) {
      return false;
    }
    if (this.initPriority != other.initPriority) {
      return false;
    }
    if (this.priority != other.priority) {
      return false;
    }
    if (!Objects.equals(this.token, other.token)) {
      return false;
    }
    if (!Objects.equals(this.params, other.params)) {
      return false;
    }
    return this.placeparam == other.placeparam;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("( ");
    switch(placeparam) {
      case BOTH:
        sb.append(params().size() > 0 ? params().get(0).toString() : "?");
        sb.append(" ").append(token).append(" ");
        for(int i = 1; i < arity; i++) {
          sb.append(params.size() > i ? params.get(i).toString() : "?")
              .append(" ");
        }
        return sb.append(")").toString();
      case LEFT:
        for(int i = 0; i < arity; i++) {
          sb.append(params.size() > i ? params.get(i).toString() : "?")
              .append(" ");
        }
        return sb.append(token).append(" )").toString();
      case NONE:
        return sb.append(token).append(" )").toString();
      default:
        sb.append(token).append(" ");
        for(int i = 0; i < arity; i++) {
          sb.append(params.size() > i ? params.get(i).toString() : "?")
              .append(" ");
        }
        return sb.append(")").toString();
    }
  }
  
}
