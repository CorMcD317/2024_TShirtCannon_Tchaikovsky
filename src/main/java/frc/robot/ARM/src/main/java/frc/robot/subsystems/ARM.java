// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ARM extends SubsystemBase {

  private final CANSparkMax motor;
  private final double reduction 5 * 4 * 3 * 56.0 / 18.0;

  /** Creates a new ARM. */
  public ARM(int deviceId) {
    motor = new CANSparkMax(0, MotorType.kBrushless);
    motor.getEncoder().setPositionConversionFactor(2 * Math.PI / reduction);
    motor.getEncoder().setVelocityConversionFactor(2 * Math.PI / reduction / 60.0);

  }

  public double getPositionDegrees(){
    return Units.radiansToDegrees(motor.getEncoder().getPosition());
  }

  public double getVelocityDPS(){
    return Units.radiansToDegrees(motor.getEncoder().getVelocity());

  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
