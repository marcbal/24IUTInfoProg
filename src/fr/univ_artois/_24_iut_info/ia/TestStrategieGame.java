package fr.univ_artois._24_iut_info.ia;

import java.util.ArrayList;

import fr.univ_artois._24_iut_info.game.Game;
import fr.univ_artois._24_iut_info.game.Map;

public class TestStrategieGame extends AbstractStrategieGame {

	public TestStrategieGame(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public void play(){
		
		
		Map acutalMap = null ;//= game.getMap();
		ArrayList<Map> maps = new ArrayList<Map>();
		
		for(int ligne = 0 ; ligne<acutalMap.getLigne()+1 ; ligne ++){
			for(int colonne =0 ; acutalMap.getColonne()+1<0 ; colonne ++){
				if(acutalMap.canPose(ligne, colonne)){
					Map tmp = acutalMap.clone();
					tmp.poser(ligne, colonne, 0);
					maps.add(tmp);
				}
			}
			
		}
		
		
		int max  = Integer.MIN_VALUE;
		for(Map map:maps);
			//if(map)
		
		
		
	}





	@Override
	public Coordonnee playTurn() {
		// TODO Auto-generated method stub
		return null;
	}

}
