package frc.robot.commands.ArmCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSpeedCommand extends Command{
    private final ArmSubsystem m_ArmSubsystem;
    private final Supplier<Double> m_speed; 

    public ArmSpeedCommand(ArmSubsystem ArmSubsystem, Supplier<Double> speed) {
        m_ArmSubsystem = ArmSubsystem;
        m_speed = speed; 
        addRequirements(m_ArmSubsystem); 
    }

    public void execute() {
        m_ArmSubsystem.setSpeed(m_speed.get() * ArmConstants.kSpeedLimitFactor);
    }

    public void end(boolean interrupted) {
        m_ArmSubsystem.setSpeed(0);
        //IDK if I need this
        //m_ArmSubsystem.setPosition(m_ArmSubsystem.getPosition());
    }
}
