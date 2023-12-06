package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TabiChassi extends SubsystemBase {
    public CANSparkMax leftCanSparkMax = new CANSparkMax(5, MotorType.kBrushless);
    public CANSparkMax rightCanSparkMax = new CANSparkMax(8, MotorType.kBrushless);
    public CANSparkMax leftFollowerCanSparkMax = new CANSparkMax(6, MotorType.kBrushless);
    public CANSparkMax rightFollowerCanSparkMax = new CANSparkMax(1, MotorType.kBrushless);
    public DifferentialDrive drivetrain = new DifferentialDrive(leftCanSparkMax, rightCanSparkMax);
    public RelativeEncoder leftEncoder = leftCanSparkMax.getEncoder();
    public RelativeEncoder rightEncoder = rightCanSparkMax.getEncoder();
    public RelativeEncoder leftFollowerEncoder = leftFollowerCanSparkMax.getEncoder();
    public RelativeEncoder rightFollowerEncoder = rightFollowerCanSparkMax.getEncoder();

    public TabiChassi() {
        for (CANSparkMax m : new CANSparkMax[] { leftCanSparkMax, rightCanSparkMax, leftFollowerCanSparkMax,rightFollowerCanSparkMax }) {
            m.restoreFactoryDefaults(false);
            m.clearFaults();

            m.setIdleMode(IdleMode.kCoast);
            // m.setIdleMode(IdleMode.kBrake);

            m.setSmartCurrentLimit(240 / 4, 240 / 4);// 240 is sensible current limit to chassis
        }
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);

        leftCanSparkMax.setInverted(false);
        rightCanSparkMax.setInverted(true);
        leftFollowerCanSparkMax.setInverted(false);
        rightFollowerCanSparkMax.setInverted(false);

        // leftEncoder.setPositionConversionFactor(TabiChassi.EncoderDistancePerPulse);
        // leftEncoder.setVelocityConversionFactor(TabiChassi.EncoderDistancePerPulse /
        // 60.0); // RPM to m/s
        // rightEncoder.setPositionConversionFactor(TabiChassi.EncoderDistancePerPulse);
        // rightEncoder.setVelocityConversionFactor(TabiChassi.EncoderDistancePerPulse /
        // 60.0); // RPM to m/s

        // configure followers
        //leftFollowerCanSparkMax.follow(leftCanSparkMax);
        //rightFollowerCanSparkMax.follow(rightCanSparkMax);

    }

    public double getEncoderDistance(){
        var left = leftCanSparkMax.getEncoder().getPosition();
        var right = rightCanSparkMax.getEncoder().getPosition();
        var average = (right + left)/2;
        return average;        
    }

    public void arcadeDrive(double power, double turn) {
        drivetrain.arcadeDrive(power, turn);
        //drivetrain.arcadeDrive(power / 4, turn / 4);
    }

    public void periodic() {
        SmartDashboard.putNumber("Chasis/leftLeaderCurrent", leftCanSparkMax.getOutputCurrent());
        SmartDashboard.putNumber("Chasis/rightLeaderCurrent", rightCanSparkMax.getOutputCurrent());
        SmartDashboard.putNumber("Chasis/leftFollowerCurrent", leftFollowerCanSparkMax.getOutputCurrent());
        SmartDashboard.putNumber("Chasis/rightFollowerCurrent", rightFollowerCanSparkMax.getOutputCurrent());

        SmartDashboard.putNumber("Chasis/leftLeader", leftEncoder.getPosition());
        SmartDashboard.putNumber("Chasis/rightLeader", rightEncoder.getPosition());
        SmartDashboard.putNumber("Chasis/leftFollower", leftFollowerEncoder.getPosition());
        SmartDashboard.putNumber("Chasis/rightFollower", rightFollowerEncoder.getPosition());
    }
}
     