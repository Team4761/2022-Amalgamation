package org.robockets;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import org.robockets.ClimbingCommands.ExtendArmCommand;
import org.robockets.ClimbingCommands.RetractArmCommand;

public class OI {

    public static Joystick xbox = new Joystick(0);

    public static JoystickButton a = new JoystickButton(xbox, 0);
    public static JoystickButton b = new JoystickButton(xbox, 1);
    public static JoystickButton x = new JoystickButton(xbox, 2);
    public static JoystickButton y = new JoystickButton(xbox, 3);
	
    public static double left_trigger = xbox.getRawAxis(2);
    public static double right_trigger = xbox.getRawAxis(3);

    //<Button Board goes here>
    //Side with switch and green button is left side
    //Ordered starting at top out going down then next inward column
    //<Comments are for confused people, remove if wanted>
    //Rename buttons as needed may want to rename switch and only green button
	
	/**
	*	I need SOMEONE TO fix this! Use the two arduinos that I already built!
	*/
	
    /*
    public static Joystick LeftButtonBoard = new Joystick(0);//0 = port
    
    public static final JoystickButton lbb1 = new JoystickButton(LeftButtonBoard,6);//top red
    public static final JoystickButton lbb2 = new JoystickButton(LeftButtonBoard,2);//top black
    //left most white (doesn't work)
    public static final JoystickButton lbb4 = new JoystickButton(LeftButtonBoard,11);//bottom left red
    public static final JoystickButton lbb5 = new JoystickButton(LeftButtonBoard,12);//bottom left black
    public static final JoystickButton lbb6 = new JoystickButton(LeftButtonBoard,8);//top left white
    //black button above switch(doesn't work)
    public static final JoystickButton lbb8 = new JoystickButton(LeftButtonBoard,4);//switch
    public static final JoystickButton lbb9 = new JoystickButton(LeftButtonBoard,10);//green button
    //top middle black button(doesn't work)
    
    public static Joystick RightButtonBoard = new Joystick(1);//1 = port
    
    //top 3 right most buttons (don't work)
    public static final JoystickButton rbb4 = new JoystickButton(RightButtonBoard,7);//bottom right red
    //Bottom right black (doesn't work)
    public static final JoystickButton rbb6 = new JoystickButton(RightButtonBoard,5);//top most right white
    //Black directly under top & left most white (doesn't work)
    public static final JoystickButton rbb8 = new JoystickButton(RightButtonBoard,3);//center most right
    public static final JoystickButton rbb9 = new JoystickButton(RightButtonBoard,8);//bottom center black
    public static final JoystickButton rbb10 = new JoystickButton(RightButtonBoard,2);//right middle black
    */
    
    //The Manual Arduino
    public static final Joystick manual = new Joystick(1);
    public static final JoystickButton mb1 = new JoystickButton(manual,1);
    public static final JoystickButton mb2 = new JoystickButton(manual,2);
    public static final JoystickButton mb3 = new JoystickButton(manual,3);
    public static final JoystickButton mb4 = new JoystickButton(manual,4);
    public static final JoystickButton mb5 = new JoystickButton(manual,5);
    public static final JoystickButton mb6 = new JoystickButton(manual,6);
    public static final JoystickButton mb7 = new JoystickButton(manual,7);
    public static final JoystickButton mb8 = new JoystickButton(manual,8);
    public static final JoystickButton mb9 = new JoystickButton(manual,9);
    public static final JoystickButton mb10 = new JoystickButton(manual,10);
    public static final JoystickButton mb11 = new JoystickButton(manual,11);
    public static final JoystickButton mb12 = new JoystickButton(manual,12);
    public static final JoystickButton mb13 = new JoystickButton(manual,13);
    public static final JoystickButton mb14 = new JoystickButton(manual,14);
    public static final JoystickButton mb15 = new JoystickButton(manual,15);
    public static final JoystickButton mb16 = new JoystickButton(manual,16);
    public static final JoystickButton mb17 = new JoystickButton(manual,17);
    public static final JoystickButton mb18 = new JoystickButton(manual,18);
    public static final JoystickButton mb19 = new JoystickButton(manual,19);
    public static final JoystickButton mb20 = new JoystickButton(manual,20);
    public static final JoystickButton mb21 = new JoystickButton(manual,21);
    public static final JoystickButton mb22 = new JoystickButton(manual,22);
    public static final JoystickButton mb23 = new JoystickButton(manual,23);
    public static final JoystickButton mb24 = new JoystickButton(manual,24);
    public static final JoystickButton mb25 = new JoystickButton(manual,25);
    public static final JoystickButton mb26 = new JoystickButton(manual,26);
    public static final JoystickButton mb27 = new JoystickButton(manual,27);
    public static final JoystickButton mb28 = new JoystickButton(manual,28);
    public static final JoystickButton mb29 = new JoystickButton(manual,29);
    public static final JoystickButton mb30 = new JoystickButton(manual,30);
    public static final JoystickButton mb31 = new JoystickButton(manual,31);
    public static final JoystickButton mb32 = new JoystickButton(manual,32);

