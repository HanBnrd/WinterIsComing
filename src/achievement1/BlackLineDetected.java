package achievement1;

import java.util.Hashtable;

import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class BlackLineDetected implements Behavior
{
	private EV3ColorSensor colorSensor;
	private float[] sample;
	private Hashtable<Colour,float[]> colours;
	private MovePilot pilot;
	private int nbCasesParcourues;

	/**
	 * Constructeur du comportement BlackLineDetected
	 * @param cs une instance de la classe liee au capteur de couleurs
	 * @param colours les couleurs de reference mesurees par la phase d'initiation
	 * @param p l'instance de MovePilot utilisee
	 */
	public BlackLineDetected(EV3ColorSensor cs, Hashtable<Colour,float[]> colours, MovePilot p)
	{
		// TODO Auto-generated constructor stub
		sample = new float[cs.sampleSize()];
		colorSensor = cs;
		this.colours = colours;
		pilot = p;
		nbCasesParcourues=0;
	}

	@Override
	public boolean takeControl()
	{
		// TODO Auto-generated method stub
		colorSensor.fetchSample(sample, 0);
		Colour c = getColour(sample);
		return c == Colour.BLACK;
	}

	/**
	 * Prend une mesure avec le capteur de couleur et l'associe a une des couleurs initialement definies
	 * @return la couleur mesuree
	 */
	public Colour getSensorColour() {
		colorSensor.fetchSample(sample, 0);
		Colour colour = getColour(sample);
		return colour;
	}
	
	/**
	 * Definition des messages a afficher lors du parcours de chaque type de case
	 * @param col la couleur de la case mesurée
	 * @return le message a afficher
	 */
	public String getMessage(Colour col) {
		String message = "Position inconnue";
		switch (col)
		{
		case WHITE: message = "Position initiale du robot";
		break;
		case GREEN: message = "Prairie";
		break;
		case BROWN: message = "Montagne";
		break;
		case RED: message = "Camp militaire";
		break;
		case BLUE: message = "Mur de glace";
		break;
		case BLACK: message = "Changement de case";
		break;
		case UNKNOWN: message = "Position inconnue";
		}
		return message;
	}
	
	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		pilot.stop();
		LCD.clear();
		LCD.drawString("Changement de case", 0, 2);
		LCD.refresh();
		Colour colour = getSensorColour();
		if (colour == Colour.BLACK || colour == Colour.UNKNOWN)
			pilot.travel(10);
		colorSensor.fetchSample(sample, 0);
		colour = getColour(sample);
		nbCasesParcourues++;
		
		LCD.clear();
		LCD.drawString(getMessage(colour), 0, 2);
		LCD.drawString("Parcours de la", 0, 3);
		LCD.drawString((1+nbCasesParcourues) +"eme case", 2, 4);
		LCD.refresh();
		//Button.waitForAnyPress();
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
