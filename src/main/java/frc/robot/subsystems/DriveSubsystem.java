package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ControllerConstants;

public class DriveSubsystem extends SubsystemBase {
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
        m_masterLeft.setNeutralMode(NeutralMode.Brake);
        m_followerLeft.configFactoryDefault();
        m_followerLeft.follow(m_masterLeft);
        m_followerLeft.setInverted(InvertType.FollowMaster);

        m_followerLeft.setNeutralMode(NeutralMode.Brake);

        m_masterRight.configFactoryDefault();
        m_masterRight.setInverted(DriveConstants.kMasterRightInvert);
        m_masterRight.setNeutralMode(NeutralMode.Brake);
        m_followerRight.configFactoryDefault();
        m_followerRight.follow(m_masterRight);
        m_followerRight.setInverted(InvertType.FollowMaster);
        m_followerRight.setNeutralMode(NeutralMode.Brake);
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
        m_masterLeft.set(TalonSRXControlMode.PercentOutput, leftSpeed);
        m_masterRight.set(TalonSRXControlMode.PercentOutput, rightSpeed);
    }
}