package frc.robot.commands.ClimbSpeedCommand; 

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.Constants.ClimbConstants;

public class ClimbSpeedCommand extends Command{
    private final ClimbSubsystem m_ClimbSubsystem;
    private final Supplier<Double> m_speed; 

}
