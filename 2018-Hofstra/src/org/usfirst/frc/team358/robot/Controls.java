package org.usfirst.frc.team358.robot;

import edu.wpi.first.wpilibj.Joystick;

public final class Controls{
	
	// Joystick Axes
	private static final int JOYSTICK_AXIS_X = 0;
	private static final int JOYSTICK_AXIS_Y = 1;
	private static final int JOYSTICK_AXIS_Z = 2;
	
	// Joystick Buttons
	private static final int JOYSTICK_BUTTON_2 = 2;
	private static final int JOYSTICK_BUTTON_3 = 3;
	private static final int JOYSTICK_BUTTON_4 = 4;
	private static final int JOYSTICK_BUTTON_5 = 5;
	private static final int JOYSTICK_BUTTON_6 = 6;
	private static final int JOYSTICK_BUTTON_7 = 7;
	private static final int JOYSTICK_BUTTON_8 = 8;
	private static final int JOYSTICK_BUTTON_9 = 9;
	private static final int JOYSTICK_BUTTON_10 = 10;
	private static final int JOYSTICK_BUTTON_11 = 11;
	
	// Xbox Controller Axes
	private static final int XBOX_AXIS_LTHUMB_X = 0;
	private static final int XBOX_AXIS_LTHUMB_Y = 1;
	private static final int XBOX_AXIS_LTRIGGER = 2;
	private static final int XBOX_AXIS_RTRIGGER = 3;
	private static final int XBOX_AXIS_RTHUMB_X = 4;
	private static final int XBOX_AXIS_RTHUMB_Y = 5;
	
	// Xbox Controller Buttons
	private static final int XBOX_BUTTON_A = 1;
	private static final int XBOX_BUTTON_B = 2;
	private static final int XBOX_BUTTON_X = 3;
	private static final int XBOX_BUTTON_Y = 4;
	private static final int XBOX_BUTTON_LBUMPER = 5;
	private static final int XBOX_BUTTON_RBUMPER = 6;
	private static final int XBOX_BUTTON_BACK = 7;
	private static final int XBOX_BUTTON_START = 8;
	private static final int XBOX_BUTTON_LTHUMB = 9;
	private static final int XBOX_BUTTON_RTHUMB = 10;
	
	// Xbox Controller POV
	private static final int XBOX_POV_CENTER = -1;
	private static final int XBOX_POV_UP = 0;
	private static final int XBOX_POV_UPRIGHT = 45;
	private static final int XBOX_POV_RIGHT = 90;
	private static final int XBOX_POV_DOWNRIGHT = 135;
	private static final int XBOX_POV_DOWN = 180;
	private static final int XBOX_POV_DOWNLEFT = 225;
	private static final int XBOX_POV_LEFT = 270;
	private static final int XBOX_POV_UPLEFT = 315;
	
	// Private Constructor - Prevents class from being instantiated
	private Controls(){}
	
	// Instantiate Joysticks
	private static Joystick joystickDriveLeft = new Joystick(0);
	private static Joystick joystickDriveRight = new Joystick(1);
	private static Joystick xboxController = new Joystick(2);
	
	// Deadband Function - Ignore values within a threshold
	private static double deadband(double rawValue){
		if(Math.abs(rawValue) < 0.1) return 0.0;
		else return rawValue;
	}
	
	// Drive Controls
	public static double driveLeftThrottle(){
		return deadband(joystickDriveLeft.getRawAxis(JOYSTICK_AXIS_Y));
	}
	public static double driveRightThrottle(){
		return deadband(joystickDriveRight.getRawAxis(JOYSTICK_AXIS_Y));
	}
	
	// Elevator Controls
	public static double elevatorPositionControl(){
		return deadband(xboxController.getRawAxis(XBOX_AXIS_LTHUMB_Y));
	}
	
	// Gripper Rotation Controls
	public static double gripperRotatePositionControl(){
		return deadband(xboxController.getRawAxis(XBOX_AXIS_RTHUMB_Y));
	}
	
	// Gripper Grip Controls
	public static boolean gripperGripOpen(){
		return xboxController.getRawButton(XBOX_BUTTON_LBUMPER);
	}
	public static boolean gripperGripClose(){
		return xboxController.getRawButton(XBOX_BUTTON_RBUMPER);
	}
}