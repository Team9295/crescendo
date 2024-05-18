// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
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
    public static final boolean kMasterLeftInvert = false;
    public static final boolean kMasterRightInvert = true;

    // from last year
    public static final int kSlotID = 0;
    public static final double kSpeedLimitFactor = 1.0;
    public static final double kTurningMultiplier = -1;
    public static final double kSpeedPowerMultipler = 1;
    public static final double kTurningPowerMultiper = 1;
    public static final double kFineTurningSpeed = 0.2;

    // set ports
    public static final int kMasterLeftPort = 1;
    public static final int kFollowerLeftPort = 2;
    public static final int kMasterRightPort = 3;
    public static final int kFollowerRightPort = 4;

    private static final double gearRatio = 8.46; // double check (8.46:1?)
    private static final double wheelDiameter = 6; // in inches
    public static final double kRotationsPerFoot = gearRatio * (12 / (wheelDiameter * Math.PI));
    public static final double kTicksPerFoot = kRotationsPerFoot * 4096; // 4X Decoding
    public static final double kAngleMultiplier = 1; // set value
  }

  public static final class ClimbConstants { // set ports
    public static final int kLeftClimbPort = 10;
    public static final int kRightClimbPort = 11;
    public static final boolean kLeftClimbInverted = false;
    public static final boolean kRightClimbInverted = false;
    public static final double kSpeedLimitFactor = 0.3;

    public static final double kToleranceRotations = 1.0;

    public enum ClimberState {
      ZERO(0.0),
      BOTTOM(-1.0),
      TOP(-146.4);

      final double position;

      ClimberState(double position) {
        this.position = position;
      }

      public double getPosition() {
        return position;
      }
    }
  }

  public static final class ArmConstants { // set values
    public static final int kArmLeft = 8;
    public static final boolean kMotorInverted1 = false;
    public static final int kArmRight = 7;
    public static final boolean kMotorInverted2 = true;
    public static final int kMinPosition = 0;
    public static final double kP = 6.8; //more p 
    public static final double kI = 0.0;//003;
    public static final double kIz = 0;
    public static final double kD = 6.0; //change as needed
    public static final double kFF = 0.07;
    public static final double kSpeedLimitFactor = 0.5;
    public static final double kToleranceRotations = 0.2;

    public enum ArmState {
      ZERO(0.0),
      RESTING(10.0),
      //SCORE_AMP(10.5),
      SCORE_AMP(17.2), 
      SCORE_SPEAKER(4.4),
      SCORE_SPEAKER_AUTO(4.8),
      SCORE_SPEAKER_AUTO_2(6.3);//5.4);

      final double position;

      ArmState(double position) {
        this.position = position;
      }

      public double getPosition() {
        return position;
      }
    }
  }

  public static final class ShooterConstants {
    // set ports
    public static final int kIntakePort = 9;
    public static final int kShooterRightPort = 5;
    public static final int kShooterLeftPort = 6;

    public static final double kIntakeDeadzone = 0.1;

    // set inverted
    public static final boolean kIntakeInverted = false;
    public static final boolean kShooterFarInverted = false;
    public static final boolean kShooterNearInverted = false;

    // set speed?
    public static final double kIntakeSpeed = 0.8;
    public static final double kShooterSpeakerSpeed = 0.7;
    public static final double kShooterAmpSpeed = 0.6;

    public enum ScoringTarget {
      AMP,
      SPEAKER
    }
  }

  public static final class LoggingConstants {
    public static final boolean[] kSubsystems = { true, true, true, true, true, true, true };
  }
}
