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

    int THRES = 5;

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
        double speed = 0.01;
        xAngle = tx.getDouble(0.0);

        if (xAngle > THRES) {
            RobotMap.m_drive.tankDrive(speed, -speed);

        } else if (xAngle < -THRES) {
            RobotMap.m_drive.tankDrive(-speed, speed);
        }
        //TODO make sure robot actaully goes in right direction mess with the - signs.
        SmartDashboard.putNumber("tx", tx.getDouble(0.0));

    }

    @Override
    public boolean isFinished() {

        if (xAngle > -THRES && xAngle < THRES) {
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
