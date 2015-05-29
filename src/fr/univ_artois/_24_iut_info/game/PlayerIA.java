package fr.univ_artois._24_iut_info.game;

import fr.univ_artois._24_iut_info.ia.Coordonnee;
import fr.univ_artois._24_iut_info.ia.TestStrategieGame;

public class PlayerIA extends Player {

	TestStrategieGame st;
	
	public PlayerIA(int nbTwist, Game game, int id) {
		super(nbTwist, game, id);
		this.st = new TestStrategieGame(game);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		//StrategieBasicGame st = new StrategieBasicGame(game);
		Coordonnee playCoordonnee = st.playTurn();
		trySendTurn(playCoordonnee.getX(), playCoordonnee.getY());
	}
	
	
	public void playTest(){
		Coordonnee playCoordonnee = st.playTurn();
		System.out.println(playCoordonnee.getX() + "   :   " + playCoordonnee.getY());
	}
	
	

}
