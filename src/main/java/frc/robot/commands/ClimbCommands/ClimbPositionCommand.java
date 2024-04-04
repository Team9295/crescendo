package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.ClimbConstants.ClimberState;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbPositionCommand extends Command {
  private ClimbSubsystem climbSubsystem;
  private ClimberState targetState;

  public ClimbPositionCommand(ClimbSubsystem climbSubsystem) {
    this.climbSubsystem = climbSubsystem;
    addRequirements(climbSubsystem);
  }

  @Override
  public void execute() {
    switch (climbSubsystem.getCurrentState()) {
      case ZERO:
        targetState = ClimberState.TOP;
        climbSubsystem.setPosition(ClimberState.TOP);
        break;
      case TOP:
        targetState = ClimberState.BOTTOM;
        climbSubsystem.setPosition(ClimberState.BOTTOM);
        break;
      case BOTTOM:
        targetState = ClimberState.TOP;
        climbSubsystem.setPosition(ClimberState.TOP);
        break;
    }
  }

  @Override
  public boolean isFinished() {
    return Math.abs(targetState.getPosition() - climbSubsystem.getPosition()) < ClimbConstants.kToleranceRotations;
  }
}
