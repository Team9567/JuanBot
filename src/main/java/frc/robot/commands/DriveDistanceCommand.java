package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TabiChassi;
import edu.wpi.first.wpilibj.Timer;

public class DriveDistanceCommand extends CommandBase {
  private TabiChassi chassis;
  private Timer driveTimer;

  /** Creates a new ChassisDriveArcade. */
  public DriveDistanceCommand(TabiChassi chassis) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.chassis = chassis;
    addRequirements(chassis);
    this.driveTimer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    chassis.arcadeDrive(0.32, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassis.arcadeDrive(0,0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return driveTimer.hasElapsed(5);
  }
}

