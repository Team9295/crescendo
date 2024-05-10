package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScoreCommand extends InstantCommand {
  private ArmSubsystem armSubsystem;
  private IntakeSubsystem intakeSubsystem;
  private ShooterSubsystem shooterSubsystem;

  public ScoreCommand(ArmSubsystem armSubsystem, IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem) {
        this.armSubsystem = armSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.shooterSubsystem = shooterSubsystem;
  }

  @Override public void execute() {
      switch (shooterSubsystem.getScoringTarget()) {
      case AMP:
      CommandScheduler.getInstance().schedule(new ScoreAmpCommand(armSubsystem, intakeSubsystem, shooterSubsystem));
        
        break;
      case SPEAKER:
        CommandScheduler.getInstance().schedule(new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem, ArmState.SCORE_SPEAKER_AUTO_2));
        break;
    }
  }
}
