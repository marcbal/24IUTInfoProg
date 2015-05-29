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
						
						Map tmp = actualMap.clone();
						tmp.poser(ligne, colonne, p.getId());
						maps.add(new InstanceTmpGame(tmp, coordTmp));
						//System.out.println("test");
					}
					else{
						//System.out.println("test");
						Map tmp = actualMap.clone();
						tmp.poser(ligne, colonne, p.getId());
						
						if(p instanceof EnemyPlayer){
							calculePlayer(game.getUs(), nbTurn--, tmp, maps, coordTmp);
						} 
						else{
							calculePlayer(game.getEnemy(), nbTurn--, tmp, maps, coordTmp);
						}
					}
				}
			}
		}
			
	}
		
		
	public static void main(String[] args) {
		Map map = new Map();
		//map.decode("5:7:52:18:10:44:51|52:49:24:46:12:41:25|26:28:22:23:13:38:26|43:29:33:41:06:52:50|42:45:48:12:16:51:20|37:06:25:26:26:43:33|5:47:24:5:23:22:18|5:38:38:41:39:17:40|8:19:17:53:29:20:27|35:6:12:28:28:31:17");
		map.decode("5:7:52|52:49:24|26:28:22");
		System.out.println(map);
		
		Game game = new Game(true,map);
		
		game.setMap(map);
		game.playTest();
	}




	@Override
	public Coordonnee playTurn() {
		ArrayList<InstanceTmpGame>  maps = new ArrayList<InstanceTmpGame> ();
		int nbTurn = 2;
		if(game.getMap().nbTrou()<2){
			nbTurn = game.getMap().nbTrou();
		}
		
		
		calculePlayer(game.getUs(),nbTurn,game.getMap(), maps, null);
		
		
		int max = Integer.MIN_VALUE;
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
