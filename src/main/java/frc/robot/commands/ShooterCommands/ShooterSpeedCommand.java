package frc.robot.commands.ShooterCommands.Shooter;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSpeedCommand extends Command {

	private final ShooterSubsystem m_shooterSubsystem;

	/**
	 * Drive using speed inputs as a percentage output of the motor
	 * 
	 * @param shooterSubsystem The subsystem to be used
	 * @param speedStraight  Supplier of straight speed
	 * @param speedLeft      Supplier of left speed
	 * @param speedRight     Supplier of right speed
	 */
	public ShooterSpeedCommand(ShooterSubsystem ShooterSubsystem, Supplier<Double> speedStraight) {
		addRequirements(m_shooterSubsystem);
	}

	/**
	 * Update the motor outputs
	 */
	public void execute() {
		
		m_shooterSubsystem.arcadeDrive(speedStraight, speedLeft, speedRight);
	}
}