package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ArmCommands.SetArmPosition;
import frc.robot.commands.ArmCommands.StopArm;
import frc.robot.commands.ShooterCommands.SetIntakeSpeed;
import frc.robot.commands.ShooterCommands.SetShooterSpeed;
import frc.robot.commands.ShooterCommands.StopShooter;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScorePassCommand extends SequentialCommandGroup {
  public ScorePassCommand(ArmSubsystem armSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem) {
    addCommands(
        new SetArmPosition(armSubsystem, ArmState.SCORE_PASS),
        new SetIntakeSpeed(intakeSubsystem, -1 * ShooterConstants.kIntakeSpeed + 0.6).withTimeout(0.1),
        new SetShooterSpeed(shooterSubsystem, ShooterConstants.kShooterSpeakerSpeed).withTimeout(1.0),
        new SetIntakeSpeed(intakeSubsystem, ShooterConstants.kIntakeSpeed).withTimeout(1.0),
        new StopShooter(shooterSubsystem),
        new StopArm(armSubsystem));
  }
}