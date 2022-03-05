package org.robockets.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.RobotMap;


public class EVILAutoCommand extends CommandBase {
    //private final DrivetrainSubsystem drivetrainSubsystem = DrivetrainSubsystem.getInstance();

    // Because it's easier, we're going to run this command with a manual timer instead of frc's commands

    private double start_time;
    private boolean auto_complete = false;
    public EVILAutoCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        //addRequirements(Robot.m_drivetrain);
    }

    /**
     * The initial subroutine of a command.  Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {
        System.out.println("Running EVIL Auto command...");
        start_time = System.currentTimeMillis();
    }

    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     * (That is, it is called repeatedly until {@link #isFinished()}) returns true.)
     */

    @Override
    public void execute() {

        // Me praying that this is THE answer
        //double time_since_boot = Robot.commandScheduler.timeSinceScheduled(this);
        // turning milliseconds into seconds
        double time_since_boot = 0.001*(System.currentTimeMillis() - start_time);

        // If we're past 10 seconds, Just stop the whole command
        if(time_since_boot >= 10.0) {
            auto_complete = true;
            return;
        }
        if(time_since_boot < 2.0) {
            RobotMap.m_drive.tankDrive(-0.5,-0.5);
        }
        if(time_since_boot >= 3.0) {
            RobotMap.ShooterLeft.set(1.0);
            RobotMap.ShooterRight.set(-1.0);
            RobotMap.inside_wheel.set(1.0);
            RobotMap.inside_wheel2.set(-1.0);

        }
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
        RobotMap.m_drive.tankDrive(0.0,0.0);
    }
}
