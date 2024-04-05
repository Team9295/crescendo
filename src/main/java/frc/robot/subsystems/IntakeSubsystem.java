package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

public class IntakeSubsystem extends SubsystemBase {
  private final CANSparkMax m_motor = new CANSparkMax(ShooterConstants.kIntakePort, MotorType.kBrushless);

  private double speedModifier = 0.0;

  public IntakeSubsystem() {
    m_motor.setInverted(ShooterConstants.kIntakeInverted); // change probably not right :P
  }

  public void incrementSpeedModifier() {
    speedModifier += 0.1;
  }

  public void decrementSpeedModifier() {
    speedModifier -= 0.1;
  }

  public void runIntake() {
    setSpeed(ShooterConstants.kIntakeSpeed + speedModifier);
  }

  public void stopIntake() {
    setSpeed(0);
  }

  public void printSpeed() {
    SmartDashboard.putNumber("intakeSpeed", ShooterConstants.kIntakeSpeed + speedModifier);
  }

  public void periodic() {
    printSpeed();
  }

  public void setSpeed(double speed) {
    double speedToSet = speed;
    if(speed > 0) {
      speedToSet += speedModifier;
    } else if (speed < 0) {
      speedToSet -= speedModifier;
    } else {
      speedToSet = 0;
    }

    m_motor.set(speedToSet);
  }
}
