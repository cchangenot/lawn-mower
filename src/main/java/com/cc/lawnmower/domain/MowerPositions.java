package com.cc.lawnmower.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MowerPositions {
  List<Position> positions;

  public MowerPositions() {
  }

  public MowerPositions(List<Position> positions) {
    this.positions = positions;
  }
}
