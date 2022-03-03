package org.robockets.limelight;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.Drivetrain.DrivetrainSubsystem;
import org.robockets.RobotMap;



public class findTarget extends CommandBase {

    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public NetworkTableEntry tv = table.getEntry("tv");
    public boolean isTarget;

    public findTarget() {
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {
        isTarget = tv.getBoolean(false);
        DrivetrainSubsystem.isDrive = false;

    }

    @Override
    public void execute() {
        isTarget = tv.getBoolean(false);
        RobotMap.m_drive.arcadeDrive(0.0,0.3);
    }

    @Override
    public boolean isFinished() {
        if(isTarget){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.m_leftMotors.set(0);
        RobotMap.m_rightMotors.set(0);
        DrivetrainSubsystem.isDrive = true;

    }
}
