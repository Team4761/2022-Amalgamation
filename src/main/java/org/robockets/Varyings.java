package org.robockets;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;

/**
 * This class is responsible for holding all the variables that can and will be
 * modified by shuffleboard and the user
 * Easier and cleaner to put it in a new class rather than merge it with Robot and go
 * "Wait where did this variable come from"?
 */
public class Varyings {

    // FUN FACT: These PID controllers aren't actually used, we just want the 3 numbers
    public static PIDController drivetrainpid = new PIDController(0.6,0,0.3);
    public static PIDController shooterpid = new PIDController(0.6,0,0.3);
    public static PIDController hoodAdjusterpid = new PIDController(0.7,0,0.5);

    public static Field2d m_field = new Field2d();

    public static double flywheelMaxSpeed = 1.0;
    public static double drivetrainMaxSpeed = 0.75;
    public static double drivetrainMaxRotationSpeed = 0.65;
    public static double hoodAdjusterMaxSpeed = 0.1;

    //TODO: Condition Brody into not realizing that these values are going up every round
    public static double climberUpSpeed = 0.6;
    public static double climberDownSpeed = 0.4;

    public static String autoStatus = "Not running";
}
