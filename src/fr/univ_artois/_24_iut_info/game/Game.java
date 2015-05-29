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
	
	private Player playerEnnemy;
	private Player playerUs;
	
	private int nbTwist = 10;
	
	
	public Game(){
		try {
			con = new Connection(new InetSocketAddress(InetAddress.getByName(Main.SERVER_HOST), Main.SERVER_PORT), Main.NOM_EQUIPE, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}




	@Override
	public void onPlayerSet(int playerId) {
		
		playerUs = (Main.HUMAIN) ?
				new PlayerHuman(nbTwist,this, playerId) :
				new PLayerIA(nbTwist,this, playerId);
		
		playerEnnemy = new EnemyPlayer(nbTwist, this, (playerId == 1)?2:1);
		
		
		
	}




	@Override
	public void onGameStart(String mapStr) {
		map.decode(mapStr);		
	}




	@Override
	public void onOpponentPlay(int ligne, char colonne, int coin) {
		map.poser(ligne, colonne, coin, playerEnnemy.getId());
	}




	@Override
	public void onRoundStart() {
		System.out.println("c'est au joueur " + playerUs.getCouleur() + " de jouer");
		
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
	}
	
	
	public Map getMap(){
		return this.map;
	}
	
	public Connection getConnection(){
		return this.con;
	}

}
