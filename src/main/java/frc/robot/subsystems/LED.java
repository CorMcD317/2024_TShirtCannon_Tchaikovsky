package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
  /** Creates a new LED. */

  private final AddressableLED leds;
  private final AddressableLEDBuffer buffer;
  private int rainbowHueStart = 0;

  public LED(int port, int length){
   leds = new AddressableLED(port);
   buffer = new AddressableLEDBuffer(length);
   leds.setLength(buffer.getLength());
   leds.setData(buffer);
   leds.start();
  }

  private void setColor(Color8Bit color) {
    for (int i = 0; i < buffer.getLength(); i++) {
      buffer.setLED(i, color);
    }

  }

  private void rainbow() {
    for (var i = 0; i < buffer.getLength(); i++) {
      final var hue = (rainbowHueStart + (i * 180 / buffer.getLength())) % 180;
      buffer.setHSV(i, hue, 255, 255);
    }
    rainbowHueStart += 3;
    rainbowHueStart %= 180;
  }

  @Override
  public void periodic() {
    leds.setData(buffer);
  }

  public Command setColorCommand(Color8Bit color){
    return runOnce(() -> setColor(color));
  }

  public Command setRainbow(){
    return run(this::rainbow);
  }
}
