package frc.robot.ClimbingCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.OI.*;


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
        rightArmExtendMotor.set(-0.6);  //test values
        leftArmExtendMotor.set(-0.6);
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
