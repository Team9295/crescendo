package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.commands.ScoreSpeakerCommand;
import frc.robot.commands.ArmCommands.ArmSpeedCommand;
import frc.robot.commands.ArmCommands.ArmZeroPositionCommand;
import frc.robot.commands.ArmCommands.StopArmCommand;
import frc.robot.commands.ShooterCommands.IntakeSpeedCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShootThreeBlue  extends SequentialCommandGroup{
    public AutoShootThreeBlue (DriveSubsystem driveSubsystem, ArmSubsystem armSubsystem, 
    ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
            new ArmSpeedCommand(armSubsystem, () -> -0.2).withTimeout(1.2),
            new StopArmCommand(armSubsystem),
            new ArmZeroPositionCommand(armSubsystem),
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem, ArmState.SCORE_SPEAKER_AUTO_2),
            //reduce waits?
            new WaitCommand(0.75), 

            new TimeBasedAutoStraightCommand(driveSubsystem, 0.5).withTimeout(1.2).raceWith(
                new IntakeSpeedCommand(intakeSubsystem, 0.75).withTimeout(1.2)
            ),
            // second note scored, can only do this if we get the distance score
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem, ArmState.SCORE_SPEAKER_AUTO_2),
            new WaitCommand(0.75), 
            // turns
            new TimeBasedAutoTurnCommand(driveSubsystem, 0.6, -0.5).withTimeout(2), 
            // drive + intake for third note
            new TimeBasedAutoStraightCommand(driveSubsystem, 0.5).withTimeout(0.4).raceWith(
                new IntakeSpeedCommand(intakeSubsystem, 0.75).withTimeout(1.2)
            ),
            // third note scored, same situation as ^^^
            new TimeBasedAutoTurnCommand(driveSubsystem, 0.6, -0.5).withTimeout(1.2), 
            new TimeBasedAutoStraightCommand(driveSubsystem, -0.5).withTimeout(0.7),
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem, ArmState.SCORE_SPEAKER_AUTO_2)
            );

    }
}
