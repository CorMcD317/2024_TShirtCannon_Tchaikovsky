// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RealElevator extends SubsystemBase {

  private final CANSparkMax motor; 
  private final double gearing = 4.0 * 4.0 * 3.0 *40.0 / 30.0;
  private final double drumRadius = Units.inchesToMeters(1.0);
  private final double factor = 2 * Math.PI * drumRadius / gearing;
  private final ElevatorFeedforward feedforward; 

  /** Creates a new RealElevator. */
  public RealElevator(int deviceId) {
    motor = new CANSparkMax(deviceId, MotorType.kBrushless);
    motor.getEncoder().setPositionConversionFactor(factor);
    motor.getEncoder().setVelocityConversionFactor(factor / 60.0);
    motor.getEncoder().setAverageDepth(depth:2);
    motor.getEncoder().setMeasurementPeriod(period_ms:16);
    feedforward = new ElevatorFeedforward(ks:0, kg:0, kv:0, ka:0);

    System.out.println(gearing);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
