package org.robockets;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import static org.robockets.PIDConstants.kTrackwidthMeters;

public class RobotMap {
    //I have to look into something called "Drive Constants"

    // Drivetrain motors. The Talons already have encoders inside them
    public static WPI_TalonFX front_left = new WPI_TalonFX(0);
    public static WPI_TalonFX front_right = new WPI_TalonFX(1);
    public static WPI_TalonFX back_left = new WPI_TalonFX(2);
    public static WPI_TalonFX back_right = new WPI_TalonFX(3);

    // The encoders
    public static CANCoder c_front_left = new CANCoder(0);
    public static CANCoder c_front_right = new CANCoder(1);
    public static CANCoder c_back_left = new CANCoder(2);
    public static CANCoder c_back_right = new CANCoder(3);

    // Drivetrain
    public static final MotorControllerGroup m_leftMotors = new MotorControllerGroup(front_left,back_left);
    public static final MotorControllerGroup m_rightMotors = new MotorControllerGroup(front_right,back_right);

    public static final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors,m_rightMotors);

    //GyroScope
    public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    //Tracking Shenanigans
    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);
    public static DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(gyro.getRotation2d());

    // Intake
    public static CANSparkMax back_intake = new CANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushless);
    //public static CANSparkMax front_intake = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    // 2 is push out, 3 is in
    public static DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,2,3);

    /**
     * YO!!! Braden Code!
     */

    //Motors - 2 motors (2 arms) RobotMap
    //change motor eventually. And ports, etc. CANSparkMax. Parameters
    public static CANSparkMax leftArmExtendMotor = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax rightArmExtendMotor = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);

    public static MotorControllerGroup ArmExtendMotor = new MotorControllerGroup(leftArmExtendMotor,rightArmExtendMotor);
    // 0 is angle down, 1 is angle up
    public static DoubleSolenoid ClimberSolenoids = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);

    /**
     * Eddies Code that HE finally Uploaded!!
     */

    public static WPI_TalonFX ShooterLeft = new WPI_TalonFX(7);
    public static WPI_TalonFX ShooterRight = new WPI_TalonFX(8);
	
	public static CANSparkMax hood_adjust = new CANSparkMax(11, CANSparkMaxLowLevel.MotorType.kBrushless);
	public static CANCoder c_hood_adjust = new CANCoder(11);
	
	public static CANSparkMax inside_wheel = new CANSparkMax(12, CANSparkMaxLowLevel.MotorType.kBrushless); // This can just run on it's own to be honest
    public static CANSparkMax inside_wheel2 = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    public static MotorControllerGroup robotShoot = new MotorControllerGroup(ShooterLeft, ShooterRight);


}
