package frc.robot.commands;

import edu.wpilibj2.command.commandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class StopClimb extends CommandBase {
    private final Climb climb;

    public StopClimb(Climb subsystem) {
        climb = subsystem; 
        addRequirements(climb);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        climb.stopClimb();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
