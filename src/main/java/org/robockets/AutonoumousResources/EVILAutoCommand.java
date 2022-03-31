package org.robockets.AutonoumousResources;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.Drivetrain.pathWeaverInterpreter;
import org.robockets.Robot;
import org.robockets.RobotMap;
import org.robockets.Varyings;

import java.io.IOException;


public class EVILAutoCommand extends CommandBase {
    //private final DrivetrainSubsystem drivetrainSubsystem = DrivetrainSubsystem.getInstance();
    private double start_time;
    private boolean auto_complete = false;
    private final double MAX_TIME = 15.0;
    private CommandBase RunningPath;

    double s = 6.0;

    // The almighty
    private final boolean SEXY_AUTO = false;

    // Because it's easier, we're going to run this command with a manual timer instead of frc's commands

    /**
     * Returns a double inequality condition
     * @param min minimum value that the number can be
     * @param max maximum value that the number can be
     * @param x the number that we're looking for
     * @return returns true if x is greater than min and less than max
     */
    private boolean isInBetween(double min, double max, double x) {
        return x >= min && x <= max;
    }
    private void printf(Object o) {
        System.out.println(o);
    }

    public EVILAutoCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        //addRequirements(Robot.m_drivetrain);
        try {
            pathWeaverInterpreter.loadTrajectory(AutonomousOptions.TRAJECTORY_DIR + "EVILAUTO" + AutonomousOptions.FILE_EXTENSION);
        } catch (IOException e) {
            System.out.println("Can't load :(");
        }
        RunningPath = pathWeaverInterpreter.autoPathWeaverCommand();
    }

    /**
     * The initial subroutine of a command.  Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {
        System.out.println("Running EVIL Auto command...");
        start_time = System.currentTimeMillis();

        RobotMap.front_left.set(ControlMode.Position,PIDConstants.METERS_TO_TICKS(0.0));
        RobotMap.front_right.set(ControlMode.Position,PIDConstants.METERS_TO_TICKS(0.0));
        RobotMap.back_left.set(ControlMode.Position,PIDConstants.METERS_TO_TICKS(0.0));
        RobotMap.back_right.set(ControlMode.Position,PIDConstants.METERS_TO_TICKS(0.0));

        //RobotMap.front_left.set
        // Shimmie the intake super quick

        if(SEXY_AUTO) {
            RunningPath.withTimeout(MAX_TIME);
            withTimeout(MAX_TIME);
        }
    }

    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     * (That is, it is called repeatedly until {@link #isFinished()}) returns true.)
     */

    private String final_status = "Not running";
    @Override
    public void execute() {
        //System.out.print("Drivetrain Encoder Distance: " + RobotMap.front_left.getSelectedSensorPosition());
        //System.out.println(", Expected Meters: " + 2 * Math.PI * PIDConstants.r_wheel_meters * RobotMap.front_left.getSelectedSensorPosition() / 4096 );

        // turning milliseconds into seconds
        double time_since_boot = 0.001*(System.currentTimeMillis() - start_time);
        //System.out.println("Time Since boot: " + time_since_boot);

        if(SEXY_AUTO) {

            //TODO: Replace with Limelight Shenanigans
            if(isInBetween(8.0,MAX_TIME,time_since_boot))
                Robot.m_shooter.shootExact(5000);

            // Basic values that we don't care about precision with
            RobotMap.back_intake.set(-1.0);
            RobotMap.intakeSolenoid.set(DoubleSolenoid.Value.kForward);

            // Prep eberything else before we prep the middle wheel
            if(isInBetween(2.0,MAX_TIME,time_since_boot))
                RobotMap.inside_wheel2.set(-1.0);

            RunningPath.execute();
            auto_complete = RunningPath.isFinished();

        } else {
            // If we're past MAX_TIME seconds, Just stop the whole command
            if(time_since_boot >= MAX_TIME) {
                //System.out.println("Complete!");
                final_status = "Complete!";
                auto_complete = true;
                // Just skip everything
            } else {
                // 1. Move back to exit the start
                if (isInBetween(0.0, 2.0, time_since_boot)) {
                    final_status = "Moving back...";
                    RobotMap.m_drive.tankDrive(0.5, 0.5);
                }
                // 2. Start up the Shooter
                if (isInBetween(2.0, 7.0, time_since_boot)) {
                    final_status = "Starting Shooter...";
                    double speed = 1.0;
                    //RobotMap.ShooterLeft.set(speed);
                    //RobotMap.ShooterRight.set(-speed);
                    Robot.m_shooter.shootExact(9000);
                }
                // 3. Activate upinator
                if (isInBetween(4.0, 7.0, time_since_boot)){
                    final_status = "Activating upinator and Shooting!";
                    RobotMap.inside_wheel2.set(-1.0);
                    RobotMap.inside_wheel.set(1.0);
                    RobotMap.back_intake.set(-1.0);
                }
                // 4. push intake down and run it
                if (isInBetween(6.0, 8.5, time_since_boot)) {
                    final_status = "Pushing Down intake...";
                    //RobotMap.intakeSolenoid.set(DoubleSolenoid.Value.kForward);
                }
                // 5. Move back to get the second ball
                /*if (isInBetween(7.5, 9.0, time_since_boot)) {
                    final_status = "Moving back...";
                    RobotMap.inside_wheel2.set(-1.0);
                    RobotMap.m_drive.tankDrive(0.5, 0.5);
                }*/
               /* // 6. Go forward to get in shooting position
               if (isInBetween(8.0, 9.5, time_since_boot)) {
                    final_status = "Moving forward...";
                    RobotMap.m_drive.tankDrive(-0.5,-0.5);
                }
                // 7. Start up the Shooter
                if (isInBetween(9.5, MAX_TIME, time_since_boot)) {
                    final_status = "Running flywheels...";
                    double speed = 1.0;
                    RobotMap.ShooterLeft.set(speed);
                    RobotMap.ShooterRight.set(-speed);
                }
                // 8. Move the upinator to shoot the ball out!
                if (isInBetween(9.5, MAX_TIME, time_since_boot)) {
                    final_status = "Firing!";
                    RobotMap.inside_wheel.set(1.0);
                    RobotMap.inside_wheel2.set(-1.0);
                    //RobotMap.back_intake.set(-1.0);
                }*/
            }
        }

        Varyings.autoStatus = final_status;
    }

    /**
     * <p>
     * Returns whether this command has finished. Once a command finishes -- indicated by
     * this method returning true -- the scheduler will call its {@link #end(boolean)} method.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Hard coding this command to always
     * return true will result in the command executing once and finishing immediately. It is
     * recommended to use * {@link edu.wpi.first.wpilibj2.command.InstantCommand InstantCommand}
     * for such an operation.
     * </p>
     *
     * @return whether this command has finished.
     */
    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return auto_complete;
    }

    /**
     * The action to take when the command ends. Called when either the command
     * finishes normally -- that is it is called when {@link #isFinished()} returns
     * true -- or when  it is interrupted/canceled. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the command.
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {
        System.out.println("Ending!");
        RobotMap.inside_wheel.set(0.0);
        RobotMap.inside_wheel2.set(0.0);
        Robot.m_shooter.shootExact(0);
        RobotMap.m_drive.tankDrive(0.0,0.0);

        if(SEXY_AUTO) RunningPath.end(interrupted);
    }
}
