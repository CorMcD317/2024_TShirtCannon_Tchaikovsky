package frc.robot;

import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.LED;

public class RobotContainer {

  private final LED ledSubsystem = new LED(0, 70);
  private final CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();

  }

  private void configureBindings() {
    controller.a().onTrue(ledSubsystem.setColorCommand(new Color8Bit(0, 255, 0)));
    controller.b().onTrue(ledSubsystem.setColorCommand(new Color8Bit(0, 128, 255)));
    controller.x().whileTrue(ledSubsystem.setRainbow());
    controller.x().onFalse(ledSubsystem.setColorCommand(new Color8Bit("#d4af37")));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }


}
