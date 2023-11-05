/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jun0rr.pex;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author F6036477
 */
public class Operation implements Expression {
  
  private final List<Expression> params;
  
  private final String token;
  
  private final int arity;
  
  private PlaceParam placeparam;
  
  private final Function<Expression,Double> function;
  
  private int priority;
  
  public Operation(String token, int priority, PlaceParam pp, int arity, Function<Expression,Double> fn) {
    this.token = Objects.requireNonNull(token);
    this.function = Objects.requireNonNull(fn);
    this.placeparam = Objects.requireNonNull(pp);
    this.params = new LinkedList<>();
    this.priority = priority;
    this.arity = arity;
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
  
}
