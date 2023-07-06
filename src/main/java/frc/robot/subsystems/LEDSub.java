// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class LEDSub extends SubsystemBase {
  AddressableLED m_led;
  AddressableLEDBuffer m_ledBuffer;

  private int vorTXStreakLED = 0;
  private int numLoops;
  private boolean ledToggle = false;

  public LEDSub(int port,int length) {
    m_led = new AddressableLED(port);
    m_ledBuffer = new AddressableLEDBuffer(length);
    m_led.setLength(length);

    m_led.setData(m_ledBuffer);
    m_led.start();
  }

  public void setVorTX() {
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(i, 125, 194, 67);
    }
    m_led.setData(m_ledBuffer);
  }

  public void setVorTXStreak() {
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      // Sets the specified LED to the RGB values for blue
      m_ledBuffer.setRGB(i, 125, 194, 67);
    }

    //turns one led off
    m_ledBuffer.setRGB(vorTXStreakLED, 0, 0, 0);

    //increase brightness
    if (numLoops%3 == 0){
        vorTXStreakLED += 1;


        //Check bounds
        vorTXStreakLED %= m_ledBuffer.getLength();
      }

    m_led.setData(m_ledBuffer);


    numLoops += 1;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(RobotContainer.con1.getPOV() == 0) {
      if(!ledToggle){
        ledToggle = true;
      } else {
        ledToggle = false;
      }
    }

    if(!ledToggle) {
      setVorTXStreak();
    } else if (ledToggle) {
      setVorTX();
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
