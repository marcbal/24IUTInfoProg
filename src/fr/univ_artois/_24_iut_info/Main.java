package fr.univ_artois._24_iut_info;
import fr.univ_artois._24_iut_info.game.Game;

public class Main {
	
	public static final String NOM_EQUIPE = "Les Mouny Python";
	public static final String SERVER_HOST = "172.30.11.183";
	public static final int SERVER_PORT = 9037;
	public static final boolean HUMAIN = false;
	public static final int NB_TWIST_START = 20;

	public static void main(String[] args) {

		new Game();
	}

}
