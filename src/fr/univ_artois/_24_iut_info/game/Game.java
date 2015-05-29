package fr.univ_artois._24_iut_info.game;

import java.io.IOException;
import java.net.InetSocketAddress;

import fr.univ_artois._24_iut_info.network.Connection;
import fr.univ_artois._24_iut_info.network.ReceiveListener;

public class Game implements ReceiveListener {	
	
	
	
	private Map map;
	
	private Connection con;
	
	private Player[] players;
	
	private int nbTwist = 10;
	
	private String nomEquipe = "";
	
	private int playerTurn = 0;
	
	
	
	public Game(){
		
		
		int addr = 0;
		try {
			con = new Connection(new InetSocketAddress(addr),this.nomEquipe ,this);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	
	public Map getMap(){
		return this.map;
	}
	
	public Connection getConnection(){
		return this.con;
	}




	@Override
	public void onPlayerSet(int playerId) {
		
		this.players = new PlayerHuman[2];
		
		int tmp = 1;
		
		Player playerTmp;
		//TODO gestion d'erreur en cas d'abruti qui rentre n'importe quoi
		try {
			tmp = System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(tmp == 1 )playerTmp = new PlayerHuman(nbTwist,this);
		else playerTmp = new PLayerIA(nbTwist,this);
		
		
		if(playerId == 1){			
			players[0] = playerTmp;			
			players[1] = new EnemyPlayer(nbTwist, this);
		}
		else{			
			players[1] = playerTmp;			
			players[0] = new EnemyPlayer(nbTwist, this);
		}
		
	}




	@Override
	public void onGameStart(String mapStr) {
		map.decode(mapStr);
		this.gameLoop();
		
	}




	@Override
	public void onOpponentPlay(int ligne, char colonne, int coin) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onRoundStart() {
		playerTurn++;
		playerTurn%=2;
		
		System.out.println("c'est au joueur " + players[playerTurn].getCouleur() + " de jouer");
		
		
	}




	@Override
	public void onErrorIllegalPlay() {
		System.err.println("le serveur indique que le coup n'étais pas bon");
		
	}

}
