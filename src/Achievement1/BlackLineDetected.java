package Achievement1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class BlackLineDetected implements Behavior
{
	EV3ColorSensor colorSensor;
	float[] sample;
	Hashtable<String,float[]> nuances;
	MovePilot pilot;
	
	public BlackLineDetected(EV3ColorSensor cs, Hashtable<String,float[]> nuances, MovePilot p)
	{
		// TODO Auto-generated constructor stub
		sample = new float[]{0};
		colorSensor = cs;
		this.nuances = nuances;
		pilot = p;
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		colorSensor.fetchSample(sample, 0);
		return sample[0] <= nuances.get("Black")[1] || sample[0] >= nuances.get("Black")[0];
	}

	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		pilot.stop();
		colorSensor.fetchSample(sample, 0);
		if (sample[0] <= nuances.get("Black")[1] || sample[0] >= nuances.get("Black")[0])
			pilot.travel(20);
		colorSensor.fetchSample(sample, 0);
		String colour = getColour(sample[0]);
		System.out.println(colour + " : " + sample[0]);
	}

	private String getColour(float f) {
		// TODO Auto-generated method stub
		String result = "Unknown !";
		for (String colour : nuances.keySet())
		{
			if (f <= nuances.get(colour)[1] || sample[0] >= nuances.get(colour)[0])
				result = colour;
		}
		return result;
	}

	@Override
	public void suppress()
	{
		// TODO Auto-generated method stub
	}

}
