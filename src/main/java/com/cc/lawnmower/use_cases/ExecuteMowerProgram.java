package com.cc.lawnmower.use_cases;

import com.cc.lawnmower.domain.Dimensions;
import com.cc.lawnmower.domain.Mower;
import com.cc.lawnmower.domain.MowerProgram;
import com.cc.lawnmower.domain.Position;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExecuteMowerProgram {

  public List<Position> perform(MowerProgram program) {
    return program.getMowers()
      .stream()
      .map(mower -> computeInstructions(mower, program.getDimensions()))
      .collect(Collectors.toList());
  }

  private Position computeInstructions(Mower mower, Dimensions dimension) {
    var currentPosition = mower.getPosition();
    var maxX = dimension.getWidth();
    var maxY = dimension.getHeight();


    mower.getInstructions().chars().forEach(instruction -> {
      switch (instruction) {
        case 'D' -> currentPosition.turnRight();
        case 'G' -> currentPosition.turnLeft();
        case 'A' -> {
          switch (currentPosition.getOrientation()) {
            case N -> currentPosition.setY(Math.min(currentPosition.getY() + 1, maxY));
            case E -> currentPosition.setX(Math.min(currentPosition.getX() + 1, maxX));
            case S -> currentPosition.setY(Math.max(currentPosition.getY() - 1, 0));
            case W -> currentPosition.setX(Math.max(currentPosition.getX() - 1, 0));
          }
        }
      }
    });

    return currentPosition;
  }

}
