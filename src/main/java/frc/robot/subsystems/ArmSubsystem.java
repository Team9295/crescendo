
package frc.robot.subsystems; 

import com.revrobotics.CANSparkMax; 
import com.revrobotics.RelativeEncoder; 
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ControllerConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor1 = new CANSparkMax(ArmConstants.kArmLeft, MotorType.kBrushless);
    private final CANSparkMax m_motor2 = new CANSparkMax(ArmConstants.kArmRight, MotorType.kBrushless); 

    private final RelativeEncoder m_encoder = m_motor1.getEncoder();
    private final SparkPIDController m_pidController = m_motor1.getPIDController(); 

    private final ArmFeedforward feedForward = new ArmFeedforward(0, 0, 0); 
    private double m_setPoint; 
    
    public ArmSubsystem() {
        m_motor1.restoreFactoryDefaults();
        m_motor1.setInverted(ArmConstants.kMotorInverted1); 
        m_motor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_motor1.enableVoltageCompensation(12);
        // m_motor1.setClosedLoopRampRate(5);
        m_motor2.restoreFactoryDefaults();
        // m_motor2.setInverted(ArmConstants.kMotorInverted2); 
        m_motor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_motor2.enableVoltageCompensation(12);
        m_motor2.follow(m_motor1, true); 

        m_pidController.setOutputRange(-1 * ArmConstants.kSpeedLimitFactor, ArmConstants.kSpeedLimitFactor);
        m_pidController.setP(ArmConstants.kP);
        m_pidController.setI(ArmConstants.kI);
        m_pidController.setIZone(ArmConstants.kIz);
        m_pidController.setD(ArmConstants.kD);
        m_pidController.setFF(ArmConstants.kFF);

        //m_encoder.setPosition(0);
        m_encoder.setPosition(ArmConstants.kMinPosition); 

        m_motor1.burnFlash();
        m_motor2.burnFlash();
    }
    
    public void zeroEncoders() {
        m_encoder.setPosition(0);
    }

    public void setPosition(double position) {
        m_setPoint = position;
        m_pidController.setReference(position, ControlType.kPosition);
    }

    public void printPosition() {
        SmartDashboard.putNumber("arm position", m_encoder.getPosition());
    }

    public void printSpeed() {
        SmartDashboard.putNumber("arm speed 1", m_motor1.get());
        SmartDashboard.putNumber("arm speed 2", m_motor2.get());
    }

    public void periodic() {
        printPosition();    
        printSpeed();  
    }

    public void setSpeed(double speed) {
        m_motor1.set(speed / (1.0 - ControllerConstants.kDeadzone));
    }
}
