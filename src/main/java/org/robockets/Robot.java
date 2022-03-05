// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.robockets;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.robockets.ClimbingCommands.ClimberSubsystem;
import org.robockets.Drivetrain.DrivetrainSubsystem;
import org.robockets.Drivetrain.EVILAutoCommand;
import org.robockets.Drivetrain.pathWeaverInterpreter;
import org.robockets.Intake.IntakeSubsystem;
import org.robockets.Shooter.ShooterSubsystem;

import java.io.IOException;


/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
    private String autoSelected;
    private final SendableChooser<String> chooser = new SendableChooser<>();
    private final SendableChooser<OI.ControllerMode> mode = new SendableChooser<>();

    // Command Scheduler
    public static CommandScheduler commandScheduler = CommandScheduler.getInstance();

    // Subsystems
    public static final DrivetrainSubsystem m_drivetrain = DrivetrainSubsystem.getInstance();
    public static final IntakeSubsystem m_intake = IntakeSubsystem.getInstance();
    public static final ShooterSubsystem m_shooter = ShooterSubsystem.getInstance();
    public static final ClimberSubsystem m_climber = ClimberSubsystem.getInstance();
	
	// OI
	public static OI m_oi;

    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit()
    {
        //Initialize the buttons for the first boot up
        m_oi = new OI();

        // adds all the auto options to it
        AutonomousOptions.addOptions(chooser);
        SmartDashboard.putData("Auto choices", chooser);

        // Reverse all the motors that are needed
        //RobotMap.leftArmExtendMotor.setInverted(true);
        RobotMap.m_rightMotors.setInverted(true);
        RobotMap.robotShoot.setInverted(true);

        commandScheduler.registerSubsystem(m_intake);
        commandScheduler.registerSubsystem(m_drivetrain);
        commandScheduler.registerSubsystem(m_climber);
        commandScheduler.registerSubsystem(m_shooter);

        // le almighty
        applyToShuffleboard();
    }

    /**
     * Now we will add any necessary additions to SmartDashboard
     * For the most part, anything added here will be simple modifiable values such as:
     * motor speeds, constants, button IDs, PID values,
     * Camera Streams, raspberry PI auto code selectors, Stuff like that!
     * Check Varyings.java for more
     */
    public void applyToShuffleboard() {
        SmartDashboard.putData("Drivetrain PID", Varyings.drivetrainpid);
        SmartDashboard.putData("Shooter PID", Varyings.shooterpid);
        SmartDashboard.putData("Hood Adjuster PID", Varyings.hoodAdjusterpid);
        SmartDashboard.putNumber("Max Drivetrain Speed", Varyings.drivetrainMaxSpeed);
        SmartDashboard.putNumber("Max Drivetrain Rotational Speed", Varyings.drivetrainMaxRotationSpeed);
        SmartDashboard.putNumber("Max Hood Adjuster Speed",Varyings.hoodAdjusterMaxSpeed);

        SmartDashboard.putNumber("Climber Speeds [Up]", Varyings.climberUpSpeed);
        SmartDashboard.putNumber("Climber Speeds [Down]", Varyings.climberDownSpeed);

        mode.addOption("One Xbox Controller", OI.ControllerMode.OneXbox);
        mode.setDefaultOption("Two Xbox Controllers", OI.ControllerMode.TwoXbox);
        mode.addOption("Xbox and Button Board", OI.ControllerMode.XboxAndButtonBoard);
        SmartDashboard.putData("Control Mode", mode);

        // Add field odometry and add xbox and button board graphic
        SmartDashboard.putData("Field",Varyings.m_field);
        /*try {
            BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\Xbox reference"));
            SmartDashboard.putData("Xbox Graphic",(Sendable)img);
        } catch(Exception e) {
            DriverStation.reportError("Could not open Xbox graphic!",e.getStackTrace());
        }*/
    }
    
    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic methods, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        updateModels();
        SmartDashboard.updateValues();
        m_oi.periodic();

    }

    /**
     * This function here is responsible for updating the data that is being sent from shuffeboard
     */
    private void updateModels() {
        // update drivetrain PID
        // NOTE: Sendable is more or less an array of bytes, which allows us to convert that into any object we want!
        Varyings.drivetrainpid = (PIDController) SmartDashboard.getData("Drivetrain PID");
        RobotMap.front_right.config_kP(0,Varyings.drivetrainpid.getP());
        RobotMap.front_right.config_kI(0,Varyings.drivetrainpid.getI());
        RobotMap.front_right.config_kD(0,Varyings.drivetrainpid.getD());

        RobotMap.back_right.config_kP(0,Varyings.drivetrainpid.getP());
        RobotMap.back_right.config_kI(0,Varyings.drivetrainpid.getI());
        RobotMap.back_right.config_kD(0,Varyings.drivetrainpid.getD());

        RobotMap.front_left.config_kP(0,Varyings.drivetrainpid.getP());
        RobotMap.front_left.config_kI(0,Varyings.drivetrainpid.getI());
        RobotMap.front_left.config_kD(0,Varyings.drivetrainpid.getD());

        RobotMap.back_left.config_kP(0,Varyings.drivetrainpid.getP());
        RobotMap.back_left.config_kI(0,Varyings.drivetrainpid.getI());
        RobotMap.back_left.config_kD(0,Varyings.drivetrainpid.getD());

        // update shooter PID
        Varyings.shooterpid = (PIDController) SmartDashboard.getData("Shooter PID");
        RobotMap.ShooterLeft.config_kP(0,Varyings.shooterpid.getP());
        RobotMap.ShooterLeft.config_kI(0,Varyings.shooterpid.getI());
        RobotMap.ShooterLeft.config_kD(0,Varyings.shooterpid.getD());

        RobotMap.ShooterRight.config_kP(0,Varyings.shooterpid.getP());
        RobotMap.ShooterRight.config_kI(0,Varyings.shooterpid.getI());
        RobotMap.ShooterRight.config_kD(0,Varyings.shooterpid.getD());

        // update hood adjuster PID
        Varyings.hoodAdjusterpid = (PIDController) SmartDashboard.getData("Hood Adjuster PID");
        RobotMap.c_hood_adjust.setP(Varyings.hoodAdjusterpid.getP());
        RobotMap.c_hood_adjust.setI(Varyings.hoodAdjusterpid.getI());
        RobotMap.c_hood_adjust.setD(Varyings.hoodAdjusterpid.getD());

        // update misc values
        Varyings.climberUpSpeed = SmartDashboard.getNumber("Climber Speeds [Up]",0.0);
        Varyings.climberDownSpeed = SmartDashboard.getNumber("Climber Speeds [Down]",0.0);
        Varyings.drivetrainMaxRotationSpeed = SmartDashboard.getNumber("Max Drivetrain Rotational Speed",1.0);
        Varyings.drivetrainMaxSpeed = SmartDashboard.getNumber("Max Drivetrain Speed",1.0);
        Varyings.hoodAdjusterMaxSpeed = SmartDashboard.getNumber("Max Hood Adjuster Speed",0.1);
        OI.selectedMode = mode.getSelected(); // one of these things is not like the other
    }
    
    /**
     * This autonomous (along with the chooser code above) shows how to select between different
     * autonomous modes using the dashboard. The sendable chooser code works with the Java
     * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
     * uncomment the getString line to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional comparisons to the switch structure
     * below with additional strings. If using the SendableChooser make sure to add them to the
     * chooser code above as well.
     */
    @Override
    public void autonomousInit()
    {
        // Attempting to shut down any commands that are unnecessary
        commandScheduler.unregisterSubsystem(m_intake);
        commandScheduler.unregisterSubsystem(m_drivetrain);
        commandScheduler.unregisterSubsystem(m_climber);
        commandScheduler.unregisterSubsystem(m_shooter);

        autoSelected = chooser.getSelected();
        // autoSelected = SmartDashboard.getString("Auto Selector", DEFAULT_AUTO);
        System.out.println("Auto selected: " + autoSelected);
        commandScheduler.cancelAll();
       // commandScheduler.unregisterSubsystem();
        //commandScheduler.run();

        //Evil copied code
        switch (autoSelected) {
            case AutonomousOptions.DEBUG_AUTO:
                try {
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DEBUG_AUTO_PATH);
                } catch (IOException e) {
                    DriverStation.reportError("Unable to Load the debug trajectory", true);
                    e.printStackTrace();
                }
                theRunningCommand = pathWeaverInterpreter.autoPathWeaverCommand();
                commandScheduler.schedule(theRunningCommand);
                break;
            case AutonomousOptions.DEFAULT_AUTO:
                // code goes here
                break;
            case AutonomousOptions.EASY_POINTS_AUTO:
                try {
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.EASY_POINTS_AUTO_PATH);
                } catch (IOException e) {
                    DriverStation.reportError("Unable to Load the debug trajectory", true);
                    e.printStackTrace();
                }
                theRunningCommand = pathWeaverInterpreter.autoPathWeaverCommand();
                commandScheduler.schedule(theRunningCommand);
                break;
            case AutonomousOptions.FIND_BALL_ON_GROUND:
                // raspberry pi code goes here
                break;
            case AutonomousOptions.SHOOT_BALL:
                // shooter code goes here
                break;
            case AutonomousOptions.EVIL_AUTO:
                theRunningCommand = (new EVILAutoCommand().withTimeout(10));
                commandScheduler.schedule(false,theRunningCommand); // maybe?
                break;
            default:
                // Put default auto code here
                break;
        }
        // This is for some reason returning a "loop overrun" exception?
        commandScheduler.run();
        //commandScheduler.
    }
    
    
    /** This method is called periodically during autonomous. */
    private String last_cycle_autoSelected = null;
    private CommandBase theRunningCommand;
    @Override
    public void autonomousPeriodic()
    {
        // Allows for swapping between autos
        //if(autoSelected == null) autoSelected = AutonomousOptions.EVIL_AUTO;
        /*autoSelected = chooser.getSelected();
        if(
                last_cycle_autoSelected == null ||
                !autoSelected.equals(last_cycle_autoSelected ==  null ? "Sample Text" :last_cycle_autoSelected)
        ) {
            // Stop the old command
            commandScheduler.cancel(theRunningCommand);
            switch (autoSelected) {
                case AutonomousOptions.DEBUG_AUTO:
                    try {
                        pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DEBUG_AUTO_PATH);
                    } catch (IOException e) {
                        DriverStation.reportError("Unable to Load the debug trajectory", true);
                        e.printStackTrace();
                    }
                    theRunningCommand = pathWeaverInterpreter.autoPathWeaverCommand();
                    commandScheduler.schedule(theRunningCommand);
                    break;
                case AutonomousOptions.DEFAULT_AUTO:
                    // code goes here
                    break;
                case AutonomousOptions.EASY_POINTS_AUTO:
                    try {
                        pathWeaverInterpreter.loadTrajectory(AutonomousOptions.EASY_POINTS_AUTO_PATH);
                    } catch (IOException e) {
                        DriverStation.reportError("Unable to Load the debug trajectory", true);
                        e.printStackTrace();
                    }
                    theRunningCommand = pathWeaverInterpreter.autoPathWeaverCommand();
                    commandScheduler.schedule(theRunningCommand);
                    break;
                case AutonomousOptions.FIND_BALL_ON_GROUND:
                    // raspberry pi code goes here
                    break;
                case AutonomousOptions.SHOOT_BALL:
                    // shooter code goes here
                    break;
                case AutonomousOptions.EVIL_AUTO:
                    theRunningCommand = (new EVILAutoCommand().withTimeout(2));
                    commandScheduler.schedule(theRunningCommand);
                    break;
                default:
                    // Put default auto code here
                    break;
            }
        }
        last_cycle_autoSelected = autoSelected;*/
    }

    /** This method is called once when teleop is enabled. */
    @Override
    public void teleopInit() {
        // Re-enabling all the subsystems after auto is complete.
        // This is complete and utter garbage code, and I mean that
        commandScheduler.registerSubsystem(m_intake);
        commandScheduler.registerSubsystem(m_drivetrain);
        commandScheduler.registerSubsystem(m_climber);
        commandScheduler.registerSubsystem(m_shooter);
    }
    
    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        // When it works :(
        commandScheduler.cancelAll();
        commandScheduler.run();
    }
    
    
    /** This method is called once when the robot is disabled. */
    @Override
    public void disabledInit() {}
    
    
    /** This method is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}
    
    
    /** This method is called once when test mode is enabled. */
    @Override
    public void testInit() {}
    
    
    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
