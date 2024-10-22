package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class RunAmpShooter extends Command {

  private final ShooterSubsystem shooterSubsystem;

  /**
   * Drive using speed inputs as a percentage output of the motor
   * 
   * @param shooterSubsystem The subsystem to be used
   */
  public RunAmpShooter(ShooterSubsystem shooterSubsystem) {
    addRequirements(shooterSubsystem);
    this.shooterSubsystem = shooterSubsystem;
  }

  @Override
  public void execute() {
    shooterSubsystem.setSpeed(ShooterConstants.kShooterAmpSpeed, 0);
  }
}
