package achievement3;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import general.Fight;
import general.Map;
import general.Terminator;
import general.Util;
import general.Wait;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
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
		LCD.drawString("Bonjour", 0, 3);
        Button.waitForAnyPress();
        Map.POSITION[0] = 6;
        Map.POSITION[1] = 0;
        Map.POSITION[2] = 0;
		MovePilot pilot = Util.newPilot();
		BTConnector bt = new BTConnector();
		NXTConnection btc = bt.waitForConnection(100000,NXTConnection.PACKET);
		InputStream is = btc.openInputStream();
		DataInputStream dis = new DataInputStream(is);
		try {
			String data = dis.readUTF();
			LCD.drawString(data, 0, 2);
			String[] pos = data.split(";");
			Map.WHITEWALKERPOSITION=new int[2];
			Map.WHITEWALKERPOSITION[0]=Integer.parseInt(pos[0]);
			Map.WHITEWALKERPOSITION[1]=Integer.parseInt(pos[1]);
			LCD.clear();
			LCD.drawString(pos[0]+" "+pos[1], 0, 3);
			LCD.refresh();
			btc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // récupère la valeur dans le flux
		ArrayList<BaseSensor> al=new ArrayList<>();
		Behavior w=new Wait();
		Behavior f=new Fight(pilot);
		Terminator terminator = new Terminator(al, pilot);
		Behavior[] bArray = {w,f,terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		terminator.setArbitrator(arby);
		arby.go();
	}

}
