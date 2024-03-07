// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerConstants.Axis;
import frc.robot.Constants.ControllerConstants.Button;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDriveCommand;
//import frc.robot.commands.Autos;
import frc.robot.commands.ShooterCommands.Shooter.ShooterSpeedCommand;
import frc.robot.commands.ShooterCommands.Shooter.ShooterPositionCommand;
import frc.robot.commands.ShooterCommands.Intake.IntakeSpeedCommand;
import frc.robot.commands.ShooterCommands.Intake.IntakePositionCommand;
import frc.robot.commands.ArmCommands.ArmSpeedCommand;
import frc.robot.commands.ArmCommands.ArmPositionCommand;
import frc.robot.commands.ClimbCommands.ClimbPositionCommand;
import frc.robot.commands.ClimbCommands.ClimbSpeedCommand;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    /*
     * =========================================
     * | DRIVER CONTROLS |
     * =========================================
     */
    m_driveSubsystem.setDefaultCommand(
        new ArcadeDriveCommand(m_driveSubsystem, () -> -m_driverController.getRawAxis(Axis.kRightY),
            () -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
            () -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));
        
        m_driverController.rightTrigger(0.0, new ArcadeDriveCommand(m_driveSubsystem,
            () -> 0.0, () -> -DriveConstants.kFineTurningSpeed,
            () -> DriveConstants.kFineTurningSpeed));
        
        m_driverController.leftTrigger(0.0, new ArcadeDriveCommand(m_driveSubsystem,
            () -> 0.0, () -> DriveConstants.kFineTurningSpeed,
            () -> -DriveConstants.kFineTurningSpeed));
     /*
     * =========================================
     * | OPERATOR CONTROLS |
     * =========================================
     */
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
