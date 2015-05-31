package fr.univ_artois._24_iut_info.server.network;

import java.net.SocketAddress;

public interface ReceiveListener {
	
	public void onPlayerSendName(SocketAddress playerAddr, String name);
	
	
	
	
	public void onPlayerSendPlayCoordinate(SocketAddress playerAddr, int ligne, int colonne);
	
	
	
}
