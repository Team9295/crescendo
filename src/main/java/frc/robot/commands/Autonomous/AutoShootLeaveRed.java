package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ScoreSpeakerCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShootLeaveRed extends SequentialCommandGroup {


    public AutoShootLeaveRed(DriveSubsystem driveSubsystem, ArmSubsystem armSubsystem, 
            ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem), 
            new TimeBasedAutoStraightCommand(driveSubsystem, 0.2).withTimeout(1.0),
            new TimeBasedAutoTurnCommand(driveSubsystem, 0.2, 0.5).withTimeout(0.4),
            new TimeBasedAutoStraightCommand(driveSubsystem, 0.2).withTimeout(1.0)

        );

    }
}
