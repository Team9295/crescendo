package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax.ControlType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem {
    private final TalonSRX m_masterLeft = new TalonSRX(DriveConstants.kMasterLeftPort);
    private final TalonSRX m_followerLeft = new TalonSRX(DriveConstants.kFollowerLeftPort);
    private final TalonSRX m_masterRight = new TalonSRX(DriveConstants.kMasterRightPort);
    private final TalonSRX m_followerRight = new TalonSRX(DriveConstants.kFollowerRightPort);
    private double leftDriveSpeed;
    private double rightDriveSpeed;

    public DriveSubsystem()
    {
        m_masterLeft.configFactoryDefault(); 
        m_masterLeft.setInverted(DriveConstants.kMasterLeftInvert); 

        m_followerLeft.configFactoryDefault(); 
        m_followerLeft.setInverted(DriveConstants.kFollowerLeftOppose);

        m_masterRight.configFactoryDefault(); 
        m_masterRight.setInverted(DriveConstants.kMasterRightInvert);

        m_followerRight.configFactoryDefault(); 
        m_followerRight.setInverted(DriveConstants.kMasterRightOppose);
    }

    public void periodic() {

    }

    public void arcadeDrive(double straight, double left, double right) {
        leftDriveSpeed = DriveConstants.kSpeedLimitFactor * (straight + left - right) / (1 - ControllerConstants.kDeadzone);
        rightDriveSpeed = DriveConstants.kSpeedLimitFactor * (straight - left + right)
            / (1 - ControllerConstants.kDeadzone);
        tankDrive(leftDriveSpeed, rightDriveSpeed);
    }

    /**
     * @param leftSpeed  Left motors percent output
     * @param rightSpeed Right motors percent output
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        m_masterLeft.set(ControlMode.PercentOutput, leftSpeed);
        m_masterRight.set(ControlMode.PercentOutput, rightSpeed);

        m_followerLeft.set(ControlMode.PercentOutput, m_masterLeft.getMotorOutputPercent());
        m_followerRight.set(ControlMode.PercentOutput, m_masterRight.getMotorOutputPercent());
    }

    public void setPosition() {
        
    }
}