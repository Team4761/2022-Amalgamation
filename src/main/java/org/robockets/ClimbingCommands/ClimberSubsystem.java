package org.robockets.ClimbingCommands;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.robockets.OI;
import org.robockets.RobotMap;
import org.robockets.Varyings;

import static org.robockets.OI.move_climber;

public class ClimberSubsystem extends SubsystemBase {
    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.

    /**
     * The Singleton instance of this ClimberSubsystem. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    private final static ClimberSubsystem INSTANCE = new ClimberSubsystem();

    /**
     * Returns the Singleton instance of this ClimberSubsystem. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code ClimberSubsystem.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static ClimberSubsystem getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new instance of this ClimberSubsystem. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    private ClimberSubsystem() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
        //       such as SpeedControllers, Encoders, DigitalInputs, etc.


    }

    private boolean stat = false;
    private boolean last_cycle_button = false;
    @Override
    public void periodic() {

        //Toggle between
        //
        if(move_climber.get() != last_cycle_button && move_climber.get())
            stat = !stat;

        DoubleSolenoid.Value v = stat ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse;
        RobotMap.ClimberSolenoids.set(v);

        double ClimberSpeedLeft = (OI.climberValueLeft > 0.0) ? OI.climberValueLeft * Varyings.climberUpSpeed : OI.climberValueLeft * Varyings.climberDownSpeed; // up down on right stick
        double ClimberSpeedRight = (OI.climberValueRight > 0.0) ? OI.climberValueRight * Varyings.climberUpSpeed : OI.climberValueRight * Varyings.climberDownSpeed; // up down on right stick
        RobotMap.leftArmExtendMotor.set(-ClimberSpeedLeft);
        RobotMap.rightArmExtendMotor.set(-ClimberSpeedRight);
        //RobotMap.ArmExtendMotor.set(ClimberSpeed);

        last_cycle_button = move_climber.get();
    }
}
