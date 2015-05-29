package fr.univ_artois._24_iut_info.game;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import fr.univ_artois._24_iut_info.Main;
import fr.univ_artois._24_iut_info.network.Connection;
import fr.univ_artois._24_iut_info.network.ReceiveListener;

public class Game implements ReceiveListener {	
	
	
	
	private Map map;
	
	private Connection con;
	
	private Player[] players;
	
	private int nbTwist = 10;
	
	private int playerTurn = 0;
	
	
	public Game(){
		try {
			con = new Connection(new InetSocketAddress(InetAddress.getByName(Main.SERVER_HOST),Main.SERVER_PORT),Main.NOM_EQUIPE ,this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}




	@Override
	public void onPlayerSet(int playerId) {
		
		this.players = new Player[2];
		
		Player playerTmp = (Main.HUMAIN) ?
				new PlayerHuman(nbTwist,this) :
				new PLayerIA(nbTwist,this);
		
		
		
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
		map.poser(ligne, colonne, coin, players[playerTurn].getId());
		playerTurn++;
		playerTurn%=2;
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
		playerTurn++;
		playerTurn%=2;
		
	}


	@Override
	public void onPlayerCantPlay() {
		System.err.println("ce n'est plus votre tour de jouer");
		
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
