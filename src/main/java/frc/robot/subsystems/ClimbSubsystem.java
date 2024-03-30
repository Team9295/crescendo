package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ClimbSubsystem extends SubsystemBase{
    private final CANSparkMax leftClimbMotor = new CANSparkMax(ClimbConstants.kLeftClimbPort, MotorType.kBrushless);
    private final CANSparkMax rightClimbMotor = new CANSparkMax(ClimbConstants.kRightClimbPort, MotorType.kBrushless);

    public ClimbSubsystem() {
        leftClimbMotor.restoreFactoryDefaults();
        leftClimbMotor.setInverted(ClimbConstants.kLeftClimbInverted);
        rightClimbMotor.restoreFactoryDefaults();
        rightClimbMotor.setInverted(ClimbConstants.kRightClimbInverted);
    }

    public void setSpeed(double speed) {
        leftClimbMotor.set(speed);
        rightClimbMotor.set(speed);
    }

    @Override
    public void periodic() {
        //smartdashboard stuffs
    }
}