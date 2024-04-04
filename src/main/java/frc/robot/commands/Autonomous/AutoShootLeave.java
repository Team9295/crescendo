package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ScoreSpeakerCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShootLeave extends SequentialCommandGroup {


    public AutoShootLeave(DriveSubsystem driveSubsystem, ArmSubsystem armSubsystem, 
            ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem), 
            new TimeBasedAutoStraightCommand(driveSubsystem, 0.5, 0.2),
            new TimeBasedAutoTurnCommand(driveSubsystem, 0.5, 0.2, 0.2)
        );

    }
}
