// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.ControllerConstants.Axis;
import frc.robot.Constants.ControllerConstants.Button;
import frc.robot.Constants.ControllerConstants.DPad;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ShooterConstants;
//import frc.robot.Constants.ShooterConstants.ScoringTarget;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.RunShooter;
//import frc.robot.commands.ScoreCommand;
import frc.robot.commands.ArmCommands.SetArmPosition;
import frc.robot.commands.ArmCommands.SetArmSpeed;
import frc.robot.commands.ArmCommands.ZeroArm;
import frc.robot.commands.Autonomous.AutoShootLeaveBlue;
import frc.robot.commands.Autonomous.AutoShootLeaveRed;
import frc.robot.commands.Autonomous.AutoShootThreeBlue;
import frc.robot.commands.Autonomous.AutoShootThreeRed;
import frc.robot.commands.Autonomous.AutoShootTwo;
import frc.robot.commands.ShooterCommands.SetIntakeSpeed;
//import frc.robot.commands.ShooterCommands.SetScoreTarget;
import frc.robot.commands.ShooterCommands.SetShooterSpeed;
import frc.robot.commands.ShooterCommands.StopShooter;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here
  private DriveSubsystem m_driveSubsystem;
  private IntakeSubsystem m_intakeSubsystem;
  private ShooterSubsystem m_shooterSubsystem;
  private ArmSubsystem m_armSubsystem;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick m_driverController = new Joystick(ControllerConstants.kDriverControllerPort);
  private final Joystick m_operatorController = new Joystick(ControllerConstants.kOperatorControllerPort);

  private final SendableChooser<Command> m_autoChooser = new SendableChooser<>();

  private boolean enableDrive = true;
  private boolean enableIntake = true;
  private boolean enableShooter = true;
  private boolean enableArm = true;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    if (enableDrive) {
      m_driveSubsystem = new DriveSubsystem();
    }
    if (enableIntake) {
      m_intakeSubsystem = new IntakeSubsystem();
    }
    if (enableShooter) {
      m_shooterSubsystem = new ShooterSubsystem();
    }
    if (enableArm) {
      m_armSubsystem = new ArmSubsystem();
    }

    // Configure the trigger bindings
    configureBindings();

    SmartDashboard.putData("Zero Arm", new ZeroArm(m_armSubsystem));

    // create options
    m_autoChooser.setDefaultOption(
        "ShootTwo",
        new AutoShootTwo(m_driveSubsystem, m_armSubsystem, m_shooterSubsystem, m_intakeSubsystem));

    m_autoChooser.addOption(
        "BlueShootLeave",
        new AutoShootLeaveBlue(m_driveSubsystem, m_armSubsystem, m_shooterSubsystem, m_intakeSubsystem));

    m_autoChooser.addOption(
        "RedShootLeave",
        new AutoShootLeaveRed(m_driveSubsystem, m_armSubsystem, m_shooterSubsystem, m_intakeSubsystem));

    m_autoChooser.addOption("RedShootThree",
        new AutoShootThreeRed(m_driveSubsystem, m_armSubsystem, m_shooterSubsystem, m_intakeSubsystem));

    m_autoChooser.addOption("BlueShootThree",
        new AutoShootThreeBlue(m_driveSubsystem, m_armSubsystem, m_shooterSubsystem, m_intakeSubsystem));

    SmartDashboard.putData("auto", m_autoChooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
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
          new ArcadeDrive(m_driveSubsystem, () -> -m_driverController.getRawAxis(Axis.kLeftY),
              () -> -1 * (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1),
              () -> -1 * (m_driverController.getRawAxis(Axis.kRightTrigger) + 1)));

      new JoystickButton(m_driverController, Button.kRightBumper).whileTrue(
          new ArcadeDrive(m_driveSubsystem,
              () -> 0.0, () -> -1 * DriveConstants.kTurningMultiplier * DriveConstants.kFineTurningSpeed,
              () -> DriveConstants.kTurningMultiplier * DriveConstants.kFineTurningSpeed));

      new JoystickButton(m_driverController, Button.kLeftBumper).whileTrue(
          new ArcadeDrive(m_driveSubsystem,
              () -> 0.0, () -> DriveConstants.kTurningMultiplier * DriveConstants.kFineTurningSpeed,
              () -> -1 * DriveConstants.kTurningMultiplier * DriveConstants.kFineTurningSpeed));
    }

    if (enableIntake) {
      new JoystickButton(m_driverController, Button.kX).whileTrue(
          new SetIntakeSpeed(m_intakeSubsystem, ShooterConstants.kIntakeSpeed)// )
      );
      new JoystickButton(m_driverController, Button.kY).whileTrue(
          new SetIntakeSpeed(m_intakeSubsystem, -1 * ShooterConstants.kIntakeSpeed));
    }

    if (enableShooter) {
      new JoystickButton(m_driverController, Button.kA)
          .onTrue(new RunShooter(m_intakeSubsystem, m_shooterSubsystem));
    }

    if (enableArm) {
      new JoystickButton(m_driverController, Button.kB)
          .onTrue(new SetArmSpeed(m_armSubsystem, 1.0));
      // .onTrue(new ArmPositionCommand(m_armSubsystem, ArmState.RESTING));
    }
    /*
     * =========================================
     * | OPERATOR CONTROLS |
     * =========================================
     */
    if (enableIntake) {
      new JoystickButton(m_operatorController, Button.kLeftBumper)
          .onTrue(new InstantCommand(() -> m_intakeSubsystem.decrementSpeedModifier(), m_intakeSubsystem));
      new JoystickButton(m_operatorController, Button.kRightBumper)
          .onTrue(new InstantCommand(() -> m_intakeSubsystem.incrementSpeedModifier(), m_intakeSubsystem));
    }
    if (enableShooter) {
      new Trigger(() -> m_driverController.getPOV() == DPad.kLeft)
          .onTrue(new SetArmPosition(m_armSubsystem, ArmState.SCORE_SPEAKER));
      // .onTrue(new SetScoringTargetCommand(m_shooterSubsystem,ScoringTarget.SPEAKER));
      new Trigger(() -> m_driverController.getPOV() == DPad.kRight)
          .onTrue(new SetArmPosition(m_armSubsystem, ArmState.SCORE_AMP));
      // .onTrue(new SetScoringTargetCommand(m_shooterSubsystem, ScoringTarget.AMP));
      new Trigger(() -> m_driverController.getPOV() == DPad.kUp)
          .onTrue(new SetArmPosition(m_armSubsystem, ArmState.SCORE_PASS));
      // .onTrue(new SetScoringTargetCommand(m_shooterSubsystem, ScoringTarget.PASS));
      new Trigger(() -> m_driverController.getPOV() == DPad.kDown)
          .onTrue(new SetArmPosition(m_armSubsystem, ArmState.ZERO));

      new Trigger(() -> Math.abs(m_operatorController.getRawAxis(Axis.kLeftTrigger)) > 0)
          .onTrue(new InstantCommand(() -> m_shooterSubsystem.decrementSpeedModifier(), m_shooterSubsystem));
      new Trigger(() -> Math.abs(m_operatorController.getRawAxis(Axis.kRightTrigger)) > 0)
          .onTrue(new InstantCommand(() -> m_shooterSubsystem.incrementSpeedModifier(), m_shooterSubsystem));

      new JoystickButton(m_operatorController, Button.kA)
          .whileTrue(new SetShooterSpeed(m_shooterSubsystem, -1 * ShooterConstants.kShooterAmpSpeed));
      new JoystickButton(m_operatorController, Button.kX)
          .onTrue(new StopShooter(m_shooterSubsystem));
    }
    if (enableArm) {
      new Trigger(() -> Math.abs(m_operatorController.getRawAxis(Axis.kLeftY)) > ControllerConstants.kDeadzone)
          .whileTrue(new SetArmSpeed(m_armSubsystem, -1 * m_operatorController.getRawAxis(Axis.kLeftY)));
      new JoystickButton(m_operatorController, Button.kB)
          .onTrue(new ZeroArm(m_armSubsystem));
    }
  }

  public Command getAutonomousCommand() {
    return m_autoChooser.getSelected();
  }

  public void periodic() {
    if (enableDrive) {
      m_driveSubsystem.periodic();
    }
    if (enableIntake) {
      m_intakeSubsystem.periodic();
    }
    if (enableShooter) {
      m_shooterSubsystem.periodic();
    }
    if (enableArm) {
      m_armSubsystem.periodic();
    }
  }
}
