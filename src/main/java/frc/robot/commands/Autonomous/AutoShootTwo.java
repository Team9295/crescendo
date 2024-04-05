package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ScoreSpeakerCommand;
import frc.robot.commands.ShooterCommands.IntakeSpeedCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShootTwo extends SequentialCommandGroup{
    public AutoShootTwo(DriveSubsystem driveSubsystem, ArmSubsystem armSubsystem, 
    ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem),
            new WaitCommand(1.5), 
            new TimeBasedAutoStraightCommand(driveSubsystem, 0.5).withTimeout(1.0).raceWith(
                new IntakeSpeedCommand(intakeSubsystem, 0.75).withTimeout(1.0)
            ),
            //don't know if they need timeout and for how long
            new TimeBasedAutoStraightCommand(driveSubsystem, -0.5).withTimeout(1.0),
            new ScoreSpeakerCommand(armSubsystem, intakeSubsystem, shooterSubsystem)
            
            );

    }
}
