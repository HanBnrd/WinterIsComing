package general;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.Hashtable;

import achievement1.BlackLineDetected;
import general.*;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.hardware.sensor.EV3TouchSensor;
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
		LCD.drawString("Hello World !", 0, 0);
        Button.waitForAnyPress();
        ArrayList<BaseSensor> sensors = new ArrayList<BaseSensor>();
		
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		cs.setCurrentMode(cs.getRGBMode().getName());
		sensors.add(cs);
		Hashtable<Colour,float[]> colours = getColourValues(cs);
		
		MovePilot pilot = Util.newPilot();
		Behavior forward = new Forward(cs, colours, pilot); // Avancer
		BlackLineDetected bld = new BlackLineDetected(cs, colours, pilot);
		Terminator terminator = new Terminator(sensors, pilot);
		Behavior[] bArray = {forward, bld, terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		terminator.setArbitrator(arby);
		bld.setArbitrator(arby);
		LCD.clear();
		LCD.drawString("Ready to go !", 0, 2);
		LCD.refresh();
		Button.waitForAnyPress();
		arby.go();
	}
	
	private static Hashtable<Colour,float[]> getColourValues(EV3ColorSensor cs)
	{
		Hashtable<Colour, float[]> nuances = new Hashtable<Colour, float[]>();
		boolean allColours = false;
		int i = 0;
		Colour colour = null;
		while (!allColours)
		{
			i++;
			switch (i)
			{
				case 1: colour = Colour.BLACK; 
				break;
				case 2: colour = Colour.WHITE;
				break;
				case 3: colour = Colour.GREEN;
				break;
				case 4: colour = Colour.BLUE;
				break;
				case 5: colour = Colour.RED;
				break;
				case 6: colour = Colour.BROWN;
				allColours = true;
				break;
				
			}
			LCD.clear();
			LCD.drawString(colour.toString(), 0, 0);
			LCD.refresh();
			Button.ENTER.waitForPress();
			float[] s = new float[cs.sampleSize()];
			cs.fetchSample(s, 0);
			String str = "" + s[0];
			LCD.drawString(str, 0, 1);
			str = "" + s[1];
			LCD.drawString(str, 0, 2);
			str = "" + s[2];
			LCD.drawString(str, 0, 3);
			LCD.refresh();
			nuances.put(colour, s);
			Button.ENTER.waitForPress();
		}
		return nuances;
	}
}