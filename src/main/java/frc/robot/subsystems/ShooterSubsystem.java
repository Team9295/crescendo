package frc.robot.subsystems; 

import java.util.concurrent.TimeUnit;

import com.revrobotics.CANSparkMax; 
import com.revrobotics.RelativeEncoder; 
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ShooterConstants;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.ShooterCommands.IntakeSpeedCommand;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    private final TalonSRX shooterFarMotor = new TalonSRX(ShooterConstants.kShooterFarPort);
    private final TalonSRX shooterNearMotor = new TalonSRX(ShooterConstants.kShooterNearPort);
    private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();

    public ShooterSubsystem() {
        shooterFarMotor.configFactoryDefault();
        shooterNearMotor.setInverted(ShooterConstants.kShooterFarInverted);

        shooterNearMotor.configFactoryDefault();
        shooterNearMotor.setInverted(ShooterConstants.kShooterNearInverted);
    }

    

    public void runShooter()
    {
        shooterFarMotor.set(TalonSRXControlMode.PercentOutput, ShooterConstants.kShooterSpeed);
        shooterNearMotor.set(TalonSRXControlMode.PercentOutput, ShooterConstants.kShooterSpeed);
    }

    public void stopShooter()
    {
        shooterFarMotor.set(TalonSRXControlMode.PercentOutput, 0);
        shooterNearMotor.set(TalonSRXControlMode.PercentOutput, 0);
    }

    public void periodic() {

    }
    
    public void setSpeed(double speed) {
        shooterFarMotor.set(TalonSRXControlMode.PercentOutput, speed);
        shooterNearMotor.set(TalonSRXControlMode.PercentOutput, speed);
    }

}