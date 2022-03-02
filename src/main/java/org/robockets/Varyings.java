package org.robockets;

import edu.wpi.first.math.controller.PIDController;

/**
 * This class is responsible for holding all the variables that can and will be
 * modified by shuffleboard and the user
 * Easier and cleaner to put it in a new class rather than merge it with Robot and go
 * "Wait where did this variable come from"?
 */
public class Varyings {

    public static PIDController drivetrainpid = new PIDController(0.6,0,0.3);
    public static PIDController shooterpid = new PIDController(0.6,0,0.3);

    public static double flywheelMaxSpeed = 1.0;
    public static double drivetrainMaxSpeed = 1.0;
    public static double drivetrainMaxRotationSpeed = 1.0;

}
