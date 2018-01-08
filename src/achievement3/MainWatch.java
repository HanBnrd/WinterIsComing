package achievement3;

import java.io.DataInputStream;
import java.io.InputStream;

import general.Fight;
import general.Terminator;
import general.Util;
import general.Wait;
import lejos.hardware.Button;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainWatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bonjour");
        Button.waitForAnyPress();
		MovePilot pilot = Util.newPilot();
		BTConnector bt = new BTConnector();
		NXTConnection btc = bt.waitForConnection(100000,NXTConnection.PACKET);
		InputStream is = btc.openInputStream();
		DataInputStream dis = new DataInputStream(is);
		Behavior w=new Wait();
		Behavior gp = new GetPosition(dis);
		//Behavior fight = new Fight(pilot);
		Terminator terminator = new Terminator(null, pilot);
		Behavior[] bArray = {w,gp,terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		terminator.setArbitrator(arby);
		arby.go();
	}

}
