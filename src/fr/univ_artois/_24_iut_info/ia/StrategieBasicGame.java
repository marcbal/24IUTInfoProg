package fr.univ_artois._24_iut_info.ia;

import java.util.ArrayList;
import java.util.List;

import fr.univ_artois._24_iut_info.game.Game;
import fr.univ_artois._24_iut_info.game.Map;

public class StrategieBasicGame extends AbstractStrategieGame {

<<<<<<< HEAD
	public StrategieBasicGame(Game game) {
		super(game);
=======
	public StrategieBasicGame(Game g) {
		super(g);
>>>>>>> branch 'master' of https://github.com/marcbal/24IUTInfoProg
		// TODO Stub du constructeur généré automatiquement
	}

	
	private void playTurn()
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
