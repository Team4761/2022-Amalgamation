package org.robockets.ClimbingCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.RobotMap;



public class RetractArmCommand extends CommandBase {

    public RetractArmCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        //Motors in reverse direction. Motors connected to winch (andymark climber in a box)
        RobotMap.rightArmExtendMotor.set(-0.6);  //test values
        RobotMap.leftArmExtendMotor.set(-0.6);
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
