package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ShooterCommands.SetIntakeSpeed;
import frc.robot.commands.ShooterCommands.SetShooterSpeed;
import frc.robot.commands.ShooterCommands.StopShooter;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RunShooter extends SequentialCommandGroup {
  public RunShooter(IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem) {
    addCommands(
        new SetIntakeSpeed(intakeSubsystem, -1 * ShooterConstants.kIntakeSpeed + 0.0).withTimeout(0.1),
        new SetShooterSpeed(shooterSubsystem, ShooterConstants.kShooterSpeakerSpeed).withTimeout(1.0),
        new SetIntakeSpeed(intakeSubsystem, ShooterConstants.kIntakeSpeed).withTimeout(1.0),
        new StopShooter(shooterSubsystem));
  }
}
