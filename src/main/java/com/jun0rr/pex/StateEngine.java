/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex;

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
  
  public static final int BRACKET_PRIORITY = 15000;
  
  public static final int START_PRIORITY = 1500;
  
  public static enum State {
    VARIABLE, VALUE, OPERATION, OPERATION_PART, BRACKET_OPEN, BRACKET_CLOSE, BLANK, FINISH;
  }
  
  private final List<Operation> ops;
  
  private final StringBuilder builder;
  
  private String value;
  
  private State oldState;
  
  private State newState;
  
  private int priority;
  
  public StateEngine(List<Operation> ops) {
    this.ops = Objects.requireNonNull(ops);
    this.builder = new StringBuilder();
    this.value  =  null;
    this.newState = null;
    this.oldState = null;
    this.priority = START_PRIORITY;
  }
  
  public boolean isStateChanged() {
    return newState == State.FINISH
        || (oldState != null
        && oldState != newState
        && oldState != State.BLANK
        && oldState != State.BRACKET_CLOSE
        && oldState != State.BRACKET_OPEN
        && oldState != State.OPERATION_PART);
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
    priority = START_PRIORITY;
    clearValue();
    return this;
  }
  
  private StateEngine clearValue() {
    value  = builder.toString();
    builder.delete(0, builder.length());
    return this;
  }
  
  public String builder() {
    return builder.toString();
  }
  
  public String value() {
    return value;
  }
  
  public int priority() {
    return priority;
  }
  
  public StateEngine finish() {
    return update('|');
  }
  
  public StateEngine update(char c) {
    String part = builder.toString();
    State prev = oldState;
    oldState = newState;
    if(oldState == State.BRACKET_CLOSE) {
      priority -= BRACKET_PRIORITY;
      //System.out.printf("isStateChanged() && oldState == State.BRACKET_CLOSE && priority=%d%n", priority);
    }
    if(isFinish(c)) {
      oldState = prev;
      newState = State.FINISH;
    }
    else if(isBlank(c)) {
      newState = State.BLANK;
      clearValue();
    }
    else if(isOpenBracket(c)) {
      newState = State.BRACKET_OPEN;
      priority += BRACKET_PRIORITY;
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
    else if(newState != State.FINISH) {
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
    priority--;
    //System.out.printf("[DEBUG] isStateChanged()=%s, oldState=%s%n", isStateChanged(), oldState);
    return this;
  }
  
  @Override
  public String toString() {
    return "StateEngine{" + "builder=" + builder + ", oldState=" + oldState + ", newState=" + newState + ", priority=" + priority + '}';
  }
  
}
