// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSub;

public class ShooterCom extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSub shooter;

  public ShooterCom(ShooterSub inputShooter) {
    shooter = inputShooter;
    addRequirements(shooter);
  }


  //these tell the shooter subsystem to move at a certain speed
  public void shootSlow() {
    shooter.move(.2);
  }

  public void shootMed() {
    shooter.move(.5);
  }
  
  public void shootMax() {
    shooter.move(1);
  }

  public void stop() {
    shooter.move(0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
