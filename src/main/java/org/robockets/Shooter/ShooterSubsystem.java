package org.robockets.Shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.robockets.OI;
import org.robockets.Robot;
import org.robockets.RobotMap;

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
        double speed = OI.activate_fly_wheel_max_speed.get() ? 0.5 : 0.0;
        System.out.println(speed);
        if(!DriverStation.isAutonomous()) {
            RobotMap.ShooterLeft.set(-speed);
            RobotMap.ShooterRight.set(speed);
        }
            //RobotMap.robotShoot.set(speed); // This way if another command is running the shooter, we don't brute force set it to 0

        double insidewheelspeed = OI.activate_inner_wheel.get() ? 0.5 : 0.0;
        RobotMap.inside_wheel.set(insidewheelspeed);

        // adjust shooter
        double hoodShooterSpeed = OI.hood_raise.get() ? 1.0 : (OI.hood_lower.get() ? -1.0 : 0.0);
        hoodShooterSpeed *= 0.1;
        RobotMap.hood_adjust.set(hoodShooterSpeed);


    }
}
