package fr.univ_artois._24_iut_info.server.game;

import java.io.IOException;

import fr.univ_artois._24_iut_info.server.ServerMain;
import fr.univ_artois._24_iut_info.server.network.ServerConnection;

public class Game {
	
	private PlayerManager players = new PlayerManager(this);
	private ServerConnection connection;
	private Map map;
	
	
	public Game() {
		try {
			connection = new ServerConnection(ServerMain.SERVER_PORT, players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void startGame() {
		
		
		map = new MapGenerator().generate(ServerMain.MAP_HEIGHT, ServerMain.MAP_WIDTH, ServerMain.MAP_MAX_VALUE);
		
		
		for (Player p : players.getPlayers()) {
			try {
				connection.sendMap(p.address, map.toPlayerString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public boolean hasStarted() { return map != null; }
	
	
	/**
	 * Fait jouer le joueur donné dans la partie<br/>
	 * La méthode est appelé quand je joueur envoi son jeu au serveur
	 * @param p le joueur qui a joué
	 * @param ligne la ligne sur laquelle il joue (partant de 0)
	 * @param colonne la colonne sur laquelle il joue (partant de 0)
	 * @return <code>true</code> si le joueur a joué correctement ou <code>false</code> sinon
	 */
	public boolean onPlayerPlay(Player p, int ligne, int colonne) {
		
		// TODO
		
		return false;
	}
	
	
	public ServerConnection getConnection() { return connection; }
	
	
}
