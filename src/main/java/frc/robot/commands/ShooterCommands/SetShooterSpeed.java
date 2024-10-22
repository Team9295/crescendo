package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class SetShooterSpeed extends Command {

  private final ShooterSubsystem shooterSubsystem;
  private final double speed;

  /**
   * Drive using speed inputs as a percentage output of the motor
   * 
   * @param shooterSubsystem The subsystem to be used
   * @param speed            Supplier of straight speed
   */
  public SetShooterSpeed(ShooterSubsystem shooterSubsystem, double speed) {
    addRequirements(shooterSubsystem);

    this.shooterSubsystem = shooterSubsystem;
    this.speed = speed;
  }

  @Override
  public void execute() {
    shooterSubsystem.setSpeed(speed);
  }
}
