package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team358.robot.Controls;
import org.usfirst.frc.team358.robot.Robot;

public class DefaultElevatorCommand extends Command{
	
	// Reference to main robot object
	private Robot robot;
	
	public DefaultElevatorCommand(Robot robotInstance){
		robot = robotInstance;
		requires(robot.elevatorSubsystem);
	}
	
	protected void execute(){
		if(robot.isOperatorControl()){// Only drive with joysticks in teleop mode
		
			if(Controls.elevatorPositionControl() > 0) robot.elevatorSubsystem.setTargetPositionRelative(20);
			else if(Controls.elevatorPositionControl() < 0) robot.elevatorSubsystem.setTargetPositionRelative(-20);
		
		}
	}
	
	protected boolean isFinished(){
		return false;
	}
}