
package frc.robot.subsystems; 

import com.revrobotics.CANSparkMax; 
import com.revrobotics.RelativeEncoder; 
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.controller.ArmFeedforward;

import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.ArmConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor1 = new CANSparkMax(ArmConstants.kArmLeft, MotorType.kBrushless);
    private final CANSparkMax m_motor2 = new CANSparkMax(ArmConstants.kArmRight, MotorType.kBrushless); 

    private final RelativeEncoder m_encoder1 = m_motor1.getEncoder();
    private final SparkPIDController m_pidController1 = m_motor1.getPIDController(); 
    private final RelativeEncoder m_encoder2 = m_motor1.getEncoder();
    private final SparkPIDController m_pidController2 = m_motor2.getPIDController(); 
    private final ArmFeedforward feedForward = new ArmFeedforward(0, 0, 0); 
    private double m_setPoint; 
    
    public ArmSubsystem() {
        m_motor1.restoreFactoryDefaults();
        m_motor1.setInverted(ArmConstants.kMotorInverted1); 
        m_motor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_motor1.enableVoltageCompensation(12);
        m_motor2.restoreFactoryDefaults();
        m_motor2.setInverted(ArmConstants.kMotorInverted2); 
        m_motor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_motor2.enableVoltageCompensation(12);
        //make sure these constants are set specifically for arm @EZRAAAA 
        m_pidController1.setP(ArmConstants.kP);
        m_pidController1.setI(ArmConstants.kI);
        m_pidController1.setIZone(ArmConstants.kIz);
        m_pidController1.setD(ArmConstants.kD);
        m_pidController1.setFF(ArmConstants.kFF);
        m_pidController2.setP(ArmConstants.kP);
        m_pidController2.setI(ArmConstants.kI);
        m_pidController2.setIZone(ArmConstants.kIz);
        m_pidController2.setD(ArmConstants.kD);
        m_pidController2.setFF(ArmConstants.kFF);

        //m_encoder.setPosition(0);
        m_encoder1.setPosition(-ArmConstants.kMinPosition); 
        m_encoder2.setPosition(-ArmConstants.kMinPosition); 
    }
    
    public void periodic() {
        
    }

    public void setSpeed(double speed) {
        m_motor1.set(speed);
        m_motor2.set(speed);
    }
}
