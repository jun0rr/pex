/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex;

import com.jun0rr.indexed.Indexed;
import com.jun0rr.pex.ops.Ceil;
import com.jun0rr.pex.ops.Divide;
import com.jun0rr.pex.ops.Equals;
import com.jun0rr.pex.ops.Floor;
import com.jun0rr.pex.ops.Greater;
import com.jun0rr.pex.ops.GreaterEquals;
import com.jun0rr.pex.ops.Lesser;
import com.jun0rr.pex.ops.LesserEquals;
import com.jun0rr.pex.ops.Max;
import com.jun0rr.pex.ops.Attr;
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

/**
 *
 * @author F6036477
 */
public class Parser {
  
  public static final int BRACKET_PRIORITY = 15000;
  
  private final Map<Integer,Expression> stack;
  
  private final List<Expression> prestack;
  
  private final Map<String,Expression> variables;
  
  private final List<Operation> ops;
  
  private final StateEngine engine;
  
  public Parser() {
    stack = new TreeMap();
    variables = new TreeMap();
    prestack = new LinkedList<>();
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
    ops.add(new Attr(variables));
    ops.add(new Min());
    ops.add(new Random());
    ops.add(new Round());
    engine = new StateEngine(ops);
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
  
  private int descPriority(Indexed<Expression> a, Indexed<Expression> b) {
    return ascPriority(a, b) * -1;
  }
  
  private int ascPriority(Indexed<Expression> a, Indexed<Expression> b) {
    return Integer.compare(a.value().priority(), b.value().priority());
  }
  
  private int prevValidExp(int index) {
    System.out.printf("[DEBUG] prevValidExp( %d ):%n", index);
    for(int i = index-1; i >= 0; i--) {
      Expression e = prestack.get(i);
      System.out.printf("[DEBUG]   - %d: %s%n", i, e);
      if(e != Value.NaN) {
        return i;
      }
    }
    return -1;
  }
  
  private int nextValidExp(int index) {
    System.out.printf("[DEBUG] nextValidExp( %d ):%n", index);
    for(int i = index+1; i < prestack.size(); i++) {
      Expression e = prestack.get(i);
      System.out.printf("[DEBUG]   - %d: %s%n", i, e);
      if(e != Value.NaN) {
        return i;
      }
    }
    return -1;
  }
  
  public Expression parse(String s) {
    prestack.clear();
    engine.clear();
    for(int i = 0; i < s.length(); i++) {
      engine.update(s.charAt(i));
      if(i == s.length() -1) {
        engine.finish();
      }
      System.out.println("[DEBUG] " + engine);
      if(engine.isStateChanged()) {
        System.out.printf("[DEBUG] state=%s, value=%s, priority=%s%n", engine.state(), engine.value(), engine.priority());
        switch(engine.state()) {
          case VALUE:
            prestack.add(Value.of(engine.value())
                .priority(engine.priority()));
            break;
          case OPERATION:
            Operation op = ops.stream()
                .filter(o->o.token().equalsIgnoreCase(engine.value()))
                .findFirst().get();
            op.addPriority(engine.priority());
            prestack.add(op);
            break;
          case VARIABLE:
            prestack.add(new Variable(engine.value(), variables)
                .priority(engine.priority()));
            break;
          default: break;
        }
      }
    }
    System.out.println("[INFO] * Prestack:");
    prestack.forEach(e->System.out.println("[INFO]  - " + e));
    //System.out.println("Operations:");
    List<Indexed<Expression>> resolve = prestack.stream()
        .map(Indexed.indexed())
        .filter(i->i.value().isOperation())
        .filter(i->i.value().arity() > 0)
        .sorted(this::descPriority)
        //.peek(i->System.out.println("  - " + i))
        .toList();
    for(Indexed<Expression> i : resolve) {
      Expression e = i.value();
      for(int j = 0; j < e.arity(); j++) {
        int idx = 0;
        switch(e.placeParam()) {
          case LEFT:
          case BOTH:
            if(j == 0) {
              idx = prevValidExp(i.index());
            }
            else {
              idx = nextValidExp(i.index());
            }
            break;
          case RIGHT:
            idx = nextValidExp(i.index());
            break;
          default: break;
        }
        e.addParam(prestack.get(idx));
        prestack.set(idx, Value.NaN);
        System.out.println("[INFO] * Eval: " + i);
      }
    }
    return prestack.get(nextValidExp(-1));
  }
  
}
