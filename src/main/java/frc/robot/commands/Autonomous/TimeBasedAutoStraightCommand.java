package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class TimeBasedAutoStraightCommand extends Command{
    private final DriveSubsystem m_driveSubsystem;
    private final double m_speed;

    public TimeBasedAutoStraightCommand(DriveSubsystem driveSubsystem, double speed) {
        addRequirements(driveSubsystem);
        m_driveSubsystem = driveSubsystem;
        m_speed = speed;
    }

    @Override
    public void execute() {
        m_driveSubsystem.tankDrive(m_speed, m_speed+0.2);
    }

    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.tankDrive(0, 0);
    }
}
