package achievement3;

import general.Map;
import achievement3.SendPosition;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class SquareMove implements Behavior {
	private MovePilot pilot;
	private SendPosition sp;
	
	/**
	 * Constructeur du comportement que le marcheur blanc utilise pour se deplacer en formant un mouvement carre
	 * tant que sa position n'a pas ete envoyee a la garde de nuit
	 * @param mp l'instance de MovePilot du robot marcheur blanc
	 * @param sp l'instance du comportement permettant d'envoyer sa position a la garde de nuit
	 */
	public SquareMove(MovePilot mp, SendPosition sp) {
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
        LCD.drawString("("+Map.POSITION[0]+","+Map.POSITION[1]+" ", 0, 2);	
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
