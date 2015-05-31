package fr.univ_artois._24_iut_info.server.game;

import java.net.SocketAddress;

public class Player {
	public final SocketAddress address;
	public final String name;
	
	
	
	public Player(String n, SocketAddress a) {
		if (n == null) throw new IllegalArgumentException();
		name = n;
		address = a;
	}
	
	
	
	
	
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Player))
			return false;
		
		return address.equals(((Player)o).address);
	}
}
