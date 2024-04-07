package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants.ArmState;
import frc.robot.commands.ScoreSpeakerCommand;
import frc.robot.commands.ArmCommands.ArmSpeedCommand;
import frc.robot.commands.ArmCommands.ArmZeroPositionCommand;
import frc.robot.commands.ArmCommands.StopArmCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShootLeaveBlue extends SequentialCommandGroup {


    public AutoShootLeaveBlue(DriveSubsystem driveSubsystem, ArmSubsystem armSubsystem, 
            ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
            new ArmSpeedCommand(armSubsystem, () -> -0.2).withTimeout(1.2),
            new StopArmCommand(armSubsystem),
            new ArmZeroPositionCommand(armSubsystem),
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem, ArmState.SCORE_SPEAKER_AUTO_2), 
            new TimeBasedAutoStraightCommand(driveSubsystem, 0.2).withTimeout(1.2),
            new TimeBasedAutoTurnCommand(driveSubsystem, 0.2, -0.5).withTimeout(0.4),
            new TimeBasedAutoStraightCommand(driveSubsystem, 0.2).withTimeout(1.2)

        );

    }
}
