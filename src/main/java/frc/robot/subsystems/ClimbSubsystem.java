package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.ClimbConstants.ClimberState;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ClimbSubsystem extends SubsystemBase {
  private final CANSparkMax leftClimbMotor = new CANSparkMax(ClimbConstants.kLeftClimbPort, MotorType.kBrushless);
  private final CANSparkMax rightClimbMotor = new CANSparkMax(ClimbConstants.kRightClimbPort, MotorType.kBrushless);
  private final RelativeEncoder leftEncoder = leftClimbMotor.getEncoder();
  private final RelativeEncoder rightEncoder = rightClimbMotor.getEncoder();

  private SparkPIDController pidControllerLeft = leftClimbMotor.getPIDController();
  private SparkPIDController pidControllerRight = rightClimbMotor.getPIDController();
  private double setpoint;

  private ClimberState currentState = ClimberState.ZERO;

  public ClimbSubsystem() {
    leftClimbMotor.restoreFactoryDefaults();
    leftClimbMotor.setInverted(ClimbConstants.kLeftClimbInverted);
    rightClimbMotor.restoreFactoryDefaults();
    rightClimbMotor.setInverted(ClimbConstants.kRightClimbInverted);
    //rightClimbMotor.follow(leftClimbMotor);

    zeroEncoders();

    pidControllerLeft.setOutputRange(-1.0*ClimbConstants.kSpeedLimitFactor, 1.0*ClimbConstants.kSpeedLimitFactor);
    pidControllerLeft.setP(0.1);
    pidControllerLeft.setI(0);
    pidControllerLeft.setD(0);
    pidControllerLeft.setFF(0);

    pidControllerRight.setOutputRange(-1.0*ClimbConstants.kSpeedLimitFactor, 1.0*ClimbConstants.kSpeedLimitFactor);
    pidControllerRight.setP(0.1);
    pidControllerRight.setI(0);
    pidControllerRight.setD(0);
    pidControllerRight.setFF(0);
  }

  public void zeroEncoders() {
    leftEncoder.setPosition(ClimberState.ZERO.getPosition());
    rightEncoder.setPosition(ClimberState.ZERO.getPosition());
  }

  public double getPosition() {
    return leftEncoder.getPosition();
  }

  public ClimberState getCurrentState() {
    return currentState;
  }

  public void setPosition(ClimberState targetState) {
    currentState = targetState;
    setpoint = targetState.getPosition();
    pidControllerLeft.setReference(targetState.getPosition(), ControlType.kPosition);
    pidControllerRight.setReference(targetState.getPosition(), ControlType.kPosition);
  }

  public void setSpeed(double speedLeft, double speedRight) {
    leftClimbMotor.set(speedLeft);
    rightClimbMotor.set(speedRight);
  }

  public void printPosition() {
    SmartDashboard.putNumber("left climb pos", leftEncoder.getPosition());
    SmartDashboard.putNumber("right climb pos", rightEncoder.getPosition());
    SmartDashboard.putNumber("climb setpoint", setpoint);
  }

  public void printCurrentState() {
    SmartDashboard.putBoolean("climbTop", currentState == ClimberState.TOP);
    SmartDashboard.putBoolean("climbBottom", currentState == ClimberState.BOTTOM);
    SmartDashboard.putBoolean("climbZero", currentState == ClimberState.ZERO);
  }

  public void printSpeed() {
    SmartDashboard.putNumber("left climb speed", leftClimbMotor.getAppliedOutput());
    SmartDashboard.putNumber("right climb speed", rightClimbMotor.getAppliedOutput());
  }

  @Override
  public void periodic() {
    printPosition();
    printCurrentState();
    printSpeed();
  }
}
