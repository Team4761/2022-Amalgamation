package org.robockets.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;


public class ShooterShootCommand extends CommandBase {

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
        double x;
        
        double theta = 45;
        double rtheta = Math.toRadians(theta);
        
        double cos = Math.cos(rtheta);
        
        double velocity = Math.sqrt((4.905*x*x)/(cos*cos*(y-(x*Math.tan(rtheta))));
        double rpm = (30*velocity)/((22/7)*(radius/39.37));
        
        //regular
        double radius = 2;
        double d = Math.sin(rtheta);

        double Hspeedms = Math.sqrt((9.81*5.32)/(d*d));
        double Lspeedms = Math.sqrt((9.81*3.68)/(d*d));

        double Hrpm = (30*Hspeedms)/((22/7)*(radius/39.37));
        double Lrpm = (30*Lspeedms)/((22/7)*(radius/39.37));
        
        //rename Button1, Button2, Button3 to whatever the buttons wanted for shooting are named in OI.java
        if(OI.Button1 = true){
            RobotMap.robotShoot.Shoot(Lspeed * frictionMakeup);
        } else if (OI.Button2 = true){
            RobotMap.robotShoot.Shoot(Hspeed * frictionMakeup);
        } else if(OI.Button2 = true){
            RobotMap.robotShoot.Shoot(rpm * frictionMakeup);
        } else {
            RobotMap.robotShoot.Shoot(0);
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
