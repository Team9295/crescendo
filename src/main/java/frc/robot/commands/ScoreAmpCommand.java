package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ArmCommands.SetArmPosition;
import frc.robot.commands.ArmCommands.SetArmSpeed;
import frc.robot.commands.ArmCommands.StopArm;
import frc.robot.commands.ShooterCommands.SetIntakeSpeed;
import frc.robot.commands.ShooterCommands.RunAmpShooter;
import frc.robot.commands.ShooterCommands.StopShooter;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScoreAmpCommand extends SequentialCommandGroup {
  public ScoreAmpCommand(ArmSubsystem armSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem) {
    System.out.println("scoring in amp");
    addCommands(
        new SetArmPosition(armSubsystem, ArmState.SCORE_AMP),
        new SetIntakeSpeed(intakeSubsystem, -1 * ShooterConstants.kIntakeSpeed + 0.0).withTimeout(0.1),
        new RunAmpShooter(shooterSubsystem).withTimeout(1.5),
        new SetIntakeSpeed(intakeSubsystem, ShooterConstants.kIntakeSpeed - 0.6).withTimeout(2.0),
        new StopShooter(shooterSubsystem),
        new SetArmSpeed(armSubsystem, -0.4).withTimeout(0.4),
        new StopArm(armSubsystem));
  }
}
