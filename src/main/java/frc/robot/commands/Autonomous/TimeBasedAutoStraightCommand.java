package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

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
        double speedToSet = m_speed; //+ 0.2;
        if (m_speed < 0) {
            speedToSet = speedToSet; // - 0.4;
        }
        m_driveSubsystem.tankDrive(m_speed, speedToSet);
        // when going forwar need to add backward subtract
    }

    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.tankDrive(0, 0);
    }
}
