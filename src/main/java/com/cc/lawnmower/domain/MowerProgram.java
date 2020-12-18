package com.cc.lawnmower.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class MowerProgram {

  @Valid
  @NotNull
  Dimensions dimensions;

  @Valid
  @NotNull
  List<Mower> mowers;

  public MowerProgram(Dimensions dimensions, List<Mower> mowers) {
    this.dimensions = dimensions;
    this.mowers = mowers;
  }
}
