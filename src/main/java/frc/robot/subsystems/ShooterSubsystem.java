package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterConstants.ScoringTarget;

public class ShooterSubsystem extends SubsystemBase {
  private final VictorSPX shooterFarMotor = new VictorSPX(ShooterConstants.kShooterRightPort);
  private final VictorSPX shooterNearMotor = new VictorSPX(ShooterConstants.kShooterLeftPort);

  private ScoringTarget scoringTarget = ScoringTarget.SPEAKER;
  
  private double speedModifier = 0.0;

  public ShooterSubsystem() {
    shooterNearMotor.configFactoryDefault();
    shooterFarMotor.configFactoryDefault();
    shooterNearMotor.setNeutralMode(NeutralMode.Brake);
    shooterFarMotor.setNeutralMode(NeutralMode.Brake);

    shooterNearMotor.setInverted(ShooterConstants.kShooterNearInverted);
    shooterFarMotor.setInverted(ShooterConstants.kShooterFarInverted); // TODO: this should probably just be a follow
                                                                       // call?
    // shooterFarMotor.setInverted(InvertType.OpposeMaster);
  }

  public void setScoringTarget(ScoringTarget target) {
    scoringTarget = target;
  }

  public ScoringTarget getScoringTarget() {
    return scoringTarget;
  }
  
  public void incrementSpeedModifier() {
    speedModifier += 0.1;
  }

  public void decrementSpeedModifier() {
    speedModifier -= 0.1;
  }

  public void runShooter() {
    setSpeed(ShooterConstants.kShooterSpeakerSpeed + speedModifier);
  }

  public void stopShooter() {
    setSpeed(0);
  }
  public void printTarget() {
    SmartDashboard.putBoolean("targetAmp", scoringTarget == ScoringTarget.AMP);
    SmartDashboard.putBoolean("targetSpeaker", scoringTarget == ScoringTarget.SPEAKER);
  }
  
  public void printSpeed() {
    SmartDashboard.putNumber("shooterAmpSpeed", ShooterConstants.kShooterAmpSpeed + speedModifier);
    SmartDashboard.putNumber("shooterSpeakerSpeed", ShooterConstants.kShooterSpeakerSpeed + speedModifier);
  }

  public void periodic() {
    printTarget();
    printSpeed();
  }

  public void setSpeed(double speed) {
    shooterNearMotor.set(VictorSPXControlMode.PercentOutput, speed + speedModifier);
    shooterFarMotor.set(VictorSPXControlMode.PercentOutput, speed + speedModifier);
  }

}
