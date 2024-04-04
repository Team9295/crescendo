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

  private SparkPIDController pidController = leftClimbMotor.getPIDController();
  private double setpoint;

  private ClimberState currentState = ClimberState.ZERO;

  public ClimbSubsystem() {
    leftClimbMotor.restoreFactoryDefaults();
    leftClimbMotor.setInverted(ClimbConstants.kLeftClimbInverted);
    rightClimbMotor.restoreFactoryDefaults();
    rightClimbMotor.setInverted(ClimbConstants.kRightClimbInverted);
    rightClimbMotor.follow(leftClimbMotor);

    zeroEncoders();

    pidController.setOutputRange(-0.6, 0.6);
    pidController.setP(0.1);
    pidController.setI(0);
    pidController.setD(0);
    pidController.setFF(0);
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
    pidController.setReference(targetState.getPosition(), ControlType.kPosition);
  }

  public void setSpeed(double speed) {
    leftClimbMotor.set(speed);
    rightClimbMotor.set(speed);
  }

  public void printPosition() {
    SmartDashboard.putNumber("left climb pos", leftEncoder.getPosition());
    SmartDashboard.putNumber("right climb pos", rightEncoder.getPosition());
    SmartDashboard.putNumber("climb setpoint", setpoint);
  }

  @Override
  public void periodic() {
    printPosition();
  }
}
