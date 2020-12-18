package com.cc.lawnmower.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class Dimensions {

  @NotNull
  @Positive
  Integer width;

  @NotNull
  @Positive
  Integer height;

  public Dimensions(Integer width, Integer height) {
    this.width = width;
    this.height = height;
  }
}
