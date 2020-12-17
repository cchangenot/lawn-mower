package com.cc.lawnmower.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mower {
  Position position;
  String instructions;

  public Mower(Integer x, Integer y, Orientation orientation, String instructions) {
    this.position = new Position(x, y, orientation);
    this.instructions = instructions;
  }
}
