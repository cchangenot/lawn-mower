package com.cc.lawnmower.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Position {

  @NotNull
  @Min(0)
  Integer x;

  @NotNull
  @Min(0)
  Integer y;

  @NotNull
  Orientation orientation;

  public Position(Integer x, Integer y, Orientation orientation) {
    this.x = x;
    this.y = y;
    this.orientation = orientation;
  }

  public void turnRight() {
    switch (orientation) {
      case N -> this.orientation = Orientation.E;
      case E -> this.orientation = Orientation.S;
      case S -> this.orientation = Orientation.W;
      case W -> this.orientation = Orientation.N;
    }
  }

  public void turnLeft() {
    switch (orientation) {
      case N -> this.orientation = Orientation.W;
      case W -> this.orientation = Orientation.S;
      case S -> this.orientation = Orientation.E;
      case E -> this.orientation = Orientation.N;
    }
  }
}
