/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex;

import com.jun0rr.pex.StateEngine.State;
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

/**
 *
 * @author F6036477
 */
public class Parser {
  
  public static final int BRACKET_PRIORITY = 15000;
  
  private final Map<Integer,Expression> stack;
  
  private final Map<String,Expression> variables;
  
  private final List<Operation> ops;
  
  private final StateEngine engine;
  
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
  
  public void parse(String s) {
    //(5-3)*2-2
    //1+1
    stack.clear();
    engine.clear();
    int priority = 0;
    int increase = 0;
    for(int i = 0; i < s.length(); i++) {
      engine.update(s.charAt(i));
      if(State.BRACKET_OPEN == engine.newState()) {
        increase = BRACKET_PRIORITY;
        priority += increase;
      }
      else if(State.BRACKET_CLOSE == engine.newState()) {
        priority -= increase;
        increase = 0;
      }
      if(engine.isStateChanged()) {
        switch(engine.state()) {
          case VALUE:
            priority++;
            stack.put(priority, Value.of(engine.value()));
            break;
          case OPERATION:
            Operation op = ops.stream()
                .filter(o->o.token().equalsIgnoreCase(engine.value()))
                .findFirst().get();
            increase = op.priority();
            priority += increase;
        }
      }
    }
  }
  
}
