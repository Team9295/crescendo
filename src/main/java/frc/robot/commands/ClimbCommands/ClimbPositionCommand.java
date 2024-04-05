package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.ClimbConstants.ClimberState;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbPositionCommand extends InstantCommand {
  private ClimbSubsystem climbSubsystem;

  public ClimbPositionCommand(ClimbSubsystem climbSubsystem) {
    this.climbSubsystem = climbSubsystem;
    addRequirements(climbSubsystem);
  }

  @Override
  public void execute() {
    switch (climbSubsystem.getCurrentState()) {
      case ZERO:
        climbSubsystem.setPosition(ClimberState.TOP);
        break;
      case TOP:
        climbSubsystem.setPosition(ClimberState.BOTTOM);
        break;
      case BOTTOM:
        climbSubsystem.setPosition(ClimberState.TOP);
        break;
    }
  }
}
