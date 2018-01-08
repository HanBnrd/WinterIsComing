package achievement5;

import java.util.ArrayList;

import general.Map;
import general.Util;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

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
		pilot.setAngularSpeed(30);
		float[] tab = new float[1];
		ultrasonicSensor.enable();
		gyroSensor.reset();
		
		pilot.rotate(360);
		while (pilot.isMoving())
		{
			ultrasonicSensor.getDistanceMode().fetchSample(tab, 0);
			if (tab[0] <= 0.75)
	        	pilot.stop();
		}
		float distance = tab[0];
		gyroSensor.getAngleMode().fetchSample(tab, 0);
		pilot.rotate(0 - tab[0]);
		Map.WHITEWALKERPOSITION = getWhiteWalkerPosition(distance, tab[0]);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

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
		
		whitewalkerPosition[0] += Map.POSITION[0];
		whitewalkerPosition[1] += Map.POSITION[1];

		return whitewalkerPosition;
	}
}
