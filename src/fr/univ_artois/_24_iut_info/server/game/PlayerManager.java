package fr.univ_artois._24_iut_info.server.game;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univ_artois._24_iut_info.server.network.ReceiveListener;

public class PlayerManager implements ReceiveListener {
	
	private Game game;
	
	private List<Player> players = new ArrayList<Player>();
	
	private int currentPlayer = 0;
	
	public PlayerManager(Game g) {
		game = g;
	}
	
	
	
	
	
	
	
	public Player getPlayer(SocketAddress addr) {
		for (Player p : players) {
			if (p.address.equals(addr))
				return p;
		}
		return null;
	}
	
	
	public Player getOtherPlayer(Player p) {
		for (Player o : players)
			if (!o.equals(p))
				return o;
		return null;
	}
	
	
	
	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
	
	
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}
	
	public int getPlayerId(Player p) {
		return players.indexOf(p)+1;
	}
	
	public Player nextPlayer() {
		currentPlayer++;
		currentPlayer %= players.size();
		return getCurrentPlayer();
	}
	
	
	public boolean hasEveryoneFinished() {
		for (Player p : players)
			if (!p.hasFinishPlay())
				return false;
		return true;
	}



	@Override
	public synchronized void onPlayerSendName(SocketAddress playerAddr, String name) {
		if (getPlayer(playerAddr) != null || players.size() >= 2) {
			try {
				game.getConnection().sendCantJoin(playerAddr);
			} catch (IOException e) { }	// on en fait rien car de toute façon, le joueur qui a tenté de se co n'a pas d'importance
		}
		
		
		try {
			int position = players.size()+1;
			game.getConnection().sendPlayerNumber(playerAddr, position);
			players.add(new Player(name, playerAddr));
			
			if (players.size() == 2)
				game.startGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}





	@Override
	public synchronized void onPlayerSendPlayCoordinate(SocketAddress playerAddr, int ligne,
			int colonne) {

		if (getPlayer(playerAddr) == null) {
			try {
				game.getConnection().sendCantJoin(playerAddr);
			} catch (IOException e) { }	// on en fait rien car de toute façon, le joueur qui a tenté de se co n'a pas d'importance
		}
		
		if (!game.hasStarted()) {
			try {
				game.getConnection().sendProtocolError(playerAddr, "La partie n'a pas encore commencée");
			} catch (IOException e) { }	// on en fait rien car de toute façon, le joueur qui a tenté de se co n'a pas d'importance
		}
		
		if (!getPlayer(playerAddr).equals(getCurrentPlayer())) {
			try {
				game.getConnection().sendProtocolError(playerAddr, "Ce n'est pas à vous de jouer");
			} catch (IOException e) { }	// on en fait rien car de toute façon, le joueur qui a tenté de se co n'a pas d'importance
		}
		
		if (getPlayer(playerAddr).hasFinishPlay()) {
			try {
				game.getConnection().sendProtocolError(playerAddr, "Vous ne pouvez plus jouer");
			} catch (IOException e) { }	// on en fait rien car de toute façon, le joueur qui a tenté de se co n'a pas d'importance
		}
		
		Player p = getPlayer(playerAddr);
		
		boolean ok = game.onPlayerPlay(p, getPlayerId(p), ligne, colonne);
		
		try {
			if (ok) {
				game.getConnection().sendGoodPlay(playerAddr);
				game.getConnection().sendEnnemyGoodPlay(getOtherPlayer(p).address, ligne, colonne);
			}
			else {
				game.getConnection().sendBadPlay(playerAddr);
				game.getConnection().sendEnnemyBadPlay(getOtherPlayer(p).address);
			}
			if (p.hasFinishPlay())
				game.getConnection().sendNoMorePawn(playerAddr);
			
			if (hasEveryoneFinished() || !game.getMap().containEmptyPion())
				for (Player pl : players)
					game.getConnection().sendFinish(pl.address, game.getPlayersScore());
			else
				game.getConnection().sendPlay(getOtherPlayer(p).address);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		nextPlayer();
		
		
	}
	
	
	
	

}
