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

	public Coordonnee playTurn()
	{
		int ligne; 
		int colonne;
		
		int nbPions = game.getMap().nbPion(); //TODO a initialiser avec la methode de map
		// Tour 1
		if(nbPions == 0)
		{
			// jouer les tour 1
			TurnPossibility tr = getMaxTwistValues();
			return new Coordonnee(tr.getX(), tr.getY());
			
		}
		
		else
		{ 
			// Tour x
			class MaListe
			{ 
				int ligne;
				int colonne;
				int valeur;
				
				public MaListe(int l, int c, int val)
			    {
			         this.ligne = l;
			         this.colonne = c;
			         this.valeur = val;
			    }
			
			}
			
			List<MaListe> pointsPotentielsContre =new ArrayList<MaListe>();
			List<MaListe> pointsPotentielsIA =new ArrayList<MaListe>();

			
			for(int i = 0; i < game.getMap().getLigne(); i++)
			{
				for(int j = 0; j < game.getMap().getColonne(); j++)
				{
					if(game.getMap().canPose(i, j))
					{
						pointsPotentielsIA.add(new MaListe(i,j, 0)) ;//methodeLouis --> nbPointsLock
					}
				}
			}
			
			for(int i = 0; i < game.getMap().getLigne(); i++)
			{
				for(int j = 0; j < game.getMap().getColonne(); j++)
				{
					if(game.getMap().canPose(i, j))
					{
						pointsPotentielsContre.add(new MaListe(i,j, 0)) ;//methodeLouis --> bPointsContre
					}
				}
			}
			
			// si on ne peut rien lock
			if( !(pointsPotentielsContre.get(0) instanceof MaListe))
			{
				// si on a un (ou +) 2-1, on securise le meilleur
				
				
				
				
				
				// sinon, on pred le meilleur 2-0 possible
			}
			 
		}
	}
	
	
	
	
}
