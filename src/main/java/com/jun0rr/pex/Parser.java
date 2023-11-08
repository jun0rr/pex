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
public class Parser implements StateObserver {
  
  public static final int BRACKET_PRIORITY = 15000;
  
  public static final int START_PRIORITY = 1500;
  
  private final List<Expression> stack;
  
  private final Map<String,Expression> variables;
  
  private final List<Operation> ops;
  
  private final StateEngine engine;
  
  private int priority;
  
  public Parser() {
    variables = new TreeMap();
    stack = new LinkedList<>();
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
    this.priority = START_PRIORITY;
    engine.addObserver(this);
    variables.put("pi", Value.of(3.14159265359));
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
  
  public Parser setVariable(String name, Expression value) {
    variables.put(Objects.requireNonNull(name), Objects.requireNonNull(value));
    return this;
  }
  
  public Parser setVariable(String name, double value) {
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
    for(int i = index-1; i >= 0; i--) {
      Expression e = stack.get(i);
      if(e != Value.NaN) {
        return i;
      }
    }
    return -1;
  }
  
  private int nextValidExp(int index) {
    for(int i = index+1; i < stack.size(); i++) {
      Expression e = stack.get(i);
      if(e != Value.NaN) {
        return i;
      }
    }
    return -1;
  }
  
  public Expression parse(String s) {
    stack.clear();
    engine.clear();
    System.out.printf("  -> Stack.size=%d%n", stack.size());
    priority = START_PRIORITY;
    for(int i = 0; i < s.length(); i++) {
      engine.update(s.charAt(i));
      //System.out.println(engine.update(s.charAt(i)));
      priority--;
    }
    engine.finish();
    System.out.printf("  -> Stack.size=%d%n", stack.size());
    stack.forEach(e->System.out.printf("  -> %s\t%d%n", e, e.priority()));
    List<Indexed<Expression>> resolve = stack.stream()
        .map(Indexed.indexed())
        .filter(i->i.value().isOperation())
        .filter(i->i.value().arity() > 0)
        .sorted(this::descPriority)
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
        e.addParam(stack.get(idx));
        stack.set(idx, Value.NaN);
      }
    }
    return stack.get(nextValidExp(-1));
  }

  @Override
  public void bracketOpen(StateEngine e) {
    System.out.println(">>> bracketOpen: " + e);
    priority += BRACKET_PRIORITY;
  }

  @Override
  public void bracketClose(StateEngine e) {
    System.out.println(">>> bracketClose: " + e);
    priority -= BRACKET_PRIORITY;
  }

  @Override
  public void operation(StateEngine e) {
    System.out.println(">>> operation: " + e);
    Operation op = ops.stream()
        .filter(o->o.token().equalsIgnoreCase(engine.value()))
        .findFirst().get().reset();
    op.addPriority(priority);
    stack.add(op);
  }

  @Override
  public void value(StateEngine e) {
    System.out.println(">>> value: " + e);
    stack.add(Value.of(engine.value()).priority(priority));
  }

  @Override
  public void variable(StateEngine e) {
    System.out.println(">>> variable: " + e);
    stack.add(new Variable(
        engine.value(), variables
    ).priority(priority));
  }
  
}
