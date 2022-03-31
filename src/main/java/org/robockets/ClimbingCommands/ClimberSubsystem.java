package org.robockets.ClimbingCommands;


import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.robockets.OI;
import org.robockets.Robot;
import org.robockets.RobotMap;
import org.robockets.Varyings;

import static org.robockets.OI.move_climber;

public class ClimberSubsystem extends SubsystemBase {

    private boolean sexyPID = false;
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

        RobotMap.leftArmExtendMotor.getEncoder().setPosition(0.0);
        RobotMap.rightArmExtendMotor.getEncoder().setPosition(0.0);
        // If I were a good programmer I'd just put the PIDController and encoder in RobotMap
        // But I don't have time for that :(

        // Set PID
        if(Robot.RESET_EVERY_TIME) {
            double P = 0.4, I = 0.0, D = 0.2;

            RobotMap.leftArmExtendMotor.getPIDController().setP(P);
            RobotMap.leftArmExtendMotor.getPIDController().setI(I);
            RobotMap.leftArmExtendMotor.getPIDController().setD(D);

            RobotMap.rightArmExtendMotor.getPIDController().setP(P);
            RobotMap.rightArmExtendMotor.getPIDController().setI(I);
            RobotMap.rightArmExtendMotor.getPIDController().setD(D);

            // Set Ramp Factor
            int rf = 1;

            RobotMap.leftArmExtendMotor.setClosedLoopRampRate(rf);
            RobotMap.leftArmExtendMotor.setOpenLoopRampRate(rf);

            RobotMap.rightArmExtendMotor.setClosedLoopRampRate(rf);
            RobotMap.rightArmExtendMotor.setOpenLoopRampRate(rf);

            // Set Idle Mode and Motor mode
            RobotMap.leftArmExtendMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
            RobotMap.rightArmExtendMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

            RobotMap.leftArmExtendMotor.burnFlash();
            RobotMap.leftArmExtendMotor.burnFlash();
        }
    }

    private boolean stat = false;
    private boolean last_cycle_button = false;

    // Just add the velocities with respect to time to get the position
    // easier to do it this way than set both motors to velocity, and have the motors offset at all
    // +c calculus moment
    private double left_integrated_position = 0.0;
    private double right_integrated_position = 0.0;
    private double width = 0.1;

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

        left_integrated_position += width*ClimberSpeedLeft;
        right_integrated_position += width*ClimberSpeedRight;

        if(sexyPID) {
            // The only reason I want to do this is because both motors seem to be out of sync.
            RobotMap.leftArmExtendMotor.getPIDController().setReference(left_integrated_position, CANSparkMax.ControlType.kPosition);
            RobotMap.rightArmExtendMotor.getPIDController().setReference(right_integrated_position, CANSparkMax.ControlType.kPosition);

        } else {
            RobotMap.leftArmExtendMotor.set(ClimberSpeedLeft);
            RobotMap.rightArmExtendMotor.set(ClimberSpeedRight);
        }

        last_cycle_button = move_climber.get();
    }
}
