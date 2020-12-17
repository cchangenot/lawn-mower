package com.cc.lawnmower.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dimensions {
  Integer width;
  Integer height;

  public Dimensions(Integer width, Integer height) {
    this.width = width;
    this.height = height;
  }
}
