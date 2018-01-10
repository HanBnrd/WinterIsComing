package achievement3;

import general.Map;
import achievement3.SendPosition;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Circle implements Behavior {
	MovePilot pilot;
	SendPosition sp;
	
	public Circle(MovePilot mp, SendPosition sp) {
		// TODO Auto-generated constructor stub
		this.pilot=mp;
		this.sp = sp;
	}

	public boolean takeControl() {
		// TODO Auto-generated method stub
		return !sp.isMessageSent();
	}

	public void action() {
		// TODO Auto-generated method stub
		pilot.travel(2*88);
        pilot.stop();
        if (Map.POSITION[2]==0) {
        	Map.POSITION[0]=Map.POSITION[0]-2;
        } else if (Map.POSITION[2]==1) {
        	Map.POSITION[1]=Map.POSITION[1]+2;
        } else if (Map.POSITION[2]==2) {
        	Map.POSITION[0]=Map.POSITION[0]+2;
        } else if (Map.POSITION[2]==3) {
        	Map.POSITION[1]=Map.POSITION[1]-2;
        }
        pilot.rotate(80);
        if (Map.POSITION[2]==0) {
        	Map.POSITION[2]=1;
        } else if (Map.POSITION[2]==1) {
        	Map.POSITION[2]=2;
        } else if (Map.POSITION[2]==2) {
        	Map.POSITION[2]=3;
        } else if (Map.POSITION[2]==3) {
        	Map.POSITION[2]=0;
        }
        System.out.println(Map.POSITION[0]+" "+Map.POSITION[1]+" "+Map.POSITION[2]); 	
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
