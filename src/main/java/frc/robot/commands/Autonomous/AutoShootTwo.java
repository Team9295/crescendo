package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.commands.ScoreSpeakerCommand;
import frc.robot.commands.ArmCommands.SetArmSpeed;
import frc.robot.commands.ArmCommands.ZeroArm;
import frc.robot.commands.ArmCommands.StopArm;
import frc.robot.commands.ShooterCommands.SetIntakeSpeed;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShootTwo extends SequentialCommandGroup {
    public AutoShootTwo(DriveSubsystem driveSubsystem, ArmSubsystem armSubsystem,
            ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
                new SetArmSpeed(armSubsystem, -0.2).withTimeout(1.2),
                new StopArm(armSubsystem),
                new ZeroArm(armSubsystem),
                new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem, ArmState.SCORE_SPEAKER_AUTO_2),
                new WaitCommand(1.5),
                new TimeBasedAutoStraight(driveSubsystem, 0.5).withTimeout(1.2).raceWith(
                        new SetIntakeSpeed(intakeSubsystem, 0.75).withTimeout(1.2)),
                new TimeBasedAutoStraight(driveSubsystem, -0.5).withTimeout(1.3),
                new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem,
                        ArmState.SCORE_SPEAKER_AUTO_2));

    }
}
