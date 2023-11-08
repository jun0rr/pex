/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.ops;

import com.jun0rr.pex.Expression;
import com.jun0rr.pex.Operation;
import com.jun0rr.pex.Value;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author F6036477
 */
public class Attr extends Operation {
  
  public static final int PRIORITY = 3000;
  
  private final Map<String,Expression> variables;
  
  public Attr(Map<String,Expression> vars) {
    super("=", PRIORITY, PlaceParam.BOTH, 2, e->{
      Attr attr = (Attr) e;
      Expression name = attr.params().get(0);
      Expression value;
      if(attr.params().size() > 1) {
        value = attr.params().get(1);
      }
      else if(attr.variables.containsKey(name.token())) {
        value = attr.variables.get(name.token());
      }
      else {
        value = Value.of(0);
      }
      attr.variables.put(name.token(), value);
      return value.resolve();
    });
    this.variables = Objects.requireNonNull(vars);
  }
  
  @Override
  public Attr clone() {
    return new Attr(variables);
  }
  
}
