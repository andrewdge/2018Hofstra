package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team358.robot.Robot;

public class ElevatorPositionCommand extends Command{
	
	// Reference to main robot object
	private Robot robot;
	
	// Target position as a percentage of the range
	private double targetPositionPercentage;
	
	public ElevatorPositionCommand(Robot robotInstance, double targetPositionPercentage){
		robot = robotInstance;
		this.targetPositionPercentage = targetPositionPercentage;
		
		requires(robot.elevatorSubsystem);
	}
	
	protected void initialize(){
		robot.elevatorSubsystem.setTargetPosition(robot.elevatorSubsystem.rangePercentageToAbsolute(targetPositionPercentage));
	}
	
	protected boolean isFinished(){
		return robot.elevatorSubsystem.onTarget();
	}
}