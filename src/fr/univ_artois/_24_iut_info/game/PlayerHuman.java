package fr.univ_artois._24_iut_info.game;

import java.util.Scanner;

public class PlayerHuman extends Player {

	Scanner keyboard = new Scanner(System.in);

	
	public PlayerHuman(int nbTwist, Game game, int id) {
		super(nbTwist, game, id);
	}

	@Override
	public void play() {
		System.out.println("Coordonnée où positionner le twist-lock (exemple : \"0 5\")");
		
		try {
			String in = keyboard.nextLine();
			
			String[] coordsStr = in.split(" ");
			
	
			sendTurn(Integer.parseInt(coordsStr[0]), Integer.parseInt(coordsStr[1]));
			
		} catch(Exception e) {
			System.err.println("Erreur de saisie, on envoie 0 0 au serveur");
			sendTurn(0, 0);
		}
		
	}

}
