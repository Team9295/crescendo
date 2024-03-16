package frc.robot.subsystems; 

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import frc.robot.Constants.ShooterConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    private final VictorSPX shooterFarMotor = new VictorSPX(ShooterConstants.kShooterRightPort);
    private final VictorSPX shooterNearMotor = new VictorSPX(ShooterConstants.kShooterLeftPort);

    public ShooterSubsystem() {
        shooterNearMotor.configFactoryDefault();
        shooterFarMotor.configFactoryDefault();

        shooterNearMotor.setInverted(ShooterConstants.kShooterFarInverted);
        shooterFarMotor.follow(shooterNearMotor);;
        shooterFarMotor.setInverted(InvertType.OpposeMaster);
    }    

    public void runShooter()
    {
        setSpeed(ShooterConstants.kShooterSpeed);
    }

    public void stopShooter()
    {
        setSpeed(0);
    }

    public void periodic() { }
    
    public void setSpeed(double speed) {
        shooterNearMotor.set(VictorSPXControlMode.PercentOutput, speed);
    }

}