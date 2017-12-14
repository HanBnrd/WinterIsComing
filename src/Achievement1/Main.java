package Achievement1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		LCD.drawString("Helle World !", 0, 0);
        Button.waitForAnyPress();
        ArrayList<BaseSensor> sensors = new ArrayList<BaseSensor>();
		
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		sensors.add(cs);
		Hashtable<String,float[]> nuances = getColourValues(cs);
		
		MovePilot pilot = newPilot();
		Behavior forward = new Forward(pilot); // Avancer
		Behavior bld = new BlackLineDetected(cs, nuances, pilot);
		Terminator terminator = new Terminator(sensors, pilot);
		Behavior[] bArray = {forward, bld, terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		terminator.setArbitrator(arby);
		LCD.clear();
		LCD.drawString("Ready to go !", 0, 0);
		LCD.refresh();
		Button.waitForAnyPress();
		arby.go();
	}

	private static MovePilot newPilot()
	{
		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56).offset(-60);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[]{wheel1, wheel2}, 2);
		MovePilot pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(pilot.getMaxLinearSpeed());
		pilot.setLinearAcceleration(720);
		return pilot;
	}
	
	private static Hashtable<String,float[]> getColourValues(EV3ColorSensor cs)
	{
		float[] s = new float[]{0};
		Hashtable<String, float[]> nuances = new Hashtable<String, float[]>();
		boolean allColours = false;
		int i = 0;
		String colour = "";
		while (!allColours)
		{
			i++;
			switch (i)
			{
				case 1: colour = "White";
				break;
				case 2: colour = "Green";
				break;
				case 3: colour = "Brown";
				break;
				case 4: colour = "Red";
				break;
				case 5: colour = "Blue";
				break;
				case 6: colour = "Black"; allColours = true;
				break;
			}
			LCD.clear();
			LCD.drawString(colour, 0, 0);
			LCD.refresh();
			ArrayList<Float> colourValues = new ArrayList<Float>();
			int nbColours = 0;
			while (nbColours < 2 || Button.DOWN.isUp())
			{
				nbColours++;
				cs.fetchSample(s, 0);
				String str = "" + s[0];
				LCD.drawString(str, 0, 0);
				colourValues.add(s[0]);
				Button.waitForAnyPress();
			}
			Collections.sort(colourValues);
			float[] nuance = new float[2];
			nuance[0] = colourValues.get(0);
			nuance[1] = colourValues.get(colourValues.size() - 1);
			nuances.put(colour, nuance);
		}
		return nuances;
	}
}
