package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import org.usfirst.frc.team358.robot.Robot;

public class RotateCommand extends PIDCommand{
	
	// Reference to main robot object
	private Robot robot;
	
	public RotateCommand(Robot robotInstance, double targetAngle){
		super(robotInstance.preferences.getDouble("kPRotate", 0.02), robotInstance.preferences.getDouble("kIRotate", 0.0), robotInstance.preferences.getDouble("kDRotate", 0.0));
		robot = robotInstance;
		
		requires(robot.driveSubsystem);
		
		// Set PID Settings
		setInputRange(0, 360);
		getPIDController().setContinuous();
		getPIDController().setAbsoluteTolerance(2);
		getPIDController().setToleranceBuffer(20);
		setSetpoint(targetAngle);
	}
	
	protected void initialize(){
		robot.driveSubsystem.gyroZeroYaw();
	}
	
	protected double returnPIDInput(){
		return robot.driveSubsystem.getGyroYaw();
	}
	
	protected void usePIDOutput(double output){
		robot.driveSubsystem.tankDrive(output, -output);
	}
	
	protected boolean isFinished(){
		return getPIDController().onTarget();
	}
	
	protected void end(){
		robot.driveSubsystem.stopMotors();
	}
}