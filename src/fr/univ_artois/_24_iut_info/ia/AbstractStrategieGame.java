package fr.univ_artois._24_iut_info.ia;

import java.util.List;

import fr.univ_artois._24_iut_info.game.Map;

public abstract class AbstractStrategieGame {
	private Map _map;
	private List<TurnPossibility> nextTurnPossibilities;
	public AbstractStrategieGame(Map m){
		_map = m;
	}
	
}
