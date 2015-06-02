package fr.univ_artois._24_iut_info.server.game;

import java.net.SocketAddress;

import fr.univ_artois._24_iut_info.server.ServerMain;

public class Player {
	public final SocketAddress address;
	public final String name;
	private int nbTwist = ServerMain.NB_TWIST_START;
	
	
	
	public Player(String n, SocketAddress a) {
		if (n == null) throw new IllegalArgumentException();
		name = n;
		address = a;
	}
	
	
	public int getRemainingTwist() { return nbTwist; }
	public void removeTwist() { if (nbTwist > 0) nbTwist--; }
	public boolean hasFinishPlay() { return nbTwist == 0; }
	
	
	
	
	
	
	
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Player))
			return false;
		
		return address.equals(((Player)o).address);
	}
}
