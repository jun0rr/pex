/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author F6036477
 */
public class Variable implements Expression {
  
  public static final int PRIORITY = 10000;
  
  private int priority;
  
  private final String name;
  
  private final Map<String,Expression> variables;
  
  public Variable(String token, Map<String,Expression> variables) {
    this.name = Objects.requireNonNull(token);
    this.variables = Objects.requireNonNull(variables);
    this.priority = PRIORITY;
  }
  
  @Override
  public boolean isValue() {
    return false;
  }

  @Override
  public boolean isOperation() {
    return false;
  }

  @Override
  public boolean isVariable() {
    return true;
  }
  
  @Override
  public int priority() {
    return priority;
  }

  @Override
  public Variable priority(int p) {
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
  public int arity() {
    return 1;
  }

  @Override
  public PlaceParam placeParam() {
    return PlaceParam.RIGHT;
  }
  
  public Map<String,Expression> variables() {
    return variables;
  }

  @Override
  public List<Expression> params() {
    return Collections.EMPTY_LIST;
  }

  @Override
  public Variable params(Expression... e) {
    return this;
  }

  @Override
  public Variable addParam(Expression e) {
    return this;
  }

  @Override
  public boolean isPartialToken(String s) {
    return name.contains(s);
  }

  @Override
  public String token() {
    return name;
  }

  @Override
  public double resolve() {
    Expression val = variables.get(name);
    if(val == null) {
      throw new IllegalStateException(String.format("Variable value not found (%s = ?)", name));
    }
    return val.resolve();
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + this.priority;
    hash = 97 * hash + Objects.hashCode(this.name);
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
    final Variable other = (Variable) obj;
    if (this.priority != other.priority) {
      return false;
    }
    return Objects.equals(this.name, other.name);
  }

  @Override
  public String toString() {
    return name;
  }
  
}
