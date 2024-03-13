package frc.robot.commands.ArmCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSpeedCommand extends CommandBase{
    private final ArmSubsystem m_ArmSubsystem;
    private final Supplier<Double> m_speed; 

    public ArmSpeedCommand(ArmSubsystem ArmSubsystem, Supplier<Double> speed) {
        m_ArmSubsystem = ArmSubsystem;
        m_speed = speed; 
        addRequirements(m_ArmSubsystem); 
    }

    public void execute() {
        double speed = Math.abs(m_speed.get()) > ControllerConstants.kDeadzone
                ? m_speed.get()
                : 0; 
        m_ArmSubsystem.setSpeed(speed * ArmConstants.kSpeedLimitFactor);
    }

    public void end(boolean interrupted) {
        m_ArmSubsystem.setSpeed(0);
        //IDK if I need this
        //m_ArmSubsystem.setPosition(m_ArmSubsystem.getPosition());
    }
}
