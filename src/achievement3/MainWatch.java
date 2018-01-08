package achievement3;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import general.Map;
import general.Terminator;
import general.Util;
import general.Wait;
import lejos.hardware.Button;
import lejos.hardware.sensor.BaseSensor;
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
		System.out.println("PREMIER");
		try {
			System.out.println("DEUXIEME");
			String data = dis.readUTF();
			System.out.println(data);
			String[] pos = data.split(";");
			System.out.println(pos[0]);
			System.out.println(pos[1]);
			Map.WHITEWALKERPOSITION[0]=Integer.parseInt(pos[0]);
			Map.WHITEWALKERPOSITION[1]=Integer.parseInt(pos[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // récupère la valeur dans le flux
		//Behavior fight = new Fight(pilot);
		ArrayList<BaseSensor> al=new ArrayList<>();
		Behavior w=new Wait();
		Terminator terminator = new Terminator(al, pilot);
		Behavior[] bArray = {w,terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		terminator.setArbitrator(arby);
		arby.go();
	}

}
