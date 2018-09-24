package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import org.usfirst.frc.team358.robot.Robot;

public class DriveStraightCommand extends PIDCommand{
	
	// Reference to main robot object
	private Robot robot;
	
	// Speed assuming zero degree angle
	private double speed;
	
	public DriveStraightCommand(Robot robotInstance, double speed){
		super(robotInstance.preferences.getDouble("kPKeepStraight", 0.04), robotInstance.preferences.getDouble("kIKeepStraight", 0.0), robotInstance.preferences.getDouble("kDKeepStraight", 0.0));
		robot = robotInstance;
		this.speed = speed;
		
		requires(robot.driveSubsystem);
	}
	
	protected void initialize(){
		robot.driveSubsystem.gyroZeroYaw();
		setSetpoint(0.0);// Set the target angle to zero degrees (straight)
	}
	
	protected double returnPIDInput(){
		return robot.driveSubsystem.getGyroYaw();
	}
	
	protected void usePIDOutput(double output){
		robot.driveSubsystem.arcadeDrive(speed, output);// Drive using arcade drive at the specified speed and the output of the PID for the angle
	}
	
	protected boolean isFinished(){
		return false;// Run forever, intended to be interrupted by timeout set when instantiating command
	}
	
	protected void end(){
		robot.driveSubsystem.stopMotors();
	}
}