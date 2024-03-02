package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ClimbSubsystem extends SubsystemBase{
    private CANSparkMax leftCilmbMotor = new CANSparkMax(ClimbConstants.LEFT_CLIMB_MOTOR_ID, MotorType.kBrushless);
    private CANSparkMax rightClimbMotor = new CANSparkMax(ClimbConstants.RIGHT_CLIMB_MOTOR_ID, MotorType.kBrushless);

    public Climb() {
        leftClimbMotor.restoreFactoryDefaults(); 
        rightClimbMotor.restoreFactoryDefaults(); 
    }

    public void runClimb(double rspeed, double lspeed) {
        leftCilmbMotor.set(lspeed);
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




