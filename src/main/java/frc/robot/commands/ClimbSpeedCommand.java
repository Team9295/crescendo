package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimbConstants;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbSpeedCommand extends Command {
  private final ClimbSubsystem m_ClimbSubsystem;
  private final Supplier<Double> m_speedLeft;
  private final Supplier<Double> m_speedRight; 


  public ClimbSpeedCommand(ClimbSubsystem climbSubsystem, Supplier<Double> speedLeft, Supplier<Double> speedRight) {
    m_ClimbSubsystem = climbSubsystem;
    m_speedLeft = speedLeft;
    m_speedRight = speedRight;
    addRequirements(m_ClimbSubsystem);
  }


  public void execute() {
    m_ClimbSubsystem.setSpeed(
      m_speedLeft.get() * ClimbConstants.kSpeedLimitFactor,
      m_speedRight.get() * ClimbConstants.kSpeedLimitFactor
    );
  }

  public void end(boolean interrupted) {
    m_ClimbSubsystem.setSpeed(0, 0);
    // IDK if I need this
    // m_ArmSubsystem.setPosition(m_ArmSubsystem.getPosition());
  }
}
