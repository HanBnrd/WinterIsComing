package achievement3;

import general.Map;

import java.io.DataInputStream;
import java.io.IOException;

import lejos.robotics.subsumption.Behavior;

public class GetPosition implements Behavior {
	DataInputStream dis;
	String data;
	
	public GetPosition(DataInputStream dis) {
		// TODO Auto-generated constructor stub
		this.dis = dis;
		
	}

	public boolean takeControl() {
		// TODO Auto-generated method stub
		try {
			if (dis.readUTF()!=null) {
				this.data=dis.readUTF();
			}
			return data!=null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void action() {
		// TODO Auto-generated method stub
		
		String[] pos = data.split(";");
		Map.ENNEMY[0]=Integer.parseInt(pos[0]);
		Map.ENNEMY[0]=Integer.parseInt(pos[1]);
		System.out.println(Map.ENNEMY);
		
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
