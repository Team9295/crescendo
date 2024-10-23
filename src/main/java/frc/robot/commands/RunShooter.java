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
        new SetIntakeSpeed(intakeSubsystem, -0.5 * ShooterConstants.kIntakeSpeed).withTimeout(0.1),
        new SetShooterSpeed(shooterSubsystem, ShooterConstants.kShooterSpeakerSpeed).withTimeout(0.5),
        new SetIntakeSpeed(intakeSubsystem, 1).withTimeout(0.5),
        new StopShooter(shooterSubsystem));
  }
}
