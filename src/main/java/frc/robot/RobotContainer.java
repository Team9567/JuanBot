package frc.robot;


import frc.robot.subsystems.TabiChassi;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveDistanceCommand;




public class RobotContainer {

    // SUBSYSTEMS
    //
    public TabiChassi chassis = new TabiChassi();
    
    // 
    // ROBOT COMMAND DEFINITIONS
    // 
    // JOYSTICK AND BUTTON ASSIGNMENTS
    //
    //Do not reassign ports in code: Always reassign  ports in your
    //local driver station to match these.
    public Joystick driver = new Joystick(0);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      //Configure our autonomous commands, and make sure drive team can select what they want
  
        
        chassis.setDefaultCommand(
          // this one's really basic, but needed to get systems moving right away.
          new RunCommand(
            ()->{chassis.arcadeDrive(-driver.getRawAxis(1),driver.getRawAxis(2));}
            ,chassis)
          );
      }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

        return new DriveDistanceCommand(chassis);
        

  
}

}