package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Timer;


public class TimeBasedAutoTurnCommand extends Command{
    private final DriveSubsystem m_driveSubsystem;
    private final double m_speed;
    private final double m_direction; 

    public TimeBasedAutoTurnCommand(DriveSubsystem driveSubsystem, double speed, double direction) {
        addRequirements(driveSubsystem);
        m_driveSubsystem = driveSubsystem;
        m_direction = direction; 
        m_speed = speed;
    }

    @Override
    public void execute() {
         m_driveSubsystem.tankDrive(m_speed * m_direction, m_speed * -m_direction);
    }

    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.tankDrive(0, 0);
    }
}
