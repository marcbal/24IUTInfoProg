package fr.univ_artois._24_iut_info.game;

import java.io.IOException;
import java.net.InetSocketAddress;

import fr.univ_artois._24_iut_info.network.Connection;

public class Game {
	
	
	
	
	private Map map;
	
	private Connection con;
	
	private Player[] players;
	
	
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
		map.decode(tmp);
		
		this.initPlayer();
		
	}
	
	
	
	
	private void initPlayer(){
		int nbPlayers = 2;
		
		players = new Player[nbPlayers];
		
		
		
		//creation des joueurs
		for (int i = 0; i < nbPlayers; i++) {
			System.out.println("Joueur " + (i+1) + " Humain(1) ou IA(2)");
			
			int tmp = 1;
			
			
			//TODO gestion d'erreur en cas d'abruti qui rentre n'importe quoi
			try {
				tmp = System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(tmp == 1 )players[i] = new PlayerHuman();
			else players[i] = new PLayerIA();
		}
			
			
		
		
		
	}
	
	
	
	public Map getMap(){
		return this.map;
	}

}
