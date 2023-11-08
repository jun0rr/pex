/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.main;

import com.jun0rr.pex.main.StringColumn.Align;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author F6036477
 */
public class StringTable {
  
  private final List<StringRow> rows;
  
  private final String header;
  
  private final Align headerAlign;
  
  private final char outterchar;
  
  private final char innerchar;
  
  public StringTable(String header, Align headerAlign, char outterchar, char innerchar) {
    this.rows = new LinkedList<>();
    this.header = header;
    this.headerAlign = headerAlign;
    this.outterchar = outterchar;
    this.innerchar = innerchar;
  }
  
  public StringTable(char outterchar, char innerchar) {
    this(null, null, outterchar, innerchar);
  }
  
  public List<StringRow> rows() {
    return rows;
  }
  
  public StringRow newRow() {
    StringRow row = new StringRow();
    rows.add(row);
    return row;
  }
  
  public StringTable addRow(StringRow row) {
    rows.add(Objects.requireNonNull(row));
    return this;
  }
  
  public String header() {
    return header;
  }
  
  public Align headerAlign() {
    return headerAlign;
  }
  
  public char outterChar() {
    return outterchar;
  }
  
  public char innerChar() {
    return innerchar;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 29 * hash + Objects.hashCode(this.rows);
    hash = 29 * hash + Objects.hashCode(this.header);
    hash = 29 * hash + Objects.hashCode(this.headerAlign);
    hash = 29 * hash + this.outterchar;
    hash = 29 * hash + this.innerchar;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StringTable other = (StringTable) obj;
    if (this.outterchar != other.outterchar) {
      return false;
    }
    if (this.innerchar != other.innerchar) {
      return false;
    }
    if (!Objects.equals(this.header, other.header)) {
      return false;
    }
    if (!Objects.equals(this.rows, other.rows)) {
      return false;
    }
    return this.headerAlign == other.headerAlign;
  }
  
  @Override
  public String toString() {
    if(rows.isEmpty()) {
      throw new IllegalStateException("No rows found");
    }
    StringRow first = rows.get(0);
    int len = first.toString().length();
    String outterline = StringPad.of(String.valueOf(outterchar)).rpad(String.valueOf(outterchar), len);
    String innerline = StringPad.of(String.valueOf(innerchar)).rpad(String.valueOf(innerchar), len);
    StringBuilder sb = new StringBuilder(outterline).append("\n");
    if(header != null && headerAlign != null) {
      StringPad pad = StringPad.of(header);
      sb.append(switch(headerAlign) {
        case LEFT -> pad.rpad(" ", len);
        case CENTER -> pad.cpad(" ", len);
        case RIGHT -> pad.lpad(" ", len);
      }).append("\n").append(outterline).append("\n");
    }
    sb.append(first)
        .append("\n")
        .append(innerline)
        .append("\n");
    rows.stream().skip(1).forEach(r->sb.append(r).append("\n"));
    return sb.append(outterline).append("\n").toString();
  }
  
}
