package org.robockets.Drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import org.robockets.AutonoumousResources.PIDConstants;
import org.robockets.Robot;
import org.robockets.RobotMap;

import java.io.IOException;
import java.nio.file.Path;

// responsible for receiving path weaver code and turning it into stuff the drivetrain can use
public class pathWeaverInterpreter {

    public static final SimpleMotorFeedforward motorfeed = new SimpleMotorFeedforward(
            PIDConstants.ksVolts,
            PIDConstants.kvVoltsSecondsPerMeter,
            PIDConstants.kaVoltSecondsSquaredPerMeter
    );

    // Create a voltage constraint so we don't accelerate too fast
    public static final DifferentialDriveVoltageConstraint voltageConstraint =
            new DifferentialDriveVoltageConstraint(
                    motorfeed,
                    RobotMap.kDriveKinematics,11);

    // Create a trajectory configuration
    public static final TrajectoryConfig trajectoryConfig =
            new TrajectoryConfig(
                    PIDConstants.kMaxSpeedMetersPerSecond,
                    PIDConstants.kMaxAccelerationMetersPerSecondSquared
            )
                    .setKinematics(RobotMap.kDriveKinematics)
                    .addConstraint(voltageConstraint);

    //TODO: Change this to an assignment in the auto selector
    public static Trajectory usableTrajectory;

    // What a mess
    // TODO: What does any of this actually mean?
    public static RamseteCommand ramseteCommand;

    // The path would be the path on the roborio.
    // Use FileZilla to upload what you need!

    public static void loadTrajectory(String path) throws IOException {
        System.out.println("Path is: " + path);
        //Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(path);
        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(path);
        usableTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    }

    public static CommandBase autoPathWeaverCommand() {
        System.out.println(usableTrajectory);
        updateCommand();
        Pose2d start = usableTrajectory.getInitialPose();
        Robot.m_drivetrain.resetOdometry(start);

        // java syntax be like:
        // I think I'm supposed to replace tankDriveVolts with some other command...
        return ramseteCommand.andThen(()->Robot.m_drivetrain.tankDriveVolts(0,0));
    }

    private static void updateCommand() {
        ramseteCommand =
                new RamseteCommand(
                        usableTrajectory,
                        Robot.m_drivetrain::getPose,
                        new RamseteController(PIDConstants.kRamseteB,PIDConstants.kRamseteZeta),
                        motorfeed,
                        RobotMap.kDriveKinematics,
                        Robot.m_drivetrain::getWheelSpeeds,
                        new PIDController(PIDConstants.kPDriveVel,0,0),
                        new PIDController(PIDConstants.kPDriveVel, 0,0),
                        Robot.m_drivetrain::tankDriveVolts,
                        Robot.m_drivetrain
                );
    }

}
