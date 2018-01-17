package achievement2;

import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class SimpleTerminator implements Behavior
{
	private Arbitrator arbitrator;
	
	/**
	 * Constructeur du comportement mettant fin au programme
	 * @param sensors les capteurs utilises par le programme qu'il faut fermer
	 */
	public SimpleTerminator()
	{
		// TODO Auto-generated constructor stub
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
