package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ZeroArm extends Command {
  ArmSubsystem armSubsystem;

  public ZeroArm(ArmSubsystem armSubsystem) {
    this.armSubsystem = armSubsystem;

    addRequirements(armSubsystem);
  }

  @Override
  public void execute() {
    armSubsystem.zeroEncoders();
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
