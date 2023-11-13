/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.main;

import java.util.List;

/**
 *
 * @author Juno
 */
public class Main {
  
  public static void main(String... args) {
    Terminal term = new Terminal();
    if(args != null && args.length > 0) {
      List.of(args).stream().map(term.parser()::parse).forEach(e->System.out.printf("[JPex]> %s = %s%n", e, e.resolve()));
      System.exit(0);
    }
    term.loop();
  }
  
}
