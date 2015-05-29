package fr.univ_artois._24_iut_info.ia;

import java.util.List;

import fr.univ_artois._24_iut_info.game.Map;

public class StrategieBasicGame extends AbstractStrategieGame {

	public StrategieBasicGame(Map m) {
		super(m);
		// TODO Stub du constructeur généré automatiquement
	}

	@Override
	public void playTurn() {
		// TODO Stub de la méthode généré automatiquement
		
	}

	
	public TurnPossibility getMaxTwistValues() {
		TurnPossibility turnMaxPointCaptured = nextTurnPossibilities.get(0).get(0);
		for(List<TurnPossibility> row : nextTurnPossibilities) {
			for(TurnPossibility col : row) {
				if(turnMaxPointCaptured.getCapturedPoint() > col.getCapturedPoint())
					turnMaxPointCaptured = col;
			}
		}
		return turnMaxPointCaptured;
	}
	
	
	
	
}
