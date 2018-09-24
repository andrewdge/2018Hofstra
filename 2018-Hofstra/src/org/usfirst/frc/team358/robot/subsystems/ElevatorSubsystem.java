package org.usfirst.frc.team358.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team358.robot.IO;
import org.usfirst.frc.team358.robot.Robot;
import org.usfirst.frc.team358.robot.commands.DefaultElevatorCommand;

public class ElevatorSubsystem extends PIDSubsystem{
	
	// Store only one instance of this class
	private static ElevatorSubsystem instance;
	public static ElevatorSubsystem getInstance(Robot robotInstance){
		if(instance == null) instance = new ElevatorSubsystem(robotInstance);
		return instance;
	}
	
	// Reference to main robot object
	private Robot robot;
	
	// Instantiate IO
	private VictorSP motor = new VictorSP(IO.motorElevator);
	private Servo brake = new Servo(IO.servoElevatorBrake);
	private AnalogInput potentiometer = new AnalogInput(IO.potentiometerElevator);
	
	// Private Constructor - Class should only be instantiated once in the getInstance method
	private ElevatorSubsystem(Robot robotInstance){
		super(robotInstance.preferences.getDouble("kPElevator", 0.001), robotInstance.preferences.getDouble("kIElevator", 0.0), robotInstance.preferences.getDouble("kDElevator", 0.0));
		robot = robotInstance;
		
		// PID Controller Options
		setAbsoluteTolerance(15);// If the potentiometer is within +- this value it is considered on target
		setInputRange(robot.preferences.getInt("limitElevatorBottom", 0), robot.preferences.getInt("limitElevatorTop", 4096));// Set the input range, i.e. the limits of the potentiometer
		setSetpoint(getPotentiometerValue());// Set the initial target position to the current value so that the elevator does not move by default
	}
	
	protected void initDefaultCommand(){
		setDefaultCommand(new DefaultElevatorCommand(robot));
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
		robot.preferences.putDouble("kPElevator", kP);
	}
	public void setI(double kI){
		getPIDController().setI(kI);
		robot.preferences.putDouble("kIElevator", kI);
	}
	public void setD(double kD){
		getPIDController().setD(kD);
		robot.preferences.putDouble("kDElevator", kD);
	}
	
	// Potentiometer
	public int getPotentiometerValue(){
		return potentiometer.getValue();
	}
	
	// Convert a percentage of the elevator range to an absolute potentiometer value
	public int rangePercentageToAbsolute(double percentage){
		int bottomLimit = robot.preferences.getInt("limitElevatorBottom", 0);
		int topLimit = robot.preferences.getInt("limitElevatorTop", 4096);
		return (int) Math.round(bottomLimit + ((topLimit - bottomLimit) * percentage));
	}
}