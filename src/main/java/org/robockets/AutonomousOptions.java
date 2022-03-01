package org.robockets;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutonomousOptions {

    //Every time you rewrite code, you kill a kitten :(
    private static final String SYSTEM_DIR = System.getProperty("user.dir");
    private static final String TRAJECTORY_DIR = SYSTEM_DIR + "\\trajectory";

    public static final String DEFAULT_AUTO = "Default";
    public static final String DEBUG_AUTO = "First Debug Auto";
    public static final String EASY_POINTS_AUTO = "MOve Robot out of Spawn";
    public static final String FIVE_BALL = "Five Ball Auto";
    public static final String SHOOT_BALL = "Shoot Ball From Anywhere Auto";
    public static final String FIND_BALL_ON_GROUND = "Goto Ball";

    public static final String DEFAULT_AUTO_PATH = TRAJECTORY_DIR + "\\easy path.wpilib.json";
    public static final String DEBUG_AUTO_PATH = "\\debug path.wpilib.json";
    public static final String EASY_POINTS_AUTO_PATH = "\\easy dubs.wpilib.json";
    public static final String FIVE_BALL_PATH = TRAJECTORY_DIR + "\\Main Path.wpilib.json";
    public static final String SHOOT_BALL_PATH = "";
    public static final String FIND_BALL_ON_GROUND_PATH = "";

    public static void addOptions(SendableChooser<String> sender) {
        sender.setDefaultOption(DEFAULT_AUTO,DEFAULT_AUTO);
        sender.addOption(DEBUG_AUTO,DEBUG_AUTO);
        sender.addOption(FIVE_BALL,FIVE_BALL);
        sender.addOption(SHOOT_BALL,SHOOT_BALL);
        sender.addOption(FIND_BALL_ON_GROUND,FIND_BALL_ON_GROUND);
        sender.addOption(EASY_POINTS_AUTO,EASY_POINTS_AUTO);
    }

    public static String getSelected(SendableChooser<String> sender) {
        return sender.getSelected();
    }
}
