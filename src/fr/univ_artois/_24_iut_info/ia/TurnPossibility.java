package fr.univ_artois._24_iut_info.ia;

import fr.univ_artois._24_iut_info.game.Map;

public class TurnPossibility {
	private int x;
	private int y;
	private Map map;
	public TurnPossibility(int x, int y,Map map){
		this.x = x;
		this.y = y;
		this.map = map;
	}
	public int getCapturedPoint(){
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
