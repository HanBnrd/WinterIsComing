package achievement5;

import java.util.ArrayList;

import general.Watch;
import general.Fight;
import general.Terminator;
import general.Util;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainWatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovePilot pilot = Util.newPilot();
		EV3UltrasonicSensor uts = new EV3UltrasonicSensor(SensorPort.S4);
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S2);
		ArrayList<BaseSensor> sensors = new ArrayList<BaseSensor>();
		sensors.add(uts);
		sensors.add(gyro);
		Terminator terminator = new Terminator(sensors, pilot);
		Fight fight = new Fight(pilot);
		Behavior dww = new DetectWhiteWalker(uts, gyro, pilot);
		//Behavior watch = new Watch(pilot);
		Behavior[] bArray = {dww, fight, terminator}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		terminator.setArbitrator(arby);
		LCD.clear();
		LCD.drawString("Ready to go !", 0, 2);
		LCD.refresh();
		Button.waitForAnyPress();
		arby.go();
	}
}
