package fr.univ_artois._24_iut_info.game;

import java.io.IOException;
import java.net.InetSocketAddress;

import fr.univ_artois._24_iut_info.network.Connection;

public class Game {
	
	
	
	
	private Map map;
	
	private Connection con;
	
	
	
	public Game(){
		int addr = 0;
		try {
			con = new Connection(new InetSocketAddress(addr));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
		//TODO recupreation des la map par la connection
		String tmp = "";
		
		
		
		
		
	}
	
	
	
	public Map getMap(){
		return this.map;
	}

}
