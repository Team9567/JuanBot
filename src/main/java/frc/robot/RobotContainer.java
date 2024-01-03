package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.DriveDistanceCommand;
import frc.robot.subsystems.TabiChassi;

public class RobotContainer {
  //Variables
  //private final Command m_complexAuto = new ComplexAut
  //private final Command m_complexAuto = new ComplexAuto(m_robotDrive, m_hatchSubsystem);
  //A chooser for autonomus Commands
  SendableChooser<Command> m_Chooser = new SendableChooser<Command>();

  // SUBSYSTEMS
  //
  public TabiChassi chassis = new TabiChassi();
  // final Command m_simpleAuto = new DriveDistanceCommand(AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, chassis);

  //
  // ROBOT COMMAND DEFINITIONS
  //
  // JOYSTICK AND BUTTON ASSIGNMENTS
  //
  // Do not reassign ports in code: Always reassign ports in your
  // local driver station to match these.
  public Joystick driver = new Joystick(0);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure our autonomous commands, and make sure drive team can select what
    // they want

    chassis.setDefaultCommand(
        // this one's really basic, but needed to get systems moving right away.
        new RunCommand(
            () -> {
              chassis.arcadeDrive(-driver.getRawAxis(1), driver.getRawAxis(2));
            }, chassis));

      //attach drive distance to button A

      m_Chooser.addOption("drive 5 feet", new DriveDistanceCommand(chassis));
  }

  public Command getAutonomousCommand() {

    var command = m_Chooser.getSelected();
    // An ExampleCommand will run in autonomous
    return command ;

  }

}