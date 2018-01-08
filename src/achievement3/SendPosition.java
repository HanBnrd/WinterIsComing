package achievement3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import general.Map;

import lejos.hardware.Button;
import lejos.remote.nxt.BTConnection;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class SendPosition implements Behavior {
	MovePilot pilot;
	BTConnection btc;
	
	public SendPosition(MovePilot mp, BTConnection btc) {
		// TODO Auto-generated constructor stub
		this.pilot = mp;
		this.btc = btc;
		
	}

	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Button.UP.isDown();
	}

	public void action() {
		// TODO Auto-generated method stub
		pilot.stop();
		
		OutputStream os = btc.openOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		try {
			dos.writeUTF(Map.POSITION[0]+";"+Map.POSITION[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // écrit une valeur int dans le flux
		try {
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // force l’envoi
		
		
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
