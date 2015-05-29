package fr.univ_artois._24_iut_info.ia;

import fr.univ_artois._24_iut_info.game.Game;
import fr.univ_artois._24_iut_info.game.Map;

public class StrategieBasicGame extends AbstractStrategieGame {

	public StrategieBasicGame(Game g) {
		super(g);
		// TODO Stub du constructeur généré automatiquement
	}

	
	private void playTurn()
	{
		int nbPions = game.getMap().getNbPions(); //TODO a initialiser avec la methode de map
		// Tour 1
		if(nbPions == 0)
		{
			// jouer les tour 1
			
		}
		
		else
		{ 
			// Tour x
		}
	}
	
	
	
	
}
