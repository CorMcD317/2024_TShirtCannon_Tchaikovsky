// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RealElevaltor extends SubsystemBase {

  private final CANSparkMax motor;
  private final double gearing = 4 * 4 * 3 * 40.0 / 30.0;
  private final double drumRadius = Units.inchesToMeters(1.0);
  private final double factor = 2 * Math.PI * drumRadius / gearing;
  private final ElevatorFeedforward feedforward;

  /** Creates a new RealElevaltor. */
  public RealElevaltor(int deviceId) {
    motor = new CANSparkMax(deviceId, MotorType.kBrushless);
    motor.getEncoder().setPositionConversionFactor(factor);
    motor.getEncoder().setVelocityConversionFactor(factor / 60.0);
    motor.getEncoder().setAverageDepth(2);
    motor.getEncoder().setMeasurementPeriod(16);
    feedforward = new ElevatorFeedforward(0, 0, 0, 0);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
