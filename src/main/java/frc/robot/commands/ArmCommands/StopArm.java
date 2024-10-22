package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ArmSubsystem;

public class StopArm extends InstantCommand {
  private final ArmSubsystem armSubsystem;

  public StopArm(ArmSubsystem armSubsystem) {
    addRequirements(armSubsystem);
    this.armSubsystem = armSubsystem;
  }

  @Override
  public void execute() {
    armSubsystem.setSpeed(0);
  }
}
