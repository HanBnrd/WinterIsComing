package achievement1;

import java.util.Hashtable;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Forward implements Behavior
{
	private MovePilot pilot;
	private EV3ColorSensor cs;
	private Hashtable<Colour,float[]> colours;
	private float[] sample;
	
	/**
	 * Constructeur du comportement Forward
	 * @param c une instance de la classe liee au capteur de couleurs
	 * @param colours les couleurs de reference mesurees par la phase d'initiation
	 * @param p l'instance de MovePilot utilisee
	 */
	public Forward(EV3ColorSensor c, Hashtable<Colour,float[]> colours, MovePilot p)
	{
		// TODO Auto-generated constructor stub
		pilot = p;
		this.cs=c;
		this.colours=colours;
		sample = new float[cs.sampleSize()];
	}

	@Override
	public boolean takeControl()
	{
		// TODO Auto-generated method stub
		cs.fetchSample(sample, 0);
		Colour c = getColour(sample);
		return c != Colour.BLACK;
	}

	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		if (!pilot.isMoving())
			pilot.forward();
		Thread.yield();
	}

	/**
	 * Permet de faire le lien entre une mesure RGB et une couleur 
	 * @param rgb la mesure
	 * @return une des couleurs definies, ou UNKNOWN si la couleur est inconnue
	 */
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
