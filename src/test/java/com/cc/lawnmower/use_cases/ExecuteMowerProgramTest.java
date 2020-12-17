package com.cc.lawnmower.use_cases;

import com.cc.lawnmower.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ExecuteMowerProgramTest {

  @Nested
  class Perform {

    @InjectMocks
    private ExecuteMowerProgram executeMowerProgram;

    @Test
    void shouldReturnMowersPositions() {
      // Given
      Dimensions dimensions = new Dimensions(5, 5);
      List<Mower> mowers = List.of(
        new Mower(1, 2, Orientation.N, "GAGAGAGAA"),
        new Mower(3, 3, Orientation.E, "AADAADADDA")
      );

      MowerProgram program = new MowerProgram(dimensions, mowers);

      // When
      var positions = executeMowerProgram.perform(program);

      // Then
      Assertions.assertThat(positions).usingRecursiveFieldByFieldElementComparator().isEqualTo(
        List.of(
          new Position(1, 3, Orientation.N),
          new Position(5, 1, Orientation.E)
        )
      );
    }

  }

}
