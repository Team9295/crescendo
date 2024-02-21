package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem{
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

