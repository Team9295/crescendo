package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScoreCommand extends SequentialCommandGroup {
  public ScoreCommand(ArmSubsystem armSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem) {
    switch (shooterSubsystem.getScoringTarget()) {
      case AMP:
        addCommands(new ScoreAmpCommand(armSubsystem, intakeSubsystem, shooterSubsystem));
        break;
      case SPEAKER:
        addCommands(new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem));
        break;
    }
  }
}
