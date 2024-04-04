package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.ClimbConstants.ClimberState;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbPositionCommand extends Command {
  private ClimbSubsystem climbSubsystem;
  private ClimberState targetState;

  public ClimbPositionCommand(ClimbSubsystem climbSubsystem, ClimberState targetState) {
    this.climbSubsystem = climbSubsystem;
    this.targetState = targetState;
    addRequirements(climbSubsystem);
  }

  @Override
  public void execute() {
    climbSubsystem.setPosition(targetState);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(targetState.getPosition() - climbSubsystem.getPosition()) < ClimbConstants.kToleranceRotations;
  }
}
