package org.robockets.AutonoumousResources;


import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.robockets.AutonoumousResources.limelight.adjustToAngle;
import org.robockets.Drivetrain.pathWeaverInterpreter;
import org.robockets.Robot;
import org.robockets.RobotMap;
import org.robockets.Shooter.ShooterShootCommand;
import org.robockets.Varyings;

import java.io.IOException;

import static org.robockets.AutonoumousResources.AutonomousOptions.TRAJECTORY_DIR;

public class MoveBackAndFireCommandGroup extends SequentialCommandGroup {

    /**
     * Load a trajectory that tells the robot to move backwards about 2 meters.
     * Helping hand from pathWeaverInterpreter.java
     */
    public MoveBackAndFireCommandGroup() {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new FooCommand(), new BarCommand());

        try {
            pathWeaverInterpreter.loadTrajectory(TRAJECTORY_DIR + "Back 2 m.wpilib.json"); //TODO: What unit is this in?
        } catch (IOException e) {
            DriverStation.reportError("Can't load file Back 2 m.wpilib.json. Is in the right location?",e.getStackTrace());
        }

        // Move Back
        addCommands(pathWeaverInterpreter.autoPathWeaverCommand().beforeStarting(() -> System.out.println("Loading Back 2 m.wpilib.json...")));

        // Align
        addCommands(new adjustToAngle());
        // Fire. It's just going to be an RPM thing, we DO NOT have the time to line things up any more
        addCommands(new FireBallWithDelay());
    }

    /**
     * Run the shooter, and the two inner wheels
     */
    static class ShootWillyNilly extends CommandBase {

        private boolean finished = false;
        public ShootWillyNilly() {
        }

        @Override
        public void initialize() {

        }

        @Override
        public void execute() {
            Robot.m_shooter.shootExact(1800); // this number KIND OF worked when we tried this before
            RobotMap.inside_wheel.set(1.0);
            RobotMap.inside_wheel2.set(-1.0);
        }

        @Override
        public boolean isFinished() {
            return finished;
        }

        @Override
        public void end(boolean isInterrupted) {
            System.out.println("Finished Autonomous!");
        }

    }

    static class FireBallWithDelay extends CommandBase {
        //private final DrivetrainSubsystem drivetrainSubsystem = DrivetrainSubsystem.getInstance();
        private double start_time;
        private boolean auto_complete = false;
        private final double MAX_TIME = 15.0;
        private CommandBase RunningPath;

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

        public FireBallWithDelay() {
        }

        /**
         * The initial subroutine of a command.  Called once when the command is initially scheduled.
         */
        @Override
        public void initialize() {
        }

        /**
         * The main body of a command.  Called repeatedly while the command is scheduled.
         * (That is, it is called repeatedly until {@link #isFinished()}) returns true.)
         */

        private String final_status = "Not running";
        @Override
        public void execute() {
            // start up
            double s = 6.0;
            // turning milliseconds into seconds
            double time_since_boot = 0.001*(System.currentTimeMillis() - start_time);
            //System.out.println("Time Since boot: " + time_since_boot);


                // If we're past MAX_TIME seconds, Just stop the whole command
                if(time_since_boot >= MAX_TIME) {
                    //System.out.println("Complete!");
                    final_status = "Complete!";
                    auto_complete = true;
                    // Just skip everything
                } else {
                    // 2. Start up the Shooter
                    if (isInBetween(s+0.0, s+6.0, time_since_boot)) {
                        final_status = "Starting Shooter...";
                        double speed = 1.0;
                        //RobotMap.ShooterLeft.set(speed);
                        //RobotMap.ShooterRight.set(-speed);
                        Robot.m_shooter.shootExact(1800);
                    }
                    // 3. Activate upinator
                    if (isInBetween(s+3.0, s+6.0, time_since_boot)){
                        final_status = "Activating upinator and Shooting!";
                        RobotMap.inside_wheel2.set(-1.0);
                        RobotMap.inside_wheel.set(1.0);
                        RobotMap.back_intake.set(-1.0);
                    }
                    // 4. push intake down and run it
                    if (isInBetween(s+6.0, s+7.5, time_since_boot)) {
                        final_status = "Pushing Down intake...";
                        //RobotMap.intakeSolenoid.set(DoubleSolenoid.Value.kForward);
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

}