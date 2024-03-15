package frc.robot.commands.ShooterCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSpeedCommand extends Command {

	private final ShooterSubsystem m_shooterSubsystem;
    private double speed;

	/**
	 * Drive using speed inputs as a percentage output of the motor
	 * 
	 * @param shooterSubsystem The subsystem to be used
	 * @param speed  Supplier of straight speed
	 */
	public ShooterSpeedCommand(ShooterSubsystem shooterSubsystem, double speed) {
		m_shooterSubsystem = shooterSubsystem;
		this.speed = speed;
		addRequirements(m_shooterSubsystem);
	}

	/**
	 * Update the motor outputs
	 */
	public void execute() {
		m_shooterSubsystem.setSpeed(speed);
	}
}