package org.robockets.AutonoumousResources;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutonomousOptions {

    //Every time you rewrite code, you kill a kitten :(
    public static final String SYSTEM_DIR = "";
    public static final String TRAJECTORY_DIR = SYSTEM_DIR + "trajectory/";
    public static final String FILE_EXTENSION = ".wpilib.json";

    public static final String DEFAULT_AUTO = "Default";
    public static final String DEBUG_AUTO = "First Debug Auto";
    public static final String MOVE_BACK_AND_FIRE = "Move back and fire a ball";
    public static final String FIVE_BALL = "Five Ball Auto";
    public static final String SHOOT_BALL = "Shoot Ball From Anywhere Auto";
    public static final String FIND_BALL_ON_GROUND = "Goto Ball";
    public static final String EVIL_AUTO = "Move back, Fire ball (EVIL AUTO! USE THIS ONE!)";
    public static final String LIMELIGHT_AUTO = "Find target with limelight";

    public static final String FIND_BALL_FROM_START = "Go to the Nearest Ball";

    public static final String DEFAULT_AUTO_PATH = TRAJECTORY_DIR + "easy path.wpilib.json";
    public static final String DEBUG_AUTO_PATH = TRAJECTORY_DIR + "Debug Path.wpilib.json";
    public static final String EASY_POINTS_AUTO_PATH = TRAJECTORY_DIR + "easy dubs.wpilib.json";
    public static final String FIVE_BALL_PATH = TRAJECTORY_DIR + "Main Path.wpilib.json";
    public static final String SHOOT_BALL_PATH = "";
    public static final String FIND_BALL_ON_GROUND_PATH = "";
    public static final String EVIL_AUTO_PATH = "No path dummy!";

    // These options listed below all fall under the category of following a ball
    // All of these will move the robot to the closest ball from whatever starting station they're at
    // DBL stands for Direction to Ball
    public static final String DBL_Red1 = TRAJECTORY_DIR + "DBL_Red1" + FILE_EXTENSION;
    public static final String DBL_Red2 = TRAJECTORY_DIR + "DBL_Red2" + FILE_EXTENSION;
    public static final String DBL_Red3 = TRAJECTORY_DIR + "DBL_Red3" + FILE_EXTENSION;

    public static final String DBL_Blue1 = TRAJECTORY_DIR + "DBL_Blue1" + FILE_EXTENSION;
    public static final String DBL_Blue2 = TRAJECTORY_DIR + "DBL_Blue2" + FILE_EXTENSION;
    public static final String DBL_Blue3 = TRAJECTORY_DIR + "DBL_Blue3" + FILE_EXTENSION;

    public static void addOptions(SendableChooser<String> sender) {
        sender.addOption(FIND_BALL_FROM_START,FIND_BALL_FROM_START);
        sender.addOption(DEFAULT_AUTO,DEFAULT_AUTO);
        sender.addOption(DEBUG_AUTO,DEBUG_AUTO);
        sender.addOption(FIVE_BALL,FIVE_BALL);
        sender.addOption(SHOOT_BALL,SHOOT_BALL);
        sender.addOption(FIND_BALL_ON_GROUND,FIND_BALL_ON_GROUND);
        sender.addOption(MOVE_BACK_AND_FIRE, MOVE_BACK_AND_FIRE);
        sender.addOption(LIMELIGHT_AUTO,LIMELIGHT_AUTO);
        sender.setDefaultOption(EVIL_AUTO,EVIL_AUTO);
    }

    public static String getSelected(SendableChooser<String> sender) {
        return sender.getSelected();
    }
}
