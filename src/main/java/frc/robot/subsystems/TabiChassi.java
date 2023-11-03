package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TabiChassi extends SubsystemBase {
    public CANSparkMax leftCanSparkMax = new CANSparkMax(1, MotorType.kBrushless);
    public CANSparkMax rightCanSparkMax = new CANSparkMax(4, MotorType.kBrushless);
    public CANSparkMax leftFollowerCanSparkMax = new CANSparkMax(2, MotorType.kBrushless);
    public CANSparkMax rightFollowerCanSparkMax = new CANSparkMax(5, MotorType.kBrushless);
    public DifferentialDrive drivetrain = new DifferentialDrive(leftCanSparkMax, rightCanSparkMax);
    public RelativeEncoder m_leftEncoder = leftCanSparkMax.getEncoder();
    public RelativeEncoder m_rightEncoder = rightCanSparkMax.getEncoder();
    public TabiChassi(){
        m_leftEncoder.setPosition(0);
        m_rightEncoder.setPosition(0);
        for(CANSparkMax m : new CANSparkMax []{leftCanSparkMax, rightCanSparkMax,leftFollowerCanSparkMax,rightFollowerCanSparkMax}){
            m.setIdleMode(IdleMode.kBrake);
            m.setSmartCurrentLimit(240/4, 240/4);//240 is sensible current limit to chassis
            m.clearFaults();
        }
        leftCanSparkMax.setInverted(false);
        rightCanSparkMax.setInverted(true);



    //leftEncoder.setPositionConversionFactor(Chassis.EncoderDistancePerPulse);
    //leftEncoder.setVelocityConversionFactor(Chassis.EncoderDistancePerPulse / 60.0); // RPM to m/s
    //rightEncoder.setPositionConversionFactor(Chassis.EncoderDistancePerPulse);
    //rightEncoder.setVelocityConversionFactor(Chassis.EncoderDistancePerPulse / 60.0); // RPM to m/s

    //configure followers
    leftFollowerCanSparkMax.follow(leftCanSparkMax);
    rightFollowerCanSparkMax.follow(rightCanSparkMax);

    }

    public void arcadeDrive(double power, double turn) {
    drivetrain.arcadeDrive(power,turn);
    }
    
}
