package achievement2;

import java.util.ArrayList;

import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class TerminatorSensor implements Behavior
{
	ArrayList<BaseSensor> sensors;
	Arbitrator arbitrator;
	
	public TerminatorSensor(ArrayList<BaseSensor> sensors)
	{
		// TODO Auto-generated constructor stub
		this.sensors = sensors;
	}
	
	void setArbitrator(Arbitrator arby)
	{
		arbitrator = arby;
	}

	@Override
	public boolean takeControl()
	{
		// TODO Auto-generated method stub
		return Button.ENTER.isDown() || Battery.getVoltageMilliVolt() <= 1500;
	}

	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		for (BaseSensor sensor : sensors)
		{
			if (sensor instanceof EV3TouchSensor)
				((EV3TouchSensor) sensor).close();
			else if (sensor instanceof EV3ColorSensor)
				((EV3ColorSensor) sensor).close();
			else
				sensor.close();
		}
		arbitrator.stop();
		LCD.clear();
		LCD.drawString("Au revoir", 0, 0);
		LCD.refresh();
		Button.waitForAnyPress();
		LCD.clear();
		System.exit(0);
	}

	@Override
	public void suppress()
	{
		// TODO Auto-generated method stub
		
	}
}
