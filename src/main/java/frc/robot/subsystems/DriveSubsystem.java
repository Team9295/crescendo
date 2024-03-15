package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ControllerConstants;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        m_masterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, DriveConstants.kSlotID, 10);

        m_followerLeft.follow(m_masterLeft);

        m_followerLeft.configFactoryDefault();
        m_followerLeft.setInverted(DriveConstants.kFollowerLeftOppose);

        m_masterRight.configFactoryDefault();
        m_masterRight.setInverted(DriveConstants.kMasterRightInvert);
        m_masterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, DriveConstants.kSlotID, 10);

        m_followerRight.follow(m_masterRight);

        m_followerRight.configFactoryDefault();
        m_followerRight.setInverted(DriveConstants.kFollowerRightOppose);
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

    public void setPosition(double leftPosition, double rightPosition) {
        m_masterLeft.setSelectedSensorPosition(leftPosition);
        m_masterRight.setSelectedSensorPosition(rightPosition);
    }

    
    // -------------------- Auto Methods --------------------
    /**
     * 
     * @param distance Distance in FEET
     */
    public void driveStraight(double distance) {
        
        m_masterLeft.set(TalonSRXControlMode.Position, distance * DriveConstants.kTicksPerFoot);
        m_masterRight.set(TalonSRXControlMode.Position, distance * DriveConstants.kTicksPerFoot);
    }

    /**
     * 
     * @param angle Postive clockwise, Negative counterclockwise
     */
    public void turnAngle(double angle) {
        double decimalAngle = angle / 360;
        double distance = decimalAngle * DriveConstants.kAngleMultiplier;
        m_masterLeft.set(TalonSRXControlMode.Position, distance * DriveConstants.kTicksPerFoot);
        m_masterRight.set(TalonSRXControlMode.Position, -distance * DriveConstants.kTicksPerFoot);
    }
}