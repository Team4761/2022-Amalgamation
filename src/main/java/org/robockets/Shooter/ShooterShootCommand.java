package org.robockets.Shooter;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.OI;
import org.robockets.Robot;
import org.robockets.RobotMap;
import org.robockets.mathstuff;


public class ShooterShootCommand extends CommandBase {

    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public NetworkTableEntry ty = table.getEntry("ty");

    public ShooterShootCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        //If all shoots arent making mess with this variable accordingly
        double frictionMakeup = 1.0;
        //Aim Assist Hacks
        double y = 2.44;
        //Owen put distance from limelight
        double x = mathstuff.getDistanceTower(ty.getDouble(0.0));
        
        double theta = 45;
        double rtheta = Math.toRadians(theta);
        
        double cos = Math.cos(rtheta);
        
        double velocity = mathstuff.findVelocity(ty.getDouble(0.0));
        double rpm = (30*velocity)/((22/7)*(mathstuff.r_wheel_meters/39.37));
        
        //regular
        double radius = 2;
        double d = Math.sin(rtheta);

        double Hspeedms = Math.sqrt((9.81*5.32)/(d*d));
        double Lspeedms = Math.sqrt((9.81*3.68)/(d*d));

        double Hrpm = (30*Hspeedms)/((22/7)*(radius/39.37));
        double Lrpm = (30*Lspeedms)/((22/7)*(radius/39.37));
        
        //rename Button1, Button2, Button3 to whatever the buttons wanted for shooting are named in OI.java
        boolean testing = false; //TODO so the buttons don't work the way the code had og and i don't have a way to do this without making 3 commands so for now its just for variable distance
        if(testing){
          //?  RobotMap.robotShoot.Shoot(Lspeed * frictionMakeup);
            Robot.m_shooter.Shoot(Lrpm * frictionMakeup);
        } else if (testing){
          //?  RobotMap.robotShoot.Shoot(Hspeed * frictionMakeup);
            Robot.m_shooter.Shoot(Hrpm * frictionMakeup);
        } else if(testing == false){
          //?  RobotMap.robotShoot.Shoot(rpm * frictionMakeup);
            Robot.m_shooter.Shoot(rpm * frictionMakeup);
        } else {
          //?  RobotMap.robotShoot.Shoot(0);
            Robot.m_shooter.Shoot(0);
        }
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
