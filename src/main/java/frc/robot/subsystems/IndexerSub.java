// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class IndexerSub extends SubsystemBase {
  Solenoid OutSolenoid;
  Solenoid InSolenoid;
  public IndexerSub(int outID, int inID) {
    OutSolenoid = new Solenoid(outID, PneumaticsModuleType.CTREPCM, 0);
    InSolenoid = new Solenoid(inID, PneumaticsModuleType.CTREPCM, 1);

    OutSolenoid.set(false);
    InSolenoid.set(true);
  }

  public void toggleIndexer() {
    OutSolenoid.toggle();
    InSolenoid.toggle();
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
