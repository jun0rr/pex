/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 *
 * @author F6036477
 */
public class StateEngine {
  
  public static final Predicate<String> VALUE_CHAR = Pattern.compile("[0-9.]").asMatchPredicate();
  
  public static final Predicate<String> VALUE_PART = Pattern.compile("[0-9]+\\.?([0-9]+)?").asMatchPredicate();
  
  public static enum State {
    VARIABLE, VALUE, OPERATION, OPERATION_PART, BRACKET_OPEN, BRACKET_CLOSE, BLANK;
  }
  
  private final List<Operation> ops;
  
  private final List<StateObserver> observers;
  
  private final StringBuilder builder;
  
  private String value;
  
  private State oldState;
  
  private State newState;
  
  public StateEngine(List<Operation> ops) {
    this.ops = Objects.requireNonNull(ops);
    this.observers = new LinkedList<>();
    this.builder = new StringBuilder();
    this.value  =  null;
    this.newState = null;
    this.oldState = null;
  }
  
  public StateEngine addObserver(StateObserver o) {
    this.observers.add(Objects.requireNonNull(o));
    return this;
  }
  
  public List<StateObserver> observers() {
    return observers;
  }
  
  public boolean isStateChanged() {
    return oldState != null
        && oldState != newState
        && oldState != State.BLANK
        && oldState != State.OPERATION_PART;
  }
  
  public boolean isValuePart(String s) {
    //System.out.printf("-> isValuePart( %s ): %s%n", s, VALUE_PART.test(s));
    return s.length() > 0 && VALUE_PART.test(s);
  }
  
  public boolean isValuePart(char c) {
    //System.out.printf("-> isValuePart( %s ): %s%n", c, VALUE_CHAR.test(String.valueOf(c)));
    return VALUE_CHAR.test(String.valueOf(c));
  }
  
  public boolean isCompleteOperation(String s) {
    //System.out.printf("-> isCompleteOperation( %s ): %s%n", s, s.length() > 0 && ops.stream().anyMatch(o->o.token().equalsIgnoreCase(s)));
    return s.length() > 0 && ops.stream().anyMatch(o->o.token().equalsIgnoreCase(s));
  }
  
  public boolean isOperationPart(String s) {
    //System.out.printf("-> isOperationPart( %s ): %s%n", s, s.length() > 0 && ops.stream().anyMatch(o->o.isPartialToken(s)));
    return s.length() > 0 && ops.stream().anyMatch(o->o.isPartialToken(s));
  }
  
  public boolean isOperationPart(char c) {
    return isOperationPart(String.valueOf(c));
  }
  
  public boolean isOpenBracket(char c) {
    return '(' == c;
  }
  
  public boolean isCloseBracket(char c) {
    return ')' == c;
  }
  
  public boolean isBlank(char c) {
    return ' ' == c;
  }
  
  public boolean isFinish(char c) {
    return '|' == c;
  }
  
  public State newState() {
    return newState;
  }
  
  public State state() {
    return oldState;
  }
  
  public StateEngine clear() {
    newState = oldState = null;
    clearValue();
    return this;
  }
  
  private StateEngine clearValue() {
    value = builder.toString();
    builder.delete(0, builder.length());
    return this;
  }
  
  public String builder() {
    return builder.toString();
  }
  
  public String value() {
    return value;
  }
  
  public StateEngine finish() {
    return update(' ');
  }
  
  public StateEngine update(char c) {
    String part = builder.toString();
    oldState = newState;
    if(isBlank(c)) {
      newState = State.BLANK;
      clearValue();
    }
    else if(isOpenBracket(c)) {
      newState = State.BRACKET_OPEN;
      clearValue();
    }
    else if(isCloseBracket(c)) {
      newState = State.BRACKET_CLOSE;
      clearValue();
    }
    else if(isValuePart(part + c)) {
      newState = State.VALUE;
      builder.append(c);
    }
    else if(isOperationPart(part + c)) {
      newState = State.OPERATION_PART;
      builder.append(c);
      if(isCompleteOperation(builder.toString())) {
        newState = State.OPERATION;
      }
    }
    else {
      if(!part.isEmpty() && oldState == State.OPERATION_PART) {
        oldState = State.VARIABLE;
      }
      if(isValuePart(c)) {
        clearValue();
        builder.append(c);
        newState = State.VALUE;
      }
      else if(isOperationPart(c)) {
        clearValue();
        builder.append(c);
        newState = State.OPERATION_PART;
        if(isCompleteOperation(builder.toString())) {
          newState = State.OPERATION;
        }
      }
      else {
        if(oldState != State.VARIABLE) {
          clearValue();
        }
        builder.append(c);
        newState = State.VARIABLE;
      }
    }
    System.out.println(this);
    if(isStateChanged()) {
      switch(state()) {
        case BRACKET_OPEN:
          observers.forEach(o->o.bracketOpen(this));
          break;
        case BRACKET_CLOSE:
          observers.forEach(o->o.bracketClose(this));
          break;
        case OPERATION:
          observers.forEach(o->o.operation(this));
          break;
        case VALUE:
          observers.forEach(o->o.value(this));
          break;
        case VARIABLE:
          observers.forEach(o->o.variable(this));
          break;
        default: break;
      }
    }
    return this;
  }
  
  @Override
  public String toString() {
    return "StateEngine{isStateChanged=" + isStateChanged() + ", builder=" + builder + ", value=" + value + ", oldState=" + oldState + ", newState=" + newState + '}';
  }
  
}
