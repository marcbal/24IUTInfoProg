package fr.univ_artois._24_iut_info.ia;

import java.util.ArrayList;

import fr.univ_artois._24_iut_info.game.EnemyPlayer;
import fr.univ_artois._24_iut_info.game.Game;
import fr.univ_artois._24_iut_info.game.Map;
import fr.univ_artois._24_iut_info.game.Player;

public class TestStrategieGame extends AbstractStrategieGame {

	public TestStrategieGame(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}
	
	
	private void calculePlayer(Player p, int nbTurn, Map actualMap, ArrayList<InstanceTmpGame> maps, Coordonnee first){
		
		
		for(int ligne = 0 ; ligne<actualMap.getLigne()+1 ; ligne ++){
			for(int colonne =0 ; actualMap.getColonne()+1<0 ; colonne ++){
				if(actualMap.canPose(ligne, colonne)){
					
					if(first == null)
						first = new Coordonnee(ligne, colonne);
				
					if(nbTurn == 0){
						actualMap.poser(ligne, colonne, p.getId());
						maps.add(new InstanceTmpGame(actualMap, first));
					}
					else{
						Map tmp = actualMap.clone();
						tmp.poser(ligne, colonne, p.getId());
						
						if(p instanceof EnemyPlayer){
							calculePlayer(game.getUs(), nbTurn--, tmp, maps, first);
						} 
						else{
							calculePlayer(game.getEnemy(), nbTurn--, tmp, maps, first);
						}
					}
				}
			}
		}
			
	}
		
		
	





	@Override
	public Coordonnee playTurn() {
		
		ArrayList<InstanceTmpGame>  maps = new ArrayList<InstanceTmpGame> ();
		
		int nbTurn = 4;
		calculePlayer(game.getUs(),nbTurn,game.getMap(), maps, null);
		
		
		int max = Integer.MIN_VALUE;
		Coordonnee bestCoup = null;
		
		for(int i=0 ; i<maps.size() ; i++){
			if(maps.get(i).map.getTotalPointOfPlayer(game.getUs().getId() ) -  maps.get(i).map.getTotalPointOfPlayer(game.getEnemy().getId() )>max){
				bestCoup = maps.get(i).coord;
				max = maps.get(i).map.getTotalPointOfPlayer(game.getUs().getId() ) -  maps.get(i).map.getTotalPointOfPlayer(game.getEnemy().getId() );
			}
		}
		
		return bestCoup;
		
		
		
		
	}





	@Override
	public Coordonnee playTurn() {
		// TODO Auto-generated method stub
		return null;
	}

}
