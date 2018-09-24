package org.usfirst.frc.team358.robot.commands;

import org.usfirst.frc.team358.robot.Robot;

public class DriveDistanceStraightCommand extends DriveStraightCommand{
	
	// Reference to main robot object
	private Robot robot;
	
	// Distance to drive to
	private double distance;
	
	public DriveDistanceStraightCommand(Robot robotInstance, double speed, double distance){
		super(robotInstance, speed);
		robot = robotInstance;
		this.distance = distance;
	}
	
	protected boolean isFinished(){
		return robot.driveSubsystem.getRightEncoderDistance() > distance;// Finish the command when the encoder reads the target distance
	}
}