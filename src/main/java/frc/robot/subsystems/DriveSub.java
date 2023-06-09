// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSub extends SubsystemBase {

  // Here we define really how the robot drives. Theres multiple types of drive trains, and this one is (in my opinion)
  // definitely the easiest.
  // In 2023, we used a much more complicated drive train called swerve drive.
  // I'm not going to get into that in this
  
  // Here we use a drive train called "Tank drive"
  // All it is, is a row of wheels, each controlled by one or two motors each.

  TalonSRX backLeft, 
           backRight, 
           frontLeft, 
           frontRight;
  boolean driveMode;

  public DriveSub(
    int backLeftID,
    int frontLeftID,
    int backRightID,
    int frontRightID) {

      //these assign all of the motors to their respective IDs
      backLeft = new TalonSRX(backLeftID);
      frontLeft = new TalonSRX(frontLeftID);
      backRight = new TalonSRX(backRightID);
      frontRight = new TalonSRX(frontRightID);

      // these make the front left and front right motors follow their back counterparts
      frontLeft.follow(backLeft);
      frontRight.follow(backRight);

      //this sets the default drive mode
      driveMode = true;
  }

  //this toggles the drive mode. If its true, it becomes false, and vice versa.
  public void driveToggle() {
    if (driveMode == true) {
      driveMode = false;
    } else {
      driveMode = true;
    }
  }

  // Usually tank drive is controlled by something called "GTA Drive"
  // All it is, is forward and back on the left joystick, then right and left on the right joystick.

  //this tells the left and right motors to move due to a parameter for both driving and turning
  public void gtaDrive(double drive, double turn) {
    backLeft.set(TalonSRXControlMode.PercentOutput, -drive);
    backRight.set(TalonSRXControlMode.PercentOutput, turn);
  }

  // However, 2022 we had a tank drive but the control was weird. However, it does allow for more control
  // over the robot.
  // You control the left wheels with the left joystick, and the right wheels with the right joystick
  // Our robot was named "Mantis" so I named it "Mantis Drive"

  //this allows the left and right motors to move independantly from each other
  public void mantisDrive(double left, double right) {
    backLeft.set(TalonSRXControlMode.PercentOutput, -left);
    backRight.set(TalonSRXControlMode.PercentOutput, -right);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //this declares the drive mode
    //if driveMode == true, then gtaDrive is used, and if its false, then mantisDrive is used
    if(driveMode == true){
      //this assigns the drive and turn parameters for gtaDrive to joystick inputs
      gtaDrive(RobotContainer.con1.getLeftY(), RobotContainer.con1.getRightX());
    }else{
      //this assigns the left and right parameters for mantisDrive to joystick inputs
      mantisDrive(RobotContainer.con1.getLeftY(), RobotContainer.con1.getRightY());
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
