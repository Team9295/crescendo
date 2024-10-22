package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class SetIntakeSpeed extends Command {

  private final IntakeSubsystem intakeSubsystem;
  private final double speed;

  /**
   * Drive using speed inputs as a percentage output of the motor
   * 
   * @param intakeSubsystem The subsystem to be used
   * @param speed           Speed
   */
  public SetIntakeSpeed(IntakeSubsystem intakeSubsystem, double speed) {
    addRequirements(intakeSubsystem);

    this.intakeSubsystem = intakeSubsystem;
    this.speed = speed;
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
