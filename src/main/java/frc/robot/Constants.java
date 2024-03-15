// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class ControllerConstants {
		public static final int kDriverControllerPort = 0;
		public static final int kOperatorControllerPort = 1;
		public static final double kDeadzone = .1;
		public static final double kTriggerDeadzone = .05;

		public static final class Axis {
			public static final int kLeftX = 0;
			public static final int kLeftY = 1;
			public static final int kRightX = 4;
			public static final int kLeftTrigger = 2;
			public static final int kRightTrigger = 3;
			public static final int kRightY = 5;
		}

		public static final class Button {
			public static final int kA = 1;
			public static final int kB = 2;
			public static final int kX = 3;
			public static final int kY = 4;
			public static final int kLeftBumper = 5;
			public static final int kRightBumper = 6;
			public static final int kLeftMenu = 7;
			public static final int kRightMenu = 8;
			public static final int kLeftTriggerButton = 9;
			public static final int kRightTriggerButton = 10;
		}

		public static final class DPad {
			public static final int kUp = 0;
			public static final int kRight = 90;
			public static final int kDown = 180;
			public static final int kLeft = 270;
		}
	}

  public static final class DriveConstants {
    public static final boolean kMasterLeftInvert = true;
    public static final boolean kFollowerLeftOppose = true;
    public static final boolean kMasterRightInvert = false; 
    public static final boolean kFollowerRightOppose = false; 

    //from last year
    public static final int kSlotID = 0; 
    public static final double kSpeedLimitFactor = .7; 
    public static final double kTurningMultiplier = 1; 
    public static final double kSpeedPowerMultipler = 1; 
    public static final double kTurningPowerMultiper = 1;
	public static final double kFineTurningSpeed = 0.075;

	// set ports
	public static final int kMasterLeftPort = 0;
    public static final int kFollowerLeftPort = 0;
    public static final int kMasterRightPort = 0;
    public static final int kFollowerRightPort = 0;
  }

  public static final class ClimbConstants { // set ports
	public static final int kLeftClimbPort = 0;
	public static final int kRightClimbPort = 0;
  }

  public static final class ArmConstants { // set port
	public static final int kMotorPort = 0;
	public static final boolean kMotorInverted = false;
    public static final int kMinPosition = 0;
    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kIz = 0;
    public static final double kD = 0;
    public static final double kFF = 0;
  }

  public static final class ShooterConstants {
	// set ports
	public static final int kIntakePort = 0;
	public static final int kShooterFarPort = 0;
	public static final int kShooterNearPort = 0;

	// set inverted
	public static final boolean kIntakeInverted = false;
	public static final boolean kShooterFarInverted = true;
	public static final boolean kShooterNearInverted = false;

	// set speed?
	public static final double kIntakeSpeed = 1;
	public static final double kShooterSpeed = 1;
  }
}
