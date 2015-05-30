package fr.univ_artois._24_iut_info.client;
import fr.univ_artois._24_iut_info.client.game.Game;

public class ClientMain {
	
	public static final String NOM_EQUIPE = "Les Mouny Python";
	public static final String SERVER_HOST = "172.30.7.16";
	public static final int SERVER_PORT = 10004;
	public static final boolean HUMAIN = false;
	public static final int NB_TWIST_START = 20;

	public static void main(String[] args) {

		new Game();
	}

}
