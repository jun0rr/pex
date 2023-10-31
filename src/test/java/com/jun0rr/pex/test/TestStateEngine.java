/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.test;

import com.jun0rr.pex.Operation;
import com.jun0rr.pex.StateEngine;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 *
 * @author F6036477
 */
public class TestStateEngine {
  
  private static final List<Operation> ops = initOps();
  
  private static List<Operation> initOps() {
    List<Operation> ops = new LinkedList<>();
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
    return ops;
  }
  
  
  @Test public void test() {
    StateEngine eng = new StateEngine(ops);
    String sx = "10+10 - max 1 5 * x";
    for(int i = 0; i < sx.length(); i++) {
      eng.update(sx.charAt(i));
      //System.out.printf("-> %s%n", eng.toString());
      if(eng.isStateChanged()) {
        System.out.printf(">>> state=%s, value=%s%n", eng.state(), eng.value());
      }
    }
    eng.finish();
    if(eng.isStateChanged()) {
      System.out.printf(">>> state=%s, value=%s%n", eng.state(), eng.value());
    }
  }
  
}
