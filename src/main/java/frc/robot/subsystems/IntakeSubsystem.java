package frc.robot.subsystems; 

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

//This subsystem needs to be changed its a sparkmax
public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(ShooterConstants.kIntakePort, MotorType.kBrushless);

    public IntakeSubsystem() {
        m_motor.setInverted(ShooterConstants.kIntakeInverted); //change probably not right :P
    }

    public void runIntake()
    {
        m_motor.set(ShooterConstants.kIntakeSpeed);
    }

    public void stopIntake()
    {
        m_motor.set(0);
    }

    public void periodic() {

    }
    
    public void setSpeed(double speed) {
        m_motor.set(speed);
    }
}