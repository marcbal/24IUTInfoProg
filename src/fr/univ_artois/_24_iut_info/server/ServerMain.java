package fr.univ_artois._24_iut_info.server;

import fr.univ_artois._24_iut_info.server.game.Game;

public class ServerMain {
	
	public static final int SERVER_PORT = 12346;
	
	public static final int MAP_WIDTH = 10;
	public static final int MAP_HEIGHT = 10;
	public static final int MAP_MAX_VALUE = 99;
	public static final int NB_TWIST_START = 20;

	public static void main(String[] args) {
		
		new Game();
		
	}

}
