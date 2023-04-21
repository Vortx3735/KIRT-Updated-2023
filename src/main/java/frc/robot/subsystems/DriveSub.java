// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSub extends SubsystemBase {
  TalonSRX backLeft, 
           backRight, 
           frontLeft, 
           frontRight;
  public DriveSub(
    int backLeftID,
    int frontLeftID,
    int backRightID,
    int frontRightID) {
      backLeft = new TalonSRX(backLeftID);
      frontLeft = new TalonSRX(frontLeftID);
      backRight = new TalonSRX(backRightID);
      frontRight = new TalonSRX(frontRightID);
      frontLeft.follow(backLeft);
      frontRight.follow(backRight);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(RobotContainer.con1.getLeftY() != 0){
      backLeft.set(TalonSRXControlMode.PercentOutput, -RobotContainer.con1.getLeftY());
      backRight.set(TalonSRXControlMode.PercentOutput, -RobotContainer.con1.getLeftY());
    }else{
      backLeft.set(TalonSRXControlMode.PercentOutput, 0);
      backRight.set(TalonSRXControlMode.PercentOutput, 0);
    }

    if(RobotContainer.con1.getLeftX() != 0){
      backLeft.set(TalonSRXControlMode.PercentOutput, -RobotContainer.con1.getLeftY());
      backRight.set(TalonSRXControlMode.PercentOutput, RobotContainer.con1.getLeftY());
    }else{
      backLeft.set(TalonSRXControlMode.PercentOutput, 0);
      backRight.set(TalonSRXControlMode.PercentOutput, 0);
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
