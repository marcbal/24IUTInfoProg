package fr.univ_artois._24_iut_info.client.game;

import fr.univ_artois._24_iut_info.client.ia.Coordonnee;
import fr.univ_artois._24_iut_info.client.ia.TestStrategieGame;

public class PlayerIA extends Player {

	TestStrategieGame st;
	
	public PlayerIA(int nbTwist, Game game, int id) {
		super(nbTwist, game, id);
		this.st = new TestStrategieGame(game);
		isEnnemy = false;
	}

	@Override
	public void play() {
		Coordonnee playCoordonnee = st.playTurn();
		if(playCoordonnee == null) {
			playCoordonnee = new Coordonnee(0, 0);
			System.out.println("c 'est null putain");
		}
		if (!trySendTurn(playCoordonnee.getX(), playCoordonnee.getY()))
			System.err.println("L'IA n'a pas pu envoyer son résultat au serveur car l'état actuel du jeu ne le permet pas");
	}
	
	
	public void playTest(){
		Coordonnee playCoordonnee = st.playTurn();
		
		game.getMap().poser(playCoordonnee.getX(), playCoordonnee.getY(), id);
		
		System.out.println(playCoordonnee.getX() + "   :   " + playCoordonnee.getY());
	}
	
	

}
