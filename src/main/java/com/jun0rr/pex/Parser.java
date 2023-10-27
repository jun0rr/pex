/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex;

import com.jun0rr.pex.ops.Ceil;
import com.jun0rr.pex.ops.Divide;
import com.jun0rr.pex.ops.Equals;
import com.jun0rr.pex.ops.Floor;
import com.jun0rr.pex.ops.Greater;
import com.jun0rr.pex.ops.GreaterEquals;
import com.jun0rr.pex.ops.Lesser;
import com.jun0rr.pex.ops.LesserEquals;
import com.jun0rr.pex.ops.Max;
import com.jun0rr.pex.ops.Min;
import com.jun0rr.pex.ops.Mod;
import com.jun0rr.pex.ops.Multiply;
import com.jun0rr.pex.ops.Pow;
import com.jun0rr.pex.ops.Random;
import com.jun0rr.pex.ops.Round;
import com.jun0rr.pex.ops.SquareRoot;
import com.jun0rr.pex.ops.Subtract;
import com.jun0rr.pex.ops.Sum;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 *
 * @author F6036477
 */
public class Parser {
  
  public static final int BRACKET_PRIORITY = 15000;
  
  public static final Predicate<String> VALUE_PART = Pattern.compile("[0-9.]").asMatchPredicate();
  
  private final Map<Integer,Expression> stack;
  
  private final Map<String,Expression> variables;
  
  private final List<Operation> ops;
  
  public Parser() {
    stack = new TreeMap();
    variables = new TreeMap();
    ops = new LinkedList();
    ops.add(new Sum());
    ops.add(new Subtract());
    ops.add(new Multiply());
    ops.add(new Divide());
    ops.add(new Pow());
    ops.add(new SquareRoot());
    ops.add(new Mod());
    ops.add(new Ceil());
    ops.add(new Floor());
    ops.add(new Equals());
    ops.add(new Greater());
    ops.add(new GreaterEquals());
    ops.add(new Lesser());
    ops.add(new LesserEquals());
    ops.add(new Max());
    ops.add(new Min());
    ops.add(new Random());
    ops.add(new Round());
  }
  
  public Parser addOperation(Operation o) {
    ops.add(Objects.requireNonNull(o));
    return this;
  }
  
  public List<Operation> operations() {
    return ops;
  }
  
  public Map<String,Expression> variables() {
    return variables;
  }
  
  public Parser addVariable(String name, Expression value) {
    variables.put(Objects.requireNonNull(name), Objects.requireNonNull(value));
    return this;
  }
  
  public Parser addVariable(String name, double value) {
    variables.put(Objects.requireNonNull(name), Value.of(value));
    return this;
  }
  
  public boolean isValuePart(String s) {
    return VALUE_PART.test(s);
  }
  
  public boolean isOperationPart(String s) {
    return ops.stream().anyMatch(o->o.isPartialToken(s));
  }
  
  public boolean isVariablePart(String s) {
    return variables.keySet().stream().anyMatch(v->v.contains(s));
  }
  
  public boolean isValuePart(char c) {
    return isValuePart(String.valueOf(c));
  }
  
  public boolean isOperationPart(char c) {
    return isOperationPart(String.valueOf(c));
  }
  
  public boolean isVariablePart(char c) {
    return isVariablePart(String.valueOf(c));
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
  
  public void parseStack(String s) {
    //(5-3)*2-2
    //1+1
    stack.clear();
    int priority = 0;
    boolean isvalue = false;
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      boolean transition = isvalue != isValuePart(c);
      transition = transition || isOpenBracket(c);
      tr
      if(isOpenBracket(c)) {
        priority += BRACKET_PRIORITY;
      }
      else if(isCloseBracket(c)) {
        priority -= BRACKET_PRIORITY;
      }
      if(!isBlank(c) && !isOpenBracket(c) && !isCloseBracket(c)) {
        sb.append(c);
      }
    }
  }
  
}
