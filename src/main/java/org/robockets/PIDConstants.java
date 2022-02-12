package org.robockets;

// me when #define doesn't exist in java :(
public class PIDConstants {

    public static final double kp = 0.0;
    public static final double ki = 0.0;
    public static final double kd = 0.0;

    public static final double r_wheel_meters = 0.05;
    public static final double width_meters = 0.6;

    public static double METERS_TO_REVS(double meters) {
        double circumference = Math.PI * 2.0 * r_wheel_meters;
        return meters * (TICKS_PER_REV / circumference);
    }

    // These are numbers that were obtained using the robot characterization toolsuite
    public static final double ksVolts = 0.22;
    public static final double kvVoltsSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;

    public static final double kMaxSpeedMetersPerSecond = 3.0;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3.0;

    public static final double kPDriveVel = 8.5;

    public static final double kTrackwidthMeters = 0.9;

    //TODO: add encoder constants
    public static final int TICKS_PER_REV = 4096;
}
