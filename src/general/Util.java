package general;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public abstract class Util
{
	public static final float SQUAREWIDTH = 88;
	public static MovePilot newPilot()
	{
		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56).offset(-60);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[]{wheel1, wheel2}, 2);
		MovePilot pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(60);
        pilot.setAngularSpeed(30);
		return pilot;
	}
}
