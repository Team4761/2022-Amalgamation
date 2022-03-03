package org.robockets.Shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.robockets.*;

public class ShooterSubsystem extends SubsystemBase {

    public void Shoot(double power){
      //artifact from eddie idk if this was supposed to work or not  RobotMap.robotShoot.setReferenceRPM(power);
        RobotMap.ShooterLeft.set(ControlMode.Velocity, power);
        RobotMap.ShooterRight.set(ControlMode.Velocity, power);
    }

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.

    /**
     * The Singleton instance of this ShooterSubsystem. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    private final static ShooterSubsystem INSTANCE = new ShooterSubsystem();

    /**
     * Returns the Singleton instance of this ShooterSubsystem. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code ShooterSubsystem.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static ShooterSubsystem getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new instance of this ShooterSubsystem. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    private ShooterSubsystem() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
        //       such as SpeedControllers, Encoders, DigitalInputs, etc.
    }

    @Override
    public void periodic() {
        // set motor speed
        //double speed = OI.activate_fly_wheel_max_speed.get() ? Varyings.flywheelMaxSpeed : 0.0;
        double speed = OI.shooterValue * Varyings.flywheelMaxSpeed; // This SHOULD be the left trigger
        if(!DriverStation.isAutonomous()) {
            RobotMap.ShooterLeft.set(-speed);
            RobotMap.ShooterRight.set(speed);
        }
        //RobotMap.robotShoot.set(speed); // This way if another command is running the shooter, we don't brute force set it to 0

        double insidewheelspeed = OI.activate_inner_wheel.get() ? 0.5 : 0.0;
        RobotMap.inside_wheel.set(insidewheelspeed);

        // adjust shooter
        double hoodShooterSpeed = OI.hood_raise.get() ? 1.0 : (OI.hood_lower.get() ? -1.0 : 0.0);
        hoodShooterSpeed *= Varyings.hoodAdjusterMaxSpeed;
        RobotMap.hood_adjust.set(hoodShooterSpeed);


    }

    // Output is position in encoder units changed over 100 milliseconds
    public void shootExact(double rpm) {
        double encoder_value_change_over_100_millis = (rpm * PIDConstants.TICKS_PER_REV) / 600.0; // This 600 comes from turning 1 minute into 100 milliseconds
        RobotMap.ShooterLeft.set(ControlMode.Velocity,encoder_value_change_over_100_millis * GearRatios.shooter);
        RobotMap.ShooterRight.set(ControlMode.Velocity,-encoder_value_change_over_100_millis * GearRatios.shooter);
    }

    /**
     * Get the shooter to fire an exact speed in meters per second
     * Reminder that the talonFX's velocity mode works by delta-pos in encoder ticks / 100 millis
     * @param mps meters per seond
     */
    public void shootExactMps(double mps) {
        double encoder_value = (mps / PIDConstants.shooter_width_meters) * 2.0 * Math.PI * PIDConstants.TICKS_PER_REV / 10.0; // this 10 comes from turning 1 second into 100 millis
        RobotMap.ShooterLeft.set(ControlMode.Velocity,encoder_value * GearRatios.shooter);
        RobotMap.ShooterRight.set(ControlMode.Velocity,-encoder_value * GearRatios.shooter);
    }

    public void AdjustHoodExact(double degrees) {
        //double encoder_value = (degrees / 360.0) * PIDConstants.TICKS_PER_REV / GearRatios.hoodAdjuster;
        // by default it's degrees, so that's perfect for us
        double rotations = degrees / 360.0;
        RobotMap.c_hood_adjust.setReference(rotations, CANSparkMax.ControlType.kPosition);
    }

    public void AdjustHoodExactRadians(double rad) {
        double rotations = rad / (2.0 * Math.PI);
        RobotMap.c_hood_adjust.setReference(rotations, CANSparkMax.ControlType.kPosition);
    }
}
