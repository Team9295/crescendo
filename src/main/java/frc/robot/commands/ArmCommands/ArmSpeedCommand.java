package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSpeedCommand extends Command {
  private final ArmSubsystem armSubsystem;
  private double speed;

  public ArmSpeedCommand(ArmSubsystem armSubsystem, double speed) {
    addRequirements(armSubsystem);
    this.armSubsystem = armSubsystem;
    this.speed = speed;
  }

  @Override
  public void execute() {
    speed = Math.abs(speed) > ControllerConstants.kDeadzone
        ? speed
        : 0.0;

    armSubsystem.setSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    armSubsystem.setSpeed(0);
  }
}
