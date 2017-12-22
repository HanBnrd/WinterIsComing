package achievement2;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.sensor.BaseSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	public static void main(String[] args) {
		System.out.println("Bonjour");
        Button.waitForAnyPress();
        ArrayList<BaseSensor> al=new ArrayList<>();
        Terminator t=new Terminator(al);
        Behavior w=new Wait();
        Behavior bf=new Forward();
        Behavior bb=new TurnBack();
        Behavior bl=new TurnLeft();
        Behavior br=new TurnRight();
        Behavior[] ba={w,bf,bb,bl,br,t};
        Arbitrator ar=new Arbitrator(ba);
        t.setArbitrator(ar);
        ar.go();
	}
}
