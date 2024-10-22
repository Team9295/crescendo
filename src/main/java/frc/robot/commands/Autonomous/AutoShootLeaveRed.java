package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.commands.ScoreSpeakerCommand;
import frc.robot.commands.ArmCommands.SetArmSpeed;
import frc.robot.commands.ArmCommands.ZeroArm;
import frc.robot.commands.ArmCommands.StopArm;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShootLeaveRed extends SequentialCommandGroup {

    public AutoShootLeaveRed(DriveSubsystem driveSubsystem, ArmSubsystem armSubsystem,
            ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
                // testing the turn

                new SetArmSpeed(armSubsystem, -0.2).withTimeout(1.2),
                new StopArm(armSubsystem),
                new ZeroArm(armSubsystem),
                new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem, ArmState.SCORE_SPEAKER_AUTO_2),
                new TimeBasedAutoStraight(driveSubsystem, 0.2).withTimeout(1.2),
                new TimeBasedAutoTurn(driveSubsystem, 0.6, 0.5).withTimeout(1.5),
                new TimeBasedAutoStraight(driveSubsystem, 0.2).withTimeout(1.2)

        );

    }
}
