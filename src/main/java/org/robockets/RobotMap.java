package org.robockets;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
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
    public static CANSparkMax back_intake = new CANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushed);
    public static CANSparkMax front_intake = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushed);
    // Is this DIO ports?
    public static Solenoid back_pull = new Solenoid(PneumaticsModuleType.CTREPCM,0);
    public static Solenoid front_pull = new Solenoid(PneumaticsModuleType.CTREPCM,1);

    /**
     * YO!!! Braden Code!
     */

    //Motors - 2 motors (2 arms) RobotMap
    //change motor eventually. And ports, etc. CANSparkMax. Parameters
    public static CANSparkMax leftArmExtendMotor = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushed);
    public static CANSparkMax rightArmExtendMotor = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushed);

    //Pneumatics - 2 double solenoids (2 arms)
    //push out to tilt, pull back to straight
    //left and right solenoids both in one DIO - controlled as one
    public static DoubleSolenoid ClimberSolenoids = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
    //public static DoubleSolenoid leftClimberSolenoids = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3);
    //parameters - UPDATE THE CHANNEL LOCATIONS/MODULE TYPE ONCE DECIDED BY MECHANICAL/ELECTRICAL
    //ModuleType - based on type of pneumatics
    //channels in Digital IO which control up and down of pneumatic

    /**
     * Eddies Code that HE finally Uploaded!!
     */

    public static WPI_TalonFX ShooterLeft = new WPI_TalonFX(7);
    public static WPI_TalonFX ShooterRight = new WPI_TalonFX(8);
	
	public static CANSparkMax hood_adjust = new CANSparkMax(11, CANSparkMaxLowLevel.MotorType.kBrushless);
	public static CANCoder c_hood_adjust = new CANCoder(11);
	
	public static CANSparkMax inside_wheel = new CANSparkMax(12, CANSparkMaxLowLevel.MotorType.kBrushed); // This can just run on it's own to be honest

    public static MotorControllerGroup robotShoot = new MotorControllerGroup(ShooterLeft, ShooterRight);


}
