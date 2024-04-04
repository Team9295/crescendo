package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ArmCommands.ArmPositionCommand;
import frc.robot.commands.ArmCommands.StopArmCommand;
import frc.robot.commands.ShooterCommands.IntakeSpeedCommand;
import frc.robot.commands.ShooterCommands.ShooterSpeedCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScoreSpeakerCommand extends SequentialCommandGroup {
  public ScoreSpeakerCommand(ArmSubsystem armSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem) {
    addCommands(
        new ArmPositionCommand(armSubsystem, ArmState.SCORE_SPEAKER),
        new IntakeSpeedCommand(intakeSubsystem, -1 * ShooterConstants.kIntakeSpeed).withTimeout(0.1),
        new ShooterSpeedCommand(shooterSubsystem, ShooterConstants.kShooterSpeakerSpeed).withTimeout(0.75),
        new IntakeSpeedCommand(intakeSubsystem, ShooterConstants.kIntakeSpeed).withTimeout(0.5),
        new StopArmCommand(armSubsystem));
  }
}
