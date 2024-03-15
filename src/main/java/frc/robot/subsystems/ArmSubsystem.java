
package frc.robot.subsystems; 

import com.revrobotics.CANSparkMax; 
import com.revrobotics.RelativeEncoder; 
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.controller.ArmFeedforward;

import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.ArmConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(ArmConstants.kMotorPort, MotorType.kBrushless);

    private final RelativeEncoder m_encoder = m_motor.getEncoder();
    private final SparkPIDController m_pidController = m_motor.getPIDController(); 
    private final ArmFeedforward feedForward = new ArmFeedforward(0, 0, 0); 
    private double m_setPoint; 
    
    public ArmSubsystem() {
        m_motor.restoreFactoryDefaults();
        m_motor.setInverted(ArmConstants.kMotorInverted); 
        m_motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_motor.enableVoltageCompensation(12);
        //make sure these constants are set specifically for arm @EZRAAAA 
        m_pidController.setP(ArmConstants.kP);
        m_pidController.setI(ArmConstants.kI);
        m_pidController.setIZone(ArmConstants.kIz);
        m_pidController.setD(ArmConstants.kD);
        m_pidController.setFF(ArmConstants.kFF);

        //m_encoder.setPosition(0);
        m_encoder.setPosition(-ArmConstants.kMinPosition); 
    }
    
    public void periodic() {
        
    }

    public void setSpeed(double speed) {
        m_motor.set(speed);
    }
}
