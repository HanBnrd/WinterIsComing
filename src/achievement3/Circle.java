package achievement3;

import general.Map;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Circle implements Behavior {
	MovePilot pilot;
	
	public Circle() {
		// TODO Auto-generated constructor stub
		
	}

	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	public void action() {
		// TODO Auto-generated method stub
		Wheel wheelB = WheeledChassis.modelWheel(Motor.B,56).offset(-60);
        Wheel wheelC = WheeledChassis.modelWheel(Motor.C,56).offset(60);
        Chassis ch = new WheeledChassis(new Wheel[]{wheelB,wheelC},2);
        MovePilot mp = new MovePilot(ch);
        mp.setLinearSpeed(60);
        pilot = mp;
		pilot.travel(90);
        pilot.stop();
        if (Map.POSITION[2]==0) {
        	Map.POSITION[0]=Map.POSITION[0]-1;
        } else if (Map.POSITION[2]==1) {
        	Map.POSITION[1]=Map.POSITION[1]+1;
        } else if (Map.POSITION[2]==2) {
        	Map.POSITION[0]=Map.POSITION[0]+1;
        } else if (Map.POSITION[2]==3) {
        	Map.POSITION[1]=Map.POSITION[1]-1;
        }
        System.out.println(Map.POSITION[0]+" "+" "+Map.POSITION[1]);
        
        Wheel wheelB2 = WheeledChassis.modelWheel(Motor.B,56).offset(-120);
        Wheel wheelC2 = WheeledChassis.modelWheel(Motor.C,56).offset(0);
        Chassis ch2 = new WheeledChassis(new Wheel[]{wheelB2,wheelC2},2);
        MovePilot mp2 = new MovePilot(ch2);
        mp2.setLinearSpeed(60);
        mp2.setAngularSpeed(30);
        pilot = mp2;
        pilot.travel(80);
	    pilot.stop();
		pilot.rotate(85);
		pilot.stop();
	    pilot.travel(20);
	    pilot.stop();
	    if (Map.POSITION[2]==0) {
	       	Map.POSITION[2]=1;
	       	Map.POSITION[1]=Map.POSITION[1]+1;
	    } else if (Map.POSITION[2]==1) {
	       	Map.POSITION[2]=2;
	       	Map.POSITION[0]=Map.POSITION[0]+1;
	    } else if (Map.POSITION[2]==2) {
	       	Map.POSITION[2]=3;
	       	Map.POSITION[1]=Map.POSITION[1]-1;
	    } else if (Map.POSITION[2]==3) {
	       	Map.POSITION[2]=0;
	       	Map.POSITION[0]=Map.POSITION[0]-1;
	    }
	    System.out.println(Map.POSITION[0]+" "+" "+Map.POSITION[1]);
		
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
