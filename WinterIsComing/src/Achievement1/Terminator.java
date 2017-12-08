package Achievement1;

import java.util.ArrayList;

import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Terminator implements Behavior
{
	ArrayList<BaseSensor> sensors;
	Arbitrator arbitrator;
	MovePilot pilot;
	
	public Terminator(ArrayList<BaseSensor> sensors, MovePilot p)
	{
		// TODO Auto-generated constructor stub
		this.sensors = sensors;
		pilot = p;
	}
	
	void setArbitrator(Arbitrator arby)
	{
		arbitrator = arby;
	}

	@Override
	public boolean takeControl()
	{
		// TODO Auto-generated method stub
		return Button.RIGHT.isDown() || Battery.getVoltageMilliVolt() <= 1500;
	}

	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		pilot.stop();
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
