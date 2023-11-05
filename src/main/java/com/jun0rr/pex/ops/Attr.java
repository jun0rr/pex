/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.ops;

import com.jun0rr.pex.Expression;
import com.jun0rr.pex.Operation;
import com.jun0rr.pex.Variable;
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
      Expression value = attr.variables.get(name.token());
      if(value == null) {
        value = attr.params().get(1);
      }
      attr.variables.put(name.token(), value);
      return value.resolve();
    });
    this.variables = Objects.requireNonNull(vars);
  }

  @Override
  public String toString() {
    String fmt = "( %s = %s )";
    String name = params().size() > 0 ? params().get(0).token() : "?";
    Expression e = variables.get(name);
    if(e == null && params().size() > 1) {
      e = params().get(1);
    }
    String value = e != null ? e.toString() : "?";
    return String.format(fmt, name, value);
  }
  
}
