package fr.univ_artois._24_iut_info.ia;

import java.util.List;

import fr.univ_artois._24_iut_info.game.Game;

public abstract class AbstractStrategieGame {

	
	protected Game game;
	protected List<List<TurnPossibility>> nextTurnPossibilities;
	
<<<<<<< HEAD
	
	public AbstractStrategieGame(Game g){
		map = g.getMap();
		game = g;
=======
	public AbstractStrategieGame(Game game){
		this.game =  game;
>>>>>>> branch 'master' of https://github.com/marcbal/24IUTInfoProg
	}
	
	public abstract Coordonnee playTurn();
	
}
