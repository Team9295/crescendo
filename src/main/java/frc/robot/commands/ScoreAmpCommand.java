package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ArmCommands.ArmPositionCommand;
import frc.robot.commands.ArmCommands.ArmSpeedCommand;
import frc.robot.commands.ArmCommands.StopArmCommand;
import frc.robot.commands.ShooterCommands.IntakeSpeedCommand;
import frc.robot.commands.ShooterCommands.ShooterAmpSpeedCommand;
import frc.robot.commands.ShooterCommands.StopShooterCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScoreAmpCommand extends SequentialCommandGroup {
  public ScoreAmpCommand(ArmSubsystem armSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem) {
        System.out.println("scoring in amp");
    addCommands(
        new ArmPositionCommand(armSubsystem, ArmState.SCORE_AMP),
        new IntakeSpeedCommand(intakeSubsystem, -1 * ShooterConstants.kIntakeSpeed).withTimeout(0.1),
        new ShooterAmpSpeedCommand(shooterSubsystem).withTimeout(1.5),
        new IntakeSpeedCommand(intakeSubsystem, ShooterConstants.kIntakeSpeed - 0.6).withTimeout(2.0),
        new StopShooterCommand(shooterSubsystem),
        new ArmSpeedCommand(armSubsystem, () -> -0.2).withTimeout(0.4),
        new StopArmCommand(armSubsystem));
  }
}
