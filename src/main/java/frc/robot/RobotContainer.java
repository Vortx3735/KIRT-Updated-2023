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

  public static VorTXController con1 = new VorTXController(0);

  public static IntakeSub intakeSub = new IntakeSub(0);
  public static IntakeCom intake = new IntakeCom(intakeSub);

  public static IndexerSub indexerSub = new IndexerSub(0, 0);
  public static IndexerCom indexer = new IndexerCom(indexerSub);

  public static ShooterSub shooterSub = new ShooterSub(0);
  public static ShooterCom shooter = new ShooterCom(shooterSub);

  public static DriveSub driveSub = new DriveSub(0, 0, 0, 0);

  public static Compressor phCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    phCompressor.disable();
    //phCompressor.enableDigital();

    // Configure the trigger bindings
    configureBindings();

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
    con1.cross.whileTrue(
      new RunCommand(
        intake::startIntake,
        intakeSub
      )
    );

    con1.square.whileTrue(
      new RunCommand(
        shooter::shootSlow,
        shooterSub)
    );

    con1.circle.whileTrue(
      new RunCommand(
        shooter::shootMid,
        shooterSub)
    );

    con1.triangle.whileTrue(
      new RunCommand(
        shooter::shootMax,
        shooterSub)
    );

    con1.r1.onTrue(
      new RunCommand(
        indexer::toggleIndex,
        indexerSub)
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
