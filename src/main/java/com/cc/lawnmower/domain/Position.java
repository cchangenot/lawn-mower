package com.cc.lawnmower.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
  Integer x;
  Integer y;
  Orientation orientation;

  public Position(Integer x, Integer y, Orientation orientation) {
    this.x = x;
    this.y = y;
    this.orientation = orientation;
  }
}
