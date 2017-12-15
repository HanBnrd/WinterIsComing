package general;

import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Forward implements Behavior
{
	MovePilot pilot;
	
	public Forward(MovePilot p)
	{
		// TODO Auto-generated constructor stub
		pilot = p;
	}

	@Override
	public boolean takeControl()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		pilot.forward();
	}

	@Override
	public void suppress()
	{
		// TODO Auto-generated method stub
	}
}
