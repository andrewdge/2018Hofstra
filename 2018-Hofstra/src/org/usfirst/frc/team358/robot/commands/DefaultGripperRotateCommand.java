package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team358.robot.Controls;
import org.usfirst.frc.team358.robot.Robot;

public class DefaultGripperRotateCommand extends Command{
	
	// Reference to main robot object
	private Robot robot;
	
	public DefaultGripperRotateCommand(Robot robotInstance){
		robot = robotInstance;
		requires(robot.gripperRotateSubsystem);
	}
	
	protected void execute(){
		if(robot.isOperatorControl()){// Only drive with joysticks in teleop mode
			
			if(Controls.gripperRotatePositionControl() > 0) robot.gripperRotateSubsystem.setTargetPositionRelative(20);
			else if(Controls.gripperRotatePositionControl() < 0) robot.gripperRotateSubsystem.setTargetPositionRelative(-20);
			
		}
	}
	
	protected boolean isFinished(){
		return false;
	}
}