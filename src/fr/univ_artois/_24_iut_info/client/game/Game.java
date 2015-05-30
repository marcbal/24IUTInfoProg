package fr.univ_artois._24_iut_info.client.game;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import fr.univ_artois._24_iut_info.client.ClientMain;
import fr.univ_artois._24_iut_info.client.network.Connection;
import fr.univ_artois._24_iut_info.client.network.ReceiveListener;

public class Game implements ReceiveListener {	
	
	
	
	private Map map;
	
	private Connection con;
	
	private Player playerEnnemy;
	private Player playerUs;
	
	
	public Game(){
		try {
			con = new Connection(new InetSocketAddress(InetAddress.getByName(ClientMain.SERVER_HOST), ClientMain.SERVER_PORT), ClientMain.NOM_EQUIPE, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Game(Map map){
		this.map = map;
		
		playerUs = new PlayerIA(ClientMain.NB_TWIST_START,this,1);
		playerEnnemy = new PlayerIA(ClientMain.NB_TWIST_START,this,2);
		playerEnnemy.isEnnemy = true;
	}




	@Override
	public void onPlayerSet(int playerId) {
		System.out.println("je suis id : " + playerId);
		playerUs = (ClientMain.HUMAIN) ?
				new PlayerHuman(ClientMain.NB_TWIST_START,this, playerId) :
				new PlayerIA(ClientMain.NB_TWIST_START,this, playerId);
		
		playerEnnemy = new EnemyPlayer(ClientMain.NB_TWIST_START, this, (playerId == 1)?2:1);
		
		
		
	}




	@Override
	public void onGameStart(String mapStr) {
		map = new Map();
		map.decode(mapStr);		
		System.out.println(map.toString());
	}




	@Override
	public void onOpponentPlay(int ligne, char colonne, int coin) {
		map.poser(ligne, colonne, coin, playerEnnemy.getId());
	}




	@Override
	public void onRoundStart() {
		System.out.println("-----> C'est à nous de jouer <-----");

		System.out.println(map);
		playerUs.play();
	}


	@Override
	public void onIllegalPlay() {
		System.err.println("le serveur indique que notre coup n'étais pas bon");
	}


	@Override
	public void onOpponentPlayIllegal() {
		System.err.println("le serveur indique que l'ennemi a jouer un coup illegal");
	}


	@Override
	public void onPlayerCantPlay() {
		System.err.println("vous ne pouvez plus jouer");
	}


	@Override
	public void onGameFinish(String serverMessage) {
		System.out.println(serverMessage);
		System.err.println(map);
	}
	
	
	public Map getMap(){
		return this.map;
	}
	
	public Connection getConnection(){
		return this.con;
	}
	
	
	public Player getUs(){
		return playerUs;
	}
	
	public Player getEnemy(){
		return this.playerEnnemy;
	}


	public void setMap(Map map) {
		this.map = map;
		
	}
	
	
	public void playTest(){
		for (int i = 0; i < 987; i++) {
			((PlayerIA)playerUs).playTest();
			System.out.println(map);
			((PlayerIA)playerEnnemy).playTest();
			System.out.println(map);
		}
		
		
		
	}

}
