package achievement3;

import general.Fight;
import general.Terminator;
import general.Util;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainWatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovePilot pilot = Util.newPilot();
		Terminator terminator = new Terminator(null, pilot);
		Fight fight = new Fight(pilot);
		Behavior[] bArray = {fight, terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		terminator.setArbitrator(arby);
		arby.go();
	}

}
