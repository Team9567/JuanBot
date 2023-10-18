// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive.arcadeDrive;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;

//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final CANSparkMax m_leftDrive = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax m_rightDrive = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final RelativeEncoder m_leftEncoder = m_leftDrive.getEncoder();
  private final RelativeEncoder m_rightEncoder = m_rightDrive.getEncoder();
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
  //private final XboxController m_controller = new XboxController(0);
  //Joystick exampleJoystick = new Joystick(0);
  private final Timer m_timer = new Timer();
  private final CommandJoystick joystick = new CommandJoystick(1);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightDrive.setInverted(true);
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("Left Drive Encoder", m_leftEncoder.getPosition());
    SmartDashboard.putNumber("Right Drive Encoder", m_rightEncoder.getPosition());
    SmartDashboard.putNumber("Left Drive Encoder Speed", m_leftEncoder.getVelocity());
    SmartDashboard.putNumber("Right Drive Encoder Speed", m_rightEncoder.getVelocity());
    // Drive for 2 seconds
    if (m_timer.get() < 5.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      m_robotDrive.arcadeDrive(0.03, 0.0, false); //0.03 right stops left moves //0.029 is last point of deadzone
    } else {
      m_robotDrive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    //m_robotDrive.arcadeDrive(-m_controller.getLeftY(), -m_controller.getRightX());
    double forward_speed = joystick.getRawAxis(1);
    double turn_speed = joystick.getRawAxis(0);

    m_robotDrive.arcadeDrive(forward_speed,turn_speed);
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
