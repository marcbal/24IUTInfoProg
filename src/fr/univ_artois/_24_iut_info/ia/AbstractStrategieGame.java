package fr.univ_artois._24_iut_info.ia;

import java.util.List;

import fr.univ_artois._24_iut_info.game.Game;

public abstract class AbstractStrategieGame {

	
	protected Game game;
	protected List<List<TurnPossibility>> nextTurnPossibilities;
	
	public AbstractStrategieGame(Game game){
		this.game =  game;
	}
	
	public abstract Coordonnee playTurn();
	
}