    //The Automatic Arduino
    public static final Joystick auto = new Joystick(2);
    public static final JoystickButton ab1 = new JoystickButton(auto,1);
    public static final JoystickButton ab2 = new JoystickButton(auto,2);
    public static final JoystickButton ab3 = new JoystickButton(auto,3);
    public static final JoystickButton ab4 = new JoystickButton(auto,4);
    public static final JoystickButton ab5 = new JoystickButton(auto,5);
    public static final JoystickButton ab6 = new JoystickButton(auto,6);
    public static final JoystickButton ab7 = new JoystickButton(auto,7);
    public static final JoystickButton ab8 = new JoystickButton(auto,8);
    public static final JoystickButton ab9 = new JoystickButton(auto,9);
    public static final JoystickButton ab10 = new JoystickButton(auto,10);
    public static final JoystickButton ab11 = new JoystickButton(auto,11);
    public static final JoystickButton ab12 = new JoystickButton(auto,12);
    public static final JoystickButton ab13 = new JoystickButton(auto,13);
    public static final JoystickButton ab14 = new JoystickButton(auto,14);
    public static final JoystickButton ab15 = new JoystickButton(auto,15);
    public static final JoystickButton ab16 = new JoystickButton(auto,16);
    public static final JoystickButton ab17 = new JoystickButton(auto,17);
    public static final JoystickButton ab18 = new JoystickButton(auto,18);
    public static final JoystickButton ab19 = new JoystickButton(auto,19);
    public static final JoystickButton ab20 = new JoystickButton(auto,20);
    public static final JoystickButton ab21 = new JoystickButton(auto,21);
    public static final JoystickButton ab22 = new JoystickButton(auto,22);
    public static final JoystickButton ab23 = new JoystickButton(auto,23);
    public static final JoystickButton ab24 = new JoystickButton(auto,24);
    public static final JoystickButton ab25 = new JoystickButton(auto,25);
    public static final JoystickButton ab26 = new JoystickButton(auto,26);
    public static final JoystickButton ab27 = new JoystickButton(auto,27);
    public static final JoystickButton ab28 = new JoystickButton(auto,28);
    public static final JoystickButton ab29 = new JoystickButton(auto,29);
    public static final JoystickButton ab30 = new JoystickButton(auto,30);
    public static final JoystickButton ab31 = new JoystickButton(auto,31);
    public static final JoystickButton ab32 = new JoystickButton(auto,32);
	
	// Java is disgusting I just want to pass it in by reference but now I don't know if I'm making a copy or not
	// camelCase??? never heard of it!
	
	// Here we'll assign which is which later, this makes it easier to look at a button and go "wait, what does this button do?"
	
	public static final JoystickButton back_intake_wheel = mb1;
	public static final JoystickButton back_intake_pneumatic = mb2;
	
	public static final JoystickButton front_intake_wheel = mb3;
	public static final JoystickButton front_intake_pneumatic = mb4;
	
	public static final JoystickButton extend_arm = mb5;
	public static final JoystickButton retract_arm = mb6;
	
	public static final JoystickButton hood_raise = mb7;
	public static final JoystickButton hood_lower = mb8;
	
	public static final JoystickButton activate_fly_wheel_max_speed = mb9; // this might also activate the wheel in the shaft
	
	public static final JoystickButton auto1 = mb10;
	public static final JoystickButton auto2 = mb10;
	

	// If pressing a button does something, add that here!
    public OI(){
        a.whenPressed(new ExtendArmCommand());
        b.whenPressed(new RetractArmCommand());
    }

}
