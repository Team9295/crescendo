package frc.robot.subsystems; 

import java.util.concurrent.TimeUnit;

import com.revrobotics.CANSparkMax; 
import com.revrobotics.RelativeEncoder; 
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax.ControlType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ShooterConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    private final CANSparkMax intakeMotor = new CANSparkMax(ShooterConstants.IntakePort, MotorType.kBrushless);
    private final TalonSRX shooterFarMotor = new TalonSRX(ShooterConstants.ShooterFarPort);
    private final TalonSRX shooterNearMotor = new TalonSRX(ShooterConstants.ShooterNearPort);

    public ShooterSubsystem() {
        intakeMotor.configFactoryDefault();

        shooterFarMotor.configFactoryDefault();
        shooterNearMotor.setInverted(ShooterConstants.ShooterFarInvert);

        shooterNearMotor.configFactoryDefault();
        shooterNearMotor.setInverted(ShooterConstants.ShooterNearInvert);
    }

    public void runIntake()
    {
        intakeMotor.set(ShooterConstants.IntakeSpeed);
    }

    public void stopIntake()
    {
        intakeMotor.set(0);
    }

    public void runShooter()
    {
        shooterFarMotor.set(ShooterConstants.ShooterSpeed);
        shooterNearMotor.set(ShooterConstants.ShooterSpeed);

        TimeUnit.MILLISECONDS.sleep(500); // wait half a second for the shooter to speed up

        runIntake();
    }

    public void stopShooter()
    {
        shooterFarMotor.set(0);
        shooterNearMotor.set(0);
        stopIntake();
    }

}