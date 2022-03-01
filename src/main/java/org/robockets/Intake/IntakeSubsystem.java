package org.robockets.Intake;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.robockets.OI;
import org.robockets.RobotMap;

public class IntakeSubsystem extends SubsystemBase {

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.

    private final boolean OUT = false;
    private final boolean IN = true;
    private boolean stat = IN;

    /**
     * The Singleton instance of this IntakeSubsystem. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    private final static IntakeSubsystem INSTANCE = new IntakeSubsystem();

    /**
     * Returns the Singleton instance of this IntakeSubsystem. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code IntakeSubsystem.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static IntakeSubsystem getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new instance of this IntakeSubsystem. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    private IntakeSubsystem() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
        //       such as SpeedControllers, Encoders, DigitalInputs, etc.
    }

    @Override
    public void periodic() {
        // This SHOULD be the up and down axis on the second stick
        double speed = OI.auto.getRawAxis(3);
		
        // This way, setting the speed will make both intakes pull in, or both intakes shoot out
        RobotMap.back_intake.set(speed);
        RobotMap.front_intake.set(-speed);

        //Toggle between Intake being pushed out or pulled
        DoubleSolenoid.Value v = stat ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse;
        RobotMap.intakeSolenoid.set(v);

        //if a button is pressed, change stat
        //stat = !stat;
    }

}

