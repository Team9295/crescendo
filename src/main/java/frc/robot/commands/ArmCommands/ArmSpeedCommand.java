package frc.robot.commands.ArmCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSpeedCommand extends Command {
  private final ArmSubsystem armSubsystem;
  private Supplier<Double> speedSupplier;

  public ArmSpeedCommand(ArmSubsystem armSubsystem, Supplier<Double> speed) {
    addRequirements(armSubsystem);
    this.armSubsystem = armSubsystem;
    this.speedSupplier = speed;
  }

  @Override
  public void execute() {
    double speed = Math.abs(speedSupplier.get()) > ControllerConstants.kDeadzone
        ? speedSupplier.get()
        : 0.0;

    armSubsystem.setSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    armSubsystem.setSpeed(0);
  }
}
