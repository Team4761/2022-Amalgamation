package org.robockets.ClimbingCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.RobotMap;



public class ExtendArmCommand extends CommandBase {

    public ExtendArmCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        RobotMap.leftArmExtendMotor.set(0.6); //test speed
        RobotMap.rightArmExtendMotor.set(0.6);
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
