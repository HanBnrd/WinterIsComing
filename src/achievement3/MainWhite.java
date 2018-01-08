package achievement3;

import achievement2.Wait;
import general.Terminator;
import general.Util;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainWhite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovePilot pilot = Util.newPilot();
		BTConnector bt = new BTConnector();
		BTConnection btc = bt.connect("00:16:53:43:63:D4", NXTConnection.PACKET);
		Behavior w=new Wait();
		Behavior sp = new SendPosition(pilot,btc);
		Terminator terminator = new Terminator(null, pilot);
		Behavior[] ba = {w,sp, terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(ba);
		terminator.setArbitrator(arby);
		arby.go();
	}

}
