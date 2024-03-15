package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DistanceBasedAutoTurnCommand extends Command{
    private final DriveSubsystem m_driveSubsystem;
    private final double m_angle; 

    public DistanceBasedAutoTurnCommand(DriveSubsystem driveSubsystem, double angle) {
        m_driveSubsystem = driveSubsystem;
        m_angle = angle;
        addRequirements(m_driveSubsystem); 
    }

    public void execute() {
        m_driveSubsystem.turnAngle(m_angle);
    }
}
