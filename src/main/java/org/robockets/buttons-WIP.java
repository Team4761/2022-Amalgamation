public static final Joystick manual = new Joystick(1);
public static final Joystick auto = new Joystick(2);

// The order of the buttons are starting at the top left of the buton board and working down and to the right
// The order is assuming that the green button is at the bottom
public static final JoystickButton B0 =new JoystickButton(manual,9);
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

// These are the buttons that do things (I don't know what things)
public static final JoystickButton thing1 = B0;
public static final JoystickButton thing2 = B1;
public static final JoystickButton intake_pneumatic = B2;
public static final JoystickButton hood_raise = B3;
public static final JoystickButton hood_lower = B4;
public static final JoystickButton move_climber = B5;
public static final JoystickButton activate_fly_wheel_max_speed = B6; // this might also activate the wheel in the shaft
public static final JoystickButton activate_inner_wheel = B7;
public static final JoystickButton auto1 = B8;
public static final JoystickButton EMERGENCYSTOP = B9;
public static final JoystickButton auto2 = B10;
