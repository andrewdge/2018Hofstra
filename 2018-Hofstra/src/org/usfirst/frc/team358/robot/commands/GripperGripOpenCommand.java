package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team358.robot.Robot;

public class GripperGripOpenCommand extends Command{
	
	// Reference to main robot object
	private Robot robot;
	
	public GripperGripOpenCommand(Robot robotInstance){
		robot = robotInstance;
		requires(robot.gripperGripSubsystem);
	}
	
	protected void initialize(){
		robot.gripperGripSubsystem.open();
	}
	
	protected boolean isFinished(){
		return robot.gripperGripSubsystem.onTarget();
	}
}