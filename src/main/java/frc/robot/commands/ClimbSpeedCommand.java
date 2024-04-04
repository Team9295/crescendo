package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimbConstants;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbSpeedCommand extends Command {
  private final ClimbSubsystem m_ClimbSubsystem;
  private final Supplier<Double> m_speed;

  public ClimbSpeedCommand(ClimbSubsystem climbSubsystem, Supplier<Double> speed) {
    m_ClimbSubsystem = climbSubsystem;
    m_speed = speed;
    addRequirements(m_ClimbSubsystem);
  }

  public void execute() {
    m_ClimbSubsystem.setSpeed(m_speed.get() * ClimbConstants.kSpeedLimitFactor);
  }

  public void end(boolean interrupted) {
    m_ClimbSubsystem.setSpeed(0);
    // IDK if I need this
    // m_ArmSubsystem.setPosition(m_ArmSubsystem.getPosition());
  }
}
