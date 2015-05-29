package fr.univ_artois._24_iut_info.ia;


import java.util.List;

import fr.univ_artois._24_iut_info.game.Map;

import java.util.ArrayList;


import fr.univ_artois._24_iut_info.game.Game;

public class StrategieBasicGame extends AbstractStrategieGame {


	
	public StrategieBasicGame(Game game) {
		super(game);
	}



	
	public TurnPossibility getMaxTwistValues() {
		TurnPossibility turnMaxPointCaptured = nextTurnPossibilities.get(0).get(0);
		for(List<TurnPossibility> row : nextTurnPossibilities) {
			for(TurnPossibility col : row) {
				if(turnMaxPointCaptured.getCapturedPoint() > col.getCapturedPoint())
					turnMaxPointCaptured = col;
			}
		}
		return turnMaxPointCaptured;
	}

	public void playTurn()
	{
		int nbPions = game.getMap().nbPion(); //TODO a initialiser avec la methode de map
		// Tour 1
		if(nbPions == 0)
		{
			// jouer les tour 1
			
		}
		
		else
		{ 
			// Tour x
			class MaListe
			{ 
				int ligne;
				char colonne;
				int valeur;
				
				public MaListe(int l, char c, int val)
			    {
			         this.ligne = l;
			         this.colonne = c;
			         this.valeur = val;
			    }
			
			}
			
			List<MaListe> pointsPotentielsContre =new ArrayList<MaListe>();
			List<MaListe> pointsPotentielsIA =new ArrayList<MaListe>();

			
			
			 
		}
	}
	
	
	
	
}
