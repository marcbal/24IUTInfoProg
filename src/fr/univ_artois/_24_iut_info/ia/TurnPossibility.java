package fr.univ_artois._24_iut_info.ia;

import fr.univ_artois._24_iut_info.game.Map;

public class TurnPossibility {
	private int x;
	private int y;
	private Map map;
	private Map mapAfter;
	private Map mapAfterEnemy;
	private int Joueur;
	public TurnPossibility(int x, int y,Map map,int joueur){
		this.x = x;
		this.y = y;
		this.map = map;
		this.mapAfter = map.clone();
		this.mapAfterEnemy = map.clone();
		if(this.mapAfter.canPose(x, y)){
			this.mapAfter.poser(x, y, joueur);
			this.mapAfterEnemy.poser(x, y, (joueur%2)+1);
		}
	}
	public int getCapturedPoint(){
		if(!this.mapAfter.canPose(x,y))
			return 0;
		return 0;
	}
	public int getEnemyCapturedPoint(){
		return 0;
	}
	public int getLockedPoint(){
		return 0;
	}
	public int getEnemyLockedPoint(){
		return 0;
	}
	
}
