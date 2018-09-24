package org.usfirst.frc.team358.robot;

import edu.wpi.first.wpilibj.SerialPort;

public final class IO{
	
	// Private Constructor - Prevents class from being instantiated
	private IO(){}
	
	// PWM
	public static final int motorDriveLeft = 0;
	public static final int motorDriveRight = 1;
	public static final int motorElevator = 2;
	public static final int motorGripperGrip = 3;
	public static final int motorGripperRotate = 4;
	public static final int servoElevatorBrake = 5;
	
	// Analog
	public static final int potentiometerElevator = 0;
	public static final int potentiometerGripperRotate = 1;
	public static final int potentiometerGripperGrip = 2;
	
	// Digital
	public static final int limitSwitchElevatorBottom = 0;
	public static final int limitSwitchElevatorTop = 1;
	public static final int encoderDriveLeftA = 2;
	public static final int encoderDriveLeftB = 3;
	public static final int encoderDriveRightA = 4;
	public static final int encoderDriveRightB = 5;
	
	// USB
	public static SerialPort.Port gyro = SerialPort.Port.kUSB;
}