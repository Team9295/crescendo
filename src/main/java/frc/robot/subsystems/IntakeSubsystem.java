package frc.robot.subsystems; 

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.wpilibj.Relay;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax.ControlType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import frc.robot.Constants.ShooterConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final TaleonSRX m_motor = new TalonSRX(ShooterConstants);
    private final Relay m_relay = new Relay(ShooterConstants.kRelayPort);



    public IntakeSubsystem() {
        m_motor.configFactoryDefault(); 
        m_motor.setInverted(ShooterConstants.kMotor); //change probably not right :P
    }

    public void periodic() {
    }
    
    public void setSpeed(double speed) {
        m_motor.set(speed);
    }


}