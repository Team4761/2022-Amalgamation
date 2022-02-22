package org.robockets;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class RobotMap {
    public static CANSparkMax c_ShooterLeft = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax c_ShooterRight = new CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless);
    
    public static MotorControllerGroup robotShoot = new MotorControllerGroup(c_ShooterLeft, c_ShooterRight);
}
