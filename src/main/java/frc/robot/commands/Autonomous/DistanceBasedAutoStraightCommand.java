package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DistanceBasedAutoStraightCommand extends Command{
    private final DriveSubsystem m_driveSubsystem;
    private final double m_distance; 

    public DistanceBasedAutoStraightCommand(DriveSubsystem driveSubsystem, double distance) {
        m_driveSubsystem = driveSubsystem;
        m_distance = distance;
        addRequirements(m_driveSubsystem); 
    }

    public void execute() {
        m_driveSubsystem.driveStraight(m_distance);
    }
}