package org.usfirst.frc.team358.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team358.robot.Robot;
import org.usfirst.frc.team358.robot.commands.DriveStraightCommand;

public class CrossAutoLine extends CommandGroup{
	
	public CrossAutoLine(Robot robot){
		
		//addSequential(new DriveDistanceCommand(robot, 0.5, 121));
		addSequential(new DriveStraightCommand(robot, 0.5), 4.75);
	}
}