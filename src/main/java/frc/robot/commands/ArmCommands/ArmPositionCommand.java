package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.subsystems.ArmSubsystem;

public class ArmPositionCommand extends Command {
  private ArmSubsystem armSubsystem;
  private ArmState targetState;

  public ArmPositionCommand(ArmSubsystem armSubsystem, ArmState targetState) {
    this.armSubsystem = armSubsystem;
    this.targetState = targetState;
    addRequirements(armSubsystem);
  }

  @Override
  public void execute() {
    armSubsystem.setPosition(targetState);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(targetState.getPosition() - armSubsystem.getPosition()) < ArmConstants.kToleranceRotations;
  }
}
