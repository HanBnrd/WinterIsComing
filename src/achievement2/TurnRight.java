package achievement2;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class TurnRight {
	MovePilot pilot;
	
	public TurnRight() {
		// TODO Auto-generated constructor stub
		Wheel wheelB = WheeledChassis.modelWheel(Motor.B,56).offset(-170);
        Wheel wheelC = WheeledChassis.modelWheel(Motor.C,56).offset(-50);
        Chassis ch = new WheeledChassis(new Wheel[]{wheelB,wheelC},2);
        MovePilot mp = new MovePilot(ch);
        pilot = mp;
	}

	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Button.RIGHT.isDown();
	}

	public void action() {
		// TODO Auto-generated method stub
		 pilot.rotate(90);
		 pilot.stop();
	     pilot.travel(12);
	     pilot.stop();
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
