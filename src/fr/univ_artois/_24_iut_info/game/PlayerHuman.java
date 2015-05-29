package fr.univ_artois._24_iut_info.game;

public class PlayerHuman extends Player {

	

	
	public PlayerHuman(int nbTwist, Game game) {
		super(nbTwist, game);
	}

	@Override
	public void play() {
		System.out.println("10-A vous de jouer ("+this.id+")");
		

	}

}
