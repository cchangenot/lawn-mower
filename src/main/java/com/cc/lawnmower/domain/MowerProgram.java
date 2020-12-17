package com.cc.lawnmower.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MowerProgram {
  Dimensions dimensions;
  List<Mower> mowers;

  public MowerProgram(Dimensions dimensions, List<Mower> mowers) {
    this.dimensions = dimensions;
    this.mowers = mowers;
  }
}
