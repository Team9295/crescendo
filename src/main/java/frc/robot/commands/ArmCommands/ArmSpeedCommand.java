package frc.robot.commands.ArmCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSpeedCommand extends InstantCommand {
    private final ArmSubsystem m_ArmSubsystem;
    private final double m_speed; 

    public ArmSpeedCommand(ArmSubsystem ArmSubsystem, double speed) {
        m_ArmSubsystem = ArmSubsystem;
        m_speed = speed; 
        addRequirements(m_ArmSubsystem); 
    }

    public void execute() {
        double speed = m_speed;
        // speed = Math.min(speed, ArmConstants.kSpeedLimitFactor);
        // speed = Math.max(speed, -1 * ArmConstants.kSpeedLimitFactor);
        speed = Math.abs(speed) > ControllerConstants.kDeadzone
                        ? speed
                        : 0.0;

        //speed *= 0.3;

        m_ArmSubsystem.setSpeed(speed);
    }

    public void end(boolean interrupted) {
        //m_ArmSubsystem.setSpeed(0);
        //IDK if I need this
        //m_ArmSubsystem.setPosition(m_ArmSubsystem.getPosition());
    }
}
