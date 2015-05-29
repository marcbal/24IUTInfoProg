package fr.univ_artois._24_iut_info.ia;

import java.util.List;

import fr.univ_artois._24_iut_info.game.Game;
import fr.univ_artois._24_iut_info.game.Map;

public abstract class AbstractStrategieGame {
	protected Map map;
	protected List<TurnPossibility> nextTurnPossibilities;
	public AbstractStrategieGame(Game g){
		map = g.getMap();
	}
	
}
