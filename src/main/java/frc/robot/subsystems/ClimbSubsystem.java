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
        rightClimbMotor.restoreFactoryDefaults(); 
    }

    public void runClimb(double rspeed, double lspeed) {
        leftClimbMotor.set(lspeed);
        rightClimbMotor.set(rspeed);
    }

    public void stopClimb() {
        leftClimbMotor.set(0);
        rightClimbMotor.set(0);
    }

    @Override
    public void periodic() {
        //smartdashboard stuffs
    }
}




