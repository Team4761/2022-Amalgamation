package frc.robot.ClimbingCommands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;


public class ForwardArmCommand extends CommandBase {

    public ForwardArmCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        //tilt forward
        OI.rightClimberSolenoids.set(DoubleSolenoid.Value.kForward);
        OI.leftClimberSolenoids.set(DoubleSolenoid.Value.kForward);
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
