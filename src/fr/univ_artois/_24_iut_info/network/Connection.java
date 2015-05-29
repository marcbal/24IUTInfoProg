package fr.univ_artois._24_iut_info.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Connection {
	
	Socket socket;
	
	
	
	
	public Connection(InetSocketAddress addr) throws IOException {
		socket = new Socket(addr.getAddress(), addr.getPort());
	}
	
	
	
}
