package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.subsystems.ArmSubsystem;

public class ArmPositionCommand extends Command {
  private ArmSubsystem armSubsystem;
  private ArmState targetState;
  private int loopsWithinThreshold = 0;

  public ArmPositionCommand(ArmSubsystem armSubsystem, ArmState targetState) {
    this.armSubsystem = armSubsystem;
    this.targetState = targetState;
    addRequirements(armSubsystem);
  }

  @Override
  public void initialize() {
    armSubsystem.setPosition(targetState);
    SmartDashboard.putBoolean("Arm at position", false);
  }

  @Override
  public void execute() {
    if(armSubsystem.getClosedLoopError() < ArmConstants.kToleranceRotations) {
      loopsWithinThreshold++;
    } else {
      loopsWithinThreshold = 0; 
    }

    SmartDashboard.putNumber("Arm loops within threshold", loopsWithinThreshold);
    System.out.println("arm loops at position: " + loopsWithinThreshold);
    armSubsystem.printSpeed();
  }

  @Override
  public boolean isFinished() {
    if(loopsWithinThreshold >= 10) {
      SmartDashboard.putBoolean("Arm at position", true);
      return true;
    }
    return false;
  }
}
