/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.test;

import com.jun0rr.pex.Expression;
import com.jun0rr.pex.Value;
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
import org.junit.jupiter.api.Test;

/**
 *
 * @author F6036477
 */
public class TestExpression {
  
  @Test public void test() {
    Expression ex = new Sum();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Subtract();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Multiply();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Divide();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Mod();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Pow();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new SquareRoot();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Max();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Min();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Floor();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Ceil();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Random();
    ex.params(Value.of(5.5), Value.of(2.2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Round();
    ex.params(Value.of(5.54521), Value.of(0));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Greater();
    ex.params(Value.of(5.54321), Value.of(2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Lesser();
    ex.params(Value.of(5.54321), Value.of(2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new GreaterEquals();
    ex.params(Value.of(5.54321), Value.of(2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new LesserEquals();
    ex.params(Value.of(5.54321), Value.of(2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Equals();
    ex.params(Value.of(5.54321), Value.of(2));
    System.out.printf("%s = %s%n", ex, ex.resolve());
    
    ex = new Equals();
    ex.params(Value.of(5.54321), Value.of(5.54321));
    System.out.printf("%s = %s%n", ex, ex.resolve());
  }
  
}
