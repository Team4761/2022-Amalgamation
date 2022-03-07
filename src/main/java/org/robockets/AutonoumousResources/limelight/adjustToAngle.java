package org.robockets.AutonoumousResources.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.Drivetrain.DrivetrainSubsystem;
import org.robockets.RobotMap;


public class adjustToAngle extends CommandBase {

    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public NetworkTableEntry tx = table.getEntry("tx");
    public static double xAngle;

    public adjustToAngle() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {
        DrivetrainSubsystem.isDrive = false;


    }

    //maxVoltage * rampingFactor * ((Math.abs(xAngle) / 30)+1)
    // This is literately just bang-bang control.
    @Override
    public void execute() {

        xAngle = tx.getDouble(0.0);

        if (xAngle > 1) {
            RobotMap.m_drive.tankDrive(0.2, -0.2);

        } else if (xAngle < -1) {
            RobotMap.m_drive.tankDrive(-0.2, 0.2);
        }
        //TODO make sure robot actaully goes in right direction mess with the - signs.
        SmartDashboard.putNumber("tx", tx.getDouble(0.0));

    }

    @Override
    public boolean isFinished() {

        if (xAngle > -1 && xAngle < 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        DrivetrainSubsystem.isDrive = true;

    }
}
