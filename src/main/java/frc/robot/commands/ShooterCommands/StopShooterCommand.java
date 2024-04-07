package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class StopShooterCommand extends InstantCommand {

  private final ShooterSubsystem shooterSubsystem;

  /**
   * Drive using speed inputs as a percentage output of the motor
   * 
   * @param shooterSubsystem The subsystem to be used
   * @param speed            Supplier of straight speed
   */
  public StopShooterCommand(ShooterSubsystem shooterSubsystem) {
    addRequirements(shooterSubsystem);

    this.shooterSubsystem = shooterSubsystem;
  }

  @Override
  public void execute() {
    shooterSubsystem.stopShooter();
  }
}
