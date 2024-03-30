package frc.robot.commands.ShooterCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class IntakeSpeedCommand extends InstantCommand {

    private final IntakeSubsystem m_intakeSubsystem;
    private double speed;
    /**
	 * Drive using speed inputs as a percentage output of the motor
	 * 
	 * @param intakeSubsystem The subsystem to be used
	 * @param d  Supplier of speed
	 */
	public IntakeSpeedCommand(IntakeSubsystem intakeSubsystem, double d) {
        m_intakeSubsystem = intakeSubsystem;
        this.speed = d;
		addRequirements(m_intakeSubsystem);
	}

	/**
	 * Update the motor outputs
	 */
	public void execute() {
		m_intakeSubsystem.setSpeed(speed);
	}    
}
