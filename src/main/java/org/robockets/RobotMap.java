package org.robockets;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import static org.robockets.PIDConstants.kTrackwidthMeters;

public class RobotMap {

    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

    //RAMSETE Follower variables
    // TODO: What is this?
    public static final double kRamseteB = 2.0;
    public static final double kRamseteZeta = 0.7;

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
    public static DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(gyro.getRotation2d());

}