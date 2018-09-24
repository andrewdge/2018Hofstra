package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team358.robot.Controls;
import org.usfirst.frc.team358.robot.Robot;

public class DefaultDriveCommand extends Command{
	
	// Reference to main robot object
	private Robot robot;
	
	public DefaultDriveCommand(Robot robotInstance){
		robot = robotInstance;
		requires(robot.driveSubsystem);
	}
	
	protected void execute(){
		if(robot.isOperatorControl()){// Only drive with joysticks in teleop mode
			
			robot.driveSubsystem.tankDrive(Controls.driveLeftThrottle(), Controls.driveRightThrottle());
			
		}else robot.driveSubsystem.stopMotors();
	}
	
	protected boolean isFinished(){
		return false;
	}
}