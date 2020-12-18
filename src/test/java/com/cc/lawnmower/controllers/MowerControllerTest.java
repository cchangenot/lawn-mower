package com.cc.lawnmower.controllers;

import com.cc.lawnmower.domain.*;
import com.cc.lawnmower.use_cases.ExecuteMowerProgram;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Stream;

@WebMvcTest(controllers = MowerController.class)
class MowerControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @MockBean
  private ExecuteMowerProgram executeMowerProgram;

  @Nested
  class POSTMowerProgram {

    @Test
    void shouldExecuteProgramAndReturnMowersPositions() throws Exception {
      // Given
      Dimensions dimensions = new Dimensions(5, 5);
      List<Mower> mowers = List.of(
        new Mower(1, 2, Orientation.N, "GAGAGAGAA"),
        new Mower(3, 3, Orientation.E, "AADAADADDA")
      );
      MowerProgram program = new MowerProgram(dimensions, mowers);
      List<Position> positions = List.of(new Position(0, 0, Orientation.N));
      BDDMockito.given(executeMowerProgram.perform(BDDMockito.any(MowerProgram.class))).willReturn(positions);

      // When
      mockMvc
        .perform(
          MockMvcRequestBuilders
            .post(MowerController.PATH + "/program")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(program))
        )
        // Then
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
          MockMvcResultMatchers
            .content()
            .json(objectMapper.writeValueAsString(new MowerPositions(positions)))
        );
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidProgramProvider.class)
    void shouldReturnBadRequestWhenProgramIsInvalid(Dimensions dimensions, List<Mower> mowers) throws Exception {
      // Given
      var program = new MowerProgram(dimensions, mowers);

      // When
      mockMvc
        .perform(
          MockMvcRequestBuilders
            .post(MowerController.PATH + "/program")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(program))
        )
        // Then
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

  }

  private static class InvalidProgramProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      Dimensions dimensions = new Dimensions(5, 5);
      List<Mower> mowers = List.of(
        new Mower(1, 2, Orientation.N, "GAGAGAGAA"),
        new Mower(3, 3, Orientation.E, "AADAADADDA")
      );

      return Stream.of(
        Arguments.of(null, mowers),
        Arguments.of(new Dimensions(0, 1), mowers),
        Arguments.of(new Dimensions(1, 0), mowers),
        Arguments.of(new Dimensions(1, -1), mowers),
        Arguments.of(new Dimensions(-1, 1), mowers),
        Arguments.of(new Dimensions(null, 1), mowers),
        Arguments.of(new Dimensions(1, null), mowers),
        Arguments.of(dimensions, List.of(new Mower(null, 2, Orientation.N, "GAGAGAGAA"))),
        Arguments.of(dimensions, List.of(new Mower(1, null, Orientation.N, "GAGAGAGAA"))),
        Arguments.of(dimensions, List.of(new Mower(1, 2, null, "GAGAGAGAA"))),
        Arguments.of(dimensions, List.of(new Mower(1, 2, Orientation.N, null))),
        Arguments.of(dimensions, List.of(new Mower(1, 2, Orientation.N, "")))
      );
    }
  }

}
