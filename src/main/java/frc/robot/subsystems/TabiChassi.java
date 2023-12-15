package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TabiChassi extends SubsystemBase {
    public CANSparkMax leftCanSparkMax = new CANSparkMax(1, MotorType.kBrushless);
    public CANSparkMax rightCanSparkMax = new CANSparkMax(3, MotorType.kBrushless);
    public CANSparkMax leftFollowerCanSparkMax = new CANSparkMax(2, MotorType.kBrushless);
    public CANSparkMax rightFollowerCanSparkMax = new CANSparkMax(4, MotorType.kBrushless);
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
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);

        leftCanSparkMax.setInverted(false);
        rightCanSparkMax.setInverted(true);
        leftFollowerCanSparkMax.setIdleMode(IdleMode.kCoast);
        rightFollowerCanSparkMax.setIdleMode(IdleMode.kCoast);    

        // leftEncoder.setPositionConversionFactor(TabiChassi.EncoderDistancePerPulse);
        // leftEncoder.setVelocityConversionFactor(TabiChassi.EncoderDistancePerPulse /
        // 60.0); // RPM to m/s
        // rightEncoder.setPositionConversionFactor(TabiChassi.EncoderDistancePerPulse);
        // rightEncoder.setVelocityConversionFactor(TabiChassi.EncoderDistancePerPulse /
        // 60.0); // RPM to m/s


        leftFollowerCanSparkMax.follow(leftCanSparkMax);
        rightFollowerCanSparkMax.follow(rightCanSparkMax);

    }

    public void arcadeDrive(double power, double turn) {
    drivetrain.arcadeDrive(power / 2  ,turn / 2 * -1);
    }

    public void periodic() {
        SmartDashboard.putNumber("Chasis/leftLeader", leftCanSparkMax.getOutputCurrent());
        SmartDashboard.putNumber("Chasis/rightLeader", rightCanSparkMax.getOutputCurrent());
        SmartDashboard.putNumber("Chasis/leftFollower", leftFollowerCanSparkMax.getOutputCurrent());
        SmartDashboard.putNumber("Chasis/rightFollower", rightFollowerCanSparkMax.getOutputCurrent());

        SmartDashboard.putNumber("Chasis/leftLeader", leftEncoder.getPosition());
        SmartDashboard.putNumber("Chasis/rightLeader", rightEncoder.getPosition());
        SmartDashboard.putNumber("Chasis/leftFollower", leftFollowerEncoder.getPosition());
        SmartDashboard.putNumber("Chasis/rightFollower", rightFollowerEncoder.getPosition());
    }
}
