package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team358.robot.Robot;

public class GripperRotatePositionCommand extends Command{
	
	// Reference to main robot object
	private Robot robot;
	
	// Target position as a percentage of the range
	private double targetPositionPercentage;
	
	public GripperRotatePositionCommand(Robot robotInstance, double targetPositionPercentage){
		robot = robotInstance;
		this.targetPositionPercentage = targetPositionPercentage;
		
		requires(robot.gripperRotateSubsystem);
	}
	
	protected void initialize(){
		robot.gripperRotateSubsystem.setTargetPosition(robot.gripperRotateSubsystem.rangePercentageToAbsolute(targetPositionPercentage));
	}
	
	protected boolean isFinished(){
		return robot.gripperRotateSubsystem.onTarget();
	}
}