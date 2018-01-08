package achievement3;

import general.Map;
import general.Wait;
import general.Terminator;
import general.Util;
import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainWhite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bonjour");
        Button.waitForAnyPress();
        Map.POSITION[0] = 6;
        Map.POSITION[1] = 0;
        Map.POSITION[2] = 0;
		MovePilot pilot = Util.newPilot();
		Behavior w=new Wait();
		Behavior sp = new SendPosition(pilot);
		Terminator terminator = new Terminator(null, pilot);
		Behavior[] ba = {w,sp, terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(ba);
		terminator.setArbitrator(arby);
		arby.go();
	}

}
