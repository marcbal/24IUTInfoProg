package fr.univ_artois._24_iut_info.game;

import java.io.IOException;
import java.net.InetSocketAddress;

import fr.univ_artois._24_iut_info.network.Connection;

public class Game {	
	
	
	
	private Map map;
	
	private Connection con;
	
	private Player[] players;
	
	private boolean isEnd;
	
	private int nbPlayers = 2;
	
	private int nbTwist = 10;
	
	
	
	public Game(){
		//gestion des variables
		this.isEnd = false;
		
		
		
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
		
		this.gameLoop();
		
	}
	
	
	
	
	private void initPlayer(){
		
		
		this.players = new Player[nbPlayers];
		
		
		
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
			
			if(tmp == 1 )players[i] = new PlayerHuman(nbTwist,this);
			else players[i] = new PLayerIA(nbTwist,this);
		}	
		
	}
	
	
	private void gameLoop(){
		
		
		int playerTurn = 0;
		
		while(!isEnd){
			
			players[playerTurn].play();
			
			
			//TODO calcule des points
			
			this.isGameEnd();
			
			
			
		}
		
		
		this.onGameEnd();
		
		
		
	}
	
	
	//TODO a coder
	private void isGameEnd(){
		this.isEnd = players[0].getNbTwist() == 0 && players[0].getNbTwist() == 1;
		//TODO tester avec la map la place libre
	}
	
	
	//TODO a coder
	private void onGameEnd(){
	}
	
	
	public Map getMap(){
		return this.map;
	}
	
	public Connection getConnection(){
		return this.con;
	}

}
