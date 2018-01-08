package achievement3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import general.Map;

import lejos.hardware.Button;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class SendPosition implements Behavior {
	MovePilot pilot;
	
	public SendPosition(MovePilot mp) {
		// TODO Auto-generated constructor stub
		this.pilot = mp;
		
	}

	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Button.UP.isDown();
	}

	public void action() {
		// TODO Auto-generated method stub
		pilot.stop();
		BTConnector bt = new BTConnector();
		BTConnection btc = bt.connect("00:16:53:43:63:D4", NXTConnection.PACKET);
		OutputStream os = btc.openOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		try {
			String data=Map.POSITION[0]+";"+Map.POSITION[1];
			dos.writeUTF(data);// écrit une valeur str dans le flux
			dos.flush();// force l’envoi
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
