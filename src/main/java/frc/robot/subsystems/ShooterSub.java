// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSub extends SubsystemBase {
  TalonFX shooter;
  public ShooterSub(int ID) {
    //this assigns the shooter motor to its ID
    shooter = new TalonFX(ID);
  }

  //this tells the shooter motor to move at a certain speed due to a parameter
  public void move(double percentSpeed){
    shooter.set(TalonFXControlMode.PercentOutput, percentSpeed);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
