package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class StopClimb extends Command {
    private final ClimbSubsystem m_climbSubsystem;

    public StopClimb(ClimbSubsystem subsystem) {
        m_climbSubsystem = subsystem;
        addRequirements(m_climbSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_climbSubsystem.stopClimb();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
