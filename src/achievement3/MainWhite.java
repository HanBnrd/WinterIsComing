package achievement3;

import general.Map;
import general.Terminator;
import general.Util;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainWhite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LCD.drawString("Bonjour", 0, 2);
		LCD.refresh();
        Button.waitForAnyPress();
        Map.POSITION[0] = 0;
        Map.POSITION[1] = 4;
        Map.POSITION[2] = 2;
		MovePilot pilot = Util.newPilot();
		SendPosition sp = new SendPosition(pilot);
		Behavior c=new SquareMove(pilot, sp);
		Terminator terminator = new Terminator(null, pilot);
		Behavior[] ba = {c,sp, terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(ba);
		terminator.setArbitrator(arby);
		arby.go();
	}

}
