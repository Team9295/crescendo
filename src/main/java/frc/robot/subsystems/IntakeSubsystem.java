package frc.robot.subsystems; 

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(ShooterConstants.kIntakePort, MotorType.kBrushless);

    public IntakeSubsystem() {
        m_motor.setInverted(ShooterConstants.kIntakeInverted); //change probably not right :P
    }

    public void runIntake()
    {
        setSpeed(ShooterConstants.kIntakeSpeed);
    }

    public void stopIntake()
    {
        setSpeed(0);
    }

    public void periodic() {

    }
    
    public void setSpeed(double speed) {
        m_motor.set(speed);
    }
}