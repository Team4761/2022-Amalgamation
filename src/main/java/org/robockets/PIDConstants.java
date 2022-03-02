package org.robockets;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

// me when #define doesn't exist in java :(
public class PIDConstants {

    public static final double r_wheel_meters = 0.0508;
    public static final double width_meters = 0.63;

    //max rpm TODO: Find this!
    public static final double MAX_NEO_RPM = 1000;
    public static final double MAX_FALCON_RPM = 1000;

    public static final double robot_mass_kg = 59.42;

    public static double METERS_TO_REVS(double meters) {
        double circumference = Math.PI * 2.0 * r_wheel_meters;
        return meters * (TICKS_PER_REV / circumference);
    }

    public static double METERS_TO_ROTATIONS(double meters) {
        double circumference = Math.PI * 2.0 * r_wheel_meters;
        return (meters / circumference)*GearRatios.drivetrain;
    }

    // These are numbers that were obtained using the robot characterization toolsuite
    // TODO: Find these numbers using the robot characterization toolsuite!
    public static final double ksVolts = 0.22;
    public static final double kvVoltsSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;

    public static final double kMaxSpeedMetersPerSecond = 3.0;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3.0;

    //RAMSETE Follower variables Keep these numbers! They work good!
    public static final double kRamseteB = 2.0;
    public static final double kRamseteZeta = 0.7;

    // What is this? max auto speed?
    public static final double kPDriveVel = 8.5;

    public static final double kTrackwidthMeters = 0.9;

    //TODO: add encoder constants
    public static final int TICKS_PER_REV = 4096;
}
