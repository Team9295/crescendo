// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.ControllerConstants.Axis;
import frc.robot.Constants.ControllerConstants.Button;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.LoggingConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.Autonomous.*;
import frc.robot.commands.ShooterCommands.ShooterSpeedCommand;
import frc.robot.commands.ShooterCommands.IntakeSpeedCommand;
import frc.robot.commands.ArmCommands.ArmSpeedCommand;
import frc.robot.commands.ArmCommands.ArmPositionCommand;
// import climber commands
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here
  private DriveSubsystem m_driveSubsystem;
  private IntakeSubsystem m_intakeSubsystem;
  private ShooterSubsystem m_shooterSubsystem;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick m_driverController = new Joystick(ControllerConstants.kDriverControllerPort);
  private final Joystick m_operatorController = new Joystick(ControllerConstants.kOperatorControllerPort);

  private final SendableChooser<Command> m_autoChooser = new SendableChooser<>();

  private boolean enableDrive = true;
  private boolean enableIntake = true;
  private boolean enableShooter = false;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    if (enableDrive) { m_driveSubsystem = new DriveSubsystem(); }
    if (enableIntake) { m_intakeSubsystem = new IntakeSubsystem(); }
    if (enableShooter) { m_shooterSubsystem = new ShooterSubsystem(); }

    // Configure the trigger bindings
    configureBindings();

    
    // create options
    // m_autoChooser.addOption("Simple Forward Time Auto", new TimeBasedAutoStraightCommand(m_driveSubsystem, 1, 1));
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
    if (enableDrive) {
      m_driveSubsystem.setDefaultCommand(
        new ArcadeDriveCommand(m_driveSubsystem, () -> -m_driverController.getRawAxis(Axis.kRightY),
            () -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
            () -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2)
      );
        
      new JoystickButton(m_driverController, Button.kRightBumper).whileTrue(
        new ArcadeDriveCommand(m_driveSubsystem,
        () -> 0.0, () -> -1 * DriveConstants.kTurningMultiplier * DriveConstants.kFineTurningSpeed,
        () -> DriveConstants.kTurningMultiplier * DriveConstants.kFineTurningSpeed)
      );
      
      new JoystickButton(m_driverController, Button.kLeftBumper).whileTrue(
        new ArcadeDriveCommand(m_driveSubsystem,
          () -> 0.0, () -> DriveConstants.kTurningMultiplier * DriveConstants.kFineTurningSpeed,
          () -> -1 * DriveConstants.kTurningMultiplier * DriveConstants.kFineTurningSpeed)
      );
    }

    if (enableIntake) {
      m_intakeSubsystem.setDefaultCommand(new IntakeSpeedCommand(m_intakeSubsystem, () -> 0.0));

      new JoystickButton(m_driverController, Button.kX).whileTrue(
        new IntakeSpeedCommand(m_intakeSubsystem, () -> ShooterConstants.kIntakeSpeed)
      );
      
      new JoystickButton(m_driverController, Button.kY).whileTrue(
        new IntakeSpeedCommand(m_intakeSubsystem, () -> -1 *ShooterConstants.kIntakeSpeed)
      );
    }

    if (enableShooter) {
        new JoystickButton(m_driverController, Button.kLeftTriggerButton).whileTrue(
          new ShooterSpeedCommand(m_shooterSubsystem, ShooterConstants.kShooterSpeed)
        );
        new JoystickButton(m_driverController, Button.kLeftTriggerButton).whileFalse(
          new ShooterSpeedCommand(m_shooterSubsystem, 0)
        );
    }
     /*
     * =========================================
     * | OPERATOR CONTROLS |
     * =========================================
     */

  }

  public Command getAutonomousCommand() {
    return m_autoChooser.getSelected();
  }
}
