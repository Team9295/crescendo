package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeSpeedCommand extends Command {

  private final IntakeSubsystem intakeSubsystem;
  private final double speed;

  /**
   * Drive using speed inputs as a percentage output of the motor
   * 
   * @param intakeSubsystem The subsystem to be used
   * @param d               Supplier of speed
   */
  public IntakeSpeedCommand(IntakeSubsystem intakeSubsystem, double d) {
    addRequirements(intakeSubsystem);

    this.intakeSubsystem = intakeSubsystem;
    this.speed = d;
  }

  @Override
  public void initialize() {
    intakeSubsystem.setSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.stopIntake();
  }
}
