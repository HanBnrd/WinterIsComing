package achievement5;

import java.util.ArrayList;

import general.Map;
import general.Util;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class DetectWhiteWalker implements Behavior
{
	MovePilot pilot;
	EV3UltrasonicSensor ultrasonicSensor;
	EV3GyroSensor gyroSensor;
	
	public DetectWhiteWalker(EV3UltrasonicSensor uts, EV3GyroSensor gyro, MovePilot pilot)
	{
		// TODO Auto-generated constructor stub
		this.pilot = pilot;
		ultrasonicSensor = uts;
		gyroSensor = gyro;
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		LCD.clear();
		LCD.drawString("DetectWhiteWalker", 0, 3);
		LCD.refresh();
		pilot.setAngularSpeed(30);
		float[] tab = new float[1];
		gyroSensor.reset();
		ultrasonicSensor.getDistanceMode().fetchSample(tab, 0);
		float distance = tab[0] * 1000;
		gyroSensor.getAngleMode().fetchSample(tab, 0);
		float angle = 0 - tab[0];
		
		while (distance >= 750 && angle < 360);
		{
			pilot.rotate(10);
			pilot.stop();
			ultrasonicSensor.getDistanceMode().fetchSample(tab, 0);
			distance = tab[0] * 1000;
			gyroSensor.getAngleMode().fetchSample(tab, 0);
			angle = 0 - tab[0];
		}
		
		LCD.clear();
		LCD.drawString("" + distance, 0, 2);
		LCD.drawString("" + angle, 0, 3);
		LCD.refresh();
		Button.waitForAnyPress();
		pilot.rotate(0 - angle);
		Map.WHITEWALKERPOSITION = getWhiteWalkerPosition(distance, tab[0]);
		LCD.clear();
		LCD.drawString(Map.WHITEWALKERPOSITION[1] + " ; " + Map.WHITEWALKERPOSITION[0], 0, 3);
		LCD.refresh();
		Button.waitForAnyPress();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}
	
	private int[] getWhiteWalkerPosition(float distance, float angle)
	{
		int[] whitewalkerPosition = new int[2];
		double closerAngle = -1;
		for (int[] rPos : calculRPos(distance))
		{
			double rPosAngle = Math.toDegrees(Math.atan2(rPos[0], rPos[1]));
			if (Math.abs(angle - rPosAngle) < Math.abs(angle - closerAngle)
					|| closerAngle == -1)
			{
				closerAngle = rPosAngle;
				whitewalkerPosition = rPos;
			}
		}
		
		whitewalkerPosition[0] += Map.POSITION[1];
		whitewalkerPosition[1] += Map.POSITION[0];

		return whitewalkerPosition;
	}

	private ArrayList<int[]> calculRPos(float distance)
	{
		float length = 0;
		ArrayList<int[]> positions = new ArrayList<int[]>();
		while(length < distance - Util.SQUAREWIDTH)
		{
			length += Util.SQUAREWIDTH;
			int[] pos = new int[2];
			int posX = (int)(length / Util.SQUAREWIDTH);
			float pythagore = (distance * distance) - (length * length);
			int posY = (int)(Math.sqrt(pythagore) / Util.SQUAREWIDTH);
			pos[0] = posY;
			pos[1] = posX;
			positions.add(pos);
			pos[0] = 0 - posY;
			pos[1] = posX;
			positions.add(pos);
			pos[0] = posY;
			pos[1] = 0 - posX;
			positions.add(pos);
			pos[0] = 0 - posY;
			pos[1] = 0 - posX;
			positions.add(pos);
		}
		return positions;
	}
}
