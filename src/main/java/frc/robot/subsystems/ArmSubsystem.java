
package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.Constants.ControllerConstants;

public class ArmSubsystem extends SubsystemBase {
  private final CANSparkMax m_motor1 = new CANSparkMax(ArmConstants.kArmLeft, MotorType.kBrushless);
  private final CANSparkMax m_motor2 = new CANSparkMax(ArmConstants.kArmRight, MotorType.kBrushless);

  private final RelativeEncoder m_encoder = m_motor1.getEncoder();
  private final SparkPIDController m_pidController = m_motor1.getPIDController();

  private ArmState m_setPoint;

  public ArmSubsystem() {
    m_motor1.restoreFactoryDefaults();
    m_motor1.setInverted(ArmConstants.kMotorInverted1);
    m_motor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_motor1.enableVoltageCompensation(8);

    m_motor2.restoreFactoryDefaults();
    m_motor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_motor2.enableVoltageCompensation(8);
    m_motor2.follow(m_motor1, true);

    m_pidController.setOutputRange(-1 * ArmConstants.kSpeedLimitFactor, ArmConstants.kSpeedLimitFactor);
    //m_pidController.setOutputRange(-0.4, 0.4);
    m_pidController.setP(ArmConstants.kP);
    m_pidController.setI(ArmConstants.kI);
    m_pidController.setIZone(ArmConstants.kIz);
    m_pidController.setD(ArmConstants.kD);
    m_pidController.setFF(ArmConstants.kFF);

    zeroEncoders();

    m_motor1.burnFlash();
    m_motor2.burnFlash();
  }

  public void zeroEncoders() {
    m_encoder.setPosition(ArmState.ZERO.getPosition());
  }

  public double getPosition() {
    return m_encoder.getPosition();
  }

  public double getClosedLoopError() {
    return Math.abs(m_encoder.getPosition() - m_setPoint.getPosition()); 
  }

  public void setPosition(ArmState targetState) {
    m_setPoint = targetState;
    SmartDashboard.putNumber("armsetposition", targetState.getPosition());
    m_pidController.setReference(targetState.getPosition(), ControlType.kPosition);
  }

  public void printPosition() {
    SmartDashboard.putNumber("arm target", m_setPoint != null ? m_setPoint.getPosition() : -9999);
    SmartDashboard.putNumber("arm position", m_encoder.getPosition());
  }

  public void printSpeed() {
    SmartDashboard.putNumber("arm speed 1", m_motor1.getAppliedOutput());
    SmartDashboard.putNumber("arm speed 2", m_motor2.getAppliedOutput());
  }

  public void periodic() {
    printPosition();
    printSpeed();
  }

  public void setSpeed(double speed) {
    // if (getPosition() >= 18 || getPosition() < 1) {
      // m_motor1.set(0);
    // }

    m_motor1.set(speed / (1.0 - ControllerConstants.kDeadzone));
  }
}
