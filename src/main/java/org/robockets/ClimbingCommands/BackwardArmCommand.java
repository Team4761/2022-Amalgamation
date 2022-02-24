package org.robockets.ClimbingCommands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.RobotMap;


public class BackwardArmCommand extends CommandBase {

    public BackwardArmCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        //pull backwards to straight up position/close Solenoids
        RobotMap.ClimberSolenoids.set(DoubleSolenoid.Value.kReverse);
        //OI.rightClimberSolenoids.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
