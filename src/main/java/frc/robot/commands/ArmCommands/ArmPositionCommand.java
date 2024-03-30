package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ArmSubsystem;

public class ArmPositionCommand extends InstantCommand {
    private ArmSubsystem armSubsystem;
    private Double position;

    public ArmPositionCommand(ArmSubsystem armSubsystem, Double position) {
        this.armSubsystem = armSubsystem;
        this.position = position;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        armSubsystem.setPosition(position);        
    }
}
