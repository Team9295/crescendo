package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ArmSubsystem;

public class SetArmSpeed extends Command {
  private final ArmSubsystem armSubsystem;
  private double speed;

  public SetArmSpeed(ArmSubsystem armSubsystem, double speed) {
    addRequirements(armSubsystem);
    this.armSubsystem = armSubsystem;
    this.speed = speed;
  }

  @Override
  public void execute() {
    double speedNow = Math.abs(speed) > ControllerConstants.kDeadzone
        ? speed
        : 0.0;

    armSubsystem.setSpeed(speedNow * ArmConstants.kArmSpeedModifier);
  }

  @Override
  public void end(boolean interrupted) {
    armSubsystem.setSpeed(0);
  }
}
