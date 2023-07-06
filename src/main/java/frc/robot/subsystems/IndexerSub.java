// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class IndexerSub extends SubsystemBase {

  // On this robot, the indexer is pneumatic, meaning it's controlled by air.
  // For pneumatics, its really easy on programmers, because its literally on/off.
  // There are two types of solenoids, single and double.
  // Single solenoids are only able to turn on and off air flow. They have two states
  // Double solenoids are able to turn on and off air flow through two channels, one on at a time.

  // It's also important to note that we need to initiate the compressor, which we do in RobotContainer.java

  // Usually we would use a DoubleSolenoid here, since we need to toggle air flow. But, you can use two singles and
  // you will get the same result

  Solenoid OutSolenoid,
            InSolenoid;
  public IndexerSub(int outID, int inID) {
    //this assigns each single solenoid to their respective IDs
    OutSolenoid = new Solenoid(outID, PneumaticsModuleType.CTREPCM, 0);
    InSolenoid = new Solenoid(inID, PneumaticsModuleType.CTREPCM, 1);

    //this sets the default positions for the solenoids
    OutSolenoid.set(false);
    InSolenoid.set(true);
  }

  //this toggles each solenoid
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
