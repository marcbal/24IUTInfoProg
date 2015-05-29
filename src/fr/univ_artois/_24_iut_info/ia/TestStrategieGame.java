package fr.univ_artois._24_iut_info.ia;

import java.util.ArrayList;

import fr.univ_artois._24_iut_info.game.EnemyPlayer;
import fr.univ_artois._24_iut_info.game.Game;
import fr.univ_artois._24_iut_info.game.Map;
import fr.univ_artois._24_iut_info.game.Player;

public class TestStrategieGame extends AbstractStrategieGame {

	public TestStrategieGame(Game g) {
		super(g);
	}
	
	
	private void calculePlayer(Player p, int nbTurn, Map actualMap, ArrayList<InstanceTmpGame> maps, Coordonnee first){
	
		for(int ligne = 0 ; ligne<actualMap.getLigne()+1 ; ligne ++){
			for(int colonne =0 ; colonne<actualMap.getColonne()+1 ; colonne ++){

				
				Coordonnee coordTmp = first;
				if(actualMap.canPose(ligne, colonne)){
					
					if(coordTmp == null){
						coordTmp = new Coordonnee(ligne, colonne);
					}
					
					if(nbTurn == 0){
						
						Map mapTmp = actualMap.clone();
						mapTmp.poser(ligne, colonne, p.getId());
						maps.add(new InstanceTmpGame(mapTmp, coordTmp));
						//System.out.println("test");
					}
					else{
						//System.out.println("test");
						Map tmp = actualMap.clone();
						tmp.poser(ligne, colonne, p.getId());
						
						//if(p.isEnnemy){
							calculePlayer(game.getUs(), nbTurn--, tmp, maps, coordTmp);
						//} 
						//else{
							//calculePlayer(game.getEnemy(), nbTurn--, tmp, maps, coordTmp);
						//}
					}
				}
			}
		}
			
	}
		
		
	public static void main(String[] args) {
		Map map = new Map();
		map.decode("5:7:52:18:10:44:51:51:51:51|" + 
				"5:7:52:18:10:44:51:51:51:51|" + 
				"5:7:52:18:10:44:51:51:51:51|" + 
				"5:7:52:18:10:44:51:51:51:51|" + 
				"5:7:52:18:10:44:51:51:51:51|" + 
				"5:7:52:18:10:44:51:51:51:51|" + 
				"5:7:52:18:10:44:51:51:51:51|" + 
				"5:7:52:18:10:44:51:51:51:51|" + 
				"5:7:52:18:10:44:51:51:51:51|" + 
				"5:7:52:18:10:44:51:51:51:51|" );
		//map.decode("5:7:52|52:49:24|26:28:22");
		System.out.println(map);
		
		Game game = new Game(map);
		
		game.setMap(map);
		game.playTest();
	}




	@Override
	public Coordonnee playTurn() {
		ArrayList<InstanceTmpGame>  maps = new ArrayList<InstanceTmpGame> ();
		int nbTurn = 0;
		if(game.getMap().nbTroue()<3){
			nbTurn =0;
		}
		
		
		calculePlayer(game.getUs(),nbTurn,game.getMap(), maps, null);
		
		
		int max = -100000;
		Coordonnee bestCoup = null;
		
		for(int i=0 ; i<maps.size() ; i++){
			//System.out.println("test");
			//System.out.println(maps.get(i).coord.getX() + "   :   " + maps.get(i).coord.getY() + "point : " + 
					//(maps.get(i).map.getTotalPointOfPlayer(game.getUs().getId() ) -  maps.get(i).map.getTotalPointOfPlayer(game.getEnemy().getId() )));
			if(maps.get(i).map.getTotalPointOfPlayer(game.getUs().getId() ) -  maps.get(i).map.getTotalPointOfPlayer(game.getEnemy().getId() )>max){
				bestCoup = maps.get(i).coord;
				max = maps.get(i).map.getTotalPointOfPlayer(game.getUs().getId() ) -  maps.get(i).map.getTotalPointOfPlayer(game.getEnemy().getId() );
				//System.out.println(bestCoup.getX() + "   :   " + bestCoup.getY());
			}
		}
		
		return bestCoup;
		
		
		
		
	}
}
