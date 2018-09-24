package org.usfirst.frc.team358.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team358.robot.IO;
import org.usfirst.frc.team358.robot.Robot;
import org.usfirst.frc.team358.robot.commands.DefaultGripperGripCommand;

public class GripperGripSubsystem extends PIDSubsystem{
	
	// Store only one instance of this class
	private static GripperGripSubsystem instance;
	public static GripperGripSubsystem getInstance(Robot robotInstance){
		if(instance == null) instance = new GripperGripSubsystem(robotInstance);
		return instance;
	}
	
	// Reference to main robot object
	private Robot robot;
	
	// Instantiate IO
	private Spark motor = new Spark(IO.motorGripperGrip);
	private AnalogInput potentiometer = new AnalogInput(IO.potentiometerGripperGrip);
	
	// Private Constructor - Class should only be instantiated once in the getInstance method
	private GripperGripSubsystem(Robot robotInstance){
		super(robotInstance.preferences.getDouble("kPGripperGrip", 0.001), robotInstance.preferences.getDouble("kIGripperGrip", 0.0), robotInstance.preferences.getDouble("kDGripperGrip", 0.0));
		robot = robotInstance;
		
		// PID Controller Options
		setAbsoluteTolerance(10);// If the potentiometer is within +- this value it is considered on target
		setInputRange(robot.preferences.getInt("limitGripperGripClosed", 0), robot.preferences.getInt("limitGripperGripOpen", 4096));// Set the input range, i.e. the limits of the potentiometer
		//setOutputRange(-0.5, 0.75);// Set the maximum speed for opening and closing
		setSetpoint(robot.preferences.getInt("limitGripperGripClosed", 2700));// Set the initial target position to the closed limit so that the gripper grips by default
	}
	
	protected void initDefaultCommand(){
		setDefaultCommand(new DefaultGripperGripCommand(robot));
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
	
	public void open(){
		setTargetPosition(robot.preferences.getInt("limitGripperGripOpen", 3000));
	}
	public void close(){
		setTargetPosition(robot.preferences.getInt("limitGripperGripClosed", 2700));
	}
	
	// Set PID Constants
	public void setP(double kP){
		getPIDController().setP(kP);
		robot.preferences.putDouble("kPGripperGrip", kP);
	}
	public void setI(double kI){
		getPIDController().setI(kI);
		robot.preferences.putDouble("kIGripperGrip", kI);
	}
	public void setD(double kD){
		getPIDController().setD(kD);
		robot.preferences.putDouble("kDGripperGrip", kD);
	}
	
	// Potentiometer
	public int getPotentiometerValue(){
		return potentiometer.getValue();
	}
}