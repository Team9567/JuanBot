package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax.IdleMode;

public class TabiChassi extends SubsystemBase {
    public CANSparkMax leftCanSparkMax = new CANSparkMax(1, MotorType.kBrushless);
    public CANSparkMax rightCanSparkMax = new CANSparkMax(3, MotorType.kBrushless);
    public CANSparkMax leftFollowerCanSparkMax = new CANSparkMax(2, MotorType.kBrushless);
    public CANSparkMax rightFollowerCanSparkMax = new CANSparkMax(4, MotorType.kBrushless);
    public DifferentialDrive drivetrain = new DifferentialDrive(leftCanSparkMax, rightCanSparkMax);
   //Gets the encoder distances
    public RelativeEncoder leftEncoder = leftCanSparkMax.getEncoder();
    public RelativeEncoder rightEncoder = rightCanSparkMax.getEncoder();
    public RelativeEncoder leftFollowerEncoder = leftFollowerCanSparkMax.getEncoder();
    public RelativeEncoder rightFollowerEncoder = rightFollowerCanSparkMax.getEncoder();

    public TabiChassi() {
        for (CANSparkMax m : new CANSparkMax[] { leftCanSparkMax, rightCanSparkMax, leftFollowerCanSparkMax,rightFollowerCanSparkMax }) {
            m.restoreFactoryDefaults(false);
            m.clearFaults();

            //m.setIdleMode(IdleMode.kCoast);  // NO BRAKES!!!!!!
            m.setIdleMode(IdleMode.kBrake);  // BRAKES!!!

            m.setSmartCurrentLimit(240 / 4, 240 / 4);// 240 is sensible current limit to chassis.  12-7-23 NateO - Noticed /4 to limit current by 1/4 and slow robot down.
        }
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);

        leftCanSparkMax.setInverted(false);
        rightCanSparkMax.setInverted(true);

        // leftEncoder.setPositionConversionFactor(TabiChassi.EncoderDistancePerPulse);
        // leftEncoder.setVelocityConversionFactor(TabiChassi.EncoderDistancePerPulse /
        // 60.0); // RPM to m/s
        // rightEncoder.setPositionConversionFactor(TabiChassi.EncoderDistancePerPulse);
        // rightEncoder.setVelocityConversionFactor(TabiChassi.EncoderDistancePerPulse /
        // 60.0); // RPM to m/s

        // configure followers
        leftFollowerCanSparkMax.follow(leftCanSparkMax);
        rightFollowerCanSparkMax.follow(rightCanSparkMax);

    }
    public double getEncoderSpeed(){
        var leftSpeed = leftEncoder.getVelocity();
        var rightSpeed = rightEncoder.getVelocity();
        var averageSpeed = (rightSpeed + leftSpeed)/2;
        return averageSpeed;
    }

    //Takes the encoder positions and divides them by 2
    public double getEncoderDistance(){
        var left = leftCanSparkMax.getEncoder().getPosition(); // the left drive motor position
        var right = rightCanSparkMax.getEncoder().getPosition(); // the right drive motor position
        var average = (right + left)/2;  // our average position of both left and right drive motors combined: sum and divide by 2. 
        return average;  
    }

    public void arcadeDrive(double power, double turn) {
        drivetrain.arcadeDrive(power * power, turn * -1);
        //drivetrain.arcadeDrive(power / 4, turn / 4);
    }

    public void periodic() {
        // Average position from drive motors from the get encoder distance command
        SmartDashboard.putNumber("AverageDistance", getEncoderDistance());
        SmartDashboard.putNumber("AverageSpeed", getEncoderSpeed());
        // Currents from drive motors
        SmartDashboard.putNumber("Chassis/leftLeaderCurrent", leftCanSparkMax.getOutputCurrent());
        SmartDashboard.putNumber("Chassis/rightLeaderCurrent", rightCanSparkMax.getOutputCurrent());
        SmartDashboard.putNumber("Chassis/leftFollowerCurrent", leftFollowerCanSparkMax.getOutputCurrent());
        SmartDashboard.putNumber("Chassis/rightFollowerCurrent", rightFollowerCanSparkMax.getOutputCurrent());
        // Positions from drive motors
        SmartDashboard.putNumber("Chassis/leftLeader", leftEncoder.getPosition());
        SmartDashboard.putNumber("Chassis/rightLeader", rightEncoder.getPosition());
        SmartDashboard.putNumber("Chassis/leftFollower", leftFollowerEncoder.getPosition());
        SmartDashboard.putNumber("Chassis/rightFollower", rightFollowerEncoder.getPosition());
    }
}
     