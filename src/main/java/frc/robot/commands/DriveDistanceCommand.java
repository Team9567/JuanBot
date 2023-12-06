package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TabiChassi;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    var tempAverage = chassis.getEncoderDistance();
    SmartDashboard.putNumber("TotalMovement",  tempAverage);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return driveTimer.hasElapsed(5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassis.arcadeDrive(0,0);
    //Encoder encoder = new Encoder(0, 1);
  }

}

