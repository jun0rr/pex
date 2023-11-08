/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.main;

import com.jun0rr.pex.Expression;
import com.jun0rr.pex.Parser;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Juno
 */
public class Main {
  
  public static void main(String... args) {
    Parser parser = new Parser();
    Scanner scanner = new Scanner(System.in);
    System.out.printf("+------------------------+%n");
    System.out.printf("|        JPex 1.0        |%n");
    System.out.printf("| Math Expression Parser |%n");
    System.out.printf("+------------------------+%n%n");
    if(args != null && args.length > 0) {
      List.of(args).stream().map(parser::parse).forEach(e->System.out.printf("[JPex]> %s = %s%n", e, e.resolve()));
      System.exit(0);
    }
    while(true) {
      try {
        System.out.printf("[JPex]> ");
        String line = scanner.nextLine();
        switch(line) {
          case "vars":
            System.out.printf("%s%n", StringPad.of(String.format("[ Variables: %d ]", parser.variables().size())).cpad("=", 74));
            System.out.printf("  %s %s%n", StringPad.of("Name").rpad(" ", 9), StringPad.of("Value").lpad(" ", 60));
            System.out.printf("  %s %s%n", StringPad.of("-").rpad("-", 9), StringPad.of("-").lpad("-", 60));
            parser.variables().entrySet().stream()
                .peek(e->System.out.printf("  %s", StringPad.of(e.getKey().concat(" ")).rpad("_", 10)))
                .forEach(e->System.out.printf("%s%n", StringPad.of(String.format(" %s", e.getValue())).lpad("_", 60)));
            System.out.printf("%s%n", StringPad.of(String.format("=", parser.variables().size())).cpad("=", 74));
            break;
          case "exit":
            System.out.printf("[JPex]> bye!%n");
            System.exit(0);
            break;
          case "stack":
            parser.setShowStack(!parser.isShowStack());
            System.out.printf("[JPex]> set show stack: %s%n", parser.isShowStack());
            break;
          default:
            Expression e = parser.parse(line);
            System.out.printf("[JPex]> %s = %s%n", e, e.resolve());
            break;
        }
      }
      catch(Exception e) {
        System.err.printf("[ERROR] [JPex]> %s: %s%n", e.getClass().getSimpleName(), e.getMessage());
      }
    }
  }
  
}
