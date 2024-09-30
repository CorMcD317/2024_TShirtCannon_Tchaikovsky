package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {

        private final CANSparkMax motor;
        private final double gearing = 4d * 4 * 3 * 40 / 30;
        private final double drumRadius = Units.inchesToMeters(1.0);
        private final double factor = 2 * Math.PI * drumRadius / gearing;
        private final ElevatorFeedforward feedforward;
        private final TrapezoidProfile profile;
        private final TrapezoidProfile.State setpoint;
        private final TrapezoidProfile.State goal;

        public Elevator(int deviceId) {
                motor = new CANSparkMax(deviceId, MotorType.kBrushless);
                motor.getEncoder().setPositionConversionFactor(factor);
                motor.getEncoder().setVelocityConversionFactor(factor / 60.0);
                motor.getEncoder().setAverageDepth(2);
                motor.getEncoder().setMeasurementPeriod(16);
                feedforward = new ElevatorFeedforward(0, 0, 0, 0);
                double maxVelocity = feedforward.maxAchievableVelocity(12.0, 0.0);
                double maxAcceleration = feedforward.maxAchievableAcceleration(12.0, 0.0);
                TrapezoidProfile.Constraints constraints = new Constraints(maxVelocity, maxAcceleration);
                profile = new TrapezoidProfile(constraints);
                setpoint = new State();
                goal = new State();
        }

        private void setPositionGoal(double positionGoalMeters) {
                goal.position = positionGoalMeters;
                goal.velocity = 0.0;
        }

        @Override
        public void periodic() {

        }
}