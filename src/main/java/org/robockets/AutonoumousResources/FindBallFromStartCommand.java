package org.robockets.AutonoumousResources;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.Drivetrain.pathWeaverInterpreter;

import java.io.IOException;


public class FindBallFromStartCommand extends CommandBase {

    private CommandBase RunningScript;

    public FindBallFromStartCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        //addRequirements();
    }

    @Override
    public void initialize() {
        try {
            switch (HAL.getAllianceStation()) {
                // Red
                case Red1:
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DBL_Red1);
                    break;
                case Red2:
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DBL_Red2);
                    break;
                case Red3:
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DBL_Red3);
                    break;

                // Blue
                case Blue1:
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DBL_Blue1);
                    break;
                case Blue2:
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DBL_Blue2);
                    break;
                case Blue3:
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DBL_Blue3);
                    break;
                default:
                    pathWeaverInterpreter.loadTrajectory(AutonomousOptions.DEFAULT_AUTO_PATH);
            }
        } catch (IOException e) {
            System.out.println("Could not load trajectory. Your robot must rot forever!");
        }
        RunningScript = pathWeaverInterpreter.autoPathWeaverCommand();
        RunningScript.initialize();
    }

    @Override
    public void execute() {
        // The running script command ISN'T actually running, but we DO need whatever JSON is loaded
        // So instead, we'll just run the script like this.
        // How evil of me.
        RunningScript.execute();
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return RunningScript.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        RunningScript.end(interrupted);
    }
}
