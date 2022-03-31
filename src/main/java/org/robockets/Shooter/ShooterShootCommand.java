package org.robockets.Shooter;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.AutonoumousResources.PIDConstants;
import org.robockets.AutonoumousResources.mathstuff;
import org.robockets.Robot;
import org.robockets.RobotMap;


public class ShooterShootCommand extends CommandBase {

    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public NetworkTableEntry ty = table.getEntry("ty");

    public final double HEIGHT_LIMIT = 5.0; //meters

    private double vo = 0.0;
    private double angle = 0.0;

    public ShooterShootCommand() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    double Hrpm;
    double Lrpm;
    double rpm;
    double frictionMakeup = 1.0;

    @Override
    public void initialize() {
        //If all shoots arent making mess with this variable accordingly
        //Aim bot v2
        double h = HEIGHT_LIMIT;
        double d = mathstuff.getDistanceTower(ty.getDouble(0.0));

        angle = Math.atan(2.0*h/d);
        //for angle in degrees
        angle = (angle/(Math.PI * 2)) * 360;

        vo = Math.sqrt((9.81*4.0*(h*h)*(d*d))/2.0*h);
        vo = (30*vo)/((Math.PI)*(PIDConstants.r_wheel_meters/39.37)); // Is this used anywhere?

        //map
        //Robot.m_shooter.shootExact(vo * frictionMakeup);

        //Aim Assist Hacks
        double y = 2.44;
        //Owen put distance from limelight
        double x = mathstuff.getDistanceTower(ty.getDouble(0.0));

        double theta = angle;
        double rtheta = Math.toRadians(theta);

        double cos = Math.cos(rtheta);

        double velocity = mathstuff.findVelocity(ty.getDouble(0.0));
        rpm = (30*velocity)/((Math.PI)*(PIDConstants.r_wheel_meters/39.37));

        //regular
        double radius = 2;
        double din = Math.sin(rtheta);

        double Hspeedms = Math.sqrt((9.81*5.32)/(din*din));
        double Lspeedms = Math.sqrt((9.81*3.68)/(din*din));

        Hrpm = (30*Hspeedms)/((Math.PI)*(radius/39.37));
        Lrpm = (30*Lspeedms)/((Math.PI)*(radius/39.37));

        //RobotMap.c_hood_adjust.setReference(0.0, CANSparkMax.ControlType.kPosition);
        // Reset position
        RobotMap.e_hood_adjust.setPosition(0.0);
    }

    @Override
    public void execute() {

        //rename Button1, Button2, Button3 to whatever the buttons wanted for shooting are named in OI.java
        boolean testing = false; //TODO so the buttons don't work the way the code had og and i don't have a way to do this without making 3 commands so for now its just for variable distance
        if(testing){
            //?  RobotMap.robotShoot.Shoot(Lspeed * frictionMakeup);
            Robot.m_shooter.shootExact(Lrpm * frictionMakeup);
        } else if (testing){
            //?  RobotMap.robotShoot.Shoot(Hspeed * frictionMakeup);
            Robot.m_shooter.shootExact(Hrpm * frictionMakeup);
        } else if(testing == false){
            //?  RobotMap.robotShoot.Shoot(rpm * frictionMakeup);
            Robot.m_shooter.shootExact(rpm * frictionMakeup);
        } else {
            //?  RobotMap.robotShoot.Shoot(0);
            Robot.m_shooter.shootExact(0);
        }

        Robot.m_shooter.AdjustHoodExact(angle);

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.c_hood_adjust.setReference(0.0, CANSparkMax.ControlType.kPosition);
        Robot.m_shooter.shootExact(0.0);
    }
}