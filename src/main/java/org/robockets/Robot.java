// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.robockets;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.robockets.ClimbingCommands.ClimberSubsystem;
import org.robockets.Drivetrain.DrivetrainSubsystem;
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

    // Command Scheduler
    private CommandScheduler commandScheduler = CommandScheduler.getInstance();

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
        // adds all the auto options to it
        // Yes I COULD have all this in the same class, but it's nicer when it's separated
        AutonomousOptions.addOptions(chooser);
        SmartDashboard.putData("Auto choices", chooser);
		
		m_oi = new OI();
    }
    
    
    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic methods, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {}
    
    
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
        autoSelected = chooser.getSelected();
        // autoSelected = SmartDashboard.getString("Auto Selector", DEFAULT_AUTO);
        System.out.println("Auto selected: " + autoSelected);
    }
    
    
    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic()
    {
        switch (autoSelected)
        {
            case AutonomousOptions.DEBUG_AUTO:
                try {
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DEBUG_AUTO_PATH);
                } catch (IOException e) {
                    DriverStation.reportError("Unable to Load the debug trajectory",true);
                    e.printStackTrace();
                }
                pathWeaverInterpreter.autoPathWeaverCommand();
                break;
            case AutonomousOptions.DEFAULT_AUTO:
                // code goes here
                break;
            case AutonomousOptions.EASY_POINTS_AUTO:
                try {
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.EASY_POINTS_AUTO_PATH);
                } catch (IOException e) {
                    DriverStation.reportError("Unable to Load the debug trajectory",true);
                    e.printStackTrace();
                }
                break;
            case AutonomousOptions.FIND_BALL_ON_GROUND:
                // raspberry pi code goes here
                break;
            case AutonomousOptions.SHOOT_BALL:
                // shooter code goes here
                break;
            default:
                // Put default auto code here
                break;
        }
    }
    
    
    /** This method is called once when teleop is enabled. */
    @Override
    public void teleopInit() {}
    
    
    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        // When it works :(
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
