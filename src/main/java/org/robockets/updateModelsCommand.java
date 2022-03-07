package org.robockets;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * The reason that something as mundane as updating data is because this was overrunning the watch dog
 * <p>Ya ya it's not actually that important because going over 20 milliseconds isn't a big deal, but just in case</p>
 * <p>Now that it's a command, it can run asynchronously from the rest of the robot with ease</p>
 * yay me
 */

public class updateModelsCommand extends CommandBase {

    public updateModelsCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
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
    @Override
    public void execute() {
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
        OI.selectedMode = Robot.mode.getSelected(); // one of these things is not like the other
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
        return false;
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

    }
}
