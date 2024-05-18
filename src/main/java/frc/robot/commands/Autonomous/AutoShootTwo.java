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

public class AutoShootTwo extends SequentialCommandGroup{
    public AutoShootTwo(DriveSubsystem driveSubsystem, ArmSubsystem armSubsystem, 
    ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
            new ArmSpeedCommand(armSubsystem, () -> -0.2).withTimeout(1.2),
            new StopArmCommand(armSubsystem),
            new ArmZeroPositionCommand(armSubsystem),
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem, ArmState.SCORE_SPEAKER_AUTO_2),
            new WaitCommand(1.5), 
            new TimeBasedAutoStraightCommand(driveSubsystem, 0.5).withTimeout(1.2).raceWith(
                new IntakeSpeedCommand(intakeSubsystem, 0.75).withTimeout(1.2)
            ),
            //don't know if they need timeout and for how long
            new TimeBasedAutoStraightCommand(driveSubsystem, -0.5).withTimeout(1.3),
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem, ArmState.SCORE_SPEAKER_AUTO_2)
            // new TimeBasedAutoTurnCommand(driveSubsystem, 0.6, 0.5).withTimeout(2)
            );

    }
}
