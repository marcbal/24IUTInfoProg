package fr.univ_artois._24_iut_info.game;

import fr.univ_artois._24_iut_info.ia.AbstractStrategieGame;
import fr.univ_artois._24_iut_info.ia.Coordonnee;
import fr.univ_artois._24_iut_info.ia.StrategieBasicGame;

public class PlayerIA extends Player {

	public PlayerIA(int nbTwist, Game game, int id) {
		super(nbTwist, game, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		StrategieBasicGame st = new StrategieBasicGame(game);
		Coordonnee playCoordonnee = st.playTurn();
		sendTurn(playCoordonnee.getX(), playCoordonnee.getY());
	}
	
	

}
