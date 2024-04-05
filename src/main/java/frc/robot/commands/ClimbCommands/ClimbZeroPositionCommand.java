package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbZeroPositionCommand extends Command {
  ClimbSubsystem climbSubsystem;

  public ClimbZeroPositionCommand(ClimbSubsystem climbSubsystem) {
    this.climbSubsystem = climbSubsystem;

    addRequirements(climbSubsystem);
  }

  @Override
  public void execute() {
    climbSubsystem.zeroEncoders();
  }

  @Override
  public boolean runsWhenDisabled() {
    return true;
  }

  @Override
  public boolean isFinished() {
    return true;
  }

}
