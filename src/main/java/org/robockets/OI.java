package org.robockets;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {

    public static ControllerMode selectedMode = ControllerMode.OneXbox;

    public enum ControllerMode {
        OneXbox,
        TwoXbox,
        XboxAndButtonBoard,
        FlightStick
    }

    /**
     * This is all the main controller schenanigans that are REQUIRED for any set up
     */
    public static Joystick driverController = new Joystick(0);
    public static JoystickButton dc_a = new JoystickButton(driverController, 1);
    public static JoystickButton dc_b = new JoystickButton(driverController, 2);
    public static JoystickButton dc_x = new JoystickButton(driverController, 3);
    public static JoystickButton dc_y = new JoystickButton(driverController, 4);
    //public static double dc_lefttrigger = driverController.getRawAxis(3);
    //public static double dc_righttrigger = driverController.getRawAxis(4);

    public static Joystick secondaryController;
    public static JoystickButton sc_a = new JoystickButton(secondaryController, 1);
    public static JoystickButton sc_b = new JoystickButton(secondaryController, 2);
    public static JoystickButton sc_x = new JoystickButton(secondaryController, 3);
    public static JoystickButton sc_y = new JoystickButton(secondaryController, 4);
    //public static double sc_lefttrigger = secondaryController.getRawAxis(3);
    //public static double sc_righttrigger = secondaryController.getRawAxis(4);

    /**
     * I need SOMEONE TO fix this! Use the two arduinos that I already built!
     */

    //The Manual Arduino
    public static Joystick manual;
    public static JoystickButton mb1 = new JoystickButton(manual, 1);
    public static JoystickButton mb2 = new JoystickButton(manual, 2);
    public static JoystickButton mb3 = new JoystickButton(manual, 3);
    public static JoystickButton mb4 = new JoystickButton(manual, 4);
    public static JoystickButton mb5 = new JoystickButton(manual, 5);
    public static JoystickButton mb6 = new JoystickButton(manual, 6);
    public static JoystickButton mb7 = new JoystickButton(manual, 7);
    public static JoystickButton mb8 = new JoystickButton(manual, 8);
    public static JoystickButton mb9 = new JoystickButton(manual, 9);
    public static JoystickButton mb10 = new JoystickButton(manual, 10);
    public static JoystickButton mb11 = new JoystickButton(manual, 11);
    public static JoystickButton mb12 = new JoystickButton(manual, 12);
    public static JoystickButton mb13 = new JoystickButton(manual, 13);
    public static JoystickButton mb14 = new JoystickButton(manual, 14);
    public static JoystickButton mb15 = new JoystickButton(manual, 15);
    public static JoystickButton mb16 = new JoystickButton(manual, 16);
    public static JoystickButton mb17 = new JoystickButton(manual, 17);
    public static JoystickButton mb18 = new JoystickButton(manual, 18);
    public static JoystickButton mb19 = new JoystickButton(manual, 19);
    public static JoystickButton mb20 = new JoystickButton(manual, 20);
    public static JoystickButton mb21 = new JoystickButton(manual, 21);
    public static JoystickButton mb22 = new JoystickButton(manual, 22);
    public static JoystickButton mb23 = new JoystickButton(manual, 23);
    public static JoystickButton mb24 = new JoystickButton(manual, 24);
    public static JoystickButton mb25 = new JoystickButton(manual, 25);
    public static JoystickButton mb26 = new JoystickButton(manual, 26);
    public static JoystickButton mb27 = new JoystickButton(manual, 27);
    public static JoystickButton mb28 = new JoystickButton(manual, 28);
    public static JoystickButton mb29 = new JoystickButton(manual, 29);
    public static JoystickButton mb30 = new JoystickButton(manual, 30);
    public static JoystickButton mb31 = new JoystickButton(manual, 31);
    public static JoystickButton mb32 = new JoystickButton(manual, 32);

    //The Automatic Arduino
    public static Joystick auto;
    public static JoystickButton ab1 = new JoystickButton(auto, 1);
    public static JoystickButton ab2 = new JoystickButton(auto, 2);
    public static JoystickButton ab3 = new JoystickButton(auto, 3);
    public static JoystickButton ab4 = new JoystickButton(auto, 4);
    public static JoystickButton ab5 = new JoystickButton(auto, 5);
    public static JoystickButton ab6 = new JoystickButton(auto, 6);
    public static JoystickButton ab7 = new JoystickButton(auto, 7);
    public static JoystickButton ab8 = new JoystickButton(auto, 8);
    public static JoystickButton ab9 = new JoystickButton(auto, 9);
    public static JoystickButton ab10 = new JoystickButton(auto, 10);
    public static JoystickButton ab11 = new JoystickButton(auto, 11);
    public static JoystickButton ab12 = new JoystickButton(auto, 12);
    public static JoystickButton ab13 = new JoystickButton(auto, 13);
    public static JoystickButton ab14 = new JoystickButton(auto, 14);
    public static JoystickButton ab15 = new JoystickButton(auto, 15);
    public static JoystickButton ab16 = new JoystickButton(auto, 16);
    public static JoystickButton ab17 = new JoystickButton(auto, 17);
    public static JoystickButton ab18 = new JoystickButton(auto, 18);
    public static JoystickButton ab19 = new JoystickButton(auto, 19);
    public static JoystickButton ab20 = new JoystickButton(auto, 20);
    public static JoystickButton ab21 = new JoystickButton(auto, 21);
    public static JoystickButton ab22 = new JoystickButton(auto, 22);
    public static JoystickButton ab23 = new JoystickButton(auto, 23);
    public static JoystickButton ab24 = new JoystickButton(auto, 24);
    public static JoystickButton ab25 = new JoystickButton(auto, 25);
    public static JoystickButton ab26 = new JoystickButton(auto, 26);
    public static JoystickButton ab27 = new JoystickButton(auto, 27);
    public static JoystickButton ab28 = new JoystickButton(auto, 28);
    public static JoystickButton ab29 = new JoystickButton(auto, 29);
    public static JoystickButton ab30 = new JoystickButton(auto, 30);
    public static JoystickButton ab31 = new JoystickButton(auto, 31);
    public static JoystickButton ab32 = new JoystickButton(auto, 32);

    /**
     * Yes I KNOW this is a repeat, but
     * Allister is just THAT impressive with how he was able to set everything up that
     * I just HAVE to use it!
     *
     * The order of the buttons are starting at the top left of the button board and working down and to the right
     * The order is assuming that the green button is at the bottom
     */
    public static final JoystickButton B0 = new JoystickButton(manual,9);
    public static final JoystickButton B1 = new JoystickButton(manual, 8);
    public static final JoystickButton B2 = new JoystickButton(manual, 12);
    public static final JoystickButton B3 = new JoystickButton(manual, 3);
    public static final JoystickButton B4 = new JoystickButton(manual, 13);
    public static final JoystickButton B5 = new JoystickButton(manual, 7);
    public static final JoystickButton B6 = new JoystickButton(auto, 2);
    public static final JoystickButton B7 = new JoystickButton(manual, 11);
    public static final JoystickButton S = new JoystickButton(manual, 5);
    public static final JoystickButton B8 = new JoystickButton(auto, 10);
    public static final JoystickButton B9 = new JoystickButton(auto, 23);
    public static final JoystickButton B10 = new JoystickButton(auto, 3);
    public static final JoystickButton B11 = new JoystickButton(auto, 8);
    public static final JoystickButton B12 = new JoystickButton(auto, 4);
    public static final JoystickButton B13 = new JoystickButton(auto, 5);
    public static final JoystickButton B14 = new JoystickButton(auto, 12);
    public static final JoystickButton B15 = new JoystickButton(auto, 20);
    public static final JoystickButton B16 = new JoystickButton(auto, 13);
    public static final JoystickButton B17 = new JoystickButton(auto, 21);
    public static final JoystickButton B18 = new JoystickButton(auto, 7);
    public static final JoystickButton B19 = new JoystickButton(auto, 19);
    public static JoystickButton EMERGENCYSTOP = B9; // Never used, the space bar is too fire

    /**
     * These are the main buttons that any controller setup SHOULD handle
     */
    public static JoystickButton intake_pneumatic;
    public static JoystickButton hood_raise;
    public static JoystickButton hood_lower;
    public static JoystickButton move_climber;
    public static JoystickButton activate_fly_wheel_max_speed; // this might also activate the wheel in the shaft
    public static JoystickButton activate_inner_wheel;

    public static double climberValue;
    public static double intakeValue;
    public static double shooterValue;

    public static JoystickButton auto1 = ab5;
    public static JoystickButton auto2 = ab12;

    public OI() {
        changeDriveMode();
    }

    /**
     * This stuff updates things like assigning new buttons
     */
    private void changeDriveMode() {
        switch (selectedMode) {
            case OneXbox:
                hood_raise = new JoystickButton(driverController, 5); //left trigger button
                hood_lower = new JoystickButton(driverController, 6); //right trigger button
                intake_pneumatic = dc_b;
                move_climber = dc_a;
                //activate_fly_wheel_max_speed = dc_b;
                activate_inner_wheel = dc_y;
                break;
            case TwoXbox:
                hood_raise = new JoystickButton(secondaryController, 5); //left trigger button
                hood_lower = new JoystickButton(secondaryController, 6); //right trigger button
                intake_pneumatic = sc_b;
                move_climber = sc_a;
                activate_fly_wheel_max_speed = sc_y;
                break;
            case XboxAndButtonBoard:
                manual = new Joystick(1);
                auto = new Joystick(2);

                //public static final JoystickButton thing1 = B0;
                //public static final JoystickButton thing2 = B1;
                intake_pneumatic = B2;
                hood_raise = B3;
                hood_lower = B4;
                move_climber = B5;
                activate_fly_wheel_max_speed = B6; // this might also activate the wheel in the shaft
                activate_inner_wheel = B7;
                auto1 = B8;
                EMERGENCYSTOP = B9;
                auto2 = B10;
                break;
            case FlightStick:
                break;
        }
    }

    /**
     * These values are the ones that are updated every cycle
     */
    private ControllerMode deltaSelected;
    public void periodic() {
        switch(selectedMode) {
            // Despite the button board being used, certain values just work better with the xbox controller
            case OneXbox: case XboxAndButtonBoard:
                climberValue = driverController.getRawAxis(5); // up and down on second stick
                shooterValue = driverController.getRawAxis(4); // right trigger
                intakeValue = driverController.getRawAxis(3); // left trigger
                break;
            case TwoXbox:
                climberValue = secondaryController.getRawAxis(5); // up and down on second stick
                shooterValue = secondaryController.getRawAxis(4); // right trigger
                intakeValue = secondaryController.getRawAxis(3); // left trigger
                break;
            case FlightStick:
                // no nothing lol
                break;
            default:
                DriverStation.reportError("Choose a controller mode!",false);
        }

        // If we've changed the type of drive mode, update everything here
        if(deltaSelected != selectedMode) {
            changeDriveMode();
        }
        deltaSelected = selectedMode;
    }

}
