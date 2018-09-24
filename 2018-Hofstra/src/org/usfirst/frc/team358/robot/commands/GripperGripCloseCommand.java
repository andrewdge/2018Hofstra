package org.usfirst.frc.team358.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team358.robot.Robot;

public class GripperGripCloseCommand extends Command{
	
	// Reference to main robot object
	private Robot robot;
	
	public GripperGripCloseCommand(Robot robotInstance){
		robot = robotInstance;
		requires(robot.gripperGripSubsystem);
		setTimeout(0.5);// The gripper may never reach the target position when a cube is being grabbed
	}
	
	protected void initialize(){
		robot.gripperGripSubsystem.close();
	}
	
	protected boolean isFinished(){
		return robot.gripperGripSubsystem.onTarget();
	}
}