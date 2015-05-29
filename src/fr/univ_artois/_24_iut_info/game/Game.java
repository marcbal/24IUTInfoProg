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
	
	private String nomEquipe = "Les mounny python";
	
	private int playerTurn = 0;
	
	
	public Game(String addr, int port){
		try {
			con = new Connection(new InetSocketAddress(addr,port),this.nomEquipe ,this);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
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
	}




	@Override
	public void onOpponentPlay(int ligne, char colonne, int coin) {
		//map.poser(x, y, id); //TODO
		
	}




	@Override
	public void onRoundStart() {
		playerTurn++;
		playerTurn%=2;
		
		System.out.println("c'est au joueur " + players[playerTurn].getCouleur() + " de jouer");
		
		players[playerTurn].play();
		
		
	}


	@Override
	public void onIllegalPlay() {
		System.err.println("le serveur indique que le coup n'étais pas bon");
		
	}


	@Override
	public void onOpponentPlayIllegal() {

		System.err.println("le serveur indique que l'enemy a jouer un coup illegal");
		
	}


	@Override
	public void onPlayerCantPlay() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onGameFinish(String serverMessage) {
		System.out.println(serverMessage);
		
	}
	

	
	
	public Map getMap(){
		return this.map;
	}
	
	public Connection getConnection(){
		return this.con;
	}

}
