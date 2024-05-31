package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ArmCommands.ArmPositionCommand;
import frc.robot.commands.ArmCommands.StopArmCommand;
import frc.robot.commands.ShooterCommands.IntakeSpeedCommand;
import frc.robot.commands.ShooterCommands.ShooterSpeedCommand;
import frc.robot.commands.ShooterCommands.StopShooterCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScorePassCommand extends SequentialCommandGroup {
  public ScorePassCommand(ArmSubsystem armSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem) {
    addCommands(
        new ArmPositionCommand(armSubsystem, ArmState.SCORE_PASS),
        new IntakeSpeedCommand(intakeSubsystem, -1 * ShooterConstants.kIntakeSpeed+0.6).withTimeout(0.1),
        new ShooterSpeedCommand(shooterSubsystem, ShooterConstants.kShooterSpeakerSpeed).withTimeout(1.0),
        new IntakeSpeedCommand(intakeSubsystem, ShooterConstants.kIntakeSpeed).withTimeout(1.0),
        new StopShooterCommand(shooterSubsystem),
        new StopArmCommand(armSubsystem));
  }
}
