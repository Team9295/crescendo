package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbPositionCommand extends Command {
    private ClimbSubsystem climbSubsystem;
    private Double position;

    public ClimbPositionCommand(ClimbSubsystem climbSubsystem, Double position) {
        this.climbSubsystem = climbSubsystem;
        this.position = position;
        addRequirements(climbSubsystem);
    }

    @Override
    public void execute() {
        climbSubsystem.setPosition(position);        
    }

    @Override
    public boolean isFinished() {
        return Math.abs(position - climbSubsystem.getPosition()) < 1.0;
    }
}
