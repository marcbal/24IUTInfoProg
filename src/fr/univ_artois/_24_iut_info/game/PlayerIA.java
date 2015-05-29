package fr.univ_artois._24_iut_info.game;

import fr.univ_artois._24_iut_info.ia.AbstractStrategieGame;
import fr.univ_artois._24_iut_info.ia.Coordonnee;
import fr.univ_artois._24_iut_info.ia.TestStrategieGame;

public class PlayerIA extends Player {

	public PlayerIA(int nbTwist, Game game, int id) {
		super(nbTwist, game, id);
	}

	@Override
	public void play() {
		AbstractStrategieGame st = new TestStrategieGame(game);
		Coordonnee playCoordonnee = st.playTurn();
		if (!trySendTurn(playCoordonnee.getX(), playCoordonnee.getY()))
			System.err.println("L'IA n'a pas pu envoyer son résultat au serveur car l'état actuel du jeu ne le permet pas");
	}
	
	

}
