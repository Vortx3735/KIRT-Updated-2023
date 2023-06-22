// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //This creates a controller object for each port
  public static VorTXController con1 = new VorTXController(0);
  public static VorTXController con2 = new VorTXController(1);

  public static IntakeSub intakeSub = new IntakeSub(0);
  public static IntakeCom intake = new IntakeCom(intakeSub);

  public static IndexerSub indexerSub = new IndexerSub(0, 0);
  public static IndexerCom indexer = new IndexerCom(indexerSub);

  public static ShooterSub shooterSub = new ShooterSub(0);
  public static ShooterCom shooter = new ShooterCom(shooterSub);

  public static DriveSub driveSub = new DriveSub(0, 0, 0, 0);
  public static DriveCom drive = new DriveCom(driveSub);

  public static Compressor phCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    phCompressor.disable();
    //phCompressor.enableDigital();

    // Configure the trigger bindings
    configureBindings();

    //these set the default position for each motor based subsystem
    intakeSub.setDefaultCommand(
      new RunCommand(
        intake::stopIntake, 
        intakeSub
      )
    );

    shooterSub.setDefaultCommand(
      new RunCommand(
        shooter::stop, 
        shooterSub
      )
    );
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //binds the intake to cross
    con2.cross.whileTrue(
      new RunCommand(
        intake::startIntake,
        intakeSub
      )
    );

    //binds the slow shooting speed command to square
    con2.square.whileTrue(
      new RunCommand(
        shooter::shootSlow,
        shooterSub)
    );

    //binds the medium shooting speed command to circle
    con2.circle.whileTrue(
      new RunCommand(
        shooter::shootMed,
        shooterSub)
    );

    //binds the maximum shooting speed command to triangle
    con2.triangle.whileTrue(
      new RunCommand(
        shooter::shootMax,
        shooterSub)
    );

    //binds the toggle for the indexer solenoid to r1
    con2.r1.onTrue(
      new RunCommand(
        indexer::toggleIndex,
        indexerSub)
    );

    //binds the toggle for the driving mode to l1
    con1.playstation.onTrue(
      new RunCommand(
        drive::toggleDriveMode,
        driveSub)
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new InstantCommand();
  }
}
