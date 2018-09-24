package org.usfirst.frc.team358.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team358.robot.commands.autonomous.AutonomousSide;
import org.usfirst.frc.team358.robot.commands.autonomous.SideAutoLineSwitchDeposit;
import org.usfirst.frc.team358.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team358.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team358.robot.subsystems.GripperGripSubsystem;
import org.usfirst.frc.team358.robot.subsystems.GripperRotateSubsystem;

public class Robot extends IterativeRobot{
	
	// Preferences
	public Preferences preferences = Preferences.getInstance();
	
	// Instantiate Subsystems
	public DriveSubsystem driveSubsystem = DriveSubsystem.getInstance(this);
	public ElevatorSubsystem elevatorSubsystem = ElevatorSubsystem.getInstance(this);
	public GripperRotateSubsystem gripperRotateSubsystem = GripperRotateSubsystem.getInstance(this);
	public GripperGripSubsystem gripperGripSubsystem = GripperGripSubsystem.getInstance(this);
	
	public void robotInit(){
	
	}
	
	public void robotPeriodic(){
		Scheduler.getInstance().run();
	}
	
	public void autonomousInit(){
		SideAutoLineSwitchDeposit command = new SideAutoLineSwitchDeposit(this, AutonomousSide.RIGHT);
		command.start();
	}
	
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	}
	
	public void teleopInit(){
	
	}
	
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
	}
	
	public void testInit(){
	
	}
	
	public void testPeriodic(){
		Scheduler.getInstance().run();
	}
	
	public void disabledInit(){
	
	}
	
	public void disabledPeriodic(){
		Scheduler.getInstance().run();
	}
}