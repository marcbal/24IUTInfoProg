package fr.univ_artois._24_iut_info.client.ia;

import java.util.ArrayList;
import java.util.List;

import fr.univ_artois._24_iut_info.client.game.Map;

public class TurnPossibility {
	private int x;
	private int y;
	private Map map;
	private Map mapAfter;
	private Map mapAfterEnemy;
	private byte joueur;
	private byte enemy;
	public TurnPossibility(int x, int y,Map map,byte joueur){
		this.x = x;
		this.y = y;
		this.map = map;
		this.mapAfter = map.clone();
		this.mapAfterEnemy = map.clone();
		this.joueur = joueur;
		this.enemy = (byte)((joueur%2)+1);
		if(this.mapAfter.canPose(x, y)){
			this.mapAfter.poser(x, y, joueur);
			this.mapAfterEnemy.poser(x, y,enemy) ;
		}
	}
	
	private int score(Map m,byte player){
		int score = 0;
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++){
				int x2 = x-1+i;
				int y2 = y-1+j;
				if(x2>=0&&y2>=0)
					score += m.getPoint(x2,y2);
		}
		return score;
	}
	
	private int scoreLocked(Map m,byte player){
		int score = 0;
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++){
				int x2 = x-1+i;
				int y2 = y-1+j;
				if(x2>=0&&y2>=0 && m.isLocked(x2,y2,player))
					score += m.getPoint(x2,y2);
		}
		return score;
	}
	
	public int getCapturedPoint(){
		if(!this.mapAfter.canPose(x,y))
			return 0;

		return (score(mapAfter,joueur) - score(map,joueur))+(score(map,enemy) - score(mapAfter,enemy)) ;
	}
	
	public int getEnemyCapturedPoint(){
		if(!this.mapAfter.canPose(x,y))
			return 0;

		return (score(mapAfter,enemy) - score(map,enemy))+(score(map,joueur) - score(mapAfter,joueur)) ;
	}
	
	public int getLockedPoint(){
		if(!this.mapAfter.canPose(x,y))
			return 0;

		return (scoreLocked(mapAfter,joueur) - scoreLocked(map,joueur)) ;
	}
	
	public int getEnemyLockedPoint(){
		if(!this.mapAfter.canPose(x,y))
			return 0;

		return (scoreLocked(mapAfter,enemy) - scoreLocked(map,enemy)) ;
	}
	
	public List<Coordonnee> nbFormat(int x, int y){
		List<Coordonnee> cmpt = new ArrayList<Coordonnee>();
		if(this.mapAfter.canPose(this.x,this.y))
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++){
				int x2 = this.x-1+i;
				int y2 = this.y-1+j;
				if(x2>=0&&y2>=0){
					int [] format;
					format = map.getFormat(x2, y2, joueur);
					if(format[0]==i && format[0]==j)
						cmpt.add(new Coordonnee(x2,y2));
				}
			}
		return cmpt;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
