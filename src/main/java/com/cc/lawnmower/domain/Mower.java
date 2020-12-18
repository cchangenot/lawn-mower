package com.cc.lawnmower.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Mower {

  @Valid
  @NotNull
  Position position;

  @NotEmpty
  String instructions;

  public Mower(Integer x, Integer y, Orientation orientation, String instructions) {
    this.position = new Position(x, y, orientation);
    this.instructions = instructions;
  }
}
