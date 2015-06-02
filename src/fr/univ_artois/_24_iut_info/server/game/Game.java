package fr.univ_artois._24_iut_info.server.game;

import java.io.IOException;

import fr.univ_artois._24_iut_info.server.ServerMain;
import fr.univ_artois._24_iut_info.server.network.ServerConnection;

public class Game {
	
	private PlayerManager players = new PlayerManager(this);
	private ServerConnection connection;
	private Map map = new Map(ServerMain.MAP_HEIGHT, ServerMain.MAP_WIDTH, ServerMain.MAP_MAX_VALUE);
	
	
	public Game() {
		try {
			connection = new ServerConnection(ServerMain.SERVER_PORT, players);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(map);
	}
	
	
	
	public void startGame() throws IOException {
		
		for (Player p : players.getPlayers()) {
			try {
				connection.sendMap(p.address, map.toPlayerString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		connection.sendPlay(players.getCurrentPlayer().address);
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
	public boolean onPlayerPlay(Player p, int playerId, int ligne, int colonne) {
		
		p.removeTwist();
		
		if (map.getPion(ligne, colonne) != 0)
			return false;
		
		map.setPion(ligne, colonne, playerId);
		
		return true;
	}
	
	
	
	
	
	public int[] getPlayersScore() {
		int nbPlayer = players.getPlayers().size();
		
		int[] scores = new int[nbPlayer];
		
		for (int i=0; i<nbPlayer; i++) {
			scores[i] = map.getScoreForPlayer(i+1);
		}
		
		return scores;
	}
	

	public ServerConnection getConnection() { return connection; }
	public Map getMap() { return map; }
	
	
}
