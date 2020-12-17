package com.cc.lawnmower.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

  @Nested
  class TurnRight {

    @ParameterizedTest
    @CsvSource({"N,E", "E,S", "S,W", "W,N"})
    void shouldTurnRightTheOrientationOf90Degree(Orientation currentOrientation, Orientation expectedOrientation) {
      // Given
      var position = new Position(0, 0, currentOrientation);

      // When
      position.turnRight();

      // Then
      Assertions.assertThat(position.getOrientation()).isSameAs(expectedOrientation);
    }

  }

  @Nested
  class TurnLeft {

    @ParameterizedTest
    @CsvSource({"N,W", "W,S", "S,E", "E,N"})
    void shouldTurnLeftTheOrientationOf90Degree(Orientation currentOrientation, Orientation expectedOrientation) {
      // Given
      var position = new Position(0, 0, currentOrientation);

      // When
      position.turnLeft();

      // Then
      Assertions.assertThat(position.getOrientation()).isSameAs(expectedOrientation);
    }

  }

}
