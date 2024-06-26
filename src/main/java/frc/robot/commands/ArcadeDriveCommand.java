package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDriveCommand extends Command {

  private final DriveSubsystem m_driveSubsystem;
  private final Supplier<Double> m_speedStraight, m_speedLeft, m_speedRight;

  /**
   * Drive using speed inputs as a percentage output of the motor
   * 
   * @param driveSubsystem The subsystem to be used
   * @param speedStraight  Supplier of straight speed
   * @param speedLeft      Supplier of left speed
   * @param speedRight     Supplier of right speed
   */
  public ArcadeDriveCommand(DriveSubsystem driveSubsystem, Supplier<Double> speedStraight, Supplier<Double> speedLeft,
      Supplier<Double> speedRight) {
    m_driveSubsystem = driveSubsystem;
    m_speedStraight = speedStraight;
    m_speedLeft = speedLeft;
    m_speedRight = speedRight;
    addRequirements(m_driveSubsystem);
  }

  /**
   * Update the motor outputs
   */
  public void execute() {
    double speedStraight = Math.abs(m_speedStraight.get()) > ControllerConstants.kDeadzone
        ? m_speedStraight.get() + ControllerConstants.kDeadzone
        : 0;
    double speedLeft = Math.abs(m_speedLeft.get()) > ControllerConstants.kTriggerDeadzone
        ? m_speedLeft.get() + ControllerConstants.kDeadzone
        : 0;
    double speedRight = Math.abs(m_speedRight.get()) > ControllerConstants.kTriggerDeadzone
        ? m_speedRight.get() + ControllerConstants.kDeadzone
        : 0;
    // if (speedStraight != 0) {
    // speedLeft = Math.pow(speedLeft * DriveConstants.kTurningMultiplier,
    // DriveConstants.kTurningPowerMultiplier);
    // speedRight = Math.pow(speedRight * DriveConstants.kTurningMultiplier,
    // DriveConstants.kTurningPowerMultiplier);
    // }
    // if (speedStraight != 0) {
    // speedLeft = speedLeft * DriveConstants.kTurningMultiplier;
    // speedRight = speedRight * DriveConstants.kTurningMultiplier;
    // }
    m_driveSubsystem.arcadeDrive(speedStraight, speedLeft, speedRight);
  }
}
