package achievement1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class BlackLineDetected implements Behavior
{
	EV3ColorSensor colorSensor;
	Arbitrator arby;
	float[] sample;
	Hashtable<Colour,float[]> colours;
	MovePilot pilot;

	public void setArbitrator(Arbitrator a) {
		this.arby=a;
	}

	public BlackLineDetected(EV3ColorSensor cs, Hashtable<Colour,float[]> colours, MovePilot p)
	{
		// TODO Auto-generated constructor stub
		sample = new float[cs.sampleSize()];
		colorSensor = cs;
		this.colours = colours;
		pilot = p;
	}

	@Override
	public boolean takeControl()
	{
		// TODO Auto-generated method stub
		colorSensor.fetchSample(sample, 0);
		Colour c = getColour(sample);
		//LCD.clear();
		//LCD.drawString(c, 0, 4);
		//LCD.refresh();
		//String str = "" +c.equals("Black");
		//LCD.drawString(str, 0, 5);
		//LCD.refresh();
		//if (c.equals("Black")) arby.stop();
		return c == Colour.BLACK;
	}

	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		pilot.stop();
		LCD.clear();
		LCD.drawString("Black line detected !", 0, 2);
		LCD.refresh();
		colorSensor.fetchSample(sample, 0);
		Colour colour = getColour(sample);
		if (colour == Colour.BLACK || colour == Colour.UNKNOWN)
			pilot.travel(10);
		colorSensor.fetchSample(sample, 0);
		colour = getColour(sample);
		String message = "We're totally lost !!!";
		switch (colour)
		{
		case WHITE: message = "Let's go et c'est parti les amis !";
		break;
		case GREEN: message = "I'm in the plain !";
		break;
		case BROWN: message = "Space Mountain !!!";
		break;
		case RED: message = "Bye I go to bed !";
		break;
		case BLUE: message = "This is the wall !";
		break;
		case BLACK: message = "Black is black ! There's no more hope !";
		break;
		case UNKNOWN: message = "Where did you bring me ??";
		}
		LCD.drawString(message, 0, 3);
		LCD.refresh();
		Button.waitForAnyPress();
	}

	public Colour getColour(float[] rgb) {
		// TODO Auto-generated method stub
		float[] tabColours;
		for (Colour colour : colours.keySet())
		{
			tabColours = colours.get(colour);
			if (rgb[0] <= tabColours[0] + (50*tabColours[0])/100
				&& rgb[0] >= tabColours[0] - (50*tabColours[0])/100
				&& rgb[1] <= tabColours[1] + (50*tabColours[1])/100
				&& rgb[1] >= tabColours[1] - (50*tabColours[1])/100
				&& rgb[2] <= tabColours[2] + (50*tabColours[2])/100
				&& rgb[2] >= tabColours[2] - (50*tabColours[2])/100)
			{
				return colour;
			}
		}
		return Colour.UNKNOWN;
	}

	@Override
	public void suppress()
	{
		// TODO Auto-generated method stub
	}

}
