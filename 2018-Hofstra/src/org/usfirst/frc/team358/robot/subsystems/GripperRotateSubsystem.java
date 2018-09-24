package org.usfirst.frc.team358.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team358.robot.IO;
import org.usfirst.frc.team358.robot.Robot;
import org.usfirst.frc.team358.robot.commands.DefaultGripperRotateCommand;

public class GripperRotateSubsystem extends PIDSubsystem{
	
	// Store only one instance of this class
	private static GripperRotateSubsystem instance;
	public static GripperRotateSubsystem getInstance(Robot robotInstance){
		if(instance == null) instance = new GripperRotateSubsystem(robotInstance);
		return instance;
	}
	
	// Reference to main robot object
	private Robot robot;
	
	// Instantiate IO
	private Spark motor = new Spark(IO.motorGripperRotate);
	private AnalogInput potentiometer = new AnalogInput(IO.potentiometerGripperRotate);
	
	// Private Constructor - Class should only be instantiated once in the getInstance method
	private GripperRotateSubsystem(Robot robotInstance){
		super(robotInstance.preferences.getDouble("kPGripperRotate", 0.001), robotInstance.preferences.getDouble("kIGripperRotate", 0.0), robotInstance.preferences.getDouble("kDGripperRotate", 0.0));
		robot = robotInstance;
		
		// PID Controller Options
		setAbsoluteTolerance(15);// If the potentiometer is within +- this value it is considered on target
		setInputRange(robot.preferences.getInt("limitGripperRotateDown", 4096), robot.preferences.getInt("limitGripperRotateUp", 0));// Set the input range, i.e. the limits of the potentiometer
		setSetpoint(getPotentiometerValue());// Set the initial target position to the current value so that the gripper does not move by default
	}
	
	protected void initDefaultCommand(){
		setDefaultCommand(new DefaultGripperRotateCommand(robot));
	}
	
	protected double returnPIDInput(){
		return getPotentiometerValue();
	}
	
	protected void usePIDOutput(double output){
		motor.pidWrite(output);
	}
	
	// Set Target Position
	public void setTargetPosition(int position){
		setSetpoint(position);
	}
	public void setTargetPositionRelative(int delta){
		setSetpointRelative(delta);
	}
	
	// Set PID Constants
	public void setP(double kP){
		getPIDController().setP(kP);
		robot.preferences.putDouble("kPGripperRotate", kP);
	}
	public void setI(double kI){
		getPIDController().setI(kI);
		robot.preferences.putDouble("kIGripperRotate", kI);
	}
	public void setD(double kD){
		getPIDController().setD(kD);
		robot.preferences.putDouble("kDGripperRotate", kD);
	}
	
	// Potentiometer
	public int getPotentiometerValue(){
		return potentiometer.getValue();
	}
	
	// Convert a percentage of the rotation range to an absolute potentiometer value
	public int rangePercentageToAbsolute(double percentage){
		int downLimit = robot.preferences.getInt("limitGripperRotateDown", 4096);
		int upLimit = robot.preferences.getInt("limitGripperRotateUp", 0);
		return (int) Math.round(downLimit + ((upLimit - downLimit) * percentage));
	}
}