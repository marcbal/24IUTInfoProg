package fr.univ_artois._24_iut_info.game;

import fr.univ_artois._24_iut_info.ia.Coordonnee;
import fr.univ_artois._24_iut_info.ia.TestStrategieGame;

public class PlayerIA extends Player {

	TestStrategieGame st;
	
	public PlayerIA(int nbTwist, Game game, int id) {
		super(nbTwist, game, id);
<<<<<<< HEAD
		this.st = new TestStrategieGame(game);
=======
>>>>>>> branch 'master' of https://github.com/marcbal/24IUTInfoProg
	}

	@Override
	public void play() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		//StrategieBasicGame st = new StrategieBasicGame(game);
=======
		AbstractStrategieGame st = new TestStrategieGame(game);
>>>>>>> branch 'master' of https://github.com/marcbal/24IUTInfoProg
		Coordonnee playCoordonnee = st.playTurn();
		if (!trySendTurn(playCoordonnee.getX(), playCoordonnee.getY()))
			System.err.println("L'IA n'a pas pu envoyer son résultat au serveur car l'état actuel du jeu ne le permet pas");
	}
	
	
	public void playTest(){
		Coordonnee playCoordonnee = st.playTurn();
		System.out.println(playCoordonnee.getX() + "   :   " + playCoordonnee.getY());
	}
	
	

}
