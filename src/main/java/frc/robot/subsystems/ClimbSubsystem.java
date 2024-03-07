package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ClimbSubsystem extends SubsystemBase{
    private final CANSparkMax leftClimbMotor = new CANSparkMax(ClimbConstants.LeftClimbPort, MotorType.kBrushless);
    private final CANSparkMax rightClimbMotor = new CANSparkMax(ClimbConstants.RightClimbPort, MotorType.kBrushless);

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




