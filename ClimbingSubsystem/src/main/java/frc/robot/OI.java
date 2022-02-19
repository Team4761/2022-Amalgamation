package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.SpeedControllerGroup; arcadeDrive
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    //cap extension and retraction?
    //NOTE: move/merge some of OI into RobotMap
    //Untested values/ports
    //Controller Object
    public static Joystick controller = new Joystick(0);
    //Climbing arm extend/retract - test for button numbers
    //Switch acts like two buttons
    public static JoystickButton armExtend = new JoystickButton(controller, 1);
    public static JoystickButton armRetract = new JoystickButton(controller, 2);
    //Pneumatics - climbing arm forward/backward
    public static JoystickButton armForward = new JoystickButton(controller, 3);
    public static JoystickButton armBackward = new JoystickButton(controller, 4);

    //Motors - 2 motors (2 arms) RobotMap
    //change motor eventually. And ports, etc. CANSparkMax. Parameters
    public static CANSparkMax leftArmExtendMotor = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushed);
    public static CANSparkMax rightArmExtendMotor = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushed);

    //Pneumatics - 2 double solenoids (2 arms)
    //push out to tilt, pull back to straight
    //left and right solenoids both in one DIO - controlled as one
    public static DoubleSolenoid ClimberSolenoids = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
    //public static DoubleSolenoid leftClimberSolenoids = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3);
    //parameters - UPDATE THE CHANNEL LOCATIONS/MODULE TYPE ONCE DECIDED BY MECHANICAL/ELECTRICAL
    //ModuleType - based on type of pneumatics
    //channels in Digital IO which control up and down of pneumatic
}

//SpeedControllerGroup is unnecessary for climbing motors
//import edu.wpi.first.wpilibj.SpeedControllerGroup; for arcadeDrive
//public static SpeedControllerGroup climberMotors = new SpeedControllerGroup(leftArmExtendMotor, rightArmExtendMotor);

//Don't need to worry about encoders for climber motors