package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.ShooterConstants.ScoringTarget;
import frc.robot.subsystems.ShooterSubsystem;

public class SetScoringTargetCommand extends InstantCommand {
  /**
   * Drive using speed inputs as a percentage output of the motor
   * 
   * @param shooterSubsystem The subsystem to be used
   * @param speed            Supplier of straight speed
   */
  public SetScoringTargetCommand(ShooterSubsystem shooterSubsystem, ScoringTarget target) {
    addRequirements(shooterSubsystem);

    shooterSubsystem.setScoringTarget(target);
  }
}
