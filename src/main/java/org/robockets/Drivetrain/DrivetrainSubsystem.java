package org.robockets.Drivetrain;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.robockets.GearRatios;
import org.robockets.OI;
import org.robockets.RobotMap;
//import edu.wpi.first.wpilibj2.examples.ramsetecommand.Constants.DriveConstants; //<-- I need this!

public class DrivetrainSubsystem extends SubsystemBase {

	private static final boolean TANK = true;
	private static final boolean ARCADE = false;
	
    public static boolean driveType = ARCADE;

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.
    /**
     * The Singleton instance of this DrivetrainSubsystem. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    private final static DrivetrainSubsystem INSTANCE = new DrivetrainSubsystem();

    /**
     * Returns the Singleton instance of this DrivetrainSubsystem. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code DrivetrainSubsystem.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static DrivetrainSubsystem getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new instance of this DrivetrainSubsystem. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    private DrivetrainSubsystem() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
        //       such as SpeedControllers, Encoders, DigitalInputs, etc.
    }

    @Override
    public void periodic() {
        Rotation2d angle = RobotMap.gyro.getRotation2d();
        //double l_dist = RobotMap.front_left.getSelectedSensorPosition();
        //double r_dist = RobotMap.front_right.getSelectedSensorPosition();
        double l_dist = RobotMap.c_front_left.getPosition() * GearRatios.drivetrain;
        double r_dist = RobotMap.c_front_right.getPosition() * GearRatios.drivetrain;
        RobotMap.m_odometry.update(angle,l_dist,r_dist);

        // I HATE Java!
        // I wanted to add a define preprocessor here so right_flight_stick not existing wouldn't through an error, but screw me!
		
        // There's a built in ramp feature so it will ALL work out
        //TODO: Check the axis values
        if(driveType == TANK){
            //double trans_right = OI.right_flight_stick.getRawAxis(1);
            //double trans_left = OI.left_flight_stick.getRawAxis(1);
            //RobotMap.m_drive.tankDrive(-trans_left,trans_right);
        }
        if(driveType == ARCADE){
            double rot = OI.xbox.getRawAxis(1);
            double trans = OI.xbox.getRawAxis(0);

            RobotMap.m_drive.arcadeDrive(trans,rot);
        }
    }

    /**
     * Everything below was modified from:
     * https://docs.wpilib.org/en/stable/docs/software/pathplanning/trajectory-tutorial/creating-drive-subsystem.html
     * Very helpful for the most part!
     */
    public void resetEncoders() {
        RobotMap.c_front_left.setPosition(0.0);
        RobotMap.c_front_right.setPosition(0.0);
    }

    public void resetOdometry(Pose2d pose) {
        RobotMap.m_odometry.resetPosition(pose,RobotMap.gyro.getRotation2d());
    }

    public Pose2d getPose() {
        return RobotMap.m_odometry.getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        //TODO: Check that these speeds are in the right unit!
        return new DifferentialDriveWheelSpeeds(
                RobotMap.c_front_left.getVelocity() * GearRatios.drivetrain,
                RobotMap.c_front_right.getVelocity() * GearRatios.drivetrain
        );
        //return new DifferentialDriveWheelSpeeds(RobotMap.c_front_left.getVelocity(),RobotMap.c_front_right.getVelocity());
    }

    public void tankDriveVolts(double l, double r) {
        RobotMap.front_left.setVoltage(l);
        RobotMap.back_left.setVoltage(l);

        RobotMap.front_right.setVoltage(r);
        RobotMap.back_right.setVoltage(r);

        RobotMap.m_drive.feed();
    }
}

