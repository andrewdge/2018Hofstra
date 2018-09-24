package org.usfirst.frc.team358.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.usfirst.frc.team358.robot.IO;
import org.usfirst.frc.team358.robot.Robot;
import org.usfirst.frc.team358.robot.commands.DefaultDriveCommand;

public class DriveSubsystem extends Subsystem{
	
	// Store only one instance of this class
	private static DriveSubsystem instance;
	public static DriveSubsystem getInstance(Robot robotInstance){
		if(instance == null) instance = new DriveSubsystem(robotInstance);
		return instance;
	}
	
	// Reference to main robot object
	private Robot robot;
	
	// Instantiate IO
	private VictorSP motorLeft = new VictorSP(IO.motorDriveLeft);
	private VictorSP motorRight = new VictorSP(IO.motorDriveRight);
	private Encoder encoderLeft = new Encoder(IO.encoderDriveLeftA, IO.encoderDriveLeftB);
	private Encoder encoderRight = new Encoder(IO.encoderDriveRightA, IO.encoderDriveRightB);
	private AHRS gyro = new AHRS(IO.gyro);
	
	// DifferentialDrive for controlling tank drive
	private DifferentialDrive drive = new DifferentialDrive(motorLeft, motorRight);
	
	// Private Constructor - Class should only be instantiated once in the getInstance method
	private DriveSubsystem(Robot robotInstance){
		robot = robotInstance;
		
		// Encoder Settings
		encoderLeft.setDistancePerPulse((6 * Math.PI) / 2048);
		encoderRight.setDistancePerPulse((6 * Math.PI) / 2048);
		encoderRight.setReverseDirection(true);
	}
	
	protected void initDefaultCommand(){
		setDefaultCommand(new DefaultDriveCommand(robot));
	}
	
	// Drive motors using left and right speeds
	public void tankDrive(double leftSpeed, double rightSpeed){
		drive.tankDrive(leftSpeed, rightSpeed, false);
	}
	
	// Drive motors using an angle to calculate left and right speeds
	public void arcadeDrive(double speed, double angle){
		drive.arcadeDrive(speed, angle, false);
	}
	
	// Stop the motors
	public void stopMotors(){
		drive.stopMotor();
	}
	
	// Gyro
	public boolean isGyroConnected(){
		return gyro.isConnected();
	}
	
	public boolean isGyroCalibrating(){
		return gyro.isCalibrating();
	}
	
	public double getGyroYaw(){
		return gyro.getYaw();
	}
	
	public void gyroZeroYaw(){
		gyro.zeroYaw();
	}
	
	// Encoders
	public double getLeftEncoderRate(){
		return encoderLeft.getRate();
	}

	public double getRightEncoderRate(){
		return encoderRight.getRate();
	}

	public double getLeftEncoderDistance(){
		return encoderLeft.getDistance();
	}

	public double getRightEncoderDistance(){
		return encoderRight.getDistance();
	}

	public void resetEncoders(){
		encoderLeft.reset();
		encoderRight.reset();
	}
}