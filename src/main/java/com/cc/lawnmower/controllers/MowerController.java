package com.cc.lawnmower.controllers;

import com.cc.lawnmower.domain.MowerPositions;
import com.cc.lawnmower.domain.MowerProgram;
import com.cc.lawnmower.use_cases.ExecuteMowerProgram;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(MowerController.PATH)
@Validated
public class MowerController {
  public static final String PATH = "/mower";

  private final ExecuteMowerProgram executeMowerProgram;

  MowerController(ExecuteMowerProgram executeMowerProgram) {
    this.executeMowerProgram = executeMowerProgram;
  }

  @CrossOrigin // Should not do that but required to work with the index.html
  @PostMapping("/program")
  MowerPositions programMower(@RequestBody @Valid MowerProgram program) {
    return new MowerPositions(executeMowerProgram.perform(program));
  }
}
