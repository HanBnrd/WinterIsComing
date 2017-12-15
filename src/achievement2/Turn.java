package achievement2;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Turn {
	public static void turnRight() {
		Wheel wheelB = WheeledChassis.modelWheel(Motor.B,56).offset(-170);
        Wheel wheelC = WheeledChassis.modelWheel(Motor.C,56).offset(-50);
        Chassis ch = new WheeledChassis(new Wheel[]{wheelB,wheelC},2);
        MovePilot mp = new MovePilot(ch);
        mp.rotate(90);
        mp.travel(12);
	}
	public static void turnLeft() {
		Wheel wheelB = WheeledChassis.modelWheel(Motor.B,56).offset(-120);
        Wheel wheelC = WheeledChassis.modelWheel(Motor.C,56).offset(0);
        Chassis ch = new WheeledChassis(new Wheel[]{wheelB,wheelC},2);
        MovePilot mp = new MovePilot(ch);
        mp.travel(80);
        mp.stop();
        mp.rotate(-83);
        mp.stop();
        mp.travel(10);
        mp.stop();
	}
	public static void turnBack() {
		Wheel wheelB = WheeledChassis.modelWheel(Motor.B,56).offset(-127);
        Wheel wheelC = WheeledChassis.modelWheel(Motor.C,56).offset(-7);
        Chassis ch = new WheeledChassis(new Wheel[]{wheelB,wheelC},2);
        MovePilot mp = new MovePilot(ch);
        mp.travel(50);
        mp.stop();
        mp.rotate(170);
        mp.stop();
	}
	public static void main(String[] args) {
		System.out.println("Bonjour");
        Button.waitForAnyPress();
        turnRight();
        turnLeft();
        turnBack();
        
	}
}
