package com.cc.lawnmower;

import com.cc.lawnmower.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LawnMowerApplicationTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void specificationIntegrationTest() {
    // Given
    var dimensions = new Dimensions(5, 5);
    List<Mower> mowers = List.of(
      new Mower(1, 2, Orientation.N, "GAGAGAGAA"),
      new Mower(3, 3, Orientation.E, "AADAADADDA")
    );
    var program = new MowerProgram(dimensions, mowers);
    var expectedMowerPositions = new MowerPositions(
      List.of(
        new Position(1, 3, Orientation.N),
        new Position(5, 1, Orientation.E)
      )
    );

    // When
    var response = restTemplate.postForEntity("http://localhost:" + port + "/mower/program", program, MowerPositions.class);

    // Then
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(expectedMowerPositions);
  }
}
