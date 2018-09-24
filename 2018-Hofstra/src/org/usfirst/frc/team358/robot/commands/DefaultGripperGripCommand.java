package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team358.robot.Controls;
import org.usfirst.frc.team358.robot.Robot;

public class DefaultGripperGripCommand extends Command{
	
	// Reference to main robot object
	private Robot robot;
	
	public DefaultGripperGripCommand(Robot robotInstance){
		robot = robotInstance;
		requires(robot.gripperGripSubsystem);
	}
	
	protected void execute(){
		if(robot.isOperatorControl()){// Only drive with joysticks in teleop mode
		
			if(Controls.gripperGripOpen()) robot.gripperGripSubsystem.open();
			else if(Controls.gripperGripClose()) robot.gripperGripSubsystem.close();
		
		}
	}
	
	protected boolean isFinished(){
		return false;
	}
}